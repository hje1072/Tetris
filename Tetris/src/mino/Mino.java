package mino;

import java.awt.Color;
import java.awt.Graphics2D;

import main.KeyHandler;
import main.PlayManager;



public class Mino {
	
	public Block b[] = new Block[4];
	public Block tempB[] = new Block[4];//회전가능 모양들
	
	int autoDropCounter = 0; //오토드랍변수
	public int direction = 1; //방향
	
	//블록 collision 넣어주기 (벽설정)
	boolean leftCollision, rightCollision, bottomCollision;
	//블록활성화
	public boolean active = true;
	//슬라이딩용
	public boolean deactivating;
	int deactivateCounter = 0;
	
	
	public void create(Color c) {
		b[0] = new Block(c);
		b[1] = new Block(c);
		b[2] = new Block(c);
		b[3] = new Block(c);
		
		//temp로 회전모양을 미리 잡아주는 이유는 나중에 모서리부분에서의 collison방지등을 위해서임.
		tempB[0] = new Block(c);
		tempB[1] = new Block(c);
		tempB[2] = new Block(c);
		tempB[3] = new Block(c);
	}
	
	public void setXY(int x, int y) {}
	
	public void updateXY(int direction) {
		
		//업데이트 여부 체크전 돌아가도되는놈인지 check.
		checkRotationCollision();
		
		//collsion 없을 때에만 돌리기
		if(leftCollision == false && rightCollision == false && bottomCollision == false) {
			
			this.direction =direction;
			
			b[0].x = tempB[0].x;
			b[0].y = tempB[0].y;
			b[1].x = tempB[1].x;
			b[1].y = tempB[1].y;
			b[2].x = tempB[2].x;
			b[2].y = tempB[2].y;
			b[3].x = tempB[3].x;
			b[3].y = tempB[3].y;
		}
		
		
	}
	
	//override 회전용
	public void getDirection1() {}
	public void getDirection2() {}
	public void getDirection3() {}
	public void getDirection4() {}
	
	//collision 체크용
	public void checkMovementCollision() {
		
		leftCollision = false;
		rightCollision = false;
		bottomCollision = false;
		
		//내가쌓은 블록 check.
		checkStaticBlockCollision();
		
		//아래는프레임 collision 체크과정
		
		//왼쪽벽
		for(int i = 0; i < b.length; i++) { //b.length == 4. b는 4개의 블록이죠?
			if(b[i].x == PlayManager.left_x) { //4개의 블록들이 left와 붙는지 check
				leftCollision = true;
			}
		}
		
		//오른쪽벽
		for(int i = 0; i < b.length; i++) { //(x,y좌표는 좌측상단꼭지점 그래서 블록크기 더해줌)
			if(b[i].x + Block.SIZE == PlayManager.right_x) {
				rightCollision = true;
			}
		}
		
		//아래벽
		for(int i = 0; i < b.length; i++) {
			if(b[i].y + Block.SIZE == PlayManager.bottom_y) {
				bottomCollision = true;
			}
		}
		
	}
	
	public void checkRotationCollision() {
		
		leftCollision = false;
		rightCollision = false;
		bottomCollision = false;
		
		
		//내가 쌓은 블록 check
		checkStaticBlockCollision();
		
		//아래는프레임 collision 체크과정
		
		//왼쪽벽
		for(int i = 0; i < b.length; i++) { //b.length == 4. b는 4개의 블록이죠?
			if(tempB[i].x < PlayManager.left_x) { //4개의 블록들이 left와 붙는지 check
				leftCollision = true;
			}
		}
		
		//오른쪽벽
		for(int i = 0; i < b.length; i++) { //(x,y좌표는 좌측상단꼭지점 그래서 블록크기 더해줌)
			if(tempB[i].x + Block.SIZE > PlayManager.right_x) {
				rightCollision = true;
			}
		}
		
		//아래벽
		for(int i = 0; i < b.length; i++) {
			if(tempB[i].y + Block.SIZE > PlayManager.bottom_y) {
				bottomCollision = true;
			}
		}
		
	}
	
