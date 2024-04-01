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
			case 0 : 
				GamePanel.screenRefresh = true;
				GamePanel.screen = 1; //1번이 게임화면
				break;
			case 1 : break;
			case 2 : break;
			case 3 : System.exit(0);
			}
			
		}
		
	}
	
	
	public void draw(Graphics2D g2) {
		
		int x = GamePanel.WIDTH / 8;
		int y = GamePanel.HEIGHT / 4;
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 120));
		g2.drawString("Tetris", x, y);
		
		//스타트버튼
		x += GamePanel.WIDTH / 2;
		y += 100;
		
		g2.setColor((pointer == 0) ? Color.yellow : Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 60));
		g2.drawString("Start The Game", x, y);
		
		//스코어보드
		y += 100;
		g2.setColor((pointer == 1) ? Color.yellow : Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 60));
		g2.drawString("ScoreBoard", x, y);
		
		//옵션
		y += 100;
		g2.setColor((pointer == 2) ? Color.yellow : Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 60));
		g2.drawString("Option", x, y);
		
		//끝내기
		y += 100;
		g2.setColor((pointer == 3) ? Color.yellow : Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 60));
		g2.drawString("Exit", x, y);
		
		//확인용
		
		if(GamePanel.screenRefresh) {
			g2.setColor(Color.blue);
			g2.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
		
		//확인용 2
		x -= 400;
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 60));
		g2.drawString(""+pointer, x, y);
		
		
		
		
	}
	
}
