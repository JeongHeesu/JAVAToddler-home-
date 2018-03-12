package zero16_ibatis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;

import zero16_ibatis.bean.MemberBean;
import zero16_ibatis.serviceLayer.IMemberServcie;
import zero16_ibatis.serviceLayer.IMemberServiceImpl;
import zero16_ibatis.serviceLayer.IObjectCreateService;
import zero16_ibatis.serviceLayer.IObjectCreateServiceImpl;

public class IBatisTestMain {
	
	private static Logger logger = Logger.getLogger(IBatisTestMain.class);
	
	public static void main(String[] args) {
		IMemberServcie service = IMemberServiceImpl.getInstance();
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", "a001");
		params.put("mem_pass", "asdfasdf");
		
		MemberBean memberInfo = service.getMemberInfo(params);
		System.out.println("이름 : " + memberInfo.getMem_name());
		logger.debug("debug level 이름 : " + memberInfo.getMem_name());
		logger.info("info level 이름 : " + memberInfo.getMem_name());
		logger.warn("warning level 이름 : " + memberInfo.getMem_name());
		logger.error("error level 이름 : " + memberInfo.getMem_name());
		logger.fatal("fatal level 이름 : " + memberInfo.getMem_name());
		
		// 회원 리스트를 xml로 취득
		Document memberInfoXML = service.getMemberInfoXML(params);
		Element memberXmlData = memberInfoXML.getRootElement();
		List<Element> infoes = memberXmlData.getChildren();
		for(Element element : infoes){
			System.out.println(element.getName() + " : " + element.getValue());
		}
		
		// 회원 리스트
		List<MemberBean> memberList = service.getMemberList();
		
		String[] jobs = {"주부", "회사원", "공무원", "군인"};
		Map<String, Object> dynamicParams = new HashMap<String, Object>();
		dynamicParams.put("mem_id", "a001");
		dynamicParams.put("mem_mileage", "900");
		dynamicParams.put("mem_add1", "대전");
		dynamicParams.put("jobs", jobs);
		List<MemberBean> dynamicMemberList = service.getDynamicMemberList(dynamicParams);
		
		List<String> listParam = new ArrayList<String>();
		listParam.add("서예");
		listParam.add("장기");
		listParam.add("수영");
		listParam.add("독서");
		listParam.add("당구");
		listParam.add("바둑");
		listParam.add("볼링");
		listParam.add("스키");
		List<MemberBean> useListMemberList = service.getMemberListUseList(listParam);
		
		MemberBean memberParams = new MemberBean();
		memberParams.setMem_id("b001");
		memberParams.setMem_pass("1004");
		String mem_job = service.getMemberJob(memberParams);
		
		// insert
		MemberBean insertParams = new MemberBean();
		insertParams.setMem_id("ab01");
		insertParams.setMem_pass("1000");
		insertParams.setMem_name("홍길동");
		insertParams.setMem_regno1("123456");
		insertParams.setMem_regno2("1234567");
		insertParams.setMem_zip("111-111");
		insertParams.setMem_add1("대전");		
		insertParams.setMem_add2("대흥동");
		insertParams.setMem_hometel("12-1234-1234");
		insertParams.setMem_comtel("12-1234-1234");
		insertParams.setMem_mail("test@test.com");
		
		// String mem_id = service.insertMemberInfo(insertParams);
		
		Map<String, String> deleteParam = 
				new HashMap<String, String>();
		deleteParam.put("mem_id", "a001");
		// int deleteCnt = service.deleteMemberInfo(deleteParam);
		
		// 테이블 조인 <resultMap>에서 <resultMap> 레퍼런싱의 활용
		MemberBean tableJoin1 = service.getTableJoinResult1("a001");
		System.out.println(tableJoin1.getCartInfos().size());
		
		// 테이블 조인 <resultMap>에서 <result select 속성의 활용>
		MemberBean tableJoin2 = service.getTableJoinResult2("a001");
		
		
		// ### 프로시저 또는 펑션의 OUT 바인드 변수의 값 취득시 또는 return값은 반드시 trim 처리를 수행함.###
		MemberBean procedureParam1 = new MemberBean();
		procedureParam1.setMem_id("a001");
		procedureParam1.setMem_pass("asdfasdf");
		service.getProcedureCall1(procedureParam1);
		System.out.println("mem_id : " + procedureParam1.getMem_id() + 
				" / mem_pass : " + procedureParam1.getMem_pass() +
				" / rtn_mem_regno1 : " + procedureParam1.getRtn_mem_regno1().trim() +
				" / rtn_mem_regno2 : " + procedureParam1.getRtn_mem_regno2().trim());
		
		MemberBean procedureParam2 = new MemberBean();
		procedureParam2.setParam_mem_id("a001");
		procedureParam2.setParam_mem_pass("asdfasdf");
		service.getProcedureCall2(procedureParam2);
		System.out.println("mem_id : " + procedureParam2.getParam_mem_id() + 
				" / mem_pass : " + procedureParam2.getParam_mem_pass() +
				" / rtn_mem_regno1 : " + procedureParam2.getRtn_mem_regno1().trim() +
				" / rtn_mem_regno2 : " + procedureParam2.getRtn_mem_regno2().trim());

		Map<String, String> functionParam1 = new HashMap<String,String>();
		functionParam1.put("param_mem_id", "a001");
		functionParam1.put("param_mem_pass", "asdfasdf");
		service.getFunctionCall1(functionParam1);
		System.out.println("mem_like : " + functionParam1.get("rtn_mem_like"));
		
		Map<String, String> functionParam2 = new HashMap<String,String>();
		functionParam2.put("param_mem_id", "b001");
		functionParam2.put("param_mem_pass", "1004");
		service.getFunctionCall2(functionParam2);
		System.out.println("mem_like : " + functionParam2.get("rtn_mem_like"));
		
		// 팩키지내 프로시저 호출로 ResultSet(Cursor)객체 취득
		Map<String, Object> pkgParam1 = new HashMap<String,Object>();
		pkgParam1.put("param_mem_id", "a001");
		pkgParam1.put("param_mem_pass", "asdfasdf");
		service.getpkgCall1(pkgParam1);
		List<MemberBean> memberObjList = (List<MemberBean>) pkgParam1.get("memberObj");
		for(MemberBean member : memberObjList){
			System.out.println("memberObj mem_regno1 : " + member.getMem_regno1() + 
					" / mem_regno2 : " + member.getMem_regno2() + 
					" / mem_add1 : " + member.getMem_add1() +
					" / mem_add2 : " + member.getMem_add2());
		}
		
		// ### 객체 생성
		// queryState.xml 내 create table tb_grade(
		// 							grade_code varchar2(10),
		//							grade_name varchar2(30) not null,
		//							grade_description varchar2(100),
		//							constraints pk_grade primary key(grade_code)
		//					) 와
		//                  create sequence grade_code_seq maxValue 9999 생성 후 테스트
		IObjectCreateService createService = IObjectCreateServiceImpl.getInstance();
		createService.createGradeTable();
		createService.createGradeSequence();
		createService.insertGrades();

	}
}










