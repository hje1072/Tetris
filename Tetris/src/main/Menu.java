package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;



public class Menu {
	
	public int pointer = 0;
	public int pointer2 = 0;
	
	boolean check = false;
	
	public int timer = 1;
	boolean timeMode = false;
	
	
	public void update() {
		if(timeMode) {
			if (KeyHandler.upPressed == true) {
				timer += 1;
				if (timer >= 6 ) {
					timer = 1;
				}
				KeyHandler.upPressed = false;
			}
			if (KeyHandler.downPressed == true) {
				timer -= 1;
				if (timer < 1) {
					timer = 5;
				}
				KeyHandler.downPressed = false;
			}
		}
		
		else if(check) {
			if (KeyHandler.downPressed == true) {
				pointer2 += 1;
				if (pointer2 >= 3 ) {
					pointer2 = 0;
				}
				KeyHandler.downPressed = false;
			}
			if (KeyHandler.upPressed == true) {
				pointer2 -= 1;
				if (pointer2 < 0) {
					pointer2 = 2;
				}
				KeyHandler.upPressed = false;
			}
		}
		else {
			if (KeyHandler.downPressed == true) {
				pointer += 1;
				if (pointer >= 6 ) {
					pointer = 0;
				}
				KeyHandler.downPressed = false;
			}
			if (KeyHandler.upPressed == true) {
				pointer -= 1;
				if (pointer < 0) {
					pointer = 5;
				}
				KeyHandler.upPressed = false;
			}
		}
		
		if (KeyHandler.enterPressed == true) {
			KeyHandler.enterPressed = false;
			
			switch(pointer) {
			case 0 :  //대전
				
				
				if (check) {
					switch(pointer2) {
					case 0 :
						check = false; pointer2 = 0;
						GamePanel.basicMode = true;
						
						GamePanel.screenRefresh = true;
						GamePanel.screen = 1; //대전모드.
						
						GamePanel.battleMode = true;
						break;
					case 1 :
						check = false; pointer2 = 0;
						GamePanel.basicMode = false;
						
						GamePanel.screenRefresh = true;
						GamePanel.screen = 1; //대전모드.
						
						GamePanel.battleMode = true;
						break;
					case 2 :
						
						if(timeMode) {
							
							GamePanel.timelimitMode = true;
							GamePanel.time = timer * 60;
							
							check = false; pointer2 = 0; timer = 1; timeMode = false;
							GamePanel.basicMode = true;
							
							GamePanel.screenRefresh = true;
							GamePanel.screen = 1; //대전모드.
							
							GamePanel.battleMode = true;
							break;
						}
						else {
							timeMode = true;
						}
						
						break;
					}
				}
				else {
					check = true;
				}

				break;
			case 1 : //베이직모드
				
				GamePanel.basicMode = true;
				GamePanel.screenRefresh = true;
				GamePanel.screen = 1; //1번이 게임화면
				break;
				
			case 2 : //아이템모드
				GamePanel.basicMode = false;
				GamePanel.screenRefresh = true;
				GamePanel.screen = 1; //1번이 게임화면
				break;
				
			case 3 ://스코어보드
				GamePanel.screenRefresh = true;
				GamePanel.screen = 2;
				break;
			case 4 : //설정
				GamePanel.screenRefresh = true;
				GamePanel.screen = 3;
				break;
			case 5 : System.exit(0);
			}
			
		}
		
		if (KeyHandler.menuPressed == true) {
			check = false; timer = 1; timeMode = false;
			pointer2 = 0;
			KeyHandler.menuPressed = false;
		}
		
	}
	
	
	public void draw(Graphics2D g2) {
		
		int x = GamePanel.WIDTH / 8;
		int y = GamePanel.HEIGHT / 4;
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 60 + 60 * GamePanel.SIZE));
		g2.drawString("Tetris", x, y);
		
		//스타트버튼 (베이직모드)
		x += GamePanel.WIDTH / 2;
		y += 40 + 40 * GamePanel.SIZE;
		g2.setColor((pointer == 0) ? Color.yellow : Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 30 + 30 * GamePanel.SIZE));
		g2.drawString("Battle!", x, y);
		
		//스타튼버튼 (아이템모드)
		y += 40 + 40 * GamePanel.SIZE;
		
		g2.setColor((pointer == 1) ? Color.yellow : Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 30 + 30 * GamePanel.SIZE));
		g2.drawString("Start Basic Mode", x, y);
		
		//스타튼버튼 (대전모드)
		y += 40 + 40 * GamePanel.SIZE;
		
		g2.setColor((pointer == 2) ? Color.yellow : Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 30 + 30 * GamePanel.SIZE));
		g2.drawString("Start Item Mode", x, y);
		
		//스코어보드
		y += 40 + 40 * GamePanel.SIZE;
		g2.setColor((pointer == 3) ? Color.yellow : Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 30 + 30 * GamePanel.SIZE));
		g2.drawString("ScoreBoard", x, y);
		
		//옵션
		y += 40 + 40 * GamePanel.SIZE;
		g2.setColor((pointer == 4) ? Color.yellow : Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 30 + 30 * GamePanel.SIZE));
		g2.drawString("Setting", x, y);
		
		//끝내기
		y += 40 + 40 * GamePanel.SIZE;
		g2.setColor((pointer == 5) ? Color.yellow : Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 30 + 30 * GamePanel.SIZE));
		g2.drawString("Exit", x, y);
		
		
		//키확인용
		x -= 200 + 200 * GamePanel.SIZE;
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 15 + 15 *GamePanel.SIZE));
		g2.drawString("Press ["+ Setting.whatisKey(GamePanel.userkeySetting[9]) +"] to confirm.", x, y);
		
		
		//대전모드 나누기
		
		if(check) {
			
			x += 30 + 30 * GamePanel.SIZE;
			y -=  (40 + 40 * GamePanel.SIZE) * 8;
			g2.setStroke(new BasicStroke(4f)); //테두리크기 4픽셀이라는 뜻
			g2.setColor(Color.black);
			g2.fillRect(x, y, GamePanel.WIDTH / 4, GamePanel.HEIGHT * 1 / 3); 
			
			g2.setColor(Color.orange);
			g2.drawRect(x, y, GamePanel.WIDTH  / 4, GamePanel.HEIGHT * 1 / 3); 
			
			x += 5 + 5 * GamePanel.SIZE;
			
			y += 30 + 30 * GamePanel.SIZE;
			g2.setColor((pointer2 == 0) ? Color.yellow : Color.white);
			g2.setFont(new Font("Times New Roman", Font.ITALIC, 20 + 20 * GamePanel.SIZE));
			g2.drawString("Basic Battle!", x, y);
			
			y += 30 + 30 * GamePanel.SIZE;
			g2.setColor((pointer2 == 1) ? Color.yellow : Color.white);
			g2.setFont(new Font("Times New Roman", Font.ITALIC, 20 + 20 * GamePanel.SIZE));
			g2.drawString("Item Battle!", x, y);
			
			y += 30 + 30 * GamePanel.SIZE;
			g2.setColor((pointer2 == 2) ? Color.yellow : Color.white);
			g2.setFont(new Font("Times New Roman", Font.ITALIC, 20 + 20 * GamePanel.SIZE));
			g2.drawString("Time Limit Battle!", x, y);
		}
		
		
		if(timeMode) {
			y += 20 + 20 * GamePanel.SIZE;
			g2.setColor(Color.orange);
			g2.setFont(new Font("Times New Roman", Font.ITALIC, 20 + 20 * GamePanel.SIZE));
			g2.drawString(timer+"Minutes", x, y);
		}
	}
	
}
