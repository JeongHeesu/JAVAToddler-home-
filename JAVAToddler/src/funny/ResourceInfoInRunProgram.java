package funny;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class ResourceInfoInRunProgram {
	static String data = "k1=v1,k2=v2";

	public static void main(String[] args) {
		getValue("k1");
		getValue("k2");
		
		System.out.println(System.getProperty("user.dir"));
		
		// 클래스 로더를 통해 실행된 클래스를 포함하는 팩키지만 출력
		Package[] packs = Package.getPackages();
		for(Package pack : packs){
			System.out.println("pack info : " + pack.getName());
		}
		
		getPackageNames();

	}
	
	static String getValue(String key){
		int offset = data.indexOf(key);
		String value = "";
		if(-1 != data.indexOf(',', offset)){
			value = data.substring(data.indexOf('=', offset)+1, data.indexOf(',', offset));
		}else{
			value = data.substring(data.indexOf('=', offset)+1);
		}
		return value;
	}
	
	// org.springframework.core-3.1.2.RELEASE.jar
	// org.springframework.asm-3.1.2.RELEASE.jar
	static void getPackageNames(){
		// 팩키지명이 저장되는 컬렉션
		Set<String> packagesNames = new HashSet<String>();
		
		// Guava : 구글이 작성한 자바 오픈소스 라이브러리
		//    특징 - 단일키/단일값 패턴의 일반적인 JCF Map을 구글에서 확장한 단일키/다양한값 패턴의  MultiMap을 지원.
		//          HashMap<String,HashSet<String>>으로 구성됨.
		//    라이브러리 - http://code.google.com/p/guava-libraries/
		//             guava-17.0.jar
		Multimap<String, String> classInPackage = HashMultimap.create();
		
		// new MemberBean(); 클래스로더가 MemberBean을 src or 그외 classpath 하위에서 찾아서 인스턴스화 시킴.
		// 어플리케이션이 동작하면, 해당 어플리케이션의 라이브러리 정보, 팩키지 정보, 각각의 팩키지 내 클래스들 정보가 퍼머넌스 영역.
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);

        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "/" + "**/*.class";
        Resource[] resources;
		try {
			resources = resourcePatternResolver.getResources(packageSearchPath);
			for( Resource resource : resources ) {
				MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);    
				Class aClass = Class.forName(metadataReader.getClassMetadata().getClassName());
				
				String packageName = aClass.getPackage().getName();

				System.out.println("팩키지명 : " + packageName + 
						" / 클래스명 : " + aClass.getName().substring(aClass.getName().lastIndexOf(".")+1));
				classInPackage.put(packageName, aClass.getSimpleName());
				
				packagesNames.add(packageName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
