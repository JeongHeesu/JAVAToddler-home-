package zero25_aop_code;

import org.apache.log4j.Logger;

public aspect LogMsgWeaving {

	// log4j.properties내 log4j.logger.zero25_aop_normal=VERBOTH 추가 후 확인
	private Logger logger = Logger.getLogger(LogMsgWeaving.class);

	// ################### execution ################### 
	
	// ConsolePrtCls의 전역변수 value에 값 추가 이후 위빙됨.
	pointcut addValue(String value) : set(* ConsolePrtCls.value) && args(value);
	after(String value): addValue(value){
		logger.info("ConsolePrtCls의 전역변수 value에 값 추가 후 위빙. value = " + value);
	}

	pointcut getValue() : get(* ConsolePrtCls.value);
	before(): getValue(){
		logger.info("ConsolePrtCls의 전역변수 value의 값을 어딘가에서 취득 전 위빙.");
	}
	
//	pointcut constructedAfterLogging() : (initialization(zero25_aop_normal.ConsolePrtCls.new()) || 
//			initialization(zero25_aop_normal.TestMain.new(..)));
	
	// 생성자 생성시 weaving될 pointcut 정의 및 aspect 설정
	// TestMain and ConsolePrtCls 생성자 호출 후 weaving될 aspect를 정의하고 after advice 설정
	pointcut constructedAfterLogging() : (execution(zero25_aop_normal.TestMain.new(..)) || 
			execution(zero25_aop_normal.ConsolePrtCls.new()));
	after() : constructedAfterLogging() {
		logger.info("클래스 생성 후 weaving");
	}

	// 메인 함수 호출 전 위빙...
	before(): execution(void main(String[])){
		System.out.println("**************** main 함수 호출 전 weaving됨. *********************");
	}
	
	// 동일한 pointcut 대상으로 다수의 advice와 aspect 설정
	pointcut aspectWilecard() : execution(* zero25_aop_normal.Con*.as*(..));
	before() : aspectWilecard() {
		System.out.println("======== joinpoint 포함 클래스의 인스턴스 : " + thisJoinPoint.getTarget());
		System.out.println("======== joinpoint 포함 클래스의 문자열 표현 : " + thisJoinPoint.getTarget().toString());
		System.out.println("======== joinpoint(함수) 선언 라인번호 : " + thisJoinPoint.getSourceLocation());
		System.out.println("======== joinpoint 포함 클래스명.java : " + thisJoinPoint.getSourceLocation().getFileName());
		System.out.println("======== joinpoint 구성정보 : " + thisJoinPoint.getSignature());
		System.out.println("======== joinpoint명 : " + thisJoinPoint.getSignature().getName());
		logger.debug("before() zero25_aop_normal 팩키지내 클래스와는 관계없이 aspect로 시작하는 함수 호출 전에 weaving");
	}
	after() : aspectWilecard() {
		logger.debug("after() zero25_aop_normal 팩키지내 클래스와는 관계없이 aspect로 시작하는 함수 종료 후 weaving");
	}
	after() returning(String obj) : aspectWilecard() {
		// joinpoint의 반환값 취득. 반환값 없을시 
		logger.debug("after() returning() zero25_aop_normal 팩키지내 클래스와는 관계없이 aspect로 시작하는 함수가 정상적으로 값반환 후 종료 시 weaving 반환값 = " + obj);
	}
	after() throwing() : aspectWilecard() {
		logger.debug("after() throwing(). zero25_aop_normal 팩키지내 클래스와는 관계없이 aspect로 시작하는 함수내 익셉션 발생으로 종료 시 weaving");
	}
	
	String around() : execution(* arroundAdvantage(..))  {
		logger.info("동일한 joinpoint를 대상으로한 before or after or after returning 등이 설정된 상태에서 around advice는 설정될수 없음.");
		logger.debug("around() zero25_aop_normal 팩키지내 클래스와는 관계없이 aspect로 시작하는 함수 호출 전 weaving");

		// 반환값 취득 
		Object rtn = proceed();
		
		logger.debug("around() zero25_aop_normal 팩키지내 클래스와는 관계없이 aspect로 시작하는 함수 종료 후 weaving");
		// 취득한 반환값을 반환해 함수 호출부에서 취득처리할수 있도록 함.
		return rtn.toString();
	}
	
	pointcut exceptionOccurLogging() : execution(* zero25_aop_normal.Con*.excep*(..));
	after() throwing(Exception ex) : exceptionOccurLogging() {
		logger.debug("after() throwing(Exception ex) zero25_aop_normal 팩키지내 클래스와는 관계없이 aspect로 시작하는 함수내 익셉션 발생으로 종료 시 weaving" + 
				ex.getMessage());
	}

	// ################### within ###################
	pointcut memberServiceLogging(Object params) : within(zero25_aop_nor*.I*Service+) && args(params);
	before(Object params) : memberServiceLogging(params) {
		logger.debug("within 표현식함수는 타입을 기준으로 해당 타입구현 클래스내 모든 메서드를 대상으로 aspect가 weaving됨.");
	}
}
