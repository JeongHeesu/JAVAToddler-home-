EHCache(
  사용 빈도가 높은 데이터를 보다 접근이 용이한 임시 저장장치에 적재하여 활용 성능을 향상시키기위해 사용됨.
 (JavaEE의 cache 표준인 JSR-107[JCache]를 기반으로 만들어졌으며, JCache를 기반으로 만들어진
  MemCached와 비교해 20배 정도의 성능 향상을 기대할수있음.)
   
1. 특징
    - 경량의 빠른 캐시 엔진
    - 메모리 & 디스크 저장 등을 지원하는 확장성
    - 고퀄리티
         Hibernate, Confluence, Spring 등에서 활용되고 있음.
         
2. 다운로드
   2.1 http://grepcode.com/snapshot/repo1.maven.org/maven2/net.sf.ehcache/ehcache-core/2.4.1
       ehcache-core-2.4.1.jar
       * commons-logging1.1.1.jar에 의존성 존재함.
3. 설치
   3.1 ehcache 설정 파일은 기본적으로 class path root에 ehcache.xml 파일로 존재함.
       * 특정 패스와 기본 설정 파일명 변경시 
         URL configFile = this.getClass().getResource("패스1/패스2/설정파일명.xml"); 으로 활용.
   
4. 설정
   4.1 ehcache 설정파일의 구성
       4.1.2 name                               캐시명                                                             필수
             maxElementsInMemory                메모리에 저장될 수 있는 객체의 최대 개수                           필수
             eternal                            true -  timeout 관련 설정 무시 및                          필수
                                                Element(캐시 저장 객체)가 캐시에서 삭제불가     
             overflowToDisk                     캐시의 메모리내 저장된 객체(Element) 수가 
                                                maxElementsInMemory 설정값 이상으로 저장되어야할때처리    필수 
             timeToIdleSeconds                  Element(캐시 저장객체)가 설정된 만료시간 내 활용되지 않을때    선택
                                                                        해당 Element는  캐시에서 제거됨. 
                                                0 : 만료시간 미지정(default)
             timeToLiveSeconds                  Element(캐시 저장객체)의 캐시 내 최대 유효시간.          선택
                                                                        유효시간 경과시 캐시 내에서 해당 Element 제거됨.
                                                0 : 만료시간을 미지정(default).
             diskPersistent                     어플리케이션 재실행시 이전 캐시 내 저장된 Element들의           선택
                                                                        캐시내 저장 유무(default : false).
             diskExpiryThreadIntervalSeconds    캐시내 저장된 Element들 대상의 timeToIdleSeconds,    선택
                                                timeToLiveSeconds 등의 만료시간을 체크하는 쓰레드
                                                                        수행시간을 초단위 지정.
                                                                        기본값 : 120 초
             diskPersistent                     캐시 내 저장된 Element를 어플리케이션 재실행시 새로운           선택
                                                cache에 저장할지 여부
             memoryStoreEvictionPolicy          Element의 수가 maxElementsInMemory에 도달시            선택
                                                                        캐시내 저장된 Element의 정리 정책 설정.
                                                LRU(Least Recently Used : 참조시간 기준의 최저활용빈도) : default
                                                FIFO(First In First Out)
                                                LFU(Least Frequently Used : 참조횟수 기준의 최저활용빈도)
                                                
5. 활용
   5.1 CacheManager 취득
   5.2 CacheManger로부터 Element(캐시에 저장된 객체)를 Cache로 랩핑해 취득.
   5.3 Cache를 통해 캐시대상 CRUD 수행                                