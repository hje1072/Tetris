package Test;

import static org.junit.Assert.*;


import org.junit.Test;

import main.Calc;
import main.GamePanel;
import main.KeyHandler;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main_battle.PlayManager_Battle;
import mino.Block;
import mino.Mino;
import mino.Mino_Bar;
import mino.Mino_L1;
import mino.Mino_L2;
import mino.Mino_Square;
import mino.Mino_T;
import mino.Mino_Z1;
import mino.Mino_Z2;
import mino_item.Mino_Item_Ghost;
import mino_item.Mino_Item_Swap;
import mino_item.Mino_Item_Weight;

//게임 ui,블록 모양, 그외에 게임 액션등등 
public class PlayManagerTest {
	
	//메인플레이게임 테두리
	public int WIDTH = 200 + 100 * GamePanel.SIZE;
	public int HEIGHT = 400 + 200 * GamePanel.SIZE;
	public static int left_x;
	public static int right_x;
	public static int top_y;
	public static int bottom_y;
	
	Mino weighted_item = new Mino_Item_Weight();
	
	
	//현재블록
	Mino currentMino = weighted_item;;
	int MINO_START_X;
	int MINO_START_Y;
	//다음블록
	Mino nextMino = weighted_item;;
	int NEXTMINO_X;
	int NEXTMINO_Y;
	public static ArrayList<Block> staticBlocks = new ArrayList<>();


	
	
	// others
	public static int dropInterval = 60; // 60fps로 블록 오토드랍
	public boolean gameOver;
	
	
	// 줄삭제이펙트
	boolean effectCounterOn;
	int effectCounter;
	ArrayList<Integer> effectY = new ArrayList<>(); 
	
	// 점수
	public static int level = 1;
	public static int lines = 0;
	public static int score = 0;
	
	//아이템 발동시 사용할 친구임.
	// lines 가 10의 배수 일때마다 itemChance 발동
	//잠깐 낮춰놓음
	int itemChance= 10;
	int ChanceWeight = 10;
	
	//아이템 S 전용
	boolean chanceS = false;
	boolean activateS = false;
	
	
	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 
	
	//공격받는거 전용 라인
	public static ArrayList<Block> staticBlocks_UnderAttack = new ArrayList<>();
	public static boolean underAttack = false;
	
	
	
	
	
	
	
	//공사중
	@Test
	public void PlayManager() {
		
		int O = (GamePanel.battle ? (GamePanel.WIDTH / 2) : (GamePanel.WIDTH / 2) - (WIDTH / 2));
		
		//플레이화면 크기
		left_x = O; // 1280/2 - 360 / 2 == 460
		right_x = left_x +WIDTH;
		top_y = 50;
		bottom_y = top_y + HEIGHT;
		
		//스타트지점
		MINO_START_X = left_x + (WIDTH / 2) - Block.SIZE; //x축 중앙
		MINO_START_Y = top_y - Block.SIZE ; //y축 맨위
		
		//다음미노 블록설정
		NEXTMINO_X = (GamePanel.SIZE ==0 ? right_x + 55 : right_x + 85 + 50 * GamePanel.SIZE);
		NEXTMINO_Y = top_y + 350 + 150 * GamePanel.SIZE;
		
		
		//블록들 설정해주기(현재블록, 다음블록)
		currentMino = weighted_item;
		
		currentMino.setXY(MINO_START_X, MINO_START_Y);
		
		nextMino = weighted_item;
		
		nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
		
	
	
	}
	
	
	@Test
	public void allReset() {
		
		
		dropInterval = 60;
		level = 1;
		lines = 0;
		score = 0;
		itemChance= ChanceWeight;
		gameOver = false; 
		KeyHandler.pausePressed = false;
		
		
		currentMino = weighted_item;
		currentMino.setXY(MINO_START_X, MINO_START_Y);
		nextMino = weighted_item;
		nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
		
		allDelete();
	}
	
	
	//블록 선택용
	@Test
	public void pickMino() {
	    
	    // 랜덤블록 선택
	    Mino mino = null;
	    
	    // 베이직한 모드일 때 무조건 normal에서만 미노 뽑음.
	    GamePanel.basicMode = true;
	    if(GamePanel.basicMode) {
	        mino = new Mino_Bar();
	    }
	    // item chance가 라인보다 높거나 강제 라인찬스 발동 시.
	    chanceS = true;
	    if(chanceS) {
	        chanceS = false;
	        // System.out.println("짠 아이템뽀ㅃ기~");
	        mino = weighted_item;
	    }
	    lines = itemChance + 1;
	    if (lines >= itemChance) {
	        // 디버깅 시 알아서 조절
	        itemChance += ChanceWeight;
	        chanceS = false;
	        
	        mino = weighted_item;
	    }
	     
	        mino = new Mino_Bar();
	    
	    
	    // 선택한 Mino 객체를 처리 (예: GamePanel.currentMino에 저장)
	    GamePanel.currentMino = mino;
	}

		
		
	
	@Test
	public void pick_normalMino() {
		Mino mino = null;
		
		int i = Calc.generateWRN(GamePanel.difficulty);
		i = 0;
		
		switch(i) {
		case 0: mino = new Mino_Bar(); 
		case 1: mino = new Mino_L1(); 
		case 2: mino = new Mino_L2(); 
		case 3: mino = new Mino_Square(); 
		case 4: mino = new Mino_T(); 
		case 5: mino = new Mino_Z1(); 
		case 6: mino = new Mino_Z2(); 
		}
		
		
	}
	
