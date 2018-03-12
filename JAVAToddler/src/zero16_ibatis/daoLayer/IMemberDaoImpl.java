package zero16_ibatis.daoLayer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import zero16_ibatis.bean.MemberBean;
import zero16_ibatis.build.BuildedSqlMapClient;
import zero16_ibatis.handler.MemberXMLRowHandler;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapSession;

public class IMemberDaoImpl implements IMemberDao {
	private static IMemberDao dao = null;
	private SqlMapClient client = null;
	private IMemberDaoImpl(){
		client = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static IMemberDao getInstance(){
		if(dao == null)
			dao = new IMemberDaoImpl();
		return dao;
	}
	
	@Override
	public MemberBean getMemberInfo(Map<String, String> params)
			throws SQLException {
		// parmas HashMap mem_id=a001, mem_pass=asdfasdf
		return (MemberBean) client.queryForObject("member.getMemberInfo", params);
	}

	@Override
	public Document getMemberInfoXML(Map<String, String> params)
			throws SQLException {
		String xmlString = (String) client.queryForObject("member.getMemberInfoXML", params);
		Document document = null;
		try {
			InputStream stream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
			document = new SAXBuilder().build(stream);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}

	@Override
	public List<MemberBean> getMemberList() throws SQLException {
		return client.queryForList("member.getMemberList");
	}

//	public String getMemberListXML() throws SQLException {
		// 한개의 레코드를 xml 형태로 취득가능.
		// 다수의 레코드를 xml 형태로 취득하기위해서는 아래와 같은 ibatis에의해 레코드별 개별 xml 형태로 
		// 반환되는 형태를 RowHandler 구현으로 적용.
		// <?xml version="1.0" encoding="UTF-8" standalone="no"?>
		// <members><MEM_ID>a001</MEM_ID><MEM_PASS>asdfasdf</MEM_PASS></members>
		// <?xml version="1.0" encoding="UTF-8" standalone="no"?>
		// <members><MEM_ID>b001</MEM_ID><MEM_PASS>a001</MEM_PASS></members>
//		MemberXMLRowHandler rowHandler = new MemberXMLRowHandler();
//		client.queryWithRowHandler("member.getMemberListXML", rowHandler);
//		return rowHandler.getMemberListXML();
//	}

	@Override
	public List<MemberBean> getDynamicMemberList(
			Map<String, Object> dynamicParams) throws SQLException {
		return client.queryForList("member.getDynamicMemberList", dynamicParams);
	}

	@Override
	public List<MemberBean> getMemberListUseList(List<String> params)
			throws SQLException {
		return client.queryForList("member.getMemberListUseList", params);
	}

	@Override
	public String getMemberJob(MemberBean memberParams) throws SQLException {
		// update member from set mem_mileage = 5000
		// where mem_id = #mem_id#

		// select mem_job from member where mem_id=#mem_id#
		//                              and mem_pass=#mem_pass#
		String mem_job = "";
		try{
			client.startTransaction();
			int updateCnt = 
					client.update("member.updateMileage", memberParams);
			
			mem_job = 
					(String) client.queryForObject("member.getMemberJob", memberParams);
			client.commitTransaction();
		}finally{
			client.endTransaction();
		}
		
		return mem_job;
	}

	@Override
	public String insertMemberInfo(MemberBean insertParams) throws SQLException {
		Connection conn = null;
		SqlMapSession session = null;
		String mem_id = "";
		try{
			conn = client.getDataSource().getConnection();
			conn.setAutoCommit(false);
			
			session = client.openSession(conn);
			
			MemberBean updateParam = new MemberBean();
			updateParam.setMem_id("b001");
			int updateCnt = 
					client.update("member.updateMileage", updateParam);
			
			mem_id =(String) client.insert("member.insertMemberInfo", insertParams);
			
			conn.commit();
		}catch(Exception e){
			conn.rollback();
		}finally{
			if(session != null) session.close();
			if(conn != null) conn.close();
		}
		return mem_id; 
	}

	@Override
	public int deleteMemberInfo(Map<String, String> deleteParam)
			throws SQLException {
		return client.delete("member.deleteMemberInfo", deleteParam);
	}

	@Override
	public MemberBean getTableJoinResult1(String param) throws SQLException {
		return (MemberBean) client.queryForObject("member.tablejoin1", param);
	}

	@Override
	public MemberBean getTableJoinResult2(String param) throws SQLException {
		return (MemberBean) client.queryForObject("member.tablejoin2", param);
	}

	@Override
	public void getProcedureCall1(MemberBean procedureParam1) 
			throws SQLException{
		client.queryForObject("member.iUP_MemberInfo1", procedureParam1);
	}

	@Override
	public void getProcedureCall2(MemberBean params) 
			throws SQLException{
		client.queryForObject("member.iUP_MemberInfo2", params);
	}

	@Override
	public void getFunctionCall1(Map<String, String> params)
			throws SQLException {
		client.queryForObject("member.iUF_MemberInfo1", params);
	}

	@Override
	public void getFunctionCall2(Map<String, String> params) throws SQLException {
		client.queryForObject("member.iUF_MemberInfo2", params);
	}

	@Override
	public void getpkgCall1(Map<String, Object> params) throws SQLException {
		client.queryForObject("member.iPkg_UP_MemberInfo5", params);
	}
}









