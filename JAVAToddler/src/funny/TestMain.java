package funny;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TestMain extends JFrame {
	
	JLabel la = new JLabel(); 
	public TestMain(){
		add(la);
		setTitle("이벤트 테스트");       
		setVisible(true);       
		setSize(400, 400);       
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
		//키보드 리스너 등록(익명클래스 사용)       
		addKeyListener(new KeyAdapter(){          
			@Override          
			public void keyPressed(KeyEvent e){             
				System.out.println("한영 전환키 키코드 : " + e.getKeyChar() + " | " + 
						e.getKeyCode());
			}
		});
	}
	
	public static void main(String[] args) {
		new TestMain(); 
	}

}
