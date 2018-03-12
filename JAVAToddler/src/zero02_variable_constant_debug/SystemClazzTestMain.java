package zero02_variable_constant_debug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class SystemClazzTestMain {

	public static void main(String[] args) {
		System.out.printf("%c", 'A');
		System.out.println();
		
		// 전체 5자리 문자 출력(왼쪽 정렬)
		System.out.printf("[%5c]", 'A');
		System.out.println();
		// 전체 5자리 문자 출력(오를쪽 정렬)
		System.out.printf("[%-5c]", 'A');
		System.out.println();
		// 10진수 출력
		System.out.printf("%d", 12345);
		System.out.println();
		// 8진수 출력
		System.out.printf("%o", 12345);
		System.out.println();
		// 16진수 출력
		System.out.printf("%x", 12345);
		System.out.println();
		// 전체 10자리 10진수 출력
		System.out.printf("[%10d]", 12345);
		System.out.println();
		// 전체 10자리 10진수 출력(공백 자리는 0으로 채움)
		System.out.printf("[%010d]", 12345);
		System.out.println();
		// 실수를 지수와 가수로 분리 출력
		System.out.printf("%e", 12.12745f);
		System.out.println();

//		try {
//			SystemClazzTestMain.standardInputCarriageReturn();
//			SystemClazzTestMain.standardInputModify();
//			SystemClazzTestMain.standardInputString();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		SystemClazzTestMain.standardInputScanner();
	}

	static void standardInputCarriageReturn() throws IOException{
		System.out.print("입력1 : ");
        char inputValue1 = (char)System.in.read();

        System.out.print("입력2 : ");      
        int inputValue2 = System.in.read() - 48;
        
        // 입력2는 캐리지리턴(엔터 : \r\n)에서 \r의 아스키코드값 13을 입력받게 됨.
        System.out.println("입력된 문자는 " + inputValue1 + " 입니다.");
        System.out.println("입력된 숫자는 " + inputValue2 + " 입니다.");
        
        System.in.read(); // 캐리지리턴 \n 처리
	}
	
	static void standardInputModify() throws IOException{
		System.out.print("입력3 : ");
        char inputValue3 = (char)System.in.read();

        System.in.read();  // 캐리지리턴 \r 처리
        System.in.read();  // 캐리지리턴 \n 처리
        
        System.out.print("입력4 : ");      
        char inputValue4 = (char)System.in.read();
        
        System.out.printf("입력된 문자는 %c와 %c 입니다.", inputValue3, inputValue4);
	}
	
	static void standardInputString() throws IOException{
		// 콘솔 한글 깨짐 : eclipse.ini -Dfile.encoding=UTF-8
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("숫자를 입력하세요.: ");

		// 엔터키를 눌러야 문자열 저장.(엔터키 자동 처리)
		String inputValue = in.readLine();
		
		int dan = Integer.parseInt(inputValue);
		
		System.out.printf("################## %d단 ##################\n", dan);
		System.out.printf("%d * 1 = %d\n", dan, (dan*1));
		System.out.printf("%d * 2 = %d\n", dan, (dan*2));
		System.out.printf("%d * 3 = %d\n", dan, (dan*3));
		System.out.printf("%d * 4 = %d\n", dan, (dan*4));
		System.out.printf("%d * 5 = %d\n", dan, (dan*5));
		System.out.printf("%d * 6 = %d\n", dan, (dan*6));
		System.out.printf("%d * 7 = %d\n", dan, (dan*7));
		System.out.printf("%d * 8 = %d\n", dan, (dan*8));
		System.out.printf("%d * 9 = %d\n", dan, (dan*9));
	}
	
	static String standardInputScanner(){
		// 한글 깨짐 : 클래스 선택 => Debug As => Debug Configurations 
		//             => Java Application 내 클래스 선택 => Common 탭
		//                                                       Encoding : Other = MS949 입력 실행
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("############# 회원 가입 #############");
		System.out.print("# 아이디 : ");
		String mem_id = scanner.nextLine();

		System.out.print("# 패스워드 : ");
		String mem_pass = scanner.nextLine();

		System.out.print("# 성명 : ");
		String mem_name = scanner.nextLine();
		
		System.out.println(mem_id + " | " + mem_pass + " | " + mem_name);
		return mem_id + " | " + mem_pass + " | " + mem_name;
	}
}













