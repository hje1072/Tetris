package mino;

import java.awt.Color;

public class Mino_L1 extends Mino {
	
	public Mino_L1() {
		create(Color.orange);
	}
	
	
	//itemL 구현용
	public Mino_L1(int num) {
		
		create(Color.orange);
		b[num].L = true;
	}
	
	public void setXY(int x, int y) {
		//  □ 1번
		//  ■ 얘가 0번. 왜냐면 회전한 모양 만들기 편한위치
		//  □□ 2번 3번
		
		b[0].x = x;
		b[0].y = y;
		b[1].x = b[0].x;
		b[1].y = b[0].y - Block.SIZE;
		b[2].x = b[0].x;
		b[2].y = b[0].y + Block.SIZE;
		b[3].x = b[0].x + Block.SIZE;
		b[3].y = b[0].y + Block.SIZE;
		
		
	}
	
	public void getDirection1() {
		// □
		// ■
		// □ □
		
		// 1
		// 0
		// 2 3
		
		//모양 정보 temp에 넣어두기.
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y;
		tempB[1].x = b[0].x;
		tempB[1].y = b[0].y - Block.SIZE;
		tempB[2].x = b[0].x;
		tempB[2].y = b[0].y + Block.SIZE;
		tempB[3].x = b[0].x + Block.SIZE;
		tempB[3].y = b[0].y + Block.SIZE;
		
		updateXY(1);
		
		
	}
	public void getDirection2() {
		// □ ■ □
		// □
		
		// 2 0 1
		// 3
		
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y;
		tempB[1].x = b[0].x + Block.SIZE;
		tempB[1].y = b[0].y;
		tempB[2].x = b[0].x - Block.SIZE;
		tempB[2].y = b[0].y;
		tempB[3].x = b[0].x - Block.SIZE;
		tempB[3].y = b[0].y + Block.SIZE;
		
		updateXY(2);
		
		
	}
	public void getDirection3() {
		// □ □
		//   ■
		//   □
		
		// 3 2
		//   0
		//   1
		
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y;
		tempB[1].x = b[0].x;
		tempB[1].y = b[0].y + Block.SIZE;
		tempB[2].x = b[0].x;
		tempB[2].y = b[0].y - Block.SIZE;
		tempB[3].x = b[0].x - Block.SIZE;
		tempB[3].y = b[0].y - Block.SIZE;
		
		updateXY(3);
		
	}
	public void getDirection4() {
		
		//     □
		// □ ■ □
			
		//     3
		// 1 0 2
		
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y;
		tempB[1].x = b[0].x - Block.SIZE;
		tempB[1].y = b[0].y;
		tempB[2].x = b[0].x + Block.SIZE;
		tempB[2].y = b[0].y;
		tempB[3].x = b[0].x + Block.SIZE;
		tempB[3].y = b[0].y - Block.SIZE;
		
		updateXY(4);
		
	}
	
}
