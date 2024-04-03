package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;



public class Menu {
	
	public int pointer = 0;
	
	
	public void update() {
		
		if (KeyHandler.downPressed == true) {
			pointer += 1;
			if (pointer >= 4 ) {
				pointer = 0;
			}
			KeyHandler.downPressed = false;
		}
		if (KeyHandler.upPressed == true) {
			pointer -= 1;
			if (pointer < 0) {
				pointer = 3;
			}
			KeyHandler.upPressed = false;
		}
		
		if (KeyHandler.enterPressed == true) {
			KeyHandler.enterPressed = false;
			
			switch(pointer) {
			case 0 :  // 게임 화면으로
				GamePanel.screenRefresh = true;
				GamePanel.screen = 1; //1번이 게임화면
				break;
			case 1 : //스코어보드
				GamePanel.screenRefresh = true;
				GamePanel.screen = 2;
				break;
			case 2 : //설정
				GamePanel.screenRefresh = true;
				GamePanel.screen = 3;
				break;
			case 3 : System.exit(0);
			}
			
		}
		
	}
	
	
	public void draw(Graphics2D g2) {
		
		int x = GamePanel.WIDTH / 8;
		int y = GamePanel.HEIGHT / 4;
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 60 + 60 * GamePanel.SIZE));
		g2.drawString("Tetris", x, y);
		
		//스타트버튼
		x += GamePanel.WIDTH / 2;
		y += 50 + 50 * GamePanel.SIZE;
		
		g2.setColor((pointer == 0) ? Color.yellow : Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 30 + 30 * GamePanel.SIZE));
		g2.drawString("Start The Game", x, y);
		
		//스코어보드
		y += 50 + 50 * GamePanel.SIZE;
		g2.setColor((pointer == 1) ? Color.yellow : Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 30 + 30 * GamePanel.SIZE));
		g2.drawString("ScoreBoard", x, y);
		
		//옵션
		y += 50 + 50 * GamePanel.SIZE;
		g2.setColor((pointer == 2) ? Color.yellow : Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 30 + 30 * GamePanel.SIZE));
		g2.drawString("Setting", x, y);
		
		//끝내기
		y += 50 + 50 * GamePanel.SIZE;
		g2.setColor((pointer == 3) ? Color.yellow : Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 30 + 30 * GamePanel.SIZE));
		g2.drawString("Exit", x, y);
		
		//확인용 꼭지우셈
		x -= 200 + 200 * GamePanel.SIZE;
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 60));
		g2.drawString(""+pointer, x, y);
		
		
		
		
	}
	
}
