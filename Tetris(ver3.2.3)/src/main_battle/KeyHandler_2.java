package main_battle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;
import main.KeyHandler;

public class KeyHandler_2 implements KeyListener{

	
	public static boolean skillPressed_2, downPressed_2, leftPressed_2, rightPressed_2, turnPressed_2;
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int code = e.getKeyCode();
		
		//왼쪽
		if (code == GamePanel.keySetting_battle[5]) {
			leftPressed_2 = true;
		}
		//아래
		if (code == GamePanel.keySetting_battle[6]) {
			downPressed_2 = true;
		}
		//오른쪽
		if (code == GamePanel.keySetting_battle[7]) {
			rightPressed_2 = true;
		}
		
		
		if (code == GamePanel.keySetting_battle[8]) {
			turnPressed_2 = true;
		}
		
		//스페이스바 버튼
		if (code == GamePanel.keySetting_battle[9]) {
			skillPressed_2 = true;
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void keyCheck() {
		
		if(KeyHandler_2.turnPressed_2) {
			KeyHandler_2.turnPressed_2 = false; //돌리기버튼 초기화
		}
		
		if(KeyHandler_2.skillPressed_2) {
			KeyHandler_2.skillPressed_2 = false; //낙하버튼 초기화
		}
		
		if (KeyHandler_2.downPressed_2 == true) {
			KeyHandler_2.downPressed_2 = false; //아래 초기화.
		}
		
		if (KeyHandler_2.rightPressed_2 == true) {
			KeyHandler_2.rightPressed_2 = false; //오른쪽 초기화.
		}
		
		if (KeyHandler_2.leftPressed_2 == true) {
			KeyHandler_2.leftPressed_2 = false; //왼쪽 초기화.
		} 
		
		
	}
	
	
}
