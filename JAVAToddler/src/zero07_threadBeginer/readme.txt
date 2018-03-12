스레드 : 독립적으로 수행가능한 메인 프로세스 또는 메인 프로세스 외의 기타 프로세스의 기저.
         단일 프로세스 내에서 병행적으로 운용되는 함수 크기의 실행단위. 
      Foreground/Background thread로 분류하고, 어플리케이션의 Foreground 스레드가 모두
         종료되면 해당 어플리케이션은 종료됨. 

스레드 생성 방식 
   * 컴파일 타임과 런타임시 객체의 상태 및 동적인 기능 변경 여부에 따라 white-box(extends)와 
     black-box(implements)로 나뉘며, white-box의 경우 모든 객체는 컴파일 타임 시점에 형식이 결정되어
       동적인 기능 변경이 불가능함.
     '객체 합성이 객체 상속보다 어플리케이션의 유연성과 확장성을 보장한다.'
     
   java.lang.Thread 클래스를 상속받아 생성             [white-box 방식(상속)]
   java.lang.Runnable 인터페이스를 구현한 클래스 생성 [black-box 방식(합성)]

스레드 그룹
    쓰레드 생성 후 특정 그룹으로 팩키징 하지 않으면 main 쓰레드 그룹으로 팩키징됨.

스레드 상태 천이
   ** 스레드 객체 생성 후 쓰레드 api 활용에따른 상태 천이 처리
   
   1.ready state : 쓰레드 객체의 start() 함수 호출 후 쓰레드 스케줄러에의해 해당 쓰레드의 run() 함수 호출 전
   2.run state : ready state에 있는 쓰레드가 쓰레드 스케줄러에 의해 무작위 선택되고 해당 쓰레드의 run() 함수가
                        호출되고 해당 쓰레드는 run state가 됨.
   3.done state : 해당 쓰레드의 run()함수 종료를 통해 run state로부터 done state가 됨.
                done state에서 다시 run state로 복귀할수없으며, 해당 쓰레드 객체가 GC되지는 않음
   4.non-runnable state : run state의 쓰레드의 동작이 정지된 상태로 waiting, sleeping, 
                 blocked의 세부 상태로 분류됨.  
            - waiting state : synchronized() 함수 또는 synchronized 블록 내에서 해당 쓰레드 객체의 
                             wait() 함수 호출에의해 동작정지 상태.
                                           동작정지된 쓰레드는 다른 제3의 쓰레드 객체에서 nofity() 또는 notifyAll() 함수
                                           호출을 통해 ready 상태로 회귀됨.
            - sleeping state : 지정된 시간(밀리초)동안 쓰레드의 동작이 정지된 상태.
                                           동작정지된 쓰레드는 지정된 시간이 지나면 ready state로 회귀됨. 
            - blocked state: 입,출력 관련 로직의 처리를위한 대기 또는 synchronized를통해 임계영역 내 특정로직을 
                                           다른 쓰레드가 실행 중이고 임계영역 진입을 대기하는 등의 원인으로 쓰레드가 정지된 상태. 
                                           동작정지된 쓰레드는 입,출력 관련 로직의 실행의 종료 및 임계영역 진입시 ready state로 회귀됨.
                                           
메소드 호출에 의한 Thread 제어 
   - start() : 쓰레드를 ready state로 천이 후 쓰레드 스케줄러에의해 선택되기를 대기함. 
   - run() : 쓰레드가 실행해야하는 로직을 구성. 
   - yield(): 다른 쓰레드에게 run state를 양보하고, non_runnable state의 waiting state로 천이된 이후,
             run state를 양보한 쓰레드의 작업이 종료되면 ready state로 천이되어 다시 동작됨.
   - sleep() : 지정된 시간 동안 thread를 non_runnable state의 sleeping state로 천이하고, 지정된
                   시간 경과 후 다시 동작.
   - join() : 특정 쓰레드의 실행이 완전히 종료되도록 설정.

