package zero02_variable_constant_debug;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class BlockTest {
	static {
		System.out.println("static 블록 ----------------------------------------");
		int d = 10;
		{
			int c = 20;
			{
				int b = 30;
				{
					int a = 40;
					
					a = 200;
					b = 1000;
					c = 2000;
					d = 50;
					
					System.out.println("static 1 : " + a + " " + b + " " + c
							+ " " + d);
				}
				b = 200;
				c = 300;
				d = 60;
				System.out.println("static 2 : " + b + " " + c + " " + d);
			}
			c = 300;
			d = 70;
			System.out.println("static 3 : " + c + " " + d);
		}
		d = 80;
		System.out.println("static 4 : " + d);
	}

	{
		System.out.println("normal 블록 ----------------------------------------");
		int d = 10;
		{
			int c = 20;
			{
				int b = 30;
				{
					int a = 40;
					a = 200;
					b = 1000;
					c = 2000;
					d = 50;
					System.out.println("static 1 : " + a + " " + b + " " + c
							+ " " + d);
				}
				b = 200;
				c = 300;
				d = 60;
				System.out.println("static 2 : " + b + " " + c + " " + d);
			}
			c = 300;
			d = 70;
			System.out.println("static 3 : " + c + " " + d);
		}
		d = 80;
		System.out.println("static 4 : " + d);
	}
	
	public BlockTest(){
		System.out.println("BlockTest 생성자");
	}
	
	public String returnNtryBlock(int parameter){
		// 익셉션 발생되지 않을때 : try{}내 로직에서 익셉션이 발생되지 않았을때 try{}내의 return 문이 반환됨.
		//                   만약 finally{}이 선언되어있으면, try{}내의 return문은 무시되고,
		//                   finally{} 내의 return 문이 반환됨.
		// 익셉션 발생되었을때 : try{}내 로직에서 익셉션 발생시 catch{}내의 로직 수행 후 catch{}내의
		//                 return 문이 반환됨.
		//                 만약 finally{}이 선언되어있으면, catch{}내의 return문은 무시되고,
		//                 finally{}내의 return 문이 반환됨.
		try{
			int i = 1/parameter;
			return "try 블럭내에서 반환처리";
		}catch(ArithmeticException e){
			System.out.println("ArithmeticException : 잘못된 연산 수행시 발생");
			System.out.println(stackTraceToString(e));
			return "catch 블럭내에서 반환처리";
		}finally{
			// finally{} 블럭 내에 return 선언시 warning은 해당 블럭 내에서
			// return 키워드는 deprecated(권장되지 않음)로 처리됨.
			// try{} 블럭 또는 catch{} 에서 return 선언이 바람직함.
			return "finally 블럭내에서 반환처리";
		}
	}

	public String stackTraceToString(Throwable e) {
		String retValue = null;
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retValue = sw.toString();
		}finally{
			try {
				if(pw != null)  pw.close();
				if(sw != null)  sw.close();
			}catch(IOException ignore){}
		}
		return retValue;
	}
	
	public void repeatedTryBlock(int parameter){
		// 중첩된 익셉션의 처리
		// 내부 try{} 내에서 발생된 익셉션은 해당 catch{} 블럭내에서 처리되고 동일한 블럭의 finally{}
		// 블럭도 효력을 발생시키며, 외부 catch{} 블럭은 무시되고, 외부 finally{}은 효력이 발생한다.
		try{
			try{
				int i = 1/parameter;
			}catch(ArithmeticException e){
				System.out.println("ArithmeticException : 잘못된 연산 수행시 발생");
			}finally{
				System.out.println("ArithmeticException finally{}");
			}
		}catch(Exception e){
			System.out.println("Exception 내부 try{}블럭 내에서 발생된 익셉션의 처리");
		}finally{
			System.out.println("Exception finally{}");
		}
	}
}












