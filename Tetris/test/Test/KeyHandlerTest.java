package Test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.junit.Test;

import main.GamePanel;
import main.KeyHandler;
import main_battle.KeyHandler_2;

public class KeyHandlerTest implements KeyListener{
	
	public static boolean upPressed, downPressed, leftPressed, rightPressed, pausePressed,
		quitPressed, enterPressed, menuPressed, skillPressed, turnPressed;
	
	
	@Test
	public void keyTyped() {} 
	
	//누르면 실행됨
	@Test
	public void keyPressed() {
		
		int code = 1;
		
		GamePanel.userKeyset = true;
		if (GamePanel.userKeyset) {GamePanel.userKey = code;}
		
		GamePanel.userKeyset_battle = true;
		if (GamePanel.userKeyset_battle) {GamePanel.userKey_battle = code;}
		//이건 기본값넣었음 기본값은 주석처리된 곳에 써있는 것.
		GamePanel.battle = true;
		if(GamePanel.battle) {
			//왼쪽
			if (code == 1) {
				leftPressed = true;
			}
			//아래
			if (code == 1) {
				downPressed = true;
			}
			//오른쪽
			if (code == 1) {
				rightPressed = true;
			}//돌리기
			if (code == 1) {
				turnPressed = true;
			}
			//스킬 버튼
			if (code == 1) {
				skillPressed = true;
			}
		}
		
		//왼쪽
		if (code == 1) {
			leftPressed = true;
		}
		//아래
		if (code == 1) {
			downPressed = true;
		}
		//오른쪽
		if (code == 1) {
			rightPressed = true;
		}//돌리기
		if (code == 1) {
			turnPressed = true;
		}
		//스킬 버튼
		if (code == 1) {
			skillPressed = true;
		}
		
		
		//위
		if (code == 1) {
			upPressed = true;
		}
		
		//q버튼 종료버튼
		if (code == 1) {
			quitPressed = true;
		}
		
		//퍼즈버튼 누를 때 마다 퍼즈됐다 풀렸다, 할 수 있다.
		GamePanel.screen = 1;
		if (code == 1 && GamePanel.screen == 1) {
			pausePressed = true;
			if(pausePressed) {
				pausePressed = false;
			}
			else {
				pausePressed = true;
			}
		}
		
		//esc 눌렀을 때, 메뉴로.
		if (code == 1) {
			menuPressed = true;
		}
		
		//엔터받기용
		if (code == 1) {
			enterPressed = true;
		}
		
		
	}

	@Test
	public void keyReleased() {}
	
	
	
	//키값오류 잡아주는 용도 //선입력방지용
	@Test
	public void keyCheck() {
		
		KeyHandler_2.keyCheck();
		
		KeyHandler.turnPressed = true;
		if(KeyHandler.turnPressed) {
			KeyHandler.turnPressed = false; //돌리기버튼 초기화
		}
		KeyHandler.quitPressed = true;
		if(KeyHandler.quitPressed) {
			KeyHandler.quitPressed = false; //메인메뉴는 q안눌러도 나가는 키 만듦 만약 qpressed 상태일시 초기화.
		}
		KeyHandler.menuPressed = true;
		if(KeyHandler.menuPressed) {
			KeyHandler.menuPressed = false; //메뉴버튼 초기화
		}
		
		KeyHandler.enterPressed = true;
		if(KeyHandler.enterPressed) {
			KeyHandler.enterPressed = false; //확인버튼 초기화.
		}
		KeyHandler.skillPressed = true;
		if(KeyHandler.skillPressed) {
			KeyHandler.skillPressed = false; //낙하버튼 초기화
		}
		KeyHandler.downPressed = true;
		if (KeyHandler.downPressed == true) {
			KeyHandler.downPressed = false; //아래 초기화.
		}
		KeyHandler.upPressed = true;
		if (KeyHandler.upPressed == true) {
			KeyHandler.upPressed = false; //돌리기(위) 초기화.
		}
		KeyHandler.rightPressed = true;
		if (KeyHandler.rightPressed == true) {
			KeyHandler.rightPressed = false; //오른쪽 초기화.
		}
		KeyHandler.leftPressed = true;
		if (KeyHandler.leftPressed == true) {
			KeyHandler.leftPressed = false; //왼쪽 초기화.
		} 
		
		
	}
	
	


}

