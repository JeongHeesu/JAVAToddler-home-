package zero33.easybatch.ctrl5.search;

import java.sql.Connection;
import java.sql.SQLException;
import org.easybatch.core.api.RecordProcessor;
import org.easybatch.core.impl.Engine;
import org.easybatch.core.impl.EngineBuilder;
import org.easybatch.jdbc.JdbcRecordMapper;
import org.easybatch.jdbc.JdbcRecordReader;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;

import zero16_ibatis.build.BuildedSqlMapClient;
import zero33.easybatch.MemberBean;
import com.google.gson.Gson;
import com.ibatis.sqlmap.client.SqlMapClient;

// easybatch-jdbc-3.0.0.jar import
// elasticsearch library
//    http://www.elasticsearch.org/에서 elasticsearch-1.3.3.zip 다운로드 후
//    elasticsearch-1.3.3.jar
//    lucene-core-4.9.1.jar
//    lucene-analyzers-common-4.9.1.jar
//    lucene-join-4.9.1.jar
//    lucene-queries-4.9.1.jar 
//    lucene-queryparser-4.9.1.jar
//    lucene-highlighter-4.9.1.jar
//    lucene-memory-4.9.1.jar
//    lucene-codecs-4.9.1.jar
//    lucene-suggest-4.9.1.jar
//    
// google json library
//    https://code.google.com/p/google-gson/에서 google-gson-2.2.4-release.zip 다운로드 후
//    gson-2.2.4.jar
public class TestMain_WordSearch {
	
	// iBatis 활용 컨넥션 취득
	private static Connection getConnection(){
		SqlMapClient sqlMapClient = BuildedSqlMapClient.getSqlMapClient();
		Connection connection = null;
		try {
			connection = sqlMapClient.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void main(String[] args) {
		// 원격 접속 노드 설정.
//		Client client = new TransportClient()
//        			.addTransportAddress(new InetSocketTransportAddress("http://192.168.8.17", 9300))
//        			.addTransportAddress(new InetSocketTransportAddress("http://192.168.8.19", 9300));
//        			.addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));
		
		// 단순 접근 노드 설정.
		Node node = ElasticSearchUtils.startEmbeddedNode();
		Client client = node.client();
		
		Engine engine = new EngineBuilder()
		                   .reader(new JdbcRecordReader(getConnection(), "select * from member"))
		                   .mapper(new JdbcRecordMapper<MemberBean>(MemberBean.class))
		                   .processor(new MemberBeanTransformer())
		                   .processor(new TestMain_WordSearch().new JsonMemberBeanDataIndexer(client))
		                   .build();
		
		engine.call();
		
		// D:\temp\elasticsearch에서 생성된 노드 새로고침.
        node.client().admin().indices().prepareRefresh().execute().actionGet();
        
        // 1) 인덱싱된 모든 데이타 검색
        SearchResponse searchResponse = node.client().prepareSearch()
							                .setQuery(QueryBuilders.matchAllQuery())
							                .execute()
							                .actionGet();
        //    출력
        System.out.println("\nTotal matchAll Count = " + searchResponse.getHits().totalHits());
        for (SearchHit searchHitFields : searchResponse.getHits().getHits()) {
            System.out.println("member : " + searchHitFields.getSourceAsString());
        }

        // 2) 특정 index Field내 일치 단어 검색용 쿼리 작성
        QueryBuilder matchQuery = (QueryBuilder) QueryBuilders.matchQuery("mem_name", "김은대");
        //    특정 index Field내 단어 검색
        SearchResponse matchQueryresponse = client.prepareSearch()
        		                                .setQuery(matchQuery)
        		                                .execute()
        		                                .actionGet();
        //    출력 
        System.out.println("\nTotal match Count = " + matchQueryresponse.getHits().totalHits());
        for (SearchHit searchHitFields : matchQueryresponse.getHits().getHits()) {
            System.out.println("mem_name : " + searchHitFields.getSourceAsString());
        }
        
        //  3) 다중 일치 검색용 쿼리 작성.(열거된 다수의 필드로부터 검색 키워드가 포함된 Document 검색
        QueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery("대전광역시", "mem_add1", "mem_add2");
        SearchResponse multiMatchQueryresponse = client.prepareSearch()
								                .setQuery(multiMatchQuery)
								                .execute()
								                .actionGet();
        //      출력 
        System.out.println("\nTotal multiMatch Count = " + multiMatchQueryresponse.getHits().totalHits());
        for (SearchHit searchHitFields : multiMatchQueryresponse.getHits().getHits()) {
            System.out.println("mem_adds : " + searchHitFields.getSourceAsString());
        }
        
        // 노드 접근 제한 설정 및 D:\temp\elasticsearch에서 생성된 노드 삭제
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        ElasticSearchUtils.stopEmbeddedNode(node);
        System.exit(0);
	}

	
	// ElasticSearch는 json형식의 데이타로 검색과 결과를 반환.
	// JdbcRecordReader에 제공된 Oracle DB 대상의 컨넥션과 쿼리를 통해 얻어진 결과 Cursor의 개별 레코드 대상
	// JdbcRecordMapper에의해 MemberBean으로 맵핑된 값을 input으로 또, MemberBean을 json데이타로
	// output 처리.
	static class MemberBeanTransformer implements RecordProcessor<MemberBean, String>{
		@Override
		public String processRecord(MemberBean memberBean) throws Exception {
			Gson gson = new Gson();
			String jsonData = gson.toJson(memberBean);
			return jsonData;
		}
	}
	
	// MemberBeanTransformer의 processRecord()의 output된 json data를 활용한 
	// Elasticsearch의 검색용 인덱스 생성.
	class JsonMemberBeanDataIndexer implements RecordProcessor<String, String>{
		private Client client = null;
		
		public JsonMemberBeanDataIndexer(Client client) {
			this.client = client;
		}

		@Override
		public String processRecord(String jsonMemberBeanData) throws Exception {
			IndexResponse indexingResult = client.prepareIndex("members", "member")
												.setSource(jsonMemberBeanData)
												.execute()
												.actionGet();
			
			System.out.println("인덱싱 결과  name : " + indexingResult.getIndex() + 
					" | type : " + indexingResult.getType() + 
					" | index id : " + indexingResult.getId() + 
					" | remote addr. : " + indexingResult.remoteAddress());
			
			// D:\temp\elasticsearch에서 Elasticsearch 검색 엔진 접근을위한 노드 생성 확인
			return jsonMemberBeanData;
		}
		
	}
}
