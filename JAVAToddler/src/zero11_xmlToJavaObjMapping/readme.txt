xml 데이타의 자바 클래스 맵핑(apache digester 라이브러리 활용)
저수준의 SAX, DOM 파싱을통한 객체 맵핑을 발전시켜 unmarshalling 작업(XML문서를 객체로 변환하는 작업)을 
쉽게할수 있도록 함.
(digester는 sax 파싱 기반이지만 pattern 매칭 빠르게 XML 파일을 parsing해 java Object와 맵핑처리하며
 java Object를 xml로 맵핑은 지원하지 않음.)
참고 : http://codepedia.tistory.com/69

1. http://commons.apache.org에서
   commons-beanutils-1.8.3.jar 
   commons-digester-2.1.jar
   commons-logging-1.1.1.jar
   commons-collections-3.2.jar 라이브러리 다운로드 후 프로젝트내 추가
   
2. xml 파일을 구성하는 엘리먼트와 digester의 패턴 매칭

     원본.xml          						mapping_rule.xml
   <a>              						<pattern value="a">
    <b>                							<pattern value="a/b">
        <c/>                						<pattern value="a/b/c"></pattern>
        <c/>                						<pattern value="a/b/c"></pattern>
    </b>               							</pattern>
    <b>                							<pattern value="a/b">
        <c/>                						<pattern value="a/b/c"></pattern>
        <c/>                						<pattern value="a/b/c"></pattern>
        <c/>                						<pattern value="a/b/c"></pattern>
    </b>               							</pattern>
   </a>             						</pattern>
   -------------------------------------------------------------
                                            <pattern vlaue에 선언된 엘리먼트 객체 설정 
                                            <object-create-rule pattern="a" classname="xmlToJava.A"/>
                                            
                                            <!-- pattern값이 일치하는 클래스의 변수명(setter/getter)과 엘리먼트 a의 속성명이 일치  -->
                                            <set-properties-rule/>
                                            
                                            <!-- pattern값이 일치하는 클래스의 변수명(setter/getter)과 엘리먼트 a의 속성명이 불일치
                                                 attr-name 엘리먼트 속성값
                                                 pop-name  attr-name에 선언된 엘리먼트의 속성이 저장될 변수명으로서 setter/getter 설정되어야함.
                                              -->
                                            <set-properties-rule pattern="a">
                                                <alias attr-name="id" prop-name="mem_id"/>
                                                <alias attr-name="password" prop-name="mem_pass"/>
                                            </set-properties-rule>     
    
    	                                    <!-- 해당 패턴이 setting되어야할 상위 패턴의 함수명과 해당패턴의 타입 -->
                                            <set-next-rule methodname="함수명" paramtype="해당 함수 호출시 전달값 타입"/>                                          
   
   
