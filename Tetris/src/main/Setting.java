package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

//공사중
public class Setting {
	
	public int pointer_y = 0;
	public int pointer_x = 0;
	
	
	public void update() {
		
		//방향키조작용
		if (KeyHandler.downPressed == true) {
			pointer_y += 1;
			if (pointer_y >= 4 ) {
				pointer_y = 0;
			}
			KeyHandler.downPressed = false;
		}
		if (KeyHandler.upPressed == true) {
			pointer_y -= 1;
			if (pointer_y < 0) {
				pointer_y = 3;
			}
			KeyHandler.upPressed = false;
		}
		if (KeyHandler.rightPressed == true) {
			pointer_x += 1;
			if (pointer_x >= 4 ) {
				pointer_x = 0;
			}
			KeyHandler.rightPressed = false;
		}
		if (KeyHandler.leftPressed == true) {
			pointer_x -= 1;
			if (pointer_x < 0) {
				pointer_x = 3;
			}
			KeyHandler.leftPressed = false;
		}
		
		
		
		if (KeyHandler.enterPressed == true) {
			KeyHandler.enterPressed = false;
			
			switch(pointer_y) {
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
		int y = GamePanel.HEIGHT / 8;
		
		g2.setColor(Color.yellow);
		g2.setStroke(new BasicStroke(4f)); //테두리크기 4픽셀이라는 뜻
		g2.drawRect(x, y, GamePanel.WIDTH * 3 / 4, GamePanel.HEIGHT * 3 / 4); 
		
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 120));
		g2.drawString("Setting", x, y);
		
		//스타트버튼
		x += GamePanel.WIDTH / 2;
		y += 100;
		
		//확인용 2
		x -= 400;
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 60));
		g2.drawString("height:"+pointer_y+" // and width:" + pointer_x, x, y);
		
		
	}
	
	
	
}
	
	
	
	
	
	