cretical section : 동일한 프로세스에 포함된 다수의 스레드가 한개의 공유자원을 활용 선점하기위해 
                          동시에 접근 가능한 영역으로 비동기화 처리에따라 예상되는 문제가 발생되는 영역이므로
                          스레드가 공유자원의 배타적인 사용을 보장받기 위해서 임계 구역에 들어가거나 나올때는 
                          세마포어 같은 동기화 매커니즘이 사용됨.

       비동기화 처리에따른 예상되는 문제
     deadlock: 다수의 쓰레드가 자원을 선점하기위한 경쟁상태에서 특정 쓰레드의 자원 선점(lock)상태가 지속될수밖에없는
                      상태(무한 sleep state 또는 선점된 상태에서의 예측할수없는 이상종료 등.)에서
                      기타 쓰레드는 자원을 선점한 쓰레드의 자원 선점상태가 종료될때까지 무한 대기하게됨.
     bottlenecks : 다수의 쓰레드가 자원을 선점을위한 경쟁상태가 무한히 지속되어 어떤 스레드도 해당 자원에 접근할수없어 
                           정상적인 프로세스 진행이 불가능한 상태.  
     race condition: 다수의 쓰레드가 동일한 자원의 메모리상 주소를통해 동시에 접근 후 추가,수정,삭제하는 경우 
                      발생할수있는 예측 불가능한 오류.

     synchronization 
	   - 한 개의 자원을 다수의 쓰레드가 동시에 사용할 경우 특정 시점에서는 한 개의 쓰레드만 
	     자원에 접근할 수 있도록 임계영역(cretical section - 공유자원 독점 보장영역)내에 자원 또는 로직을가진 블럭 및 
	     함수를 위치시켜 처리하고, 이때 기타 쓰레드는 non_runnable state의 blocked state로 천이됨. 

     synchronized 
	   - 특정 함수에 synchronized 예약어를 추가해 함수 전체를 cretical section에 위치시켜 단일 쓰레드만 접근 후
	       활용할수 있도록 함.  synchronized 반환타입 함수명(){ 코드 }
	      
	   - 특정 블럭에 synchronized 예약어를 추가해 해당 블럭 내 코드를 정의하여 블럭 전체를 cretical section에 위치시켜 
	       단일 쓰레드만 접근 후 활용할수 있도록 함. synchronized(클래스.class){ 코드 }
	     synchronization에비해 세밀한 제어가 가능함.

      뮤텍스
       * 크리티컬 섹션내 공유 자원에 접근할수있는 스레드는 단일 스레드로 하며, 특정 스레드가 공유자원 점유시 
             다른 스레드들은 대기열을 구성해 대기상태이고, 공유자원을 점유한 스레드가 섹션을 빠져나가면 대기열의
             다음 스레드가 섹션내 공유 자원을 점유하는 방식.  

       세마포어
       * 크리티컬 섹션내 공유 자원에 접근할수 있는 스레드 최대치 설정 방식
       * 빈 화장실 칸들 : 공유 자원
             열쇠 : enter section or exit section
             사람들 : 다수의 스레드
             
       - 세마포어는 빈 화장실 열쇠의 갯수에 비유할 수 있다. 
             즉 비어있는 칸 만큼 열쇠가 있다고 가정하면 사람들이 화장실에 들어갈 때 마다 열쇠의 숫자는 줄어 들게 된다. 
             화장실 칸이 다 찰 경우 카운트는 0이 되며 다음 사람은 줄을 서서 기다린다. 
             볼일을 끝내고 나오면 리소스 사용을 마쳣다는 신호로 카운트를 하나 늘이고 다음 사람에게 열쇠를 부여 한다.

      
쓰레드 실행 우선권 제어 
   - 쓰레드의 실행 순서는 쓰레드 스케줄러의 해당 프로세스 실행상황에따라 예측불가능하게 결정됨. 
   - 선 실행된 쓰레드가 우선 실행 종료된다는 보장 없음. 
   - 쓰레드 객체의 setPriority() 함수를통해 쓰레드의 실행 우선순위를 명시적으로 지정함.
   - 쓰레드 객체 생성시 default 우선순위는 5로 설정되며, 높은 숫자가 실행 우선순위가 높음.
   - 우선순위의 활용 범위
            범위         사용
        10           위기관리
        7~9          상호작용, 이벤트 처리
        4~6          IO관련 작업
        2~3          백그라운드 작업
        1            기타 작업
        
        
학습 순서;
        1. ThreadSynchBase.java
        2. ImplementsRunnable.java
        3. ThreadSynchBase1.java   (synchronized 블럭)
        4. ThreadSynchBase2.java   (synchronized 함수)
        5. ThreadStateView.java                       
        6. MainThreadNBack
        7. zero7_threadPool 팩티지의 스레드풀 구현(ThreadPoolExecutor 활용 전 세대 코드)
        8. Semaphore 예제        
        
 