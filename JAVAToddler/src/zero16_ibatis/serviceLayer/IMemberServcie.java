package zero16_ibatis.serviceLayer;

import java.util.List;
import java.util.Map;

import org.jdom2.Document;

import zero16_ibatis.bean.MemberBean;

public interface IMemberServcie {
	public MemberBean getMemberInfo(Map<String, String> params);
	public Document getMemberInfoXML(Map<String, String> params);
	public List<MemberBean> getMemberList();
	public List<MemberBean> getDynamicMemberList(Map<String, Object> dynamicParams);
	public List<MemberBean> getMemberListUseList(List<String> params);
	public String getMemberJob(MemberBean memberParams);
	public String insertMemberInfo(MemberBean insertParams);
	public int deleteMemberInfo(Map<String, String> deleteParam);
	public MemberBean getTableJoinResult1(String param);
	public MemberBean getTableJoinResult2(String param);
	
	public void getProcedureCall1(MemberBean procedureParam1);
	public void getProcedureCall2(MemberBean params);
	public void getFunctionCall1(Map<String, String> params);
	public void getFunctionCall2(Map<String, String> params);
	public void getpkgCall1(Map<String, Object> params);
}
