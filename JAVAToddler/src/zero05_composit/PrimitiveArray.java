package zero05_composit;

public class PrimitiveArray {
	/**
	 * 배열 : 동일한 데이타 타입을 저장하고 인덱스로 관리하기위한 자료구조
	 */
	public void primitiveArray(){
		
		//방법 1 배열 선언. 타입선언 -> 객체화 -> 초기값 설정
		// int[] a; or int [] a; or int a[]; or int a []; 
		int a[];
		a = new int[5];
		a[0] = 2;
		a[1] = 5;
		a[2] = 3;
		a[3] = 9;
		a[4] = 8;
		
		//방법 2 배열 선언. 타입선언 및 명시적 객체화와 초기값을 한번에 지정
		int[] b = new int[]{2, 5, 3, 9, 8};
	
		//방법 3 배열 선언. 타입선언 및 묵시적 객체화와 초기값을 한번에 지정
		int [] c = {2,5,3,9,8};
		
		for(int i=0; i<a.length; i++){
			System.out.print(a[i] + "\t");
		}
		
		System.out.println();
	
		// Shallow Copy : 복상 대상 배열의 힙 영역내 메모리 주소만 복사
		//             원본 배열 및 Shallow Copy된 배열 값 변경시 모두 변경됨.
		int [] d = a; 
		a[4] = 55;
		for(int i=0; i<a.length; i++){
			System.out.print(d[i] + "\t");
		}
		
		System.out.println();
		
		// Deep Cody : 복사 대상 배열의 원본을 복사해서 힙 영역 내 새로운 객체를 생성
		//             전혀 새로운 객체로 복사되므로 원본 배열의 값 변경 않됨.
		int [] e = new int[5];
		
		System.arraycopy(d,0,e,0,d.length);

		d[4]=100;
		for(int i=0;i<e.length;i++){
			System.out.print(e[i]+"\t");
		}
		
		// 기본 데이타 타입의 초기값 미선언시의 값으로 자동 할당 됨.
		short[] aa = new short[10];
		float[] bb = new float[10];
		boolean[] cc = new boolean[5];
		
		System.out.println("short : " + aa[0] +  
				" / float : " + bb[0] + 
				" / boolean : " + cc[0]);
	}
	
	public void twoDimensional(){
		System.out.println("방법 1 2차원 배열 선언");
		// 4(세로방향 배열 수) X 3(가로방향 배열 수) 정방형 배열 선언
		// [0][0] [0][1] [0][2]
		// [1][0] [1][1] [1][2]
		// [2][0] [2][1] [2][2]
		// [3][0] [3][1] [3][2]
		
		int[][] a = new int[4][3];
		a[0][0] = 1; 
		a[0][1] = 2;
		a[3][2] = 5;
		
		println(a);
		
		System.out.println("방법 2 2차원 배열 선언(비정방형 배열 선언)");
		// 세로방향 배열의 길이는 반드시 초기화 함. 
		int[][] b = new int[3][];
		// 가로방향 배열의 길이 초기화
		// [0][0] [0][1] [0][2] [0][3]
		// [1][0] [1][1] [1][2] [0][3] [0][4]
		// [2][0] [2][1] [2][2] [0][3] [0][4] [0][5]
		b[0] = new int[4]; 
		b[1] = new int[5];
		b[2] = new int[6];
		println(b);
		
		System.out.println("방법 3 2차원 배열 선언");
		int[][] c = new int[][]{ {1,2,3,4,5}, {2,3,4,5,6}, {6,7,8,9,0} };
		println(c);
		
		System.out.println("방법 4 2차원 배열 선언");
		// 묵시적 객체화
		int[][] g = {{1,2,3,4,5},{2,3,4,5,6},{6,7,8,9,0}};
		println(g);
	}
	
	public void println(int[][] p){
		for(int i=0;i<p.length;i++){
			for(int j=0;j<p[i].length;j++){
				System.out.print("["+p[i][j]+"] ");
			}
			System.out.println();
		}
	}
}