	public void checkStaticBlockCollision() { //쌓은블록확인용
		
		for(int i = 0; i < PlayManager.staticBlocks.size(); i++) {
			//쌓인블록 하나씩 꺼내기
			int targetX = PlayManager.staticBlocks.get(i).x;
			int targetY = PlayManager.staticBlocks.get(i).y;
			
			//아래체크
			for(int ii = 0; ii < b.length; ii++) {
				if(b[ii].y + Block.SIZE == targetY && b[ii].x == targetX) { 
					//□ 지금 이상태임. 검은색은 이미 놓인거고, 하얀색은 내가 조종중인 블록.
					//■
					bottomCollision = true;
				}
			}
			
			//옆체크(왼쪽)
			for(int ii = 0; ii < b.length; ii++) {
				if(b[ii].x - Block.SIZE == targetX && b[ii].y == targetY) { 
					//■ □ 지금 이상태임. 검은색은 이미 놓인거고, 하얀색은 내가 조종중인 블록.
					leftCollision = true;
				}
			}
			
			//옆체크(오른쪽)
			for(int ii = 0; ii < b.length; ii++) {
				if(b[ii].x + Block.SIZE == targetX && b[ii].y == targetY) { 
					//□ ■ 지금 이상태임. 검은색은 이미 놓인거고, 하얀색은 내가 조종중인 블록.
					rightCollision = true;
				}
			}
			
			
		}
		
		
	}
	
	
		
	
	public void update() {
		
		if(deactivating) {
			deactivating();
		}
		
		
		//쾅 내리기
		if(KeyHandler.fallPressed) {
			
			while (bottomCollision == false) {
				b[0].y += Block.SIZE;
				b[1].y += Block.SIZE;
				b[2].y += Block.SIZE;
				b[3].y += Block.SIZE;
				
				checkMovementCollision(); 
			}
			
			//떨어지는 속도 조절시 오토드랍카운터는 초기화시켜줍시다.
			deactivateCounter = 1557;
			
			KeyHandler.fallPressed = false;
		}
		
		//블록 움직이기
		if(KeyHandler.turnPressed) {
			switch(direction) {
			
			case 1 : getDirection2(); break;
			case 2 : getDirection3(); break;
			case 3 : getDirection4(); break;
			case 4 : getDirection1(); break;
			}
			
			KeyHandler.turnPressed = false;
			
		}
		
		checkMovementCollision();
		
		//밑에 내려도 되는 경우, 내림
		if(KeyHandler.downPressed) {
			
			if (bottomCollision == false) {
				b[0].y += Block.SIZE;
				b[1].y += Block.SIZE;
				b[2].y += Block.SIZE;
				b[3].y += Block.SIZE;
			}
			
			//떨어지는 속도 조절시 오토드랍카운터는 초기화시켜줍시다.
			autoDropCounter = 0;
			
			KeyHandler.downPressed = false;
		}
		if(KeyHandler.leftPressed) {
			
			//왼쪽벽없을시, 이동
			if (leftCollision == false) {
				b[0].x -= Block.SIZE;
				b[1].x -= Block.SIZE;
				b[2].x -= Block.SIZE;
				b[3].x -= Block.SIZE;
			}
			
			KeyHandler.leftPressed = false;
		}
		if(KeyHandler.rightPressed) {
			
			//오른쪽벽없을시, 이동
			if (rightCollision == false) {
				b[0].x += Block.SIZE;
				b[1].x += Block.SIZE;
				b[2].x += Block.SIZE;
				b[3].x += Block.SIZE;
			}
			
			KeyHandler.rightPressed = false;
		}
		
		
		if(bottomCollision) {
			deactivating = true;
		}
		else {
			autoDropCounter++; // 매 프레임마다 증가
			if(autoDropCounter == PlayManager.dropInterval) {
				//60프레임이후 한칸 내려주기
				b[0].y += Block.SIZE;
				b[1].y += Block.SIZE;
				b[2].y += Block.SIZE;
				b[3].y += Block.SIZE;
				autoDropCounter = 0; //초기화해줘서 0부터 반복.
			}
		}
		
	}	
	
	//슬라이딩용 deactivating() 메소드
	private void deactivating() {
		
		deactivateCounter ++;
		
		//슬라이딩은 20 frame 정도 시간을 줄게.
		if(deactivateCounter >= 20) {
			
			deactivateCounter = 0;
			checkMovementCollision(); // 45프레임동안 대기후 바닥에 닿아있네? 바로 active 끄기.
			
			if(bottomCollision) {
				active = false;
			}
		}
	}
	
	
	public void draw(Graphics2D g2) {
		
		int margin = 2;
		g2.setColor(b[0].c);
		g2.fillRect(b[0].x + margin, b[0].y + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));
		g2.fillRect(b[1].x + margin, b[1].y + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));
		g2.fillRect(b[2].x + margin, b[2].y + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));
		g2.fillRect(b[3].x + margin, b[3].y + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));
		
	}
	
}
