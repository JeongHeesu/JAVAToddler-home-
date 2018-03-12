package zero07_threadBeginer;

import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ThreadSynchBase2 {
	
	// 다중 cpu 환경(멀티코어)에서의 thread 우선순위 설정은 무의미함.
	// 쓰레드 순차 처리를 위해서 join() 활용을 권장함.
	public ThreadSynchBase2() {
		SynchLockPrint mr1 = new SynchLockPrint();
//		SynchLockPrint mr2 = new SynchLockPrint();
//		SynchLockPrint mr3 = new SynchLockPrint();
  		
  		Thread t1=new Thread(mr1,"a");
  		System.out.println("default 우선순위 t1 : " + t1.getPriority());
  		
//  		Thread t2=new Thread(mr2,"b");
//  		System.out.println("default 우선순위 t2 : " + t2.getPriority());
//  		t2.setPriority(6);
//  		System.out.println("갱신 우선순위 t2 : " + t2.getPriority());
//  		
//  		Thread t3=new Thread(mr3,"c");
//  		System.out.println("default 우선순위 t3 : " + t3.getPriority());
//  		t3.setPriority(7);
//  		System.out.println("갱신 우선순위 t3 : " + t3.getPriority());
  		
  		t1.start();  
//  		t2.start();  
//  		t3.start();
	}
}

class  SynchLockPrint implements Runnable{
	
	private CollectionPrint cp = new CollectionPrint();
	
	public void run(){
		show();
	}
	
	private synchronized void show(){
		
		if(((Thread.currentThread()).getName()).equals("a") ){
			System.out.println("[AThread]");
			cp.printVectorValue();
		}else if(((Thread.currentThread()).getName()).equals("b") ){
			System.out.println("[BThread]");
			cp.printVectorValue();
		}else if(((Thread.currentThread()).getName()).equals("c") ){
			System.out.println("[CThread]");
			cp.printVectorValue();
		}
	}
}

class CollectionPrint{

	private Vector<WeakReference<MemberBean>> memberList = 
			new Vector<WeakReference<MemberBean>>();
	
	public CollectionPrint() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", 
					"sem", "java");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from member");
			MemberBean member = null;
			while(rs.next()){
				member = new MemberBean();
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_pass(rs.getString("mem_pass"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_regno1(rs.getString("mem_regno1"));
				member.setMem_regno2(rs.getString("mem_regno2"));

				WeakReference<MemberBean> weakMember = 
						new WeakReference<MemberBean>(member);
				memberList.add(weakMember);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void printVectorValue(){
		for(WeakReference<MemberBean> weakMember : memberList){
			MemberBean member = weakMember.get();
			System.out.println("아이디 : " + member.getMem_id() + 
					" 패스워드 : " + member.getMem_pass() + 
					" 이름 : " + member.getMem_name());
			if("c001".equals(member.getMem_id())) break;
		}
	}
}