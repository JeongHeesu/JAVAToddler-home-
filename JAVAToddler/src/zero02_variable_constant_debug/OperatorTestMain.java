package zero02_variable_constant_debug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class OperatorTestMain {

	public static void main(String[] args) {
		OperatorTestMain operator = new OperatorTestMain();
		operator.logicComplement();
		operator.bitComplement((byte) 10, 'A', (short) 300, 400);
		operator.increaseODecrease();
		operator.shiftOperator();
		operator.identityOperator();
		operator.bitOperator();
		try {
			operator.ternaryOperator();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void logicComplement() {
		// 단항 연산자 ! : 논리 부정
		// 논리 자료형의 값을 부정하는 연산자
		boolean bool = false;
		boolean chgBool = !bool;
		bool = !chgBool;
		System.out.println("logicComplement() 최종 논리 부정 결과 : " + bool);
	}

	// Java Document 주석 작성
	int bitComplement(byte byteTarget, char charTarget, short shortTarget,
			int intTarget) {
		// 단항 연산자 ~ : 비트 부정
		// 비트 값으로 존재하는 모든 자료들에 대한 부정 값 도출 연산자
		// 연산 대상을 비트 단위로 변환 후 비트 단위 부정으로 반대값 취득
		// 제외 기본 데이터 타입 : boolean, float, double
		// 적용 대상 기본 데이터 타입 : byte, char, short, int => 비트 부정 후 int로 반환
		//                                                 long => 비트 부정 후 long로 반환
		// 공식 : 원본데이터 * (-1)-1
		// byteTarget*(-1)-1
		//      2진수 표현                      Not            1의보수      -는 1대신 0을 2의승수로 계산
		// 10        => 00001010                         => 11110101         => (-8) + (-2) + (-1) = -11
		// 'A' => 65 => 0000000001000001                 => 1111111110111110 => (-64) + (-1) + (-1) = -66
		// 100       => 0000000001100100                 => 1111111110011011 => (-64)+(-32)+(-4)+(-1) = -101
		// 1000      => 00000000000000000000001111101000 => 11111111111111111111110000010111 = (-512) + (-256) + (-128) + (-64) + (-32) + (-8) + (-1) = -1001
		byte byteRtn = (byte) ~byteTarget;
		System.out.println("logicComplement() 비트 부정 결과 : " + byteRtn);
		// charTarget*(-1)-1
		char charRtn = (char) ~charTarget;
		short shortRtn = (short) ~shortTarget;
		return ~intTarget;
	}

	void  increaseODecrease() {
		// 단항 연산자 ++, -- :  전위형(prefix) 또는 후위형(postfix) 증감 연산자
		// 특정 필드의 값을 증가 또는 감소
		int or1 = 4;
		int prefixOr = ++or1;
        int rtnPrefixOr = 4 + prefixOr * 3;                   
		System.out.println("or1 : " + or1 + " | prefixOr : " + prefixOr + 
				" | rtnPrefixOr : " +  rtnPrefixOr);

		int or2 = 5;
		int postfixOr = or2++;
		int rtnPostfixOr = 4 + postfixOr * 3;
		System.out.println("or2 : " + or2 + " | postfixOr : " + postfixOr + 
				"| rtnPostfixOr : " +  rtnPostfixOr);
	}
	
	void calculation(){
		// + : 두 개의 피연산자들의 합
	    // - : 두 개의 피연산자들의 차
	    // * : 두 개의 피연산자들의 곱
	    // / : 두 개의 피연산자들의 나눗셈
	    // % : 두 개의 피연산자들이 정수일 때 나머지(실수 경우 에러 발생)
        // 연산시 기본 데이타 타입 int보다 작은 데이타 타입은 자동 int로 형변환.
	    // 연산시 기본 데이타 타입 long의 경우 long형 연산 발생.
		int a = 10 + 20;
        int b = 20 - 10;
        int c = 3 * 4;
        int d = 20 / 10;
        int e = 25 % 3;
        
        System.out.println("10 + 20 = " + a);
        System.out.println("20 - 10 = " + b);
        System.out.println("3 * 4 = " + c);
        System.out.println("20 / 10 = " + d);
        System.out.println("24 % 3 = " + e);
	}
	
	void shiftOperator(){
		// 시프트 연산자 >>, <<, >>> 
		// 연상 대상 값을 2진수로 변경 후 주어진 비트 수 만큼 이동시켜 비트 데이터 취득 연산자.
		// 연산시 기본 데이타 타입 int보다 작은 데이타 타입은 자동 int로 형변환.
	    // 연산시 기본 데이타 타입 long의 경우 long형 연산 발생.
	    // boolean, float, double의 경우 사용할 수 없음.
		// << : bit값을 왼쪽으로 이동(빈자리는 0으로 대입)
		// >> : bit값을 오른쪽으로 이동(빈자리는 부호값으로 대입)
		// >>> : bit값을 오른쪽으로 이동(빈자리는 0으로 대입) 
		int targetOr1 = 1;
		int rtnLeftShift1 = targetOr1 << 3;  // 00000001 => 00001000      targetOr1*2^3
		System.out.println("shiftOperator() rtnLeftShift1 : " + rtnLeftShift1);

		int targetOr2 = 4;
		int rtnLeftShift2 = targetOr2 << 3;  // 00000100 => 00100000
		System.out.println("shiftOperator() rtnLeftShift2 : " + rtnLeftShift2);
		
		int targetOr3 = 1;
		int rtnLeftShift3 = targetOr3 >> 3;  // 00000001 => 00000000
		System.out.println("shiftOperator() rtnLeftShift3 : " + rtnLeftShift3);
		
		int targetOr4 = 8;
		int rtnLeftShift4 = targetOr4 >> 3;  // 00001000 => 00000001
		System.out.println("shiftOperator() rtnLeftShift4 : " + rtnLeftShift4);

		int targetOr5 = -16;                //                                 1의보수     2의 보수             
		int rtnLeftShift5 = targetOr5 >> 3; // 양수 16의 진수 표현 00010000 => 11101111 => 11101111+1 = 음수 16의 진수 표현 11110000
		                                    // 11110000 >> 3 = 11111110  (-1) + (-1) = -2
		System.out.println("shiftOperator() rtnLeftShift5 : " + rtnLeftShift5);
		
		int targetOr6 = 4;
		int rtnLeftShift6 = targetOr6 >> 3;  // 00000100 => 00000000
		System.out.println("shiftOperator() rtnLeftShift6 : " + rtnLeftShift6);
	}
	
	void identityOperator(){
		// 항등 연산자 : &, ^, |
		// 피연산자들 간 서로 같은지 다른지에 대한 판단을 하는 연산자.
	    // == : 두 개의 피연산자가 서로 같으면 true, 다르면 false.
	    // != : 두 개의 피연산자가 서로 다르면 true, 다르면 false
		boolean rtnIdentityEquals = 10 == 10;
        System.out.println("identityOperator() ==  : " + rtnIdentityEquals);

        boolean rtnIdentityNotEquals = 10 != 10;
        System.out.println("identityOperator() !=  : " + rtnIdentityNotEquals);
	}

	void bitOperator(){
		// 비트 연산자
		// 정수형 데이터의 값을 2진 비트로 변환시켜 각 비트 간에 연산을 시켜주는 연산자.
	    // 일반 논리의 연산도 가능.
	    // 연산시 기본 데이타 타입 int보다 작은 데이터에서의 연산은 32bit int 값으로 출력.
	    // 연산시 기본 데이타 타입 long의 경우 64bit long 값으로 출력.
		byte targetByte = 7;                                  // 00000111           
		char targetChar = 4;                                  // 00000100
		
		int rtnBitAnd = targetByte & targetChar;              // 00000100
		System.out.println("bitOperator() Bit AND : " + rtnBitAnd);
		
		int rtnBitEOr = targetByte ^ targetChar;              // 00000011
		System.out.println("bitOperator() Bit Exclusive Or : " + rtnBitEOr);

		int rtnBitOr = targetByte | targetChar;               // 00000111
		System.out.println("bitOperator() Bit Or : " + rtnBitOr);
	}
	
	void ternaryOperator() throws NumberFormatException, IOException{
		// 3항 연산자
	    // 3개의 항으로 작성되어 붙여진 이름.
        // 첫 번째 항인 조건항의 결과에 따라 실행되는 내용이 달라짐.
	    // 조건항은 반드시 true 또는 false의 결과 출력이 되도록 구성.
        BufferedReader bufferedIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("1. 남성    2. 여성 : ");
        int readedValue = Integer.parseInt(bufferedIn.readLine());
        String bufferedInRtn = (readedValue == 1) ? "남성" : "여성" ;
        System.out.println("ternaryOperator() : " + bufferedInRtn);
	}
}


