DTD(Document Type Definitions) : XML 작성을 유도하기위한 문법 정의 파일

* 작성된 dtd와 해당 dtd에따라 정의된 xml은 검증작업을 통해 작성함.
  1. eclipse 생성마법사 -> dtd -> dtd 파일생성 및 작성
       검증 : 작성된 dtd 파일 선택 후 오른쪽 마우스 클릭 -> validate 실행 
           
  2. eclipse 생성마법사 -> xml -> xml 파일생성 및 작성            
       검증 : 작성된 xml 파일 선택 후 오른쪽 마우스 클릭 -> validate 실행
       
  3. dtd 또는 xml 파일에서 발생된 에러는 problems 뷰에서 확인.

  4. 특정 dtd을 기초로 xml 파일 generating
     [대상 dtd 파일 선택] -> [Generate] -> [XML File] 선택
   
* XML 파일은 외부 dtd선언, elements, attributes 그외 값들로 구성되며 dtd 선언 및
  선언 가능한 elements와 선언가능한 attributes를 미리 정의해 구조화된 패턴의 서로 다른 xml을
  작성할수 있도록 함.
  
* 단점 : dtd 참조를 통해 작성된 xml 파일은 하나의 dtd만을 참조 선언할수있음.
       dtd는 문자열 타입만을 지원하며, 그외 타입(숫자, 날짜 등)을 통해 xml을 작성하도록 할 수 없음.
       dtd는 namespace를 지원하지 않음.
          동적으로 특정한 xml에 dtd를 적용할 수 없음.
          중복된 ELEMENT와 ATTRIBUTE를 정의할수없음. 
         
1. dtd의 선언
   1.1 Text 선언 : xml 형식으로 작성된 문서임을 선언하고, 인코딩 타입의 선언

   1.2 Element 선언 : xml에 선언될  엘리먼트명과 해당 엘리먼트가 가지는 속성 및 속성의 순서와 값 등의 
                             형식을 지정

   1.3 Attribute 선언 : 특정한 Element에 소속된 속성 및 순서와 값

   1.4 Entity 선언 : 반복적으로 나오는 활용되는 문장이나 문자열을 기 저장해 놓고 참조하여 활용할수 있음

   1.5 Notation 선언 : Unparsed Entity나 그 밖의 다른 non-XML 데이터들을 다룰 때 또는 XML 
                             응용 프로그램에 정보를 주기 위해 사용    
                
