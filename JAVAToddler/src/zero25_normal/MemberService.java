package zero25_normal;

import java.util.List;
import java.util.Map;
import zero16_ibatis.bean.MemberBean;

public interface MemberService {
	public int getMemberTotalCount();
	public String insertMember(MemberBean member);
	public List<MemberBean> getMemberList(Map<String, String> condMap);
	public MemberBean getMember(Map<String, String> condMap);
	public int updateMember(MemberBean member);
	public int deleteMember(Map<String, String> condMap);
	public MemberBean searchIdNPwdCheck(Map<String, Object> params);
}
