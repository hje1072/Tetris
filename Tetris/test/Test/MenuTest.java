package Test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import org.junit.Test;

import main.GamePanel;
import main.KeyHandler;
import main.Setting;



public class MenuTest {
	
	public int pointer = 0;
	public int pointer2 = 0;
	public int timer = 1;
	public int mode = 0;
	
	boolean check = false;
	boolean timeMode = false;
	@Test
	public void update() {
		modeCheck();
		
		checkEnter();
		
		pressMenu();
		
	}
	@Test
	public void modeCheck() {
		timeMode = true;
		if (timeMode) {
			checkUpPress();
			checkDownPress();
		}
		check = true;
		if (check) {
			checkDownPress();
			checkUpPress();
		}
		if(true) {
			checkDownPress();
			checkUpPress();
		}
	}
	@Test
	public void checkUpPress() {
		KeyHandler.upPressed = true;
		if (KeyHandler.upPressed) {
			switch (mode) {
			case 0:
				incrementTimer();
				
			case 1:
				decrementPointer2();
				
			case 2:
				decrementPointer();
				
			}
			
			KeyHandler.upPressed = false;
		}
	}
	
	public void checkDownPress() {
		KeyHandler.downPressed = true;
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
	@Test
	public void incrementTimer() {
		timer = (timer % 6) + 1;
	}
	@Test
	public void decrementTimer() {
		timer = (timer + 4) % 5 + 1;
	}
	@Test
	public void incrementPointer2() {
		pointer2 = (pointer2 + 1) % 3; 
	}
	@Test
	public void decrementPointer2() {
		pointer2 = (pointer2 + 2) % 3;
	}
	@Test
	public void incrementPointer() {
		pointer = (pointer + 1) % 6; 
	}
	@Test
	public void decrementPointer() {
		pointer = (pointer + 5) % 6;
	}
	@Test
	public void checkEnter() {
		KeyHandler.enterPressed = true;
		if (KeyHandler.enterPressed == true) {
			KeyHandler.enterPressed = false;
			
			selectMenu();
		}
	}
	@Test
	public void selectMenu() {
		pointer = 0;
		if (pointer == 0)
			battleMode();
		if (pointer == 5)
			System.exit(0);
		if(true) {
			
			if (pointer < 3)
				GamePanel.basicMode = (pointer == 1) ? true : false;
			basicScreen();
		}
	}
	@Test
	public void battleMode() {
		check = true;
		if (check) {
			pointer2 = 0;
			switch(pointer2) {
			case 0 :
				battleScreen();
				
			case 1 :
				battleScreen();
				
			case 2 :
				timeMode = true;
				if(timeMode) {
					GamePanel.timelimitMode = true;
					GamePanel.time = timer * 60;
					
					timer = 1; timeMode = false;
					battleScreen();
					
				}
				else
					timeMode = true;
				
				
			}
		}
		else
			check = true;
	}
	@Test
	public void battleScreen() {
		pointer2 = 3;
		if (pointer2 % 2 == 1)
			GamePanel.basicMode = false;
		if(true)
			GamePanel.basicMode = true;
		basicScreen();
		GamePanel.battleMode = true;
		check = false; pointer2 = 0;
	}
	@Test
	public void basicScreen() {
		mode = 0;
		mode = (mode < 3) ? 1 : ((mode == 3) ? 2 : 3);
		GamePanel.screenRefresh = true;
		GamePanel.screen = mode;
	}
	@Test
	public void pressMenu() {
		KeyHandler.menuPressed = true;
		if (KeyHandler.menuPressed == true) {
			check = false; timer = 1; timeMode = false;
			pointer2 = 0;
			KeyHandler.menuPressed = false;
		}
	}
//	public void draw(Graphics2D g2) { // 매개변수 없애고  g2가 임의의 Graphics를 갖도록 해야함
//		
//		int x = GamePanel.WIDTH / 8;
//		int y = GamePanel.HEIGHT / 4;
//		setString(g2, x, y, 60, "Tetris");
//		
//		//스타트버튼 (베이직모드)
//		x += GamePanel.WIDTH / 2;
//		y = setMenu(g2, x, y, 40, 0, "Battle!");
//		
//		//스타튼버튼 (아이템모드)
//		y = setMenu(g2, x, y, 40, 1, "Start Basic Mode");
//		
//		//스타튼버튼 (대전모드)
//		y = setMenu(g2, x, y, 40, 2, "Start Item Mode");
//		
//		//스코어보드
//		y = setMenu(g2, x, y, 40, 3, "ScoreBoard!");
//		
//		//옵션
//		y = setMenu(g2, x, y, 40, 4, "Setting!");
//		
//		//끝내기
//		y = setMenu(g2, x, y, 40, 5, "Exit!");
//		
//		//키확인용
//		x -= 200 + 200 * GamePanel.SIZE;
//		g2.setColor(Color.white);
//		g2.setFont(new Font("Times New Roman", Font.ITALIC, 15 + 15 *GamePanel.SIZE));
//		g2.drawString("Press ["+ Setting.whatisKey(GamePanel.userkeySetting[9]) +"] to confirm.", x, y);
//		
//		//대전모드 나누기
//		drawBattle(g2, x, y);
//	}
//	
//	private void setString(Graphics2D g2, int x, int y, int size, String name) {
//		g2.setColor(Color.white);
//		drawString(g2, x, y, size, name);
//	}
//	
//	private void drawString(Graphics2D g2, int x, int y, int size, String name) {
//		g2.setFont(new Font("Times New Roman", Font.ITALIC, size + size * GamePanel.SIZE));
//		g2.drawString(name, x, y);
//	}
//	
//	private int setMenu(Graphics2D g2, int x, int y, int size, int num, String name) {
//		y += size + size * GamePanel.SIZE;
//		if (size == 40)
//			g2.setColor((pointer == num) ? Color.yellow : Color.white);
//		else
//			g2.setColor((pointer2 == num) ? Color.yellow : Color.white);
//		drawString(g2, x, y, size - 10, name);
//		
//		return y;
//	}
//	
//	private void drawBattle(Graphics2D g2, int x, int y) {
//		if(check) {
//			x += 30 + 30 * GamePanel.SIZE;
//			y -=  (40 + 40 * GamePanel.SIZE) * 8;
//			g2.setStroke(new BasicStroke(4f)); //테두리크기 4픽셀이라는 뜻
//			g2.setColor(Color.black);
//			g2.fillRect(x, y, GamePanel.WIDTH / 4, GamePanel.HEIGHT * 1 / 3); 
//			
//			g2.setColor(Color.orange);
//			g2.drawRect(x, y, GamePanel.WIDTH  / 4, GamePanel.HEIGHT * 1 / 3); 
//			
//			x += 5 + 5 * GamePanel.SIZE;
//			y = setMenu(g2, x, y, 30, 0, "Basic Battle!");
//			y = setMenu(g2, x, y, 30, 1, "Item Battle!");
//			y = setMenu(g2, x, y, 30, 2, "Time Limit Battle!");
//		}
//
//		if(timeMode) {
//			y += 20 + 20 * GamePanel.SIZE;
//			g2.setColor(Color.orange);
//			g2.setFont(new Font("Times New Roman", Font.ITALIC, 20 + 20 * GamePanel.SIZE));
//			g2.drawString(timer+"Minutes", x, y);
//		}
//	}
}
