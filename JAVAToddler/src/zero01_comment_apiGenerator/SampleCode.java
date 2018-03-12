package zero01_comment_apiGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class SampleCode {
	int bitComplement(byte byteTarget, char charTarget, short shortTarget,
			int intTarget) {
		// 단항 연산자 ~ : 비트 부정
		// 비트 값으로 존재하는 모든 자료들에 대한 부정 값 도출 연산자
		// 연산 대상을 비트 단위로 변환 후 비트 단위 부정으로 반대값 취득
		// 제외 기본 데이터 타입 : boolean, float, double
		// 적용 대상 기본 데이터 타입 : byte, char, short, int => 비트 부정 후 int로 반환
		// long => 비트 부정 후 long로 반환
		// 공식 : 원본데이터 * (-1) -1
		// byteTarget*(-1)-1
		int byteRtn = ~byteTarget;
		System.out.println("비트 부정 결과 byte : " + byteRtn);
		int charRtn = ~charTarget;
		System.out.println("비트 부정 결과 char : " + charRtn);
		int shortRtn = ~shortTarget;
		System.out.println("비트 부정 결과 short : " + shortRtn);
		return ~intTarget;
	}

	void shiftOperator() {
		// 시프트 연산자
		// 연산 대상 값을 2진수로 변경 후 주어진 비트 수 만큼 이동시켜 비트 데이터 취득 연산자.
		// 연산시 기본 데이타 타입 int보다 작은 데이타 타입은 자동 int로 형변환.
		// 연산시 기본 데이타 타입 long의 경우 long형 연산 발생.
		// boolean, float, double의 경우 사용할 수 없음.
		// << : bit값을 왼쪽으로 이동(빈자리는 0으로 대입)
		// >> : bit값을 오른쪽으로 이동(빈자리는 부호값으로 대입)
		// >>> : bit값을 오른쪽으로 이동(빈자리는 0으로 대입)
		int targetOr1 = 1;
		int rtnLeftShift1 = targetOr1 << 3; // 00000001 => 00001000
											// targetOr1*2^3
		System.out.println("shiftOperator() rtnLeftShift1 : " + rtnLeftShift1);
		int targetOr2 = 4;
		int rtnLeftShift2 = targetOr2 << 3; // 00000100 => 00100000
		System.out.println("shiftOperator() rtnLeftShift2 : " + rtnLeftShift2);
		int targetOr3 = 1;
		int rtnLeftShift3 = targetOr3 >> 3; // 00000001 => 00000000
		System.out.println("shiftOperator() rtnLeftShift3 : " + rtnLeftShift3);
		int targetOr4 = 8;
		int rtnLeftShift4 = targetOr4 >> 3; // 00001000 => 00000001
		System.out.println("shiftCalculation() rtnLeftShift4 : "
				+ rtnLeftShift4);
		int targetOr5 = -16;                //                                 1의보수
		int rtnLeftShift5 = targetOr5 >> 3; // 양수 16의 진수 표현 00010000 => 11101111 => 11101111+1 = 11110000 
											// targetOr5/2^3
		System.out.println("shiftCalculation() rtnLeftShift5 : "
				+ rtnLeftShift5);
		int targetOr6 = 4;
		int rtnLeftShift6 = targetOr6 >> 3; // 00000100 => 00000000
		System.out.println("shiftCalculation() rtnLeftShift6 : "
				+ rtnLeftShift6);
	}

	void bitOperator() {
		// 비트 연산자
		// 정수형 데이터의 값을 2진 비트로 변환시켜 각 비트 간에 연산을 시켜주는 연산자.
		// 일반 논리의 연산도 가능.
		// 연산시 기본 데이타 타입 int보다 작은 데이터에서의 연산은 32bit int 값으로 출력.
		// 연산시 기본 데이타 타입 long의 경우 64bit long 값으로 출력.
		byte targetByte = 7; // 00000111
		char targetChar = 4; // 00000100

		int rtnBitAnd = targetByte & targetChar; // 00000100
		System.out.println("bitOperator() Bit AND : " + rtnBitAnd);
		int rtnBitEOr = targetByte ^ targetChar; // 00000011
		System.out.println("bitOperator() Bit Exclusive Or : " + rtnBitEOr);
		int rtnBitOr = targetByte | targetChar; // 00000111
		System.out.println("bitOperator() Bit Or : " + rtnBitOr);
	}

	public static void main(String[] args) {
		SampleCode sc = new SampleCode();
		sc.shiftOperator();
	}
}