	//아이템 5개 구현끝.
	@Test
	public void pick_itemMino() {
		Mino mino = null;
		
		int i = Calc.generateRN(5);
		//System.out.println(i); //디버깅용
		i = 0;
		
		switch(i) {
		
		case 0 :
			
			int j = Calc.generateWRN(GamePanel.difficulty);
			int jj = Calc.generateRN(4);
			j = 0;
			switch(j) {
			case 0: mino = new Mino_Bar(jj);
			case 1: mino = new Mino_L1(jj); 
			case 2: mino = new Mino_L2(jj); 
			case 3: mino = new Mino_Square(jj); 
			case 4: mino = new Mino_T(jj); 
			case 5: mino = new Mino_Z1(jj); 
			case 6: mino = new Mino_Z2(jj); 
			}
			
			
		
		case 1 : //무게추 아이템
			
			mino = new Mino_Item_Weight(); 
			
		case 2 : //유령아이템
			
			mino = new Mino_Item_Ghost(); 
			
		case 3 : //스코어뻥핑아이템 + 아이템 쿨초기화.
			int k = Calc.generateWRN(GamePanel.difficulty);
			int kk = Calc.generateRN(4);
			
			k = 0;
			switch(k) {
			case 0: mino = new Mino_Bar(kk, 0);
			case 1: mino = new Mino_L1(kk, 0); 
			case 2: mino = new Mino_L2(kk, 0); 
			case 3: mino = new Mino_Square(kk, 0); 
			case 4: mino = new Mino_T(kk, 0); 
			case 5: mino = new Mino_Z1(kk, 0);
			case 6: mino = new Mino_Z2(kk, 0); 
			}
			
			
		
		case 4 : //스왑아이템
			mino = new Mino_Item_Swap(); 
		}
		
		
		
	}
	
