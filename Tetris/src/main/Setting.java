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
			if (pointer_y >= 5 ) {
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
		
		
		//수정필요
		if (KeyHandler.enterPressed == true) {
			KeyHandler.enterPressed = false;
			
			switch(pointer_y) {
			case 0 :  //해상도
				switch(pointer_x) {
				case 0 :
					// 640 * 360
					break;
				case 1 : //디폴트값.
					// 1280 * 720
					break;
				case 2 : 
					//1920 * 1080
					break;
				}
				break;
			case 1 : //키보드설정1
				
				switch(pointer_x) {
				case 0 :
					//0 = 돌리기
					break;
				case 1 :
					// 1 = 내리기
					break;
				case 2 : 
					//2 = 왼쪽
					break;
				case 3 : 
					//3 = 오른쪽
					break;
				}
				
				
				
				break;
			case 2 : //키보드 설정 2
			
				break;
			
			case 3 : //키보드 세팅 변경 확인용도.
				break;
				
			case 4 : //색맹모드
				
				break;
			case 5 : //스크어보드초기화.
				
				
				break;
			
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
	
	
	
	
	
	