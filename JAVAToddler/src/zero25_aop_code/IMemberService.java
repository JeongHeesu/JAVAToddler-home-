package zero25_aop_code;

import java.util.List;
import java.util.Map;

import zero16_ibatis.bean.MemberBean;

public interface IMemberService {
	public MemberBean getMemberInfo(Map<String, String> params);
	public List<MemberBean> getMemberList();
	public String insertMemberInfo(MemberBean params);
	public String deleteMemberInfo(MemberBean params);
	public String updateMemberInfo(MemberBean params);
}
