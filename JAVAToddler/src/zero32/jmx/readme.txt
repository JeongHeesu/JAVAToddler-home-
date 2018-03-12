JMX(Java Management Extension)
1. JavaEE의 JSR 160 스펙을 따르며, 응용 프로그램 | 특정 서비스 | 장치(프린터 등) 및 서비스 지향 네트워크 등을 
    로컬 또는 원격으로 감시 또는 관리 및 실행과 설치를 위한 도구를 제공하는 자바 API. 
    응용프로그램 내 및 서비스 또는 장치 내부에 포함되어 실행중인 한개 이상의 JavaBean 형태의 MBean(Managed Bean)을
    활용 해당 응용프로그램 또는 장치의 상태를 감시 또는 관리 및 실행과 설치.

2. JMX Architecture(3개의 tier로 구성)
   2.1 Instrumentation Tier
          응용프로그램, 장치, 서비스 내부에 구성된 MBean을 Agent Tier의 JMX Agent를 통해 접근
       
   2.2 JMX Agent Tier
       응용프로그램, 장치, 서비스 내부에서 MBean의 등록 및 접근 관리를위한 MBeanServer가 활용된 JMX Agent
      
   2.3 Remote Management Tier
          로컬 또는 원격 접근을 가능하게하는 JMX Agent를 생성하는 Protocol Adaptor와 Connector 정의 
  
3. 활용
   3.1 응용프로그램, 장치, 서비스 등의 설치 및 관리와 감시
   3.2 JMX Agent의 개발
   3.3 원격 관제 응용프로그램의 개발
   

* tutorial
  http://docs.oracle.com/javase/tutorial/jmx/index.html
  
* example
  http://docs.oracle.com/javase/7/docs/technotes/guides/jmx/examples.html

* 로컬 또는 원격지 JMX 서버 접근 및 제어 
     %JAVA_HOME%\bin\jconsole.exe
   로컬 또는 원격지 JVM 모니터링
     %JAVA_HOME%\bin\jvisualvm.exe


    
  
  
 