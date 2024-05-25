package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;



public class Menu {
	
	public int pointer = 0;
	public int pointer2 = 0;
	public int timer = 1;
	
	boolean check = false;
	boolean timeMode = false;
	
	public void update() {
		modeCheck();
		
		checkEnter();
		
		pressMenu();
		
	}
	
	private void modeCheck() {
		if (timeMode) {
			checkUpPress(0);
			checkDownPress(0);
		}
		else if (check) {
			checkDownPress(1);
			checkUpPress(1);
		}
		else {
			checkDownPress(2);
			checkUpPress(2);
		}
	}
	
	private void checkUpPress(int mode) {
		if (KeyHandler.upPressed) {
			switch (mode) {
			case 0:
				incrementTimer();
				break;
			case 1:
				decrementPointer2();
				break;
			case 2:
				decrementPointer();
				break;
			}
			
			KeyHandler.upPressed = false;
		}
	}
	
	private void checkDownPress(int mode) {
		if (KeyHandler.downPressed) {
			switch (mode) {
			case 0:
				decrementTimer();
				break;
			case 1:
				incrementPointer2();
				break;
			case 2:
				incrementPointer();
				break;
			}
			
			KeyHandler.downPressed = false;
		}
	}
	
	private void incrementTimer() {
		timer = (timer % 6) + 1;
	}
	
	private void decrementTimer() {
		timer = (timer + 4) % 5 + 1;
	}
	
	private void incrementPointer2() {
		pointer2 = (pointer2 + 1) % 3; 
	}
	
	private void decrementPointer2() {
		pointer2 = (pointer2 + 2) % 3;
	}
	
	private void incrementPointer() {
		pointer = (pointer + 1) % 6; 
	}
	
	private void decrementPointer() {
		pointer = (pointer + 5) % 6;
	}
	
	private void checkEnter() {
		if (KeyHandler.enterPressed == true) {
			KeyHandler.enterPressed = false;
			
			selectMenu();
		}
	}
	
	private void selectMenu() {
			
		if (pointer == 3)
			System.exit(0);
		
		else {
			if (pointer == 1)
				GamePanel.basicMode = true;
			
			basicScreen(pointer);
		}
	}
	
	private void basicScreen(int mode) {
		mode += 1;
		GamePanel.screenRefresh = true;
		GamePanel.screen = mode;
	}
	
	private void pressMenu() {
		if (KeyHandler.menuPressed == true) {
			check = false; timer = 1; timeMode = false;
			pointer2 = 0;
			KeyHandler.menuPressed = false;
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int x = GamePanel.WIDTH / 8;
		int y = GamePanel.HEIGHT / 4;
		setString(g2, x, y, 60, "Tetris");
		
		//스타트버튼 (베이직모드)
		x += GamePanel.WIDTH / 2;
		y += 40 + 40 * GamePanel.SIZE;
		
		//스타튼버튼 (아이템모드)
		y = setMenu(g2, x, y, 40, 0, "Start Basic Mode");
		
		//스코어보드
		y = setMenu(g2, x, y, 40, 1, "ScoreBoard!");
		
		//옵션
		y = setMenu(g2, x, y, 40, 2, "Setting!");
		
		//끝내기
		y = setMenu(g2, x, y, 40, 3, "Exit!");
		
		//키확인용
		x -= 200 + 200 * GamePanel.SIZE;
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 15 + 15 *GamePanel.SIZE));
		g2.drawString("Press ["+ Setting.whatisKey(GamePanel.userkeySetting[9]) +"] to confirm.", x, y);
		
	}
	
	private void setString(Graphics2D g2, int x, int y, int size, String name) {
		g2.setColor(Color.white);
		drawString(g2, x, y, size, name);
	}
	
	private void drawString(Graphics2D g2, int x, int y, int size, String name) {
		g2.setFont(new Font("Times New Roman", Font.ITALIC, size + size * GamePanel.SIZE));
		g2.drawString(name, x, y);
	}
	
	private int setMenu(Graphics2D g2, int x, int y, int size, int num, String name) {
		y += size + size * GamePanel.SIZE;
		if (size == 40)
			g2.setColor((pointer == num) ? Color.yellow : Color.white);
		else
			g2.setColor((pointer2 == num) ? Color.yellow : Color.white);
		drawString(g2, x, y, size - 10, name);
		
		return y;
	}
}