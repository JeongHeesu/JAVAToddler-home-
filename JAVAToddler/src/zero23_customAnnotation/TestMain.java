package zero23_customAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.itextpdf.text.pdf.PatternColor;

import zero23_customAnnotation.target.TargetClass;

public class TestMain {
    public static void main(String[] args) {
    	Class<TargetClass> clazz = TargetClass.class;

		dump("클래스", clazz.getDeclaredAnnotations());

		Constructor[] cs = clazz.getConstructors();
		for(Constructor c : cs){
			dump("생성자", c.getDeclaredAnnotations());
		}

		Field[] fs = clazz.getDeclaredFields();
		for(Field f : fs){
			dump("전역변수", f.getDeclaredAnnotations());
		}

		Method[] ms = clazz.getDeclaredMethods();
		for(Method m : ms){
			dump("메서드", m.getDeclaredAnnotations());
			
			Annotation[][] mParam = m.getParameterAnnotations();
			for(Annotation[] param : mParam){
				dump("파라메터", param);
			}
		}
    }
    
    public static void dump(String message, Annotation[] as) {
		System.out.print(message);
		for (Annotation a : as) {
			System.out.println(" / 어노테이션 타입 " + a.annotationType());
			
			Map<String, String> annotationContextMap = changeMap(a);
			Iterator<String> itr = annotationContextMap.keySet().iterator();
			while(itr.hasNext()){
				String key = itr.next();
				System.out.println("key : " + key + 
						" / value : " + annotationContextMap.get(key));
			}
		}
		
	}
    
    private static Map<String, String> changeMap(Annotation a){
    	// @zero23_customAnnotation.definition.StringAnnotation(belongTo=타겟클래스, value=런타임시취득어노테이션, defaultValue=1)
    	String content = a.toString();
    	
//    	Pattern pattern1 = Pattern.compile("[a-z0-9]+");
//    	Matcher match1 = pattern1.matcher(content);
//    	while(match1.find()){
//    		System.out.println("match1 : " + match1.group());
//    	}
//    	
//    	Pattern pattern2 = Pattern.compile("[가-힣]+");
//    	Matcher match2 = pattern2.matcher(content);
//    	while(match2.find()){
//    		System.out.println("match2 : " + match2.group());
//    	}

    	content = content.replaceAll("^@.+\\(", "").replace(")", "");
    	
    	Map<String, String> map = new HashMap<String, String>();
    	
    	StringTokenizer firstToken = new StringTokenizer(content, ", ");
    	StringTokenizer secondToken = null;
    	while(firstToken.hasMoreElements()){
    		String subContent = (String) firstToken.nextToken();
    		secondToken = new StringTokenizer(subContent, "=");
    		map.put(secondToken.nextToken(), secondToken.nextToken());
    	}
    	return map;
    }
}