2. dtd 작성의 예(sqlMapConfig.xml을 작성하는 sql-map-config-2.dtd를 예시함.)
   2.1 text 선언
       <?xml version="1.0" encoding="UTF-8" ?>
       - ?와 xml 사이에 공백이 존재할 수 없음.
  
   2.2 Element 선언(대문자로 선언)
      2.2.1 <!ELEMENT 엘리먼트명 (Content Model)>
                  가) <!ELEMENT mem_id (자식 엘리먼트들  | #PCDATA | 자식 엘리먼트들 #PCDATA의 혼합 
                                 | EMPTY | ANY )
                  
                  나) Content Model의 반복횟수(#PCDATA에는 반복횟수를 정의할수없음.)
               , : 열거 순서
               | : 선택
               ? : 반복하지 않거나 한번 반복
               * : 0번 이상 반복
               + : 1번 이상 반복
                 : 1번 
               
               ==> ()내에는 | 기호만 활용 가능
                 
                  다) PCDATA(Parsing Character Data) : xml 파서에의해 구문분석되어 활용되는 대상
               CDATA(Character Data) : xml 파서에의해 파싱되지 않음.
                
            ex1) dtd : <!ELEMENT member (mem_id, mem_pass, mem_name)>
                       <!ELEMENT mem_id (#PCDATA)>
                       <!ELEMENT mem_pass (#PCDATA)>
                       <!ELEMENT mem_name (#PCDATA)>
                
                       (dtd 엘리먼트 member에 열거된 순서대로 작성되어져야함.)
                 xml : <member>
                          <mem_id>a001</mem_id>
                          <mem_pass>asdfasdf</mem_pass>
                          <mem_name>김은대</mem_name>
                          <mem_add>김은대</mem_add>      <= dtd에 정의되지않은 엘리먼트 
                       </member                           작성시 에러발생
           
            ex2) dtd : <!ELEMENT members (member)+>
                       <!ELEMENT member ((male | female), mem_id, mem_pass, 
                                         mem_name, mem_regno1?, mem_regno2?,
                                        (mem_hometel | mem_comtel | mem_hp), 
                                        mem_mail*)>
                       <!ELEMENT male (#PCDATA)>                                        
                       <!ELEMENT female (#PCDATA)>                                        
                       <!ELEMENT mem_id (#PCDATA)>
                       <!ELEMENT mem_pass (#PCDATA)>
                       <!ELEMENT mem_name (#PCDATA)>
                       <!ELEMENT mem_regno1 (#PCDATA)>
                       <!ELEMENT mem_regno2 (#PCDATA)>
                       <!ELEMENT mem_hometel (#PCDATA)>
                       <!ELEMENT mem_comtel (#PCDATA)>
                       <!ELEMENT mem_hp (#PCDATA)>
                       <!ELEMENT mem_mail (#PCDATA)>
              
                xml : <!DOCTYPE 루트엘리먼트명 SYSTEM "패스와 외부dtd파일명 또는 url">
                          - path : 패스는 /폴더명/dtd파일명.dtd로 작성함.
                            url : http://localhost/support/dtd파일명.dtd
                          - <!DOCTYPE members SYSTEM 
                                           "file:///D:/temp/elements.dtd">
                          - <!DOCTYPE members SYSTEM 
                                           "../zero26_dtd_sample/elements.dtd">
                          - <!DOCTYPE members SYSTEM 
                              "http://localhost/zero26_dtd_sample/elements.dtd">  
                            
                      <!DOCTYPE 루트엘리먼트명 PUBLIC "FPI" "url">
                          - <!DOCTYPE members PUBLIC "-//localhost//DTD elements 1.0//KO"      
                              "http://localhost/elements.dtd">

                      * SYSTEM : 일반에 공개되지않는 dtd를 명시함.
                      * PUBLIC : 일반에 공개되고 w3c로부터 FPI를 부여받음을 의미함.
                                (형식적인 절차임.)
                           - FPI(Formal Public Identifier[공개 식별자 형식]) : 작성된 
                                                              dtd의 카테고리 형식 정의                                                                     
                     
                    <?xml version="1.0" encoding="UTF-8"?>
					<!DOCTYPE members SYSTEM "sample_member.dtd">
					<members>
						<member>
							<mem_id>a001</mem_id>
							<mem_pass>asdfasdf</mem_pass>
							<mem_name>김은대</mem_name>
							<mem_hometel>041-111-1111</mem_hometel>
						</member>
						<member>
							<male>남성</male>
							<mem_id>a001</mem_id>
							<mem_pass>asdfasdf</mem_pass>
							<mem_name>김은대</mem_name>
							<mem_comtel>042-111-1111</mem_comtel>
							<mem_mail>test1@ddit.or.kr</mem_mail>
						</member>
					</members>
      
   2.3 ATTRIBUTE 선언(한개의 ELEMENT에 다수의 ATTRIBUTE가 선언될수 있으며, 중복 선언될수는 없음.)
      2.3.1 <!ATTLIST 적용대상엘리먼트명  속성명1 속성유형 성질
                                                     속성명2 속성유형 성질
                                                     속성명3 속성유형 성질>
            가) 적용대상 엘리먼트명 : 해당 ATTRIBUTE가 적용될 기 선언된 ELEMENT 이름
            나) 속성명 : ELEMENT내에 선언될 속성의 이름 . 
                  <member mem_id="a001"></member>의 mem_id를 의미함.
            다) 속성유형 : CDATA - 속성의 값이 임의의 문자열을 정의할 수 있음을 의미. 
                           <![CDATA[..]]>와는 별개의 의미임.
                           <,>,&,',"는 사용불가함.
                             열거형 - (값1 | 값2 | 값3) 의 형식으로 해당 속성은 열거된 값 중 하나만을 값으로 
                                       정의할수있음.
                   ID - xml 작성시 정의되는 ELEMENT들을 구분하기위한 유일한 키로 활용되는 속성값을 
                                   정의하고, xml문서 내에서 해당 속성이 정의된 ELEMENT는 유일해야함.
                                   숫자로 시작될수없으며, 값 정의시 공백이 포함될 수 없음.
                   IDREF  - xml 문서내에서 선언된 ID 속성유형으로 기 정의된 값만을 값으로 정의
                                         할수있음.
                   IDREFS - xml 문서내에서 선언된 ID 속성유형으로 기 정의된 값만을 값으로 복수 
                                         정의할수있으며, 공백으로 구분함.
                   NMTOKEN - CDATA와 동일하게 임의의 문자열을 의미
                             W3C xml 작성 권고안에따라 정의 문자열 작성해야함.
                                           첫문자는 글자 또는 숫자, _와-가 될수있으며, 문자열내 공백이 존재할수없음.
                                            
                   NMTOKENS - NMTOKEN을 복수 정의할수있으며, 값들은 공백으로 구분함.
                   
            라) 성질 : xml내 ATTLIST가 적용된  대상 엘리먼트에서 ATTLIST가 반드시 정의되어야하는지의 여부
                #IMPLIED - 속성이 대상 엘리먼트에 정의될수있으며, 생략도 가능함.
                #REQUIRED - 속성이 대상 엘리먼트에 반드시 정의되어야함.
                #FIXED - 속성이 대상 엘리먼트에 반드시 정의되어야하고, 속성유형 중 열거형에 선언된 값 
                                     이외의 값은 정의될수없음. 또는 #FIXED '값'으로 설정값을 강제할수있음.
                        명기하지않음 - 제한없음.
                        
        ex1) dtd : <?xml version="1.0" encoding="UTF-8"?>
				   <!ELEMENT members (member)+>
					   <!ELEMENT member (#PCDATA)>
							<!ATTLIST member mem_id ID #REQUIRED
							                 mem_pass CDATA #REQUIRED
							                 mem_name CDATA #REQUIRED
							                 mem_nickname CDATA #IMPLIED
							                 mem_regno1 CDATA #REQUIRED
							                 mem_regno2 CDATA #REQUIRED
							                 mem_job (회사원|주부|군인) #IMPLIED
							                 mem_like NMTOKENS #IMPLIED>
             xml : <?xml version="1.0" encoding="UTF-8"?>
				   <!DOCTYPE members SYSTEM 
				                          "../zero26_dtd_sample/attributes.dtd">
				   <members>
						<member mem_id="a001" mem_pass="asdfasdf" 
						        mem_name="김은대" mem_regno1="123456" 
						        mem_regno2="1234567" mem_job="주부"
						        mem_like="독서 영화">
						</member>
						<member mem_id="b001" mem_pass="1004" mem_name="이쁜이"
								mem_nickname="이뿌니" 
						        mem_regno1="123456" mem_regno2="1234567" 
						        mem_like="게임">
						</member>
				   </members>                                
               
   2.4 ENTITY 선언(dtd내에서 외부의 다른 dtd를 참조하여 활용할때 선언. 공통 dtd의 선언화 활용.)
      2.4.1 <!ENTITY % 객체명 SYSTEM "file:///D:/temp/etc1.dtd">
            <!ENTITY % 객체명 SYSTEM "../zero26_dtd_sample/etc2.dtd">
            <!ENTITY % 객체명 SYSTEM 
                                  "http://localhost/zero26_dtd_sample/etc3.dtd">
            * 객체 선언시의 객체명을 %객체명; 의 형식으로 다른 dtd를 활용함.
            
        ex1) 참조 대상의 외부 dtd 선언.(레퍼런싱 대상의 엘레먼트를 ENTITY로 선언.)
             <?xml version="1.0" encoding="UTF-8"?>
			 <!ENTITY % commonMemberInfo "mem_id, mem_pass, mem_name">
			      <!ELEMENT mem_id (#PCDATA)>
			      <!ELEMENT mem_pass (#PCDATA)>
			      <!ELEMENT mem_name (#PCDATA)>
			      
			 외부 dtd 참조 dtd 선언
			 <?xml version="1.0" encoding="UTF-8"?>
			 <!ELEMENT members (member)+>
			    <!-- 외부 dtd를 memberInfo라는 객체명으로 include 처리 -->
				<!ENTITY % memberInfo SYSTEM "external.dtd">
				<!-- 외부객체 참조 선언(ENTITY를 통해 레퍼런싱하는 객체명으로 반드시 선언되어야함.) -->
				%memberInfo;
				<!ELEMENT member ((male | female)?, %commonMemberInfo;, 
				                   mem_regno1?, mem_regno2?,
				                  (mem_hometel | mem_comtel | mem_hp)+, 
				                  mem_mail*)>
					<!ELEMENT male (#PCDATA)>                                        
					<!ELEMENT female (#PCDATA)>                                        
					<!ELEMENT mem_regno1 (#PCDATA)>
					<!ELEMENT mem_regno2 (#PCDATA)>
					<!ELEMENT mem_hometel (#PCDATA)>
					<!ELEMENT mem_comtel (#PCDATA)>
					<!ELEMENT mem_hp (#PCDATA)>
					<!ELEMENT mem_mail (#PCDATA)>
					
			   * ELEMENT 선언시 외부 참조 dtd의 ENTITY명(%commonMemberInfo;)으로 
			      레퍼런싱.			    
			 
             xml : <?xml version="1.0" encoding="UTF-8"?>
				   <!DOCTYPE members SYSTEM "../zero26_dtd_sample/entities.dtd">
				   <members>
						<member>
							<mem_id>a001</mem_id>
							<mem_pass>asdfasdf</mem_pass>
							<mem_name>김은대</mem_name>
							<mem_hometel>041-111-1111</mem_hometel>
						</member>
						<member>
							<male>남성</male>
							<mem_id>a001</mem_id>
							<mem_pass>asdfasdf</mem_pass>
							<mem_name>김은대</mem_name>
							<mem_comtel>042-111-1111</mem_comtel>
							<mem_mail>test1@ddit.or.kr</mem_mail>
						</member>
				   </members>
             
과제. 자신의 sqlMapConfig.xml의 dtd를 작성해보세요.