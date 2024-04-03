package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;

//블록 불러올때 걍 와카 붙여도될거같은데 일단은 위에 남겨두었음 
import mino.Block;
import mino.Mino;
import mino.Mino_Bar;
import mino.Mino_L1;
import mino.Mino_L2;
import mino.Mino_Square;
import mino.Mino_T;
import mino.Mino_Z1;
import mino.Mino_Z2;

//게임 ui,블록 모양, 그외에 게임 액션등등 
public class PlayManager {
	
	//메인플레이게임 테두리
	int WIDTH = 300;
	int HEIGHT = 600;
	public static int left_x;
	public static int right_x;
	public static int top_y;
	public static int bottom_y;
	
	
	//현재블록
	Mino currentMino;
	final int MINO_START_X;
	final int MINO_START_Y;
	//다음블록
	Mino nextMino;
	final int NEXTMINO_X;
	final int NEXTMINO_Y;
	public static ArrayList<Block> staticBlocks = new ArrayList<>();
	
	
	// others
	public static int dropInterval = 60; // 60fps로 블록 오토드랍
	boolean gameOver;
	
	// 줄삭제이펙트
	boolean effectCounterOn;
	int effectCounter;
	ArrayList<Integer> effectY = new ArrayList<>();
	
	// 점수
	int level = 1;
	int lines = 0;
	int score = 0;
	
	
	
	public PlayManager() {
		
		//플레이화면 크기
		left_x = (GamePanel.WIDTH / 2) - (WIDTH / 2); // 1280/2 - 360 / 2 == 460
		right_x = left_x +WIDTH;
		top_y = 50;
		bottom_y = top_y + HEIGHT;
		
		//스타트지점
		MINO_START_X = left_x + (WIDTH / 2) - Block.SIZE; //x축 중앙
		MINO_START_Y = top_y - Block.SIZE ; //y축 맨위
		
		//다음미노 블록설정
		NEXTMINO_X = right_x + 175;
		NEXTMINO_Y = top_y + 500;
		
		
		//블록들 설정해주기(현재블록, 다음블록)
		currentMino = pickMino();
		currentMino.setXY(MINO_START_X, MINO_START_Y);
		nextMino = pickMino();
		nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
		
		
	}
	
	public void allReset() {
		dropInterval = 60;
		level = 1;
		lines = 0;
		score = 0;
		gameOver = false;
		KeyHandler.pausePressed = false;
		
		
		currentMino = pickMino();
		currentMino.setXY(MINO_START_X, MINO_START_Y);
		nextMino = pickMino();
		nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
		
		allDelete();
	}
	
	
	//블록 선택용
	private Mino pickMino() {
		
		//랜덤블록선택
		Mino mino =null;
		int i = new Random().nextInt(7);
		
		switch(i) {
		case 0: mino = new Mino_L1(); break;
		case 1: mino = new Mino_L2(); break;
		case 2: mino = new Mino_Square(); break;
		case 3: mino = new Mino_Bar(); break;
		case 4: mino = new Mino_T(); break;
		case 5: mino = new Mino_Z1(); break;
		case 6: mino = new Mino_Z2(); break;
		}
		
		
		return mino;
		
	}
	
	//게임오버 확인용
	public boolean isgameOver() {
		
		boolean full = false;
		
		for(int i = 0; i < PlayManager.staticBlocks.size(); i++) {
			//쌓인블록 하나씩 꺼내기 y값 알아내기위함.
			int targetY = PlayManager.staticBlocks.get(i).y;
			
			//위치체크
			for(int ii = 0; ii < currentMino.b.length; ii++) {
				if(targetY <= top_y) { 
					//한놈이라도 빨간줄에 걸치거나 나가면 게임종료.
					full = true;
				}
			}
			
		}
		return full;
	}
	
	
	
