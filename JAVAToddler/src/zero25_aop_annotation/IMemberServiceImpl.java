package zero25_aop_annotation;

import java.util.List;
import java.util.Map;

import zero16_ibatis.bean.MemberBean;

public class IMemberServiceImpl implements IMemberService {
	
	@Override
	public MemberBean getMemberInfo(Map<String, String> params) {
		System.out.println("getMemberInfo() Call");
		return null;
	}

	@Override
	public List<MemberBean> getMemberList() {
		System.out.println("getMemberList() Call");
		return null;
	}

	@Override
	public String insertMemberInfo(MemberBean params) {
		System.out.println("insertMemberInfo() Call");
		return null;
	}

	@Override
	public String deleteMemberInfo(MemberBean params) {
		System.out.println("deleteMemberInfo() Call");
		return null;
	}

	@Override
	public String updateMemberInfo(MemberBean params) {
		System.out.println("updateMemberInfo() Call");
		return null;
	}

}
