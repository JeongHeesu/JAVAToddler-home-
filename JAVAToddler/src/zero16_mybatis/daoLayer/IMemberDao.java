package zero16_mybatis.daoLayer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;

import zero16_mybatis.bean.MemberBean;

public interface IMemberDao {
	public MemberBean getMemberInfo(Map<String, String> params) 
			throws SQLException;
	public Document getMemberInfoXML(Map<String, String> params)
			throws SQLException;
	
	public MemberBean getConditionTest(Map<String, String> params) 
			throws SQLException;
	public List<MemberBean> getMemberList() throws SQLException;
	public List<MemberBean> getDynamicMemberList(Map<String, Object> dynamicParams)
			throws SQLException;
	public List<MemberBean> getMemberListUseList(List<String> params)
			throws SQLException;
	
	public String getMemberJob(MemberBean memberParams) 
			throws SQLException;
	public String insertMemberInfo(MemberBean insertParams)
			throws SQLException;
	public int deleteMemberInfo(Map<String, String> deleteParam)
			throws SQLException;
	public MemberBean getTableJoinResult1(String param)
			throws SQLException;
	public MemberBean getTableJoinResult2(String param)
			throws SQLException;
	public MemberBean getTableJoinResult3(String param)
			throws SQLException;
	
	public void getProcedureCall1(MemberBean procedureParam1)
			throws SQLException;
	public void getProcedureCall2(MemberBean params)
			throws SQLException;
	
	public void getFunctionCall1(Map<String, String> params)
			throws SQLException;
	public void getFunctionCall2(Map<String, String> params)
			throws SQLException;
	public void getpkgCall1(Map<String, Object> params) 
			throws SQLException;
}