	public void update() {
		
		
		//Active상태라서 현재블록으로 계속 플레이해도 되는지 확인.
		if(currentMino.active == false) {
			//만약에 더이상 블록을 움직일 수 없다? 블록을 고정시켜주기.
			staticBlocks.add(currentMino.b[0]);
			staticBlocks.add(currentMino.b[1]);
			staticBlocks.add(currentMino.b[2]);
			staticBlocks.add(currentMino.b[3]);
			
			/*
			//게임오버 확인용
			if(currentMino.b[0].x == MINO_START_X && currentMino.b[0].y == MINO_START_Y) {
				//스타팅위치에서 바로 움직일 수 없는 위치일경우?
				//바로 게임종료. 괜히 슬라이딩해서 계속끌기 막자
				gameOver = true;
			}
			*/
			
			currentMino.deactivating = false;
			
			//옛것바꾸고, 새거 넣어주고
			currentMino = nextMino;
			currentMino.setXY(MINO_START_X, MINO_START_Y);
			nextMino = pickMino();
			nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
			
			//블록을 놓은후 라인이 지워지는지 확인해주자.
			checkDelete();
			
			//게임오버 확인후 테트리스 게임 종료
			//staticBlocks 상태 보고 결정
			//System.out.println(isgameOver()); 확인용
			gameOver = isgameOver();
			
			
		}
		else {
			currentMino.update();
		}
		
	}
	
	
	//라인지우기
	private void checkDelete() {
		
		int x = left_x;
		int y = top_y;
		int blockCount = 0;
		int lineCount = 0;
		
		while(x < right_x && y < bottom_y) {
			
			for(int i = 0; i < staticBlocks.size(); i++) {
				if(staticBlocks.get(i).x == x && staticBlocks.get(i).y == y) {
					//우리가 쌓은블록을 스캔하는 과정임 만약에 있으면 blockcount를 올려줄것.
					//블록카운트가 한줄(10)을 다채워주면 줄 삭제하면 되겠죠?
					//한줄 올라갈때에는 blockcount를 초기화시켜줘서 다시 반복.
					blockCount++;
				}
			}
			
			
			//우리 게임을 한네모 단위로 다 훑어주는 거임. (맨아래부터 왼쪽에서 오른쪽으로 한줄보고 올라가기)
			x +=Block.SIZE;
			
			if(x == right_x) {
				
				
				
				//현재게임에서는 한줄에 10블록이 들어감
				//그래서 한줄을 훑었는데 블록카운트가 10라는것은 한줄을 완성했다는 뜻.
				if(blockCount >= 10 ) {
					
					effectCounterOn = true;
					effectY.add(y); //n개의 줄의 삭제정보 얻기
					
					for(int i = staticBlocks.size() - 1; i > -1; i--) {
						//해당줄 찾아서 삭제해주기.
						if(staticBlocks.get(i).y == y) {
							staticBlocks.remove(i);
						}
						
					}
					
					lineCount++;
					lines++;
					
					//점수얻으면 그에 따라 속도갱신(흠냐륑;ㅁ;)
					if(lines % 10 == 0 && dropInterval > 1) {
						
						level++;
						if(dropInterval > 10) {
							dropInterval -= 10;
						}
						else {
							dropInterval -= 1;
						}
						
					}
					
					
					
					//해당하는 줄만큼 위에있는놈들 내려주기.
					for(int i = 0; i < staticBlocks.size(); i++) {
						if(staticBlocks.get(i).y < y) {
							//그 위에있는 놈들 내려주는 과정.
							staticBlocks.get(i).y += Block.SIZE;
						}
					}
					
				}
				
				
				blockCount = 0;
				x = left_x;
				y += Block.SIZE;
			}
			
		}
		
		//줄삭제 확인 후 점수 갱신
		if(lineCount > 0) {
			int singleLineScore = 10 * level;
			score += singleLineScore * lineCount;
		}
		
	}
	
