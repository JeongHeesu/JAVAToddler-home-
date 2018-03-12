package zero15_classLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.commons.logging.LogSource;

import zero04_String_date.common.CommonUtil;
import zero07_threadBeginer.MemberBean;

public class TestMain {

	// iis 서버 서비스 url
	private static final String JARUrl = "http://192.168.8.25/library";
	private static final String ZIPUrl = "";
	
	public static void main(String[] args) {
		TestMain.useJar1();
		TestMain.useJar2();
	}

	private static void useJar1(){
		URL[] urls = new URL[1];
		try {
			urls[0] = new URL(JARUrl);
			URLClassLoader loader = new URLClassLoader(urls);

			Object commonUtil = loader.loadClass("zero04_String_date.common.CommonUtil").newInstance();
			
			CommonUtil commonUtilCls = (CommonUtil) commonUtil;
			
			String a = new String("1");
			String b = new String("1");
			
			if(a.intern() == b.intern()){
				System.out.println("a b는 같다");
			}
			
			System.out.println("commonUtil : " + System.identityHashCode(commonUtilCls) +
					" / getClass : " + commonUtil.getClass());
			System.out.println("활용 : " + commonUtilCls.Kor2Uni("abc"));
			
			System.out.println("casting name : " + (commonUtil.getClass()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void useJar2(){
		URLClassLoader urlClassLoader;
		try {
			urlClassLoader = new URLClassLoader(new URL[]{new URL("file:///D:\\programming\\library\\gooleGson\\gson-2.2.4.jar")});

		// 로더를 통해 해당 팩키지의 해당 클래스 취득
			Class gsonClass = urlClassLoader.loadClass("com.google.gson.Gson");  

			// 생성자 취득
			Constructor constructor = gsonClass.getConstructor();  
			
			// 생성자를 통한 인스턴스화
			Object gsonObj = constructor.newInstance();
			
			// 해당 클래스의 toJson 함수를 취득
			Method method = gsonClass.getMethod("toJson",Object.class);

			MemberBean member = new MemberBean();
			member.setMem_id("a001");
			member.setMem_pass("asdfasdf");
			member.setMem_name("김은대");
			
			// 함수를 통해 json 데이타로 변경
			Object returnObj =  method.invoke(gsonObj, member);
			
			String jsonString = (String)returnObj;  
			
			System.out.println(jsonString);  
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}  
		
	}
}
