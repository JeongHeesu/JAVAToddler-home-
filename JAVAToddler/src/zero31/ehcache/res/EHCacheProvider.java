package zero31.ehcache.res;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EHCacheProvider {
	private static CacheManager cacheManager;
	private static Map<String, Cache> cacheMap = new HashMap<String, Cache>();
	
	// ehcache 관리자 반환
	public static void createCacheManger(URL configFile){
		if(cacheManager == null){
			
			// 콘솔의 SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder". 무시
			cacheManager = new CacheManager(configFile);
			
			// ehcache.xml 내 선언된 캐쉬의 설정을 기초로 캐쉬 생성 및 저장
			cacheMap.put("memberCache", cacheManager.getCache("memberCache"));
			cacheMap.put("prodCache", cacheManager.getCache("prodCache"));
		}
	}
	
	// cache에 객체 저장
	// key : memberCache or prodCache
	public static void insertCache(String key, Object bean){
		if(cacheMap.get(key).get(key) == null){
			cacheMap.get(key).put(new Element(key, bean));
		}else{
			System.out.println("[" + key +"] 와 동일한 이름으로 캐시에 저장되어있는 객체가 존재합니다.");
		}
	}
	// cache내 객체 갱신(동일 키로 저장시 업데이트 처리)
	public static void updateProdCache(String key, Object bean){
		if(cacheMap.get(key).get(key) == null){
			cacheMap.get(key).put(new Element(key, bean));
		}else{
			System.out.println("[" + key +"] 와 동일한 이름으로 캐시에 저장되어있는 객체가 없으므로 갱신할 수 없습니다.");
		}
	}
	// cache내 객체 삭제(동일 키로 삭제 처리)
	public static void deleteProdCache(String key){
		if(cacheMap.get(key).get(key) != null){
			cacheMap.get(key).remove(key);
		}else{
			System.out.println("[" + key +"] 와 동일한 이름으로 캐시에 저장되어있는 객체가 없으므로 삭제할 수 없습니다.");
		}
	}
	// cache내 객체 취득(동일 키로 취득)
	public static Object selectProdCache(String key){
		if(cacheMap.get(key).get(key) != null){
			Element element = (Element)cacheMap.get(key).get(key);
			return element.getValue();
		}else{
			System.out.println("[" + key +"] 와 동일한 이름으로 캐시에 저장되어있는 객체가 없으므로 취득할 수 없습니다.");
			return null;
		}
	}
}