	//초기화용 //라인지우는 함수에서 따옴.
	private void allDelete() {
		
		int x = left_x;
		int y = top_y;
		int blockCount = 0;
		
		while(x < right_x && y < bottom_y) {
			
			for(int i = 0; i < staticBlocks.size(); i++) {
				if(staticBlocks.get(i).x == x && staticBlocks.get(i).y == y) {
					blockCount++;
				}
			}
			
			x +=Block.SIZE;
			
			if(x == right_x) {

				//현재게임에서는 한줄에 10블록이 들어감
				//그래서 한줄을 훑었는데 블록카운트가 10라는것은 한줄을 완성했다는 뜻.
				if(blockCount >= 0 ) {
					
					for(int i = staticBlocks.size() - 1; i > -1; i--) {
						//모든 라인 지워주기.
						if(staticBlocks.get(i).y == y) {
							staticBlocks.remove(i);
						}
						
					}
				
				blockCount = 0;
				x = left_x;
				y += Block.SIZE;
				}
				
			}
			
		}
		
	}
	
	
	
	public void draw(Graphics2D g2) {
		
		//플레이 화면테두리 그려주기
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(4f)); //테두리크기 4픽셀이라는 뜻
		g2.drawRect(left_x - 4, top_y - 4, WIDTH + 8, HEIGHT + 8); //네모그려주기 rectangle
		
		
		//게임종료라인 만들기
		g2.setColor(Color.black);
		g2.drawLine(left_x - 4 , top_y - 4, left_x + WIDTH + 8, top_y - 4);
		g2.setColor(Color.red);
		float[] dashPattern = {5, 5};
		BasicStroke dashedStroke = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dashPattern, 0.0f);
		g2.setStroke(dashedStroke);
		g2.drawLine(left_x - 4 , top_y - 4, left_x + WIDTH + 8, top_y - 4);
		
		
		//상자설치용 라인그리기.
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(4f));
		
		
		//다음 미노타일 예고상자
		int x = right_x + 100;
		int y = bottom_y - 200;
		g2.drawRect(x, y, 200, 200);
		
		//다음블록 텍스트표현해주기
		g2.setFont(new Font("Arial", Font.PLAIN, 30));
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); // 이건 그냥 글자 쓰는데 렌더링해주는겨
		g2.drawString("NEXT", x + 60, y + 60);
		
		//점수보드그려주기
				g2.drawRect(x, top_y, 250, 300);
				x += 40;
				y = top_y + 90;
				g2.drawString("LEVEL: " + level, x, y); y+= 70;
				g2.drawString("LINES: " + lines, x, y); y+= 70;
				g2.drawString("SCORE: " + score, x, y);
		
		
		
		//현재블록 그려주기 오류없으면 그려주세요
		if((currentMino != null) && (gameOver == false)) {
			currentMino.draw(g2);
		}
		
		//다음블록 표시해주기
		nextMino.draw(g2);
		
		
		//쌓은 블록 (staticblock) 표시해주기
		for(int i = 0; i < staticBlocks.size(); i++) {
			//한블록씩가져와서 표시해줍시다.
			staticBlocks.get(i).draw(g2);
		}
		
		//이펙트 그려주기
		if(effectCounterOn) {
			effectCounter++;
			
			g2.setColor(Color.white);
			for(int i = 0; i< effectY.size(); i++) {
				g2.fillRect(left_x, effectY.get(i), WIDTH, Block.SIZE);
			}
			
			if(effectCounter == 10) {
				effectCounterOn = false;
				effectCounter = 0;
				effectY.clear();
			}
			
		}
		
		
		//퍼즈상태 or 게임오버 표시
		g2.setColor(Color.yellow);
		g2.setFont(g2.getFont().deriveFont(50f));
		
		if(gameOver) {
			x = left_x + 25;
			y = top_y + 320;
			g2.drawString("GAME OVER", x, y);
		}
		else if(KeyHandler.pausePressed) {
			x = left_x + 70;
			y = top_y + 320;
			g2.drawString("PAUSE", x, y);
			
			//임시용
			g2.drawString("To quit the game", WIDTH / x, y );
			g2.drawString("please press 'q'", WIDTH / x, y + 100 );
			g2.drawString("To return to the menu", WIDTH / x, y + 200);
			g2.drawString("please press the ESC", WIDTH / x, y + 300);
		}
		
		
	}
	
	
}