	//게임오버 확인용 stactblocks이용
	@Test
	public void isgameOver() {
		
		boolean full = false;
		
//		for(int i = 0; i < PlayManagerTest.staticBlocks.size(); i++) {
//			//쌓인블록 하나씩 꺼내기 y값 알아내기위함.
//			int targetY = PlayManagerTest.staticBlocks.get(i).y;
//			
//			//위치체크
//			for(int ii = 0; ii < currentMino.b.length; ii++) {
//				if(targetY <= top_y) { 
//					//한놈이라도 빨간줄에 걸치거나 나가면 게임종료.
//					full = true;
//				}
//			}
//			
//		}
		
	}
	
	
	//업데이트
	@Test
	public void update() {
		currentMino.swap = true;
		if(currentMino.swap) {
			currentMino.swap = false;
			Mino tempMino = currentMino;
			
			currentMino = nextMino;
			nextMino = tempMino;
			
			currentMino.setXY(MINO_START_X, MINO_START_Y);
			nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
		}
		
		//Active상태라서 현재블록으로 계속 플레이해도 되는지 확인.
		currentMino.active = false;
		if(currentMino.active == false) {
			currentMino.b[0].W =false;
			//무게추아이템일 경우 무시해줌.
			if(currentMino.b[0].W == false) {
				currentMino.b[0].G = true;
				if(currentMino.b[0].G) {
					staticBlocks.add(currentMino.center);
				}
				
				staticBlocks.add(currentMino.b[0]);
				staticBlocks.add(currentMino.b[1]);
				staticBlocks.add(currentMino.b[2]);
				staticBlocks.add(currentMino.b[3]);
				
			}
			
			
			

			
			currentMino.deactivating = false;
			
			underAttack = true;
			
			if(underAttack) {
				//System.out.println("공습경보");
				underAttack = false;
				underAttack();
			}
			
			//블록을 놓은후 라인이 지워지는지 확인해주자.
//			checkDelete();
			
			
			//테스트용
			//System.out.println(staticBlocks.get(0).x);
			
			//System.out.println((bottom_y - staticBlocks.get(0).y)/GamePanel.blockSize);
			/*
			for (Block b : PlayManager.staticBlocks) {
				System.out.println((b.x - left_x)/GamePanel.blockSize + 1);
				System.out.println((bottom_y - b.y)/GamePanel.blockSize);
			}
			*/
			
			//나중에 들어온 놈만들기.
//			oldSet(); //old 블록과 new 블록 구분.
//			
//			
//			//게임오버 확인후 테트리스 게임 종료
//			//staticBlocks 상태 보고 결정
//			//System.out.println(isgameOver()); 확인용
//			gameOver = false;
//			
//			gameOver = true;
//			GamePanel.battle = true;
//			if(gameOver && GamePanel.battle) {GamePanel.p2Win = true;}
//			
//			//게임오버 아니면 계속 게임 진행.
//			//옛것바꾸고, 새거 넣어주고
//			currentMino = nextMino;
//			currentMino.setXY(MINO_START_X, MINO_START_Y);
//			nextMino = weighted_item;
//			nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
//			
//
//			
//			
//		}
		
//		
//			currentMino.update();
		}
		}
		
	
	
	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 
	@Test
	public void oldSet() {
		for(Block b : staticBlocks) {
			b.oldSet();
		}
	}
	@Test
	public void staticUpper() {
	    // 고정된 블록 좌표 리스트 설정
	    List<int[]> blocks = Arrays.asList(
	        new int[] {50, 100},
	        new int[] {100, 150},
	        new int[] {150, 200}
	    );

	    // 블록 좌표의 y 값을 감소시킴
	    for (int[] block : blocks) {
	        block[1] -= Block.SIZE;
	    }
	}
	
	
	
