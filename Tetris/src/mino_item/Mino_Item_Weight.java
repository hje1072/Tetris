package mino_item;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import main.PlayManager;
import main_battle.PlayManager_Battle;
import mino.*;

public class Mino_Item_Weight extends Mino {
	
	//활성화되면 키조작더이상 안됨.
	boolean erasureMode = false;
	
	
	public Mino_Item_Weight() {
		extracted(Color.white);
	}
	

	
	public void setXY(int x, int y) {
		//0번블록기준으로 W을 줄 수 있는 놈인지 확인.
		b[0].W = true;
		//   □ □ //작동x
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
	
	
	public void checkMovementCollision() {
		

		
		leftCollision = false;
		rightCollision = false;
		bottomCollision = false;
		
		int leftWall = (battle ? PlayManager_Battle.left_x : PlayManager.left_x);
		int rightWall = (battle ? PlayManager_Battle.right_x : PlayManager.right_x);
		int bottomFloor = (battle ? PlayManager_Battle.bottom_y : PlayManager.bottom_y);
		
		//내가쌓은 블록 check.
		//checkStaticBlockCollision();
		
		//아래는프레임 collision 체크과정
		
		//왼쪽벽
		for(int i = 0; i < b.length; i++) { //b.length == 4. b는 4개의 블록이죠?
			if(b[i].x == leftWall) { //4개의 블록들이 left와 붙는지 check
				leftCollision = true;
			}
		}
		
		//오른쪽벽
		for(int i = 0; i < b.length; i++) { //(x,y좌표는 좌측상단꼭지점 그래서 블록크기 더해줌)
			if(b[i].x + Block.SIZE == rightWall) {
				rightCollision = true;
			}
		}
		
		//아래벽
		for(int i = 0; i < b.length; i++) {
			if(b[i].y + Block.SIZE == bottomFloor) {
				bottomCollision = true;
			}
		}
		
	}
	
	public void update() {
		if(deactivating) {
			deactivating();
		}
		
		if(erasureMode != true) {
			handling();
		}
		handling_Auto();
		checkMovementCollision();
		erasure();
	}
	
	
	public void erasure() {
		
		ArrayList<Block> staticBlocks = (battle ? PlayManager_Battle.staticBlocks : PlayManager.staticBlocks);
		
		
		for(int i = 0; i < staticBlocks.size(); i++) {
			//쌓인블록 하나씩 꺼내기
			int targetX = staticBlocks.get(i).x;
			int targetY = staticBlocks.get(i).y;
			
			for(int ii = 0; ii < 4; ii++) {
				if(targetX == b[ii].x && targetY == b[ii].y) {
					staticBlocks.remove(i);
					erasureMode = true;
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
			g2.drawString("W", b[i].x , b[i].y + Block.SIZE - margin * 2);
			g2.setColor(b[0].c);
			
			}
		

		g2.fillRect(b[0].x + margin, b[0].y - Block.SIZE + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));
		g2.fillRect(b[2].x + margin, b[2].y - Block.SIZE + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));

		g2.setColor(Color.black);
		g2.drawString("W", b[0].x, b[0].y- Block.SIZE + Block.SIZE - margin * 2);
		g2.drawString("W", b[2].x, b[2].y- Block.SIZE + Block.SIZE - margin * 2);

		g2.setColor(b[0].c);
		
	}
	
	
	
	public void getDirection1() {}
	public void getDirection2() {}
	public void getDirection3() {}
	public void getDirection4() {}
}
