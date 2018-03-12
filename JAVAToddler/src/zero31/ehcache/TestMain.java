package zero31.ehcache;

import java.net.URL;
import zero31.ehcache.res.EHCacheProvider;

public class TestMain {

	private URL getConfigFileURL(String path){
		return this.getClass().getResource(path);
	}
	public static void main(String[] args) {
		EHCacheProvider.createCacheManger(
				new TestMain().getConfigFileURL("/zero31/ehcache/conf/ehcache.xml"));

		// 특정 테이블 대상 select 질의 후 EHCacheProvider.insertCache(key, 조회결과 저장 객체) 로 저장처리
		// -> 동일한 객체의 활용은 EHCacheProvider.selectCache(key)로 특정 Cache에 저장된 객체를 활용
		// 특정 테이블 대상 update 질의 후  update된 테이블 대상 조회를 통한 결과 객체를 EHCacheProvider.updateCache(key, 조회결과 저장 객체)로
		// 갱신 해 이후 Cache 내에서 업데이트된 객체를 활용하도록 함.
		// 특정 테이블 대상 delete 질의 후  delete된 테이블 대상 조회를 통한 결과 객체를 EHCacheProvider.updateCache(key, 조회결과 저장 객체)로
		// 갱신 해 이후 Cache 내에서 업데이트된 객체를 활용하도록 함.
		// Cache 내 불필요하게 저장된 객체는 EHCacheProvider.deleteCache(key)를 통해 삭제 처리.
	}

}
