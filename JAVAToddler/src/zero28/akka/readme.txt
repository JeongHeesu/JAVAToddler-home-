Akka(TypeSafe사에서 개발한 자바 병렬/분산처리 미들웨어)
    - 멀티스레드를 통해 개별 스레드의 정확한 동작 구현(데드락, 보틀랙, 레이스컨디션)은 불가능하며, 
         활용 가능한 자원의 충분한 활용 역시 불가능함. 
    - 멀티스레드 대상의 디버깅작업 (로그 또는 브레이크포인트 설정 등의 로직의 개입)은 디버거(관찰자)가 관찰 대상의 
         상태를 변경하는 현상 유발을 초래함(하이젠버그 효과).
    - 스레드의 경우 런타임시 발생된 익셉션에따라 해당 스레드가 정지되어, 개별적인 모니터링 존재가 필요한 반면
      AkkA를 통해 구현된 액터는 무정지를 기본으로 액터의 정지되어야만 하는 요인 발생시에도 자동 재실행되어짐.
    - 액터를 통해 다중 병렬 처리가 가능하며, 액터 그룹핑 및 그룹 관리가 가능하고,
         효과적인 생명주기 관리 환경을 제공하고 이벤트 드리븐 방식을 통해 액터 대 액터, 액터 대 그룹 등의 관계에서
         다양한 형태의 메세지 전달이 가능함.  
    
1. 활용사 : 아마존, 블리자드, VM웨어, 오토데스크 등

2. 특징
    2.1 동시실행/병렬처리/분산처리 단위를 액터(Actor : 경량화된 프로세스)로 정의함.
    2.2 동시실행/병렬처리/분산처리를위한 코어는 AkkA System에의해 관리되며, 개발자는
            비지니스 로직 구현에만 집중할수있는 환경 제공.
    2.3 정의된 액터 간 상태는 독립적이며 공유될수 없음.
         - 다중 스레드 프로그래밍의 데드락, 보틀랙, 레이스컨디션을 방지할수 있음.
    2.4 액터 간 통신은 이벤트 드리븐 방식이 적용되며(Node.js와 유사함),
            메세지 큐(FIFO)를 통해 메세지는 순차 처리됨.
    2.5 RAI(Remote Actor Invocation)는 RMI를 지향함.
         - 분산처리시의 통신방식과 서버 구성등에 따른 고민이 불필요.
    2.6 효과적인 자원의 활용을 통해 동일 기능구현을통해 좀더 빠른 성능 향상을 기대할수 있음
        (메모리의 활용율 : 평균 25% 저감 | 실행속도 : 평균 50% 향상).
    2.7 실행중인 액터내에서의 에러 발생시에도 무정지 처리를 지향함.
    2.8 액터는 new 키워드로 인스턴스화 될수 없음.

3. 개발환경
    3.1 http://akka.io 접속 후 akka-2.2.4.zip 다운로드 후 압축 해제
        import library : config-1.0.2.jar
                         scala-library.jar
                         akka-actor_2.10-2.2.4.jar
                         
        api : http://doc.akka.io/japi/akka/2.3.1/
              chrome - http://doc.akka.io/api/akka/2.3.1/#akka.actor.ActorContext

    3.2 Help -> eclipse marketplae -> Scala IDE 설치
        akka는 java 기반의 scala 언어를통해 개발되었으며, 프로그래밍시의 코드어시스트를 지원받기위해 설치
    
    3.3 Maven의 활용
        <repository>
	        <id>Akka</id>
 	        <name>Akka Maven2 Repository</name>
 	        <url>http://akka.io/repository/</url>
        </repository>
            
4. 프로그래밍 규칙
   4.1 작업단위 Actor는 extends UntypedActor로 구현함.
   
   4.2 구현된 Actor의 실행 후 Actor간 비동기방식으로 통신.
   
   4.3 Actor의 LifeCycle
       NEW : 액터 클래스의 인스턴스화 시점으로 통신 불가능.
       STARTED : ActorRef에 Actor 등록시점으로 통신 가능.
       SHUTDOWN : 정지, 또는 일시정지된 시점으로 통신 불가능
   
       4.3.1 Actor LifeCycle에따른 CallBack Method
             preStart(): 액터 시작 전에 호출.
             postStop(): 액터 종료 후에 호출.
             preRestart(Throwable reason): 액터 재시작 전에 호출.
             postRestart(Throwable reason): 액터 재시작 후에 호출.
   
   4.4 Actor간 통신에따른 공차(Tolerance) 처리
       (액터 모티터링 및 관리 액터인 슈퍼바이저 액터를 통해서 수행되며, 무정지 서비스를 지향함.)
       
       4.4.1 permanent: '통신 처리 과정'에서 예외 발생시 액터를 재시작해 실행상태를 유지하도록 설정함.(default)
                   가) 예외 발생시의 재시작 방식
               One For One : 예외 발생된 액터만 재시작.
                             (ActorPath를 기준으로 OneForOneStrategy가 선언된 액터를 포함하지 않는
                                            하위 패스 내 다수의 액터들 중 에러 발생 액터만을 대상으로 Resume, Restart, Stop처리.)
                                             
               All For One : 예외 발생된 액터뿐만아니라 해당 액터의 자식으로 설정된 모든 액터를 재시작.(default)
                             (ActorPath 기준 에러가 발생된 해당 액터가 포함된 하위 패스 모든 액터가 재시작되며,
                                             에러발생 액터의 상위패스 액터는 관계없음.) 
             
       4.4.2 temporary: '통신 처리 과정'에서 예외 발생시 액터 종료 상태로 변경되도록 설정함.(Akka 서비스에 반함.)
             
   4.5 구현된 Actor간 메세지 송,수신시 수신은 UntypedActor의 onReceive()를 통해 처리
   
   4.6 Actor 실행간 옵션설정은 Props를 활용.
       (또는 동적으로 액터를 래퍼런싱할때 활용. Pops.empty())
   
   4.7 메세지 송신,수신 방식
       Fire-And-Forget : 메시지를 전달하고 메시지에 대한 응답을 기다리지 않으며 병행 및 확장에 적합한 
                                     메시지 전달 방식.(비동기식)
                                     
       Send-And-Receive-Future : 메시지를 전달하고 응답을 받기 위한 Future를 반환함.(비동기식)
                        * static import는 코드어시스트 창을통해 import 처리되지않음
                          import static akka.dispatch.Futures.future;
                          import static akka.pattern.Patterns.pipe;
                          
       Send-And-Receive-Eventually : 메시지를 전달하고 응답을 받기까지 블록킹됨(동기식).
   
   4.8 액터는 인스턴스화 시점을 기준으로 부모와 자식 관계를 갖으며, ActorPath(논리적인 계층 구조를 구성하여 관계를 관리함.)를 구성함.
       
       ActorPath Root <----------- Supervisor Actor <--- ActorContext <--- Actor <--- ActorContext <--- Actor
        akka.tcp://sys@host:2522     /user                                /actor명                      /actor명
       
       * ActorPath를 활용해 액터들을 그룹핑 처리가 가능함

   4.9 스케줄러

   4.10 TCP/UDP 소켓통신 환경 제공
          가) library import : akka-remote_2.10-2.2.4.jar
          
   4.11 원격지 다수의 서버 접근 및 원격지의 액터를 로컬의 액터와 같이 메세지 송,수신 가능.
          가) library import : akka-kernel_2.10-2.2.4.jar
          나) 설정파일
          class path root에 application.conf 파일 작성
          다) 작업 파일은 jar로 배포하고 akka_home에 카피 후 akka.bat 파일 실행
   