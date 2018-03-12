package zero25_normal;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import zero16_ibatis.bean.MemberBean;

/**
 * 어플리케이션 개발시 많은 클래스들을 작성하게되고, 다양한 방법으로 로그를 출력하게됨.
 * 로그 출력 코드는 그만큼 반복하게 되고, 보일러판 코드가 생성되고, 코딩량이 증가하게 됨.
 * aop를 활용하면 반복되는 보일러판 코드를 배제할수 있게됨.
 *
 */
public class MemberServiceImpl implements MemberService{
	
	private Logger logger = Logger.getLogger(this.getClass());
	private static MemberServiceImpl service = null;
	private MemberServiceImpl(){}
	
	public static MemberServiceImpl getInstance(){
		if(service == null){
			service = new MemberServiceImpl();
		}
		return service;
	}

	@Override
	public int getMemberTotalCount() {
		logger.debug("getMemberTotalCount() 호출");
		return 0;
	}

	@Override
	public String insertMember(MemberBean member) {
		logger.debug("insertMember(MemberBean member) 호출");
		return null;
	}

	@Override
	public List<MemberBean> getMemberList(Map<String, String> condMap) {
		logger.debug("getMemberList(Map<String, String> condMap) 호출");
		return null;
	}

	@Override
	public MemberBean getMember(Map<String, String> condMap) {
		logger.debug("getMember(Map<String, String> condMap) 호출");
		return null;
	}

	@Override
	public int updateMember(MemberBean member) {
		logger.debug("updateMember(MemberBean member) 호출");
		return 0;
	}

	@Override
	public int deleteMember(Map<String, String> condMap) {
		logger.debug("deleteMember(Map<String, String> condMap) 호출");
		return 0;
	}

	@Override
	public MemberBean searchIdNPwdCheck(Map<String, Object> params) {
		logger.debug("searchIdNPwdCheck(Map<String, Object> params) 호출");
		return null;
	}
	
}






