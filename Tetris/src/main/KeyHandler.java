package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public static boolean upPressed, downPressed, leftPressed, rightPressed, pausePressed,
		QPressed, enterPressed, escPressed;
	
	
	
	@Override // 이거 당장은 안쓸거임
	public void keyTyped(KeyEvent e) {} 
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		//위
		if (code == KeyEvent.VK_UP) {
			upPressed = true;
		}
		//아래
		if (code == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
		//왼쪽
		if (code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		//오른쪽
		if (code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		
		//q버튼 종료버튼
		if (code == KeyEvent.VK_Q) {
			QPressed = true;
		}
		
		//퍼즈버튼 누를 때 마다 퍼즈됐다 풀렸다, 할 수 있다.
		if (code == KeyEvent.VK_SPACE) {
			if(pausePressed) {
				pausePressed = false;
			}
			else {
				pausePressed = true;
			}
		}
		
		//esc 눌렀을 때, 메뉴로.
		if (code == KeyEvent.VK_ESCAPE) {
			escPressed = true;
		}
		
		//엔터받기용
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
	}

	@Override // 이것도 당장은 안쓸거임
	public void keyReleased(KeyEvent e) {}
	
	
	
	//키값오류 잡아주는 용도 //선입력방지용
	public static void keyCheck() {
		if(KeyHandler.QPressed) {
			KeyHandler.QPressed = false; //메인메뉴는 q안눌러도 나가는 키 만듦 만약 qpressed 상태일시 초기화.
		}
		
		if(KeyHandler.escPressed) {
			KeyHandler.escPressed = false; //pressed 상태일시 초기화.
		}
		
		if(KeyHandler.pausePressed) {
			KeyHandler.pausePressed = false; //pressed 상태일시 초기화.
		}
		
		if(KeyHandler.enterPressed) {
			KeyHandler.enterPressed = false; //pressed 상태일시 초기화.
		}
		
		if (KeyHandler.downPressed == true) {
			KeyHandler.downPressed = false; //pressed 상태일시 초기화.
		}
		
		if (KeyHandler.upPressed == true) {
			KeyHandler.upPressed = false; //pressed 상태일시 초기화.
		}
		
		if (KeyHandler.rightPressed == true) {
			KeyHandler.rightPressed = false; //pressed 상태일시 초기화.
		}
		
		if (KeyHandler.leftPressed == true) {
			KeyHandler.leftPressed = false; //pressed 상태일시 초기화.
		} 
		
		
	}
	
	

}
