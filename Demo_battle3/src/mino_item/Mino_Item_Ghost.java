package mino_item;

import java.awt.Color;
import java.awt.Graphics2D;

import main.KeyHandler;
import main.PlayManager;
import main_battle.KeyHandler_2;
import main_battle.PlayManager_Battle;
import mino.Block;
import mino.Mino;

public class Mino_Item_Ghost extends Mino{
	
	
	public Mino_Item_Ghost() {
		
		extracted(Color.white);
		center = new Block(Color.white);
	}
	
	public void setXY(int x, int y) {
		//0번블록기준으로 W을 줄 수 있는 놈인지 확인.
		b[0].G= true;
		b[1].G= true;
		b[2].G= true;
		b[3].G= true;
		center.G= true;
		//   3 
		// 0 □ 1
		//   2
			 
		
		b[0].x = x;
		b[0].y = y;
		b[1].x = b[0].x + 2 * Block.SIZE;
		b[1].y = b[0].y;
		b[2].x = b[0].x + Block.SIZE;
		b[2].y = b[0].y + Block.SIZE;
		b[3].x = b[0].x + Block.SIZE;
		b[3].y = b[0].y - Block.SIZE;
		
	}
	
	//collision 체크용
	public void checkMovementCollision() {
		
		leftCollision = false;
		rightCollision = false;
		bottomCollision = false;
		
		int leftWall = (battle ? PlayManager_Battle.left_x : PlayManager.left_x);
		int rightWall = (battle ? PlayManager_Battle.right_x : PlayManager.right_x);
		int bottomFloor = (battle ? PlayManager_Battle.bottom_y : PlayManager.bottom_y);
		
		//아래는프레임 collision 체크과정
		
		//왼쪽벽
		for(int i = 0; i < this.b.length; i++) { //b.length == 4. b는 4개의 블록이죠?
			if(b[i].x == leftWall) { //4개의 블록들이 left와 붙는지 check
				leftCollision = true;
			}
		}
		
		//오른쪽벽
		for(int i = 0; i < this.b.length; i++) { //(x,y좌표는 좌측상단꼭지점 그래서 블록크기 더해줌)
			if(b[i].x + Block.SIZE == rightWall) {
				rightCollision = true;
			}
		}
		
		//아래벽
		for(int i = 0; i < this.b.length; i++) {
			if(b[i].y + Block.SIZE == bottomFloor) {
				bottomCollision = true;
			}
		}
		
	}
	
	
	public void update() {
		
		boolean SKILL = (battle ? KeyHandler_2.skillPressed_2 : KeyHandler.skillPressed);
		
		//그자리에 고정시키기.
		if(SKILL) {
			
			center.x = b[0].x + Block.SIZE;
			center.y = b[0].y;
			
			erasure();
			active = false;
			KeyHandler.skillPressed = false;
		}
		
		handling();
		handling_Auto();
	}
	
	
	
	public void erasure() {
		for(int i = 0; i < PlayManager.staticBlocks.size(); i++) {
			//쌓인블록 하나씩 꺼내기
			int targetX = PlayManager.staticBlocks.get(i).x;
			int targetY = PlayManager.staticBlocks.get(i).y;
			
			if(targetX == center.x && targetY == center.y) {
				PlayManager.staticBlocks.remove(i);
			}
			
			for(int ii = 0; ii < 4; ii++) {
				if(targetX == b[ii].x && targetY == b[ii].y) {
					PlayManager.staticBlocks.remove(i);
				}
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int margin = 2;
		g2.setColor(b[0].c);
		
		
		for(int i = 0 ; i < 4 ; i ++) {
			
			g2.setColor(b[0].c);
			g2.fillRect(b[i].x + margin, b[i].y + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));
			
			g2.setColor(Color.black);
			g2.drawString("G", b[i].x + margin*2, b[i].y + Block.SIZE - margin * 2);
			g2.setColor(b[0].c);
			
			}
		

		g2.fillRect(b[2].x + margin, b[2].y - Block.SIZE + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));

		g2.setColor(Color.black);
		g2.drawString("G", b[2].x + margin*2, b[2].y- Block.SIZE + Block.SIZE - margin * 2);

		g2.setColor(b[0].c);
		
	}
	
}
