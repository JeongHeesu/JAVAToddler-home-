package zero16_ibatis.build;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BuildedSqlMapClient{
	
	private static SqlMapClient client = null;
	
	static{
		try{
		  Charset ch = Charset.forName("utf-8");
		  Resources.setCharset(ch); 
		  Reader reader = Resources.getResourceAsReader("zero16_ibatis/conf/SqlMapConfig.xml");
		  client = SqlMapClientBuilder.buildSqlMapClient(reader);
		  reader.close(); 
		}catch(IOException e){
			throw new RuntimeException("SqlMapClient 생성 실패" + e, e);
		}
	}
	
	public static SqlMapClient getSqlMapClient(){
		return client;
	}

}
