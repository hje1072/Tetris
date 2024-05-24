package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main_battle.KeyHandler_2;

public class KeyHandler implements KeyListener{
	
	public static boolean upPressed, downPressed, leftPressed, rightPressed, pausePressed,
		quitPressed, enterPressed, menuPressed, skillPressed, turnPressed;
	
	
	@Override // 이거 당장은 안쓸거임
	public void keyTyped(KeyEvent e) {} 
	
	//누르면 실행됨
	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		
		if (GamePanel.userKeyset) {GamePanel.userKey = code;}
		
		if (GamePanel.userKeyset_battle) {GamePanel.userKey_battle = code;}
		//이건 기본값넣었음 기본값은 주석처리된 곳에 써있는 것.
		
		if(GamePanel.battle) {
			//왼쪽
			if (code == GamePanel.keySetting_battle[0]) {
				leftPressed = true;
			}
			//아래
			if (code == GamePanel.keySetting_battle[1]) {
				downPressed = true;
			}
			//오른쪽
			if (code == GamePanel.keySetting_battle[2]) {
				rightPressed = true;
			}//돌리기
			if (code == GamePanel.keySetting_battle[3]) {
				turnPressed = true;
			}
			//스킬 버튼
			if (code == GamePanel.keySetting_battle[4]) {
				skillPressed = true;
			}
		}
		else {
			//왼쪽
			if (code == GamePanel.keySetting[1]) {
				leftPressed = true;
			}
			//아래
			if (code == GamePanel.keySetting[2]) {
				downPressed = true;
			}
			//오른쪽
			if (code == GamePanel.keySetting[3]) {
				rightPressed = true;
			}//돌리기
			if (code == GamePanel.keySetting[4]) {
				turnPressed = true;
			}
			//스킬 버튼
			if (code == GamePanel.keySetting[6]) {
				skillPressed = true;
			}
		}
		
		//위
		if (code == GamePanel.keySetting[0]) {
			upPressed = true;
		}
		
		//q버튼 종료버튼
		if (code == GamePanel.keySetting[5]) {
			quitPressed = true;
		}
		
		//퍼즈버튼 누를 때 마다 퍼즈됐다 풀렸다, 할 수 있다.
		if (code == GamePanel.keySetting[7] && GamePanel.screen == 1) {
			if(pausePressed) {
				pausePressed = false;
			}
			else {
				pausePressed = true;
			}
		}
		
		//esc 눌렀을 때, 메뉴로.
		if (code == GamePanel.keySetting[8]) {
			menuPressed = true;
		}
		
		//엔터받기용
		if (code == GamePanel.keySetting[9]) {
			enterPressed = true;
		}
		
		
	}

	@Override // 이것도 당장은 안쓸거임
	public void keyReleased(KeyEvent e) {}
	
	
	
	//키값오류 잡아주는 용도 //선입력방지용
	public static void keyCheck() {
		
		KeyHandler_2.keyCheck();
		
		if(KeyHandler.turnPressed) {
			KeyHandler.turnPressed = false; //돌리기버튼 초기화
		}
		
		if(KeyHandler.quitPressed) {
			KeyHandler.quitPressed = false; //메인메뉴는 q안눌러도 나가는 키 만듦 만약 qpressed 상태일시 초기화.
		}
		
		if(KeyHandler.menuPressed) {
			KeyHandler.menuPressed = false; //메뉴버튼 초기화
		}
		
		
		if(KeyHandler.enterPressed) {
			KeyHandler.enterPressed = false; //확인버튼 초기화.
		}
		
		if(KeyHandler.skillPressed) {
			KeyHandler.skillPressed = false; //낙하버튼 초기화
		}
		
		if (KeyHandler.downPressed == true) {
			KeyHandler.downPressed = false; //아래 초기화.
		}
		
		if (KeyHandler.upPressed == true) {
			KeyHandler.upPressed = false; //돌리기(위) 초기화.
		}
		
		if (KeyHandler.rightPressed == true) {
			KeyHandler.rightPressed = false; //오른쪽 초기화.
		}
		
		if (KeyHandler.leftPressed == true) {
			KeyHandler.leftPressed = false; //왼쪽 초기화.
		} 
		
		
	}
	
	


}
