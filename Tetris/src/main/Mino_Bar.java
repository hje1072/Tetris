package main;

import java.awt.Color;

public class Mino_Bar extends Mino {
	
	public Mino_Bar() {
		create(Color.cyan);
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
		tempB[1].y = b[0].y + Block.SIZE;
		tempB[2].x = b[0].x;
		tempB[2].y = b[0].y - Block.SIZE;
		tempB[3].x = b[0].x;
		tempB[3].y = b[0].y - (Block.SIZE * 2);
		
		updateXY(2);
		
		
	}
	public void getDirection3() {
		//1번이랑 같은모양
		getDirection1();
	}
	public void getDirection4() {
		//2번이랑 같은모양
		getDirection2();
	}
	
}
