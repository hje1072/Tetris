package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.Color;

import main.GamePanel;
import mino.Block;
import mino.Mino;

public class Mino_SquareTest extends Mino {
    Block[] b = new Block[4];
    Block[] tempB = new Block[4];
    Color c = Color.red;

    public Mino_SquareTest() {
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
	public void Mino_Square1() {
		create(GamePanel.colorMode, Color.yellow);
	}
	@Test
	public void Mino_Square2() {
		int num = 1;
		create(GamePanel.colorMode, Color.yellow);
		b[num].L = true;
	}
	@Test
	public void Mino_Square3 () {
		int num = 2; int dump = 2;
		create(GamePanel.colorMode, Color.yellow);
		b[num].S = true;
	}
	@Test
	public void setXY() {
		// ■ □
		// □ □
		
		// 0 2
		// 1 3
		
		b[0].x = 3;
		b[0].y = 3;
		b[1].x = b[0].x;
		b[1].y = b[0].y + Block.SIZE;
		b[2].x = b[0].x + Block.SIZE;
		b[2].y = b[0].y;
		b[3].x = b[0].x + Block.SIZE;
		b[3].y = b[0].y + Block.SIZE;
		
		
	}
	@Test
	public void getDirection1() {
		// ■ □
		// □ □
		
		// 0 2
		// 1 3
		
		
		//모양 정보 temp에 넣어두기.
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y - Block.SIZE;
		tempB[1].x = tempB[0].x;
		tempB[1].y = tempB[0].y + Block.SIZE;
		tempB[2].x = tempB[0].x + Block.SIZE;
		tempB[2].y = tempB[0].y;
		tempB[3].x = tempB[0].x + Block.SIZE;
		tempB[3].y = tempB[0].y + Block.SIZE;
		
//		updateXY(1);
		
	}
	@Test
	public void getDirection2() {
		// □ ■ 
		// □ □ 
		
		// 1 0
		// 3 2
		
		//모양 정보 temp에 넣어두기.
		tempB[0].x = b[0].x + Block.SIZE;
		tempB[0].y = b[0].y;
		tempB[1].x = tempB[0].x - Block.SIZE;
		tempB[1].y = tempB[0].y;
		tempB[2].x = tempB[0].x;
		tempB[2].y = tempB[0].y + Block.SIZE;
		tempB[3].x = tempB[0].x - Block.SIZE;
		tempB[3].y = tempB[0].y + Block.SIZE;
//		
//		updateXY(2);
		
	}
	@Test
	public void getDirection3() {
		
		// □ □
		// □ ■  
		
		// 3 1
		// 2 0
		
		//모양 정보 temp에 넣어두기.
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y + Block.SIZE;
		tempB[1].x = tempB[0].x;
		tempB[1].y = tempB[0].y - Block.SIZE;
		tempB[2].x = tempB[0].x - Block.SIZE;
		tempB[2].y = tempB[0].y;
		tempB[3].x = tempB[0].x - Block.SIZE;
		tempB[3].y = tempB[0].y - Block.SIZE;
		
//		updateXY(3);
		
	}
	@Test
	public void getDirection4() {
		
		// □ □
		// ■ □  
		
		// 2 3
		// 0 1
		
		//모양 정보 temp에 넣어두기.
		tempB[0].x = b[0].x - Block.SIZE;
		tempB[0].y = b[0].y;
		tempB[1].x = tempB[0].x + Block.SIZE;
		tempB[1].y = tempB[0].y;
		tempB[2].x = tempB[0].x;
		tempB[2].y = tempB[0].y - Block.SIZE;
		tempB[3].x = tempB[0].x + Block.SIZE;
		tempB[3].y = tempB[0].y - Block.SIZE;
		
//		updateXY(4);
		
	}
	
}