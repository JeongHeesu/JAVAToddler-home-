현업 프로젝트 진행간 개발에 참여한 모든 개발자가 공통적으로 쓸수있는 라이브러리를 작성하게됩니다.
자바 API를 기초로 작성해보는 경험을 해주세요.

1. CommonUtil.java
    파라메터 int형을 String형으로 변환 후 반환
    파라메터 String형을 Int형으로 변환 후 반환
    파라메터 String형을 Float 형으로 변환 후 반환
    파라메터 String을 Double형으로 변환 후 반환
    파라메터 Object형을 Int형으로 변환 후 반환
    파라메터 char형을 Int형으로 변환 후 반환
    파라메터 String 변수의 Null 체크후 Null일시 공백을 반환
    두개의 파라메터 String 변수와 String 대체값에서 String값이 Null 이거나 공백일시 대체값 반환
    두개의 파라메터 String, String에서 변수의 값이 같다면 true를 반환
    두개의 파라메터 int, int에서 첫 파라메터 숫자를 두번째 int수 만큼 앞에 0을 붙여 스트링으로 반환
    파라메터 String 문자열을 substring 할때 해당 변수가 문자가 Null일경우 substring 하지않고 ""으로 반환
    파라메터 String의 HexCode를 반환
   
2.StringUtil.java
   파라메터 String 변수에 원하는 문자가 몇 개 포함되어 있는지 반복수 반환
   두개의 파라메터 String, int에서 String 문자열이 int값을 넘어갔을때 ..을 붙인후 반환
   두개의 파라메터 String, String에서 첫번째 String 문자열내 두번째 String을 기준으로 문자열을 분리 후 String[]로 반환 
   파라메터 String내에 한글이 몇글자 포함되었는지 포함갯수 반환
   세개의 파라메터 String, int, int에서 String을 두번째 int부터 세번째 int까지 끊어서 반환
   세개의 파마메터 String, Stirng, String에서 첫번재 String내에 두번재 String을 세번째 String으로 변경 후 반환
   파라메터 String을 char 배열로 반환
   파라메터 char 배열을 문자열로 반환
   한글이 포함된 문자열인지 체크
   특정 문자열로 연결된 문자열을 분리한다
  String변수의 글자수를 Max값까지. 그이상 넘어갔을 경우 ..을 붙인후 반환
  String 변수의 값중에서 싱글따음표를 쌍따음표로 변환
  String 변수의 값중에서 쌍따음표를 싱글따음표로 변환
  문자열을 "euc-kr", "ksc5601", "iso-8859-1", "8859_1", "ascii", "UTF-8" 등의 주어진 인코딩타입으로 변환
  
3.DateUtil.java
  윤년여부 반환
  정적 문자형 배열로 천간을 한글로 작성
  정적 문자혈 배열로 천간을 한자로 작성
  정적 문자형 배열로 지지를 한글로 작성
  정적 문자혈 배열로 지지를 한자로 작성
  파라메터 Date에서 년 반환
  파라메터 Date에서 월 반환
  파라메터 Date에서 일 반환
  파라메터 Date에서 시 반환
  파라메터 Date에서 분 반환
  파라메터 Date에서 초 반환  
  파라메터 int year, int month를 이용해 해당 월의 첫 시작 요일 반환
  두개의 파라메터 Date, String에서 해당 Date를 String 포맷으로 변경 후 반환
  세개의 파라메터 Date, int, String에서 해당 Date에서 int값 만큼 추가한 날을 String 포맷으로 변경 후 반환
  파라메터 String에서 com.ibm.icu.util.ChineseCalendar ibm 라이브러리를 이용한 양력을 음력으로 반환
  파라메터 String에서 com.ibm.icu.util.ChineseCalendar ibm 라이브러리를 이용한 음력을 양력으로 반환
  
4.한자를 한글로 변환
  JAXB 활용(java object => xml로 맵핑 생성 or xml => java object 맵핑 생성)
  https://jaxb.java.net/ 에서 jaxb-ri-2.2.7.zip 다운로드
  
  
  
  
  
  
  
  
  
  
  
  
  
  
   

  