	//라인지우기
	@Test
	public void checkDelete() {
		
		int x = left_x;
		int y = top_y;
		int blockCount = 0;
		int lineCount = 0;
		
		boolean checkBlock;
		
		//배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 
		//공격전용라인생성
		ArrayList<Block> staticBlocks_Attack = new ArrayList<>();
		int attackTemp_y = bottom_y - GamePanel.blockSize;
		
		
		int attackTemp_x = (GamePanel.SIZE == 0 ? 415 : (GamePanel.SIZE == 1 ? 620: 880));
		
		
		//itemS 로서, 점수 증폭 및 아이템 쿨타임감소.
		boolean checkS;
		x = right_x - 1;
		y = bottom_y - 1;
		
		while(x < right_x && y < bottom_y) {
			checkBlock = false;
			
//			for(int i = 0; i < 3; i++) {
//				
//				staticBlocks.get(i).x = x;
//				staticBlocks.get(i).y = y;
//				if(staticBlocks.get(i).x == x && staticBlocks.get(i).y == y) {
//					//우리가 쌓은블록을 스캔하는 과정임 만약에 있으면 blockcount를 올려줄것.
//					//블록카운트가 한줄(10)을 다채워주면 줄 삭제하면 되겠죠?
//					//한줄 올라갈때에는 blockcount를 초기화시켜줘서 다시 반복.
//					
//					
//					checkBlock = true;
//					
//					//만약에 itemL이 있으면 바로 blockCount의 값을 10 추가. 
//					//그러면 무조건 삭제됨
//					staticBlocks.get(i).L = true;
//					if(staticBlocks.get(i).L) {
//						blockCount += 10;
//					}
//				}
//				
//			}
			checkBlock = true;
			if (checkBlock) {
				blockCount ++;
			}
			
			
			//우리 게임을 한네모 단위로 다 훑어주는 거임. (맨아래부터 왼쪽에서 오른쪽으로 한줄보고 올라가기)
			x +=Block.SIZE;
			
			x = right_x;
			if(x == right_x) {
				
				
				
				//현재게임에서는 한줄에 10블록이 들어감
				//그래서 한줄을 훑었는데 블록카운트가 10라는것은 한줄을 완성했다는 뜻.
				
				//ghost블록은 겹쳐서 들어가서 10줄 확인할 때, 중복체크해줘야함.
				blockCount = 10 + 1;
				if(blockCount >= 10) {
					
					staticUpper();
					
					checkS = false;
					
					//System.out.println(blockCount);
					
					effectCounterOn = true;
					effectY.add(y); //n개의 줄의 삭제정보 얻기
					
					for(int i = staticBlocks.size() - 1; i > -1; i--) {
						//해당줄 찾아서 삭제해주기.
						staticBlocks.get(i).y = y;
						if(staticBlocks.get(i).y == y ) {
							staticBlocks.get(i).S = true;
							if(staticBlocks.get(i).S) {
								checkS = true;
							}
							
							//배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 
							staticBlocks.get(i).old = true;
							if(staticBlocks.get(i).old) {
								staticBlocks.get(i).x -= attackTemp_x;
								staticBlocks.get(i).ySet(attackTemp_y);
								
								//아이템 초기화 
								staticBlocks.get(i).S = false;
								staticBlocks.get(i).L = false;
								staticBlocks.get(i).colorSet();
								staticBlocks_Attack.add(staticBlocks.get(i));
							}
							
							staticBlocks.remove(i);
							
						}
						
						
					}
					
//					checkS = true;
//					if(checkS) {
//						chanceS = checkS;
//						activateS =checkS;
//					}
					
//					lineCount++;
//					lines++;
//					
//					//점수얻으면 그에 따라 속도갱신(흠냐륑;ㅁ;)
//					lines = 10;
//					dropInterval = 2;
//					if(lines % 10 == 0 && dropInterval > 1) {
//						
//						setDropInterval();
//						
//					}
//					
//					
//					//해당하는 줄만큼 위에있는놈들 내려주기.
//					for(int i = 0; i < staticBlocks.size(); i++) {
//						if(staticBlocks.get(i).y < y) {
//							//그 위에있는 놈들 내려주는 과정.
//							staticBlocks.get(i).y += Block.SIZE;
//						}
//					}
//					
//				}
//				
//				
//				blockCount = 0;
//				x = left_x;
//				y += Block.SIZE;
//			}
//			
//		}
//		
//		//줄삭제 확인 후 점수 갱신
//		lineCount = 1;
//		if(lineCount > 0) {
//			int singleLineScore = 100 * level;
//			
//			//2줄이상 연속으로 지웠을 시, 점수 제곱으로 부여.
//			score += singleLineScore * lineCount * lineCount; 
//			
//			//itemS 를 지웠을 때 점수 증폭. 밸런스를 위해 제곱은 안함.
//			score += (activateS? 500 * level * lineCount : 0);
//			
//			
//			//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 
//			if(lineCount >= 1 ) {
//				
//				PlayManager_Battle.underAttack = true;
//				
//				for(int i = 1; i <=lineCount; i++) {
//					staticUpper();
//				}
//				
//				
//				
//				for(Block b : staticBlocks_Attack) {
//					PlayManager_Battle.staticBlocks_UnderAttack.add(b);
//				}
//				
//				//공격받는거
//				int checklist = bottom_y - GamePanel.blockSize * 10 ; //10번쨰 블록. 즉 가장 높게 차있을 수 있는 위치
//				//10줄 벗어나면 없애기
//				for(int i = PlayManager_Battle.staticBlocks_UnderAttack.size() - 1; i > -1; i--) {
//					//해당줄 찾아서 삭제해주기.
//					if(PlayManager_Battle.staticBlocks_UnderAttack.get(i).y <  checklist) {
//						PlayManager_Battle.staticBlocks_UnderAttack.remove(i);
					}
					
				}
		}
				/* 블록제대로있는지 체크용
				for(Block b : staticBlocks_Attack) {
					System.out.println(b.x);
				}
				*/
	}
			
		
		
	
	
	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 
	@Test
	public void underAttack() {
		int cnt = 3;
		
//		for(Block b : staticBlocks_UnderAttack) {
//			if (b.y < cnt) {
//				cnt = b.y;
//			}
//		}
//		
//		cnt = (3 - cnt) / 1;
//		
//		for(int i = 1; i<= cnt ; i++) {
//			staticUpper();
//		}
//		
//		//System.out.println(cnt);
//		
//		for(Block b : staticBlocks_UnderAttack) {
//			staticBlocks.add(b);
//		}
		
		//공격받은 블록 초기화
		staticBlocks_UnderAttack = new ArrayList<>();
		
	}
	
	
	
	
	//점수 갱신 후 dropInterval 변화.
	@Test
	public void setDropInterval() {
		
		int speed = (int)(10 * (2 - Calc.difficulty_To_num(GamePanel.difficulty)));
		
		level++;
		dropInterval = 13;
		if(dropInterval > 12) {
			dropInterval -= speed;
			//System.out.println(Calc.difficulty_To_num(GamePanel.difficulty));
		}
		
			dropInterval -= 1;
		
	}


