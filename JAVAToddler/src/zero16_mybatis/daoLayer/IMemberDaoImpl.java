package zero16_mybatis.daoLayer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import zero16_mybatis.bean.MemberBean;
import zero16_mybatis.build.BuildedSqlSessionClient;

import com.ibatis.common.jdbc.exception.NestedSQLException;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapSession;

public class IMemberDaoImpl implements IMemberDao {
	private static IMemberDao dao = null;
	private SqlSessionFactory sqlSessionFactory = null;
	private IMemberDaoImpl(){
		sqlSessionFactory = BuildedSqlSessionClient.getSqlSessionFactory();
	}
	
	public static IMemberDao getInstance(){
		if(dao == null)
			dao = new IMemberDaoImpl();
		return dao;
	}
	
	@Override
	public MemberBean getMemberInfo(Map<String, String> params)
			throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		MemberBean memberInfo = null;
		try{
			// parmas HashMap mem_id=a001, mem_pass=asdfasdf
			memberInfo = (MemberBean) session.selectOne("member.getMemberInfo", params);
		}finally{
			session.close();
		}
		return memberInfo;
	}

	@Override
	public Document getMemberInfoXML(Map<String, String> params)
			throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		Document document = null;
		try{
			String xmlString = (String) session.selectOne("member.getMemberInfoXML", params);
			InputStream stream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
			document = new SAXBuilder().build(stream);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return document;
	}

	@Override
	public MemberBean getConditionTest(Map<String, String> params)
			throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		MemberBean memberInfo = null;
		try{
			memberInfo = session.selectOne("member.getConditionTest", params);
		}finally{
			session.close();
		}
		return memberInfo;
	}

	@Override
	public List<MemberBean> getMemberList() throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		List<MemberBean> memberList = null;
		try{
			memberList = session.selectList("member.getMemberList");
		}finally{
			session.close();
		}
		return memberList;
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
		SqlSession session = sqlSessionFactory.openSession();
		List<MemberBean> memberList = null;
		try{
			memberList = session.selectList("member.getDynamicMemberList", dynamicParams);
		}finally{
			session.close();
		}
		return memberList;
	}

	@Override
	public List<MemberBean> getMemberListUseList(List<String> params)
			throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		List<MemberBean> memberList = null;
		try{
			memberList = session.selectList("member.getMemberListUseList", params);
		}finally{
			session.close();
		}
		return memberList;
	}

	@Override
	public String getMemberJob(MemberBean memberParams) throws SQLException {
		// MyBatis 트랜잭션 처리.
		SqlSession session = sqlSessionFactory.openSession();
		String mem_job = "";
		try{
			int updateCnt = 
					session.update("member.updateMileage", memberParams);
			
			mem_job = 
					(String) session.selectOne("member.getMemberJob", memberParams);
			session.commit();
		}catch (RuntimeException e) {
			session.rollback();
		}finally{
			session.close();
		}
		return mem_job;
	}

	public String insertMemberInfo(MemberBean insertParams) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		String mem_id = "";
		try{	
			MemberBean updateParam = new MemberBean();
			updateParam.setMem_id("b001");
			int updateCnt = 
					session.update("member.updateMileage", updateParam);
			
			mem_id = String.valueOf(session.insert("member.insertMemberInfo", insertParams));
			session.commit();
		}catch(RuntimeException e){
			session.rollback();
		}finally{
			session.close();
		}
		return mem_id; 
	}

	@Override
	public int deleteMemberInfo(Map<String, String> deleteParam)
			throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		int deleteCnt = -1;
		try{
			deleteCnt = session.delete("member.deleteMemberInfo", deleteParam);
			session.commit();
		}catch(RuntimeException e){
			session.rollback();
		}finally{
			session.close();
		}
		return deleteCnt;
	}

	@Override
	public MemberBean getTableJoinResult1(String param) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		MemberBean memberInfo = null;
		try{
			memberInfo = (MemberBean) session.selectOne("member.tablejoin1", param);
		}finally{
			session.close();
		}
		return memberInfo;
	}

	@Override
	public MemberBean getTableJoinResult2(String param) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		MemberBean memberInfo = null;
		try{
			memberInfo = (MemberBean) session.selectOne("member.tablejoin2", param);
		}finally{
			session.close();
		}
		return memberInfo;
	}

	@Override
	public MemberBean getTableJoinResult3(String param) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		MemberBean memberInfo = null;
		try{
			memberInfo = (MemberBean) session.selectOne("member.tablejoin3", param);
		}finally{
			session.close();
		}
		return memberInfo;
	}

	@Override
	public void getProcedureCall1(MemberBean procedureParam1) 
			throws SQLException{
		SqlSession session = sqlSessionFactory.openSession();
		try{
			session.update("member.iUP_MemberInfo1", procedureParam1);
		}finally{
			session.close();
		}
	}

	@Override
	public void getProcedureCall2(MemberBean params) 
			throws SQLException{
		SqlSession session = sqlSessionFactory.openSession();
		try{
			session.update("member.iUP_MemberInfo2", params);
		}finally{
			session.close();
		}
	}

	@Override
	public void getFunctionCall1(Map<String, String> params)
			throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try{
			session.update("member.iUF_MemberInfo1", params);
		}finally{
			session.close();
		}
	}

	@Override
	public void getFunctionCall2(Map<String, String> params) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try{
			session.update("member.iUF_MemberInfo2", params);
		}finally{
			session.close();
		}
	}

	@Override
	public void getpkgCall1(Map<String, Object> params) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try{
			session.update("member.iPkg_UP_MemberInfo5", params);
		}finally{
			session.close();
		}
	}
}









