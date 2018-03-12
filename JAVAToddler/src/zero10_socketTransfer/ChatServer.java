package zero10_socketTransfer;

import java.io.*;
import java.net.*;
import java.util.Vector;
public class ChatServer {
  private ServerSocket ss;
  private Socket s;
  private Vector<Thread> v;
  
  public ChatServer(){
    v = new Vector<Thread>(5,5);
	System.out.println("서버가 시작되었습니다.");
  }
  
  public void giveAndTake(){
    try{
      ss = new ServerSocket(5420);
	  
      // 서버 소켓에 설정된 포트의 활용가능 여부. 활용할수 없을때 SocketException 발생됨.
      ss.getReuseAddress() ;
      
      while(true){
    	  // 클라이언트 접속 요청 대기. 접속 요청을 Socket 객체로 반환.
    	  s = ss.accept();
    	  
          ServerSocketThread svrth = new ServerSocketThread(this,s);
          addClient(svrth);
          svrth.start();
      }
    }catch(Exception ee){ee.printStackTrace();}
  }
  
  public void addClient(Thread tr){
    v.addElement(tr);
	System.out.println("Client 1명 입장, 총 "+v.size()+"명");
  }
  
  public void removeClient(Thread tr){
    v.removeElement(tr);
	System.out.println("Client 1명 퇴장 총 "+v.size()+"명");
  }
  
  public void broadCasting(String sbc){
      for(int i=0;i<v.size();i++){
          ServerSocketThread svtr=(ServerSocketThread)v.elementAt(i);
          svtr.sendMessage(sbc);
      }
  }
}