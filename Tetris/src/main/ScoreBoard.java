package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class ScoreBoard {
	
	public void update() {
		//스코어보드는 눈으로만 볼 수 있게 만듦.
		//확인후 나가는 키만 확실히 표현하면 될듯.
		if (KeyHandler.enterPressed == true) {
			
			KeyHandler.enterPressed = false;
			GamePanel.screenRefresh = true;
			GamePanel.screen = 0; //메인메뉴로
		}
		
		if(KeyHandler.menuPressed) {
			GamePanel.screen = 0;
			
			KeyHandler.menuPressed = false;
		}
	}
	
public void draw(Graphics2D g2) {
		
	
	int x = GamePanel.WIDTH / 8;
	int y = GamePanel.HEIGHT / 8;
	
	g2.setColor(Color.red);
	g2.setStroke(new BasicStroke(4f)); //테두리크기 4픽셀이라는 뜻
	g2.drawRect(x, y, GamePanel.WIDTH * 3 / 4, GamePanel.HEIGHT * 3 / 4); 
	
	g2.setColor(Color.white);
	g2.setFont(new Font("Times New Roman", Font.ITALIC, 60 + 60 * GamePanel.SIZE));
	g2.drawString("ScoreBoard", x, y);
	
	
	
	
	}
}
