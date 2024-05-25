package mino;

import java.awt.Color;

import main.GamePanel;

public class Mino_Bar extends Mino {
	
	public Mino_Bar() {
		create(GamePanel.colorMode, Color.cyan);
	}
	
	public Mino_Bar(int num) {
		
		create(GamePanel.colorMode, Color.cyan);
		b[num].L = true;
	}
	
	public Mino_Bar(int num, int dump) {
		create(GamePanel.colorMode, Color.cyan);
		b[num].S = true;
	}
	
	public void setXY(int x, int y) {
		// □ ■ □ □
		
		// 1 0 2 3
		
		b[0].x = x;
		b[0].y = y;
		b[1].x = b[0].x - Block.SIZE;
		b[1].y = b[0].y;
		b[2].x = b[0].x + Block.SIZE;
		b[2].y = b[0].y;
		b[3].x = b[0].x + (Block.SIZE * 2);
		b[3].y = b[0].y;
		
		
	}
	
	public void getDirection1() {
		// □ ■ □ □
		
		// 1 0 2 3
		
		//모양 정보 temp에 넣어두기.
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y;
		tempB[1].x = b[0].x - Block.SIZE;
		tempB[1].y = b[0].y;
		tempB[2].x = b[0].x + Block.SIZE;
		tempB[2].y = b[0].y;
		tempB[3].x = b[0].x + (Block.SIZE * 2);
		tempB[3].y = b[0].y;
		
		updateXY(1);
		
		
	}
	public void getDirection2() {
		// □
		// ■
		// □
		// □
		
		// 1
		// 0
		// 2
		// 3
		
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y;
		tempB[1].x = b[0].x;
		tempB[1].y = b[0].y - Block.SIZE;
		tempB[2].x = b[0].x;
		tempB[2].y = b[0].y + Block.SIZE;
		tempB[3].x = b[0].x;
		tempB[3].y = b[0].y + (Block.SIZE * 2);
		
		updateXY(2);
		
		
	}
	
	
	public void getDirection3() {
		
		// □ □ ■ □ 
		// 3 2 0 1
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y;
		tempB[1].x = b[0].x + Block.SIZE;
		tempB[1].y = b[0].y;
		tempB[2].x = b[0].x - Block.SIZE;
		tempB[2].y = b[0].y;
		tempB[3].x = b[0].x - (Block.SIZE * 2);
		tempB[3].y = b[0].y;
		
		updateXY(3);
		
	}
	public void getDirection4() {
		
		// □
		// □
		// ■
		// □
		
		// 3
		// 2
		// 0
		// 1
		
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y;
		tempB[1].x = b[0].x;
		tempB[1].y = b[0].y + Block.SIZE;
		tempB[2].x = b[0].x;
		tempB[2].y = b[0].y - Block.SIZE;
		tempB[3].x = b[0].x;
		tempB[3].y = b[0].y - (Block.SIZE * 2);
		
		updateXY(4);
	}
	
}
