EasyBatch

* http://www.easybatch.org/
* 자바를 통해 다양한 일괄처리 환경을 쉽게 구현할수있는 프레임워크로서, CSV(Comma Separated Value) 또는
  Text 파일 등의 파일 읽기, 필터링, DataSource를 대상으로한 분석 및 검증, 입력 데이타 대상의 단순히 반복적이거나 
   또는 메인 어플리케이션의 실행과 별도로 실행되어야 할 비니지스 로직의 실행을위한 경량화된 API.
  http://www.easybatch.org/api/index.html?org/easybatch/core/api/package-summary.html 
   
* EasyBatch 프레임웍을통한 일괄 처리의 대상 : Data Reading, Data Parsing, Data Filtering,
                                   Data Mapping, Error Logging, Data Validation,
                                   Batch Reporting
* 특징
  1. 실행 간 최소한의 메모리 활용
  2. POJO 기반의 관점 지향 프로그래밍 환경
  3. 병렬 실행 환경
  4. JMX(Java Management Extension)을 활용한 Batch 실행 환경 Monitoring
  5. 모듈화된 아키텍처 제공

1. 설치
   1.1 maven
        <dependency>
		    <groupId>org.easybatch</groupId>
		    <artifactId>easybatch-core</artifactId>
		    <version>3.0</version>
		</dependency>
		<dependency>
            <groupId>org.easybatch</groupId>
            <artifactId>easybatch-flatfile</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.easybatch</groupId>
            <artifactId>easybatch-xml</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.easybatch</groupId>
            <artifactId>easybatch-jdbc</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.easybatch</groupId>
            <artifactId>easybatch-validation</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.easybatch</groupId>
            <artifactId>easybatch-tools</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.easybatch</groupId>
            <artifactId>easybatch-spring</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.easybatch</groupId>
            <artifactId>easybatch-jms</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.easybatch</groupId>
            <artifactId>easybatch-json</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.easybatch</groupId>
            <artifactId>easybatch-xstream</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.easybatch</groupId>
            <artifactId>easybatch-quartz</artifactId>
            <version>3.0.0</version>
        </dependency>
        
   1.2 easybatch 라이브러리 import
          제공된 easybatch_libs.zip 압축 해제 후 import 활용
                		
2. 테스트 데이터 작성
   2.1 csv
       SQL> set linesize 500
       SQL> select table_name, xmlagg(xmlelement(node, lower(column_name), ',') 
                                       order by column_id).extract('//text()').getstringval() val
            from user_tab_columns
            where table_name='MEMBER'
            group by table_name;       
            
       SQL> set echo off
       SQL> set heading off
       SQL> set linesize 500
       SQL> spool d:\member.csv
       SQL> select mem_id||','||mem_pass||','||mem_name||','||mem_regno1||','||mem_regno2||','||
                   mem_bir||','||mem_zip||','||mem_add1||','||mem_add2||','||mem_hometel||','||
                   mem_comtel||','||mem_hp||','||mem_mail||','||mem_job||','||mem_like||','||
                   mem_memorial||','||mem_memorialday||','||mem_mileage
            from member;
       SQL> spool off
       
          또는 SqlDeveloper의 데이타 익스포트 활용(추천)
       
       *** , 구분자가 생략된 csv 데이타 생성 가능성이 있으므로 검토 후 활용.
       
   2.2 xml(SqlDeveloper에서 실행)
       select  xmlroot(
            xmlelement("MEMBERS",
                        xmlagg(xmlelement("MEMBER", xmlattributes(mem_id),
                                                    xmlforest(mem_pass,
                                                              mem_name,
                                                              mem_regno1,
                                                              mem_regno2)))),
        version '1.0" encoding="UTF-8') xmlData
        from member;
   
           또는 SqlDeveloper의 데이타 익스포트 활용(추천)
   
3. java BeanValidation Spec.
   https://docs.oracle.com/javaee/7/tutorial/index.html 
   
4. zero21_lucenebase_elasticsearch의 readme.txt 참조
        