ElasticSearch(루씬을 기초로한 오픈소스 검색엔진)

1. 정의 : elasticsearch는 Shay Banon이 Lucene을 바탕으로 개발한 분산 검색엔진.
            설치와 서버 확장이 매우 편리.
            분산 시스템이기 때문에 검색 대상 용량이 증가했을 때 대응하기가 무척 수월.
        NoSQL을 통해 Json 형식의 데이터 모델을 활용함.
        multi-tenancy(단일 서버내 다수의 인덱스를 구성하고, 다수의 인덱스를 대상으로 쿼리 질의 가능) 지원.
            다양한 플러그인(프로토콜 변경, 모니터링 기능 등)을 지원.
        
        
    1.1 용어 비교
                             관계형 데이터베이스와 elasticsearch 용어 비교 
                  관계형 데이터베이스                                    elasticsearch
              Database                              Index
                                                    Shard
                                                    (세분화되어 데이타가 분산되며, 백업을통해 복구가능한 소Index)
                                                    Replica
                                                    (Shard가 깨졌을때를 대비해 복구를위해 Shard가 복사되는 Index)
              Table                                 Type
              Row                                   Document
              Column                                Field
              Schema                                Mapping
              Index                                 Everything is indexed
              SQL                                   Query DSL
 
    1.2 가이드
        http://www.elasticsearch.org/guide/
        
    1.3 자바 API 
        http://www.elasticsearch.org/guide/en/elasticsearch/client/java-api/current/index.html
        
    1.4 javascript API
        http://www.elasticsearch.org/guide/en/elasticsearch/client/javascript-api/current/index.html         
   

2. 설치
   2.1 엘라스틱서치 다운로드 및 설치 
       http://www.elasticsearch.org/ 에서 elasticsearch-1.3.3.zip download 및 압축 해제
       D:\압축해제폴더\bin\elasticsearch.bat 실행으로 install
       D:\압축해제폴더\lib\ 폴더의 라이브러리를 프로젝트에 import
       
       API
       https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index_.html
   
   2.2 Apache Tika 라이브러리 import
       Apache Tika(컨텐츠 분석 툴킷)
          - 다양한 문서(csv, xml, ppt, pdf, text, doc, excel 등) 및 이미지에 존재하는 메타데이터 및 
            컨텐츠 등 추출가능한 파서 제공
          - 한글(hwp) 미지원
          - Structure : Tika-Core, Tika-Parsers, Tika-Bundle, Tika-App
          - http://tika.apache.org/download.html 에서 tika-app-1.6.jar 다운로드 및 
            import
   
   2.3 jackson 라이브러리 
       http://wiki.fasterxml.com/JacksonDownload 
           jackson-core-asl-1.9.13.jar
           jackson-mapper-asl-1.9.13.jar
            
   한글 형태소 분석기를 플러그인으로 설치
   
3. 참조
   설치 및 사용기 : http://mimul.com/pebble/default/2012/02/23/1329988075236.html
   로그검색시스템 : http://helloworld.naver.com/helloworld/273788
   한글형태소분석기 : http://jjeong.tistory.com/711
   형태소 분석기로 웹 문서를 파싱 후 단어 추출 : http://naggingmachine.tistory.com/823
   
   
   
   
   
   
   
   
   
   
   
   
   
   
    