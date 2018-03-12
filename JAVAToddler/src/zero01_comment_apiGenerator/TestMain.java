package zero01_comment_apiGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;

import javax.swing.filechooser.FileSystemView;

///**
// * 자바 API 주석을 작성하는 방법을 설명하기위한 클래스
// * 사용방법 : <a href="http://iam777.tistory.com/124">더 많은 정보가 필요하십니까?</a>  
// * <pre>
// * compile : D:\workspace(java)\JAVAToddler>javac -cp .; -encoding UTF-8 src\zero01_comment_apiGenerator\TestMain.java
// * execute : D:\workspace(java)\JAVAToddler>java -cp src\ zero01_comment_apiGenerator.TestMain
// * </pre>
// * @author Inho Jon
// * @version 1.0.0
// * @since 2013.04.15
// * @see java.lang.Object
// */
/**
 * @Class Name : GradRetrieveController.java
 * @Description : 수료생 관리의 이직이력 관리 및 역량강화 교육 지도방안 조회/등록
 * @Modification Information
 * @author 전인호
 * @since  2016.04.07.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *    수정일       수정자          수정내용
 *    -------      -------     -------------------
 *    2016.04.07.  전인호      최초작성
 * Copyright (c) 2016 by DDIT  All right reserved
 * </pre>
 */
public class TestMain {
	
