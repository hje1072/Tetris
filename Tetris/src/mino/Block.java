package mino;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block extends Rectangle{
	 
	
	public int x, y;
	public static int SIZE = 30; //30X30픽셀사이즈로 만들어줄거임. 이건 1280 * 720 기준
	public Color c;
	
	//색깔결정해주기
	public Block(Color c) {
		
		this.c = c;
		
	}
	
	//블록 그리기
	public void draw(Graphics2D g2) {
		
		int margine = 2;
		g2.setColor(c);
		g2.fillRect(x + margine, y + margine, SIZE - (margine * 2), SIZE - (margine * 2));
		
	}
	
}
