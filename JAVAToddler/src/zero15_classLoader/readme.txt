1. windows7 iis 서버 추가
   windows7 제어판 -> 프로그램 및 기능 -> windows 기능 사용/사용 안함 -> 인터넷정보서비스 체크 -> 시스템 재시작

2. 제어판 -> 관리도구 -> IIS(인터넷 정보 서비스) 관리자에서 설정 및 서버 시작 및 정지

3. default 웹컨텍스트패스는 C:\inetpub\wwwroot로 설정되어있으므로
   IIS(인터넷 정보 서비스) 관리자에서 설정 및 서버 시작 및 정지 -> 고급설정 -> 실제 경로 -> 특정 폴더를 컨텍스트 패스로 설정
     활용할수 있음.
     
4. 컨텍스트 패스내 library 폴더를 생성하고 JavaToddler 프로젝트를 jar export한 jar 파일을 위치

or

1. file:/// 프로토콜을 활용한 파일시스템내 특정 jar의 클래스의 동적 활용

   google-gson-2.2.4-release.zip download
    
   https://code.google.com/p/google-gson/ 