	//초기화용
	@Test
	public void allDelete() {
		
		//쌓은블록초기화
		staticBlocks = new ArrayList<Block>();
		
	}
	
	
	//그리기
	@Test
	public void draw() {
	    // BufferedImage를 사용하여 Graphics2D 객체 생성
	    BufferedImage image = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = image.createGraphics();
	    
	    
		int x = left_x - 4;
		int y = top_y - 4;
		//플레이 화면테두리 그려주기
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(4f)); //테두리크기 4픽셀이라는 뜻
		g2.drawRect(x, y, WIDTH + 8, HEIGHT + 8); //네모그려주기 rectangle
		
		
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
		x = right_x + (25 + 25 * GamePanel.SIZE);
		y = bottom_y - (100 + 100 * GamePanel.SIZE);
		g2.drawRect(x, y, 100 + 100 * GamePanel.SIZE, 100 + 100 * GamePanel.SIZE);
		
		//다음블록 텍스트표현해주기
		g2.setFont(new Font("Arial", Font.PLAIN, 15 + 15 * GamePanel.SIZE ));
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); // 이건 그냥 글자 쓰는데 렌더링해주는겨
		g2.drawString("NEXT", x + 30 + 30 * GamePanel.SIZE, y + 30 + 30 * GamePanel.SIZE);
		
		GamePanel.battle = false;
		if(GamePanel.battle != true) {
			
			x = right_x + (25 + 25 * GamePanel.SIZE);
			y = bottom_y - (100 + 100 * GamePanel.SIZE);
			//점수보드그려주기
			g2.drawRect(x, top_y, 125 + 125 * GamePanel.SIZE, 200 + 100 * GamePanel.SIZE);
			x += 20 + 20 * GamePanel.SIZE;
			y = top_y + 40 + 50 * GamePanel.SIZE;
			g2.drawString("LEVEL: " + level, x, y); y+= 70;
			g2.drawString("LINES: " + lines, x, y); y+= 70;
			g2.drawString("SCORE: " + score, x, y);
		}
		
			//대전모드용
			x = right_x + (25 + 25 * GamePanel.SIZE);
			g2.drawRect(x, top_y, 125 + 125 * GamePanel.SIZE, 60 + 30 * GamePanel.SIZE);
			y = top_y + 30 + 30 * GamePanel.SIZE;
			x += 20 + 20 * GamePanel.SIZE;
			g2.drawString("SCORE: " + score, x, y);
			
			x = right_x + (25 + 25 * GamePanel.SIZE);
			y = top_y + 70 + 70 * GamePanel.SIZE;
			g2.drawRect(x, y, 90 + 90 * GamePanel.SIZE, 90 + 90 * GamePanel.SIZE);
			
			//공격타일 보여주기.
			g2.setColor(Color.gray);
			y -= 1 + 1 * GamePanel.SIZE;
			
//			for(Block b : PlayManagerTest.staticBlocks_UnderAttack) {
//				int point_x = (b.x - left_x) / GamePanel.blockSize;
//				int point_y = 10 - (bottom_y - b.y)/GamePanel.blockSize + 1;
//				
//				g2.drawString("■", x + (9 + 9 * GamePanel.SIZE) * point_x, y + (9 + 9 * GamePanel.SIZE)* point_y);
//				
//			
//			
//		}
		
				
				
				
				
		//쌓은 블록 (staticblock) 표시해주기
			
		
//		for(int i = 0; i < 3; i++) {
//			//한블록씩가져와서 표시해줍시다.
//			staticBlocks.get(i).draw(g2);
//		}
		
		//현재블록 그려주기 오류없으면 그려주세요
		gameOver = false;
		if((currentMino != null) && (gameOver == false)) {
			currentMino.draw(g2);
		}
		
		//다음블록 표시해주기
		nextMino.draw(g2);
		
		
		//이펙트 그려주기
		effectCounterOn = true;
		if(effectCounterOn) {
			effectCounter++;
			
			g2.setColor(activateS? Color.red : Color.white);
//			for(int i = 0; i< effectY.size(); i++) {
//				g2.fillRect(left_x, effectY.get(i), WIDTH, Block.SIZE);
//			}
			effectCounter = 10;
			if(effectCounter == 10) {
				chanceS = false;
				effectCounterOn = false;
				effectCounter = 0;
				effectY.clear();
			}
			
		}
		
		
		//퍼즈상태 or 게임오버 표시
		g2.setColor(Color.yellow);
		g2.setFont(new Font("Arial", Font.PLAIN, 25 + 25 * GamePanel.SIZE ));
		gameOver = true;
		if(gameOver) {
			x = left_x + 10 + 15 * GamePanel.SIZE;
			y = top_y + 160 + 160 * GamePanel.SIZE;
			g2.drawString((GamePanel.battle ? "You Lose..." : "GAME OVER"), x, y);
		}
		GamePanel.p1Win = true;
		if(GamePanel.p1Win) {
			x = left_x + 10 + 15 * GamePanel.SIZE;
			y = top_y + 160 + 160 * GamePanel.SIZE;
			g2.drawString(("YOU WIN!!!"), x, y);
		}
		
		
		
		
		
		KeyHandler.pausePressed = true;
		if(KeyHandler.pausePressed) {
			x = left_x + 35 + 35 * GamePanel.SIZE;
			y = top_y + 160 + 160 * GamePanel.SIZE;
			g2.drawString("PAUSE", x, y);
			
			//임시용
			g2.drawString("To quit the game", WIDTH / x, y );
			g2.drawString("please press 'q'", WIDTH / x, y + 50 + 50 * GamePanel.SIZE );
			g2.drawString("To return to the menu", WIDTH / x, y + 100 + 100 * GamePanel.SIZE);
			g2.drawString("please press the ESC", WIDTH / x, y + 150 + 150 * GamePanel.SIZE);
		}
		
		
	}
	
	
}

