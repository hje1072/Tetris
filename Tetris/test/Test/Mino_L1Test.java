package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.Color;

import main.GamePanel;
import mino.Block;
import mino.Mino;

public class Mino_L1Test extends Mino {
    Block[] b = new Block[4];
    Block[] tempB = new Block[4];
    Color c = Color.red;

    public Mino_L1Test() {
        b[0] = new Block(c);
        b[1] = new Block(c); // 예시로 초기화 값 입력
        b[2] = new Block(c); // 예시로 초기화 값 입력
        b[3] = new Block(c); // 예시로 초기화 값 입력
        
        tempB[0] = new Block(c);
        tempB[1] = new Block(c);
        tempB[2] = new Block(c);
        tempB[3] = new Block(c);
        
    }
	@Test
	public void Mino_L1() {
		create(GamePanel.colorMode, Color.orange);
	}
	
	
	//itemL 구현용
	@Test
	public void Mino_L1Test2() {
		int num = 0;
		create(GamePanel.colorMode, Color.orange);
		b[num].L = true;
	}
	
	//itemS 구현용
	@Test
	public void Mino_L1Test3() {
		int num = 0; int dump = 0;
		create(GamePanel.colorMode, Color.orange);
		b[num].S = true;
	}
	@Test
	public void setXY() {
		//  □ 1번
		//  ■ 얘가 0번. 왜냐면 회전한 모양 만들기 편한위치
		//  □□ 2번 3번
		
		b[0].x = 3;
		b[0].y = 3;
		b[1].x = b[0].x;
		b[1].y = b[0].y - Block.SIZE;
		b[2].x = b[0].x;
		b[2].y = b[0].y + Block.SIZE;
		b[3].x = b[0].x + Block.SIZE;
		b[3].y = b[0].y + Block.SIZE;
		
		
	}
	@Test
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
		
//		updateXY(1);
		
		
	}
	@Test
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
		
//		updateXY(2);
		
		
	}
	@Test
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
		
//		updateXY(3);
		
	}
	@Test
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
		
//		updateXY(4);
		
	}
	
}
