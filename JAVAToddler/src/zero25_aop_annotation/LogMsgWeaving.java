package zero25_aop_annotation;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import zero16_ibatis.bean.MemberBean;

// aj 확장자가 아닌 java 확장자 파일에 어노테이션 @Aspect 설정으로 aj 확장자 파일과 동일하게 동작함.
@Aspect
public class LogMsgWeaving {
	private Logger logger = Logger.getLogger(LogMsgWeaving.class);

	// ################### execution ###################
	
	// ConsolePrtCls의 전역변수 value에 값 추가 이후 위빙됨.
	@After("set(* ConsolePrtCls.value) && args(value)")
	public void valueSetting(String value){
		System.out.println("ConsolePrtCls의 전역변수 value에 값 추가 후 위빙. value = " + value);
		logger.info("ConsolePrtCls의 전역변수 value에 값 추가 후 위빙. value = " + value);
	}

	@Before("get(* ConsolePrtCls.value)")
	public void getValue(){
		logger.info("ConsolePrtCls의 전역변수 value의 값을 어딘가에서 취득 전 위빙.");
	}
		
	// 생성자 생성시 weaving될 pointcut 정의 및 aspect 설정
	// TestMain and ConsolePrtCls 생성자 호출 후 weaving될 aspect를 정의하고 after advice 설정
	@Pointcut("(execution(zero25_aop_anno*.TestMain.new(..)) || execution(zero25_aop_anno*.ConsolePrtCls.new()))")
	public void constructedAfterLogging(){}
	@After("constructedAfterLogging()")
	public void afterLoggingConstructed() {
		logger.info("클래스 생성 후 weaving");
	}

	@Before("execution(void main(String[]))")
	public void callBeforeMain(){
		System.out.println("**************** main 함수 호출 전 weaving됨. *********************");
	}
		
	// 동일한 pointcut 대상으로 다수의 advice와 aspect 설정
	@Pointcut("execution(* zero25_aop_anno*.Con*.as*(..))")
	public void aspectWilecard(){}
	@Before("aspectWilecard()")
	public void aspectWilecardBefore(JoinPoint joinPoint) {
		System.out.println("======== joinpoint 포함 클래스의 인스턴스 : " + joinPoint.getTarget());
		System.out.println("======== joinpoint 포함 클래스의 문자열 표현 : " + joinPoint.getTarget().toString());
		System.out.println("======== joinpoint(함수) 선언 라인번호 : " + joinPoint.getSourceLocation());
		System.out.println("======== joinpoint 포함 클래스명.java : " + joinPoint.getSourceLocation().getFileName());
		System.out.println("======== joinpoint 구성정보 : " + joinPoint.getSignature());
		System.out.println("======== joinpoint명 : " + joinPoint.getSignature().getName());
		logger.debug("before() zero25_aop_annotation 팩키지내 클래스와는 관계없이 aspect로 시작하는 함수 호출 전에 weaving");
	}
	@After("aspectWilecard()")
	public void aspectWilecardAfter(JoinPoint joinPoint) {
		logger.debug("after() zero25_aop_annotation 팩키지내 클래스와는 관계없이 aspect로 시작하는 함수 종료 후 weaving");
	}
		
	@AfterReturning(pointcut="aspectWilecard()",returning="obj")
	public void aspectWilecardAfterReturning(JoinPoint joinPoint, String obj) {
		// joinpoint의 반환값 취득. 반환값 없을시 
		logger.debug("after() returning() zero25_aop_annotation 팩키지내 클래스와는 관계없이 aspect로 시작하는 함수가 정상적으로 값반환 후 종료 시 weaving 반환값 = " + obj);
	}
	
	@AfterThrowing(pointcut="aspectWilecard()")
	public void aspectWilecardAfterThrowing() {
		logger.debug("after() throwing(). zero25_aop_annotation 팩키지내 클래스와는 관계없이 aspect로 시작하는 함수내 익셉션 발생으로 종료 시 weaving");
	}
		
	@Around("execution(* zero25_aop_anno*.Con*.arroundAdvantage(..))")
	public String aroundAspect(ProceedingJoinPoint joinPoint){
		logger.info("동일한 joinpoint를 대상으로한 before or after or after returning 등이 설정된 상태에서 around advice는 설정될수 없음.");
		logger.debug("around() zero25_aop_annotation 팩키지내 클래스와는 관계없이 aspect로 시작하는 함수 호출 전 weaving");

		// 반환값 취득 
		Object rtn = null;
		try {
			rtn = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		logger.debug("around() zero25_aop_annotation 팩키지내 클래스와는 관계없이 aspect로 시작하는 함수 종료 후 weaving");
		// 취득한 반환값을 반환해 함수 호출부에서 취득처리할수 있도록 함.
		return rtn.toString();
		
	}
	
	@AfterThrowing(pointcut="execution(* zero25_aop_anno*.Con*.excep*(..))",throwing="ex")
	public void exceptionOccurAfterThrowing(JoinPoint joinPoint, Exception ex) {
		logger.debug("after() throwing(Exception ex) zero25_aop_annotation 팩키지내 클래스와는 관계없이 aspect로 시작하는 함수내 익셉션 발생으로 종료 시 weaving" + 
				ex.getMessage());
	}
	
	// ################### within ###################
	// service.getMemberInfo(new HashMap<String, String>());
	// service.getMemberList();
	// service.insertMemberInfo(new MemberBean());
	// 에서 parameter가 선언된 joinpoint(메서드)를 대상으로 로그를 출력하고, within 활용시 System.out.println("")도 joinpoint로
	// 인식되므로 !call(* java.io.PrintStream.print*(..))를 통해 Aspect가 weaving될 대상에서 배제함.
	@Before(value="within(zero25_aop_ann*.I*Service+) && args(params) && !call(* java.io.PrintStream.print*(..))")
	public void memberServiceLogging(JoinPoint joinPoint, Object params){
		logger.info("======== joinpoint : " + joinPoint.getSignature());
		logger.debug("within 표현식함수는 타입을 기준으로 해당 타입구현 클래스내 모든 메서드를 대상으로 aspect가 weaving됨.");
	}
}
