package zeroetc1_unitTest.test;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import zeroetc1_unitTest.target.BasicOperation;

public class BasicOperationTest {
	
	// log4j.properties내 log4j.logger.zeroEtc_unitTest=VERBOTH 추가 
	private Logger logger = Logger.getLogger(this.getClass());
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("테스트 클래스 인스턴스화 직후 한번만 콜백됨.");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("테스트 클래스의 GC되기 직전 한번만 콜백됨.");
	}

	@Before
	public void setUp() throws Exception {
		logger.info("테스트 케이스의 테스트 수행 전 매번 콜백되며, 필요로하는 작업의 수행 처리 로직 구성");
	}
	
	@After
	public void tearDown() throws Exception {
		logger.info("테스트 케이스의 테스트 수행 완료 후 매번 콜백되며, 필요로하는 작업의 수행 처리 로직 구성");
	}
	
	@Test
	public void testAdd() {
		// 
		assertEquals(8, BasicOperation.add(5, 3));
		assertNotSame(new Integer(8), BasicOperation.add(5, 3));
	}

	// 해당 테스트 케이스는 timeout 설정 밀리세컨즈 타임 내에 완료되어야하며, 그외 테스트 실패로 결과 반환
	@Test(timeout=10)
	public void testSubtract() {
		assertEquals(3, BasicOperation.subtract(5, 2));
	}

	@Test
	public void testMultiply() {
		assertEquals(3, BasicOperation.subtract(1, 3));
	}

	@Test
	public void testDivide() {
		assertEquals(1, Double.compare(2.5, BasicOperation.divide(5, 2)));
	}

	// 특정 예외발생 테스트. 
	@Test(expected=ArithmeticException.class)
	public void testDivideExceptionCHeck() throws ArithmeticException {
		assertEquals(0, BasicOperation.divide(1, 0));
		throw new ArithmeticException();
	}
}
