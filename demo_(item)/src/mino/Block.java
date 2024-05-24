package mino;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GamePanel;

public class Block extends Rectangle{
	
	public int x, y;
	public static int SIZE = GamePanel.blockSize; //1280 * 720 기준, 30X30픽셀사이즈로 만들어줄거임. 
	public Color c;
	
	//itemL 라인을 삭제하는 이슈 만듦.
	public boolean L = false;
	
	//itemW 체크용.
	public boolean W = false;
	
	//itemG 체크용
	public boolean G = false;
	
	public boolean S = false;
	
	//색깔결정해주기
	public Block(Color c) {
		
		this.c = c;
	
	}
	
	//블록 그리기
	public void draw(Graphics2D g2) {
		
		int margin = 2;
		g2.setColor(c);
		g2.fillRect(x + margin, y + margin, SIZE - (margin * 2), SIZE - (margin * 2));
		
		if(S) {
			g2.setColor(Color.white);
			g2.fillRect(x + margin, y + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));

			g2.setColor(c);

			g2.drawRect(x + margin, y + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));
			
			
			g2.setColor(Color.black);
			g2.drawString("S", x + margin, y + Block.SIZE - margin * 2);
			
			g2.setColor(c);
		}
		
		g2.setColor(c);
		
	}
	
}
