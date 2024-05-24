package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Color;
import main.GamePanel;
import mino.Block;
import mino.Mino;
import java.awt.Color;
public class Mino_BarTest extends Mino {
    Block[] b = new Block[4];
    Block[] tempB = new Block[4];
    Color c = Color.red;

    public Mino_BarTest() {
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
    public void Mino_Bar() {
        create(GamePanel.colorMode, Color.cyan);
    }

    @Test
    public void Mino_Bar1() {
        int num = 3;
        create(GamePanel.colorMode, Color.cyan);
        b[num].L = true;
    }

    @Test
    public void Mino_Bar2() {
        int num = 3;
        create(GamePanel.colorMode, Color.cyan);
        b[num].S = true;
    }

    @Test
    public void setXY() {
        // □ ■ □ □
        // 1 0 2 3
        b[0].x = 3;
        b[0].y = 3;
        b[1].x = b[0].x - Block.SIZE;
        b[1].y = b[0].y;
        b[2].x = b[0].x + Block.SIZE;
        b[2].y = b[0].y;
        b[3].x = b[0].x + (Block.SIZE * 2);
        b[3].y = b[0].y;
    }

    @Test
    public void getDirection1() {
        // □ ■ □ □
        // 1 0 2 3
        // 모양 정보 temp에 넣어두기.
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x - Block.SIZE;
        tempB[1].y = b[0].y;
        tempB[2].x = b[0].x + Block.SIZE;
        tempB[2].y = b[0].y;
        tempB[3].x = b[0].x + (Block.SIZE * 2);
        tempB[3].y = b[0].y;
//        updateXY(1);
    }

    @Test
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
//        updateXY(2);
    }

    @Test
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
//        updateXY(3);
    }

    @Test
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
//        updateXY(4);
    }
}