	/**
	 * <h4>
	 * java 언어를 활용한 Desktop Application 작성시에는 
	 * main() 메서드는 작성된 산출물의 테스트 용도로 활용되고, 
	 * 그 자체로 하나의 thread(실행 단위)가 됩니다.
	 * </h4>
	 * <strong>main() 구성 내용 :</strong>
	 * <pre>
	 * java application의 실행 : 1. 실행
	 *                        2. JVM은 Class Loader를 이용해 코드로 선언되어진 클래스 내부의
	 *                          구성을 확인(선언된 변수 파악, 선언된 메서드 파악, 상속관계 파악,
	 *                          메서드 내에서의 다른 클래스 생성관계, 메서드의 메서드의 호출관계 등.)하고
	 *                          Permanent 영역에 내용 저장
	 *                        3. JVM은 Permanent영역 | OS영역 | 정적영역 | 동적(stack)영역 | Heap 영역 중 
	 *                           개별 클래스들에 선언된 변수와 메서드를 java의 동적영역에 위치시키고, 
	 *                           해당 application 종료시까지 유지.
	 *                          <ul>
	 *                          	<li>Permanent영역 : 해당 어플리케이션을 구성하는 코드 산출물들의 정보 저장</li>
	 *                          	<li>OS영역 : 해당 application 실행시 OS로부터 제공되는 정보 저장.</li>
	 *                              <li>정적영역 : static으로 선언된 상수, 변수 및 메서드 저장 </li>
	 *                          	<li>동적영역 : 특정 클래스의 인스턴스화에따른 메서드내 지역변수 생성 및 소멸 및 메서드간의 호출 관계 정보 저장 소멸</li>
	 *                          	<li>Heap : 특정 클래스의 new 키워드를 통한 인스턴스화에따른 저장 공간 또는 생성된 배열의 저장 공간</li>
	 *                          </ul>
	 * 
	 * static : 특정 클래스 내의 일반 메서드는 클래스를 메모리에 상주해야 활용할 수 있지만
	 *          static 메서드는 해당 클래스가 heap에 인스턴스화되지 않아도 접근할수 있어요.
	 *          Class Loader를통해 정적영역에 저장되어지므로...
	 * void :  모든 메서드는 메서드 종료 후 호출원으로 반환할 반환 값을 선언해요.
	 *         기본 데이터 타입과 어떤 Object라도 선언이 가능하고, 종료 후 반환될 값이 없을때 void를
	 *         선언해요.
	 * parameter : String[] args 는 해당 함수를 호출하면서 던져지는 argument를 취득하기위해 선언.
	 * argument : 특정 함수를 호출하면서 전달될 값         
	 * </pre>
	 * <h4>
	 * 자바 API주석은 주석이 구성된 구조를 기초로 파일로 작성됨.
	 * </h4>
	 * <strong>자바 API 작성 방법 :</strong>
	 * <ul>
	 * 		<li>http://www.liferay.com/community/wiki/-/wiki/Main/Javadoc+Guidelines#section-Javadoc+Guidelines-Parameter:+Boolean</li>
	 * 		<li>해당 프로젝트를 포커싱하고 export 메뉴를 선택 </li>
	 * 		<li>java 메뉴의 javaDoc 메뉴 선택</li>
	 * 		<li>javadoc Command : javadoc.exe 위치 확인</li>
	 * 		<li>Use standard doclet의 생성된 API 위치 확인</li>
	 * 		<li>Document title 설정 여부를 결정</li>
	 * 		<li>해당 어플리케이션 내에서 활용하고 있는 jar 파일의 api를 생성한 API에 포함할지 결정. rt.jar 체크.</li>
	 * 		<li>VM options내에 생성된 API의 한글 깨짐을 방지하기위한 코드 선언 -locale ko_KR -encoding UTF-8 -charset UTF-8 -docencoding UTF-8</li>
	 *      <li>finish 클릭으로 생성</li>
	 *      <li>클래스와 함수를 대상으로한 javadoc api 생산 외 팩키지에대한 주석을위해 각 팩키지내 package.html 파일을 작성하고 해당
	 *          html 파일내 <body/>에 팩키지 관련 주석 작성
	 *      </li>
	 * </ul>
	 * @param args main 함수에게 전달될 값
	 * @return void
	 * @exception java.lang.IndexOutOfBoundsException 
	 */
	public static void main(String[] args) {
		File saveFile = new File("D:\\temp\\consoleMessage.txt");
	
     	// PrintStream 클래스는 System.out.println()을 구현하기위해 활용된 텍스트 출력기반 클래스
		PrintStream ps = null;
		try {
			ps = new PrintStream(saveFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.setErr(ps);
		System.setOut(ps);

		PrintStream errOut = null;
		PrintStream sysOut = null;
		try {
			errOut = new PrintStream(new FileOutputStream("D:\\temp\\error.log", true));
	        sysOut = new PrintStream(new FileOutputStream("D:\\temp\\debug.log", true));
		}catch (Exception e) {}
	        
        System.setErr(errOut);
        System.setOut(sysOut);
	        
        System.out.println("D:\\workspace(java)\\JAVAToddler\\bin>java zero01_comment_apiGenerator/TestMain  a b c 10 20 30으로 실행");
		for(int i=0; i<args.length; i++){
			System.out.println("args : " + args[i]);
		}
		
		System.out.println("자바 한줄 주석은 // 이며, 단축키는 ctrl+/ 입니다.");
		System.out.println("자바 블럭 주석은");
		System.out.println("/* 여러줄  이며, 블럭 지정 후 ctrl+shift+/ 입니다.");
		System.out.println("자바 API 주석은 /** ~ */ 이며, " +
				"클래스 또는 메서드 상위에서 alt+shift+j 입니다.");
		System.out.println("escape 문자");
		System.out.printf("123456789012345678901234567890\n");
		System.out.printf("0        1         2          \n");
		System.out.printf("\t!\t!\n"); //8칸 점프
		System.out.printf("\t!\t!"); //8칸 점프
		System.out.printf("\n123456789\r?"); //맨앞
		System.out.printf("\n'안녕하세요?'"); //'
		System.out.printf("\n\"안녕하세요?\""); //'
		System.out.printf("\n\\안녕하세요?\\"); //
		
		System.out.printf("점."); //
		
		System.out.println("파일 시스템 정보 출력");
		FileSystemView fsv = FileSystemView.getFileSystemView();
	    File[] roots = fsv.getRoots();
	    for (int i = 0; i < roots.length; i++)
	    {
	        System.out.println("파일시스템 루트 : " + roots[i]);
	    }

	    System.out.println("홈 디렉토리 : " + fsv.getHomeDirectory());

	    File[] f = File.listRoots();
	    for (int i = 0; i < f.length; i++)
	    {
	        System.out.println("Drive: " + f[i]);
	        System.out.println("Display name: " + fsv.getSystemDisplayName(f[i]));
	        System.out.println("Is drive: " + fsv.isDrive(f[i]));
	        System.out.println("Is floppy: " + fsv.isFloppyDrive(f[i]));
	        System.out.println("Readable: " + f[i].canRead());
	        System.out.println("Writable: " + f[i].canWrite());
	        System.out.println("Total space: " + f[i].getTotalSpace());
	        System.out.println("Usable space: " + f[i].getUsableSpace());
	    }
	}
}











