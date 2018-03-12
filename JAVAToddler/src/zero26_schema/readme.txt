XML 스키마 파일 작성
1. 작성 방법
   zero26_schema.doc 팩키지의 XML 스키마.ppt 참조.
   
2. 작성
    [파일 생성 마법사] -> [XML Schema File] -> XML Schema Editor를 활용해서 작성.
    
3. 작성된 xsd 파일 검증
   [xsd 파일 선택] - > [Validate] -> [Problems View] 확인
    
4. xsd 파일을 활용한 xml 파일 작성
   [Generate] -> [XML File] -> xml 파일 작성
   
5. xsd 파일을 활용한 JaxB 클래스 파일 작성
   [Generate] -> [XML File] ->  파일 작성
   
* xml을 기초로 xsd 파일 작성
  http://thaiopensource.com/relaxng/trang.html 
      download Trang   
      
      java -jar trang.jar [xml 파일명.xml] [xsd 파일명.xsd] 

