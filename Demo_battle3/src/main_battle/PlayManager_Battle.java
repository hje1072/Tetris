package main_battle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import main.Calc;
import main.GamePanel;
import main.KeyHandler;
import main.PlayManager;
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
public class PlayManager_Battle extends PlayManager {
	
	//메인플레이게임 테두리
	protected int WIDTH = 200 + 100 * GamePanel.SIZE;
	protected int HEIGHT = 400 + 200 * GamePanel.SIZE;
	public static int left_x;
	public static int right_x;
	public static int top_y;
	public static int bottom_y;
	
	
	//현재블록
	Mino currentMino;
	int MINO_START_X;
	int MINO_START_Y;
	//다음블록
	Mino nextMino;
	int NEXTMINO_X;
	int NEXTMINO_Y;
	public static ArrayList<Block> staticBlocks = new ArrayList<>();
	
	
	// others
	public static int dropInterval = 60; // 60fps로 블록 오토드랍
	public static boolean gameOver;
	
	
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
	private int timer = 0;
	
	
	
	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 
	
	//공격받는거 전용 라인
	public static ArrayList<Block> staticBlocks_UnderAttack = new ArrayList<>();
	public static boolean underAttack = false;
	
	//공격용
	int attackChance = 1;
	
	//공사중
	public PlayManager_Battle() {
		
		//플레이화면 크기
		left_x = (GamePanel.WIDTH / 4) - WIDTH ; // 1280/2 - 360 / 2 == 460
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
		currentMino = pickMino();
		currentMino.battleMino();
		
		currentMino.setXY(MINO_START_X, MINO_START_Y);
		
		nextMino = pickMino();
		nextMino.battleMino();
		
		
		nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
		
	
	
	}
	
	
	public void allReset() {
		
		
		dropInterval = 60;
		level = 1;
		lines = 0;
		score = 0;
		itemChance= ChanceWeight;
		gameOver = false; 
		KeyHandler.pausePressed = false;
		
		
		currentMino = pickMino();
		currentMino.battleMino();
		currentMino.setXY(MINO_START_X, MINO_START_Y);
		
		nextMino = pickMino();
		nextMino.battleMino();
		nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
		
		allDelete();
	}
	
	
	//블록 선택용
	protected Mino pickMino() {
		
		//랜덤블록선택
		Mino mino =null;
		
		//베이직한 모드일 때 무조건 normal에서만 미노 뽑음.
		if(GamePanel.basicMode) {
			mino = pick_normalMino();
		}
		//item chance 가 라인보다 높거나 강제 라인찬스 발동시.
		else if(chanceS) {
			chanceS = false;
			//System.out.println("짠 아이템뽀ㅃ기~");
			mino = pick_itemMino();
		}
		
		else if (lines >= itemChance) {
			//디버깅시 알아서 조절
			itemChance += ChanceWeight;
			chanceS = false;
			
			mino = pick_itemMino();
			
		}
		else {
			mino = pick_normalMino();
			
		}
		
		//테스트위치.
		return mino;
		
	}
	
	protected Mino pick_normalMino() {
		Mino mino = null;
		
		int i = Calc.generateWRN(GamePanel.difficulty);
		
		switch(i) {
		case 0: mino = new Mino_Bar(); break;
		case 1: mino = new Mino_L1(); break;
		case 2: mino = new Mino_L2(); break;
		case 3: mino = new Mino_Square(); break;
		case 4: mino = new Mino_T(); break;
		case 5: mino = new Mino_Z1(); break;
		case 6: mino = new Mino_Z2(); break;
		}
		
		return mino;
	}
	
	//아이템 5개 구현끝.
	protected Mino pick_itemMino() {
		Mino mino = null;
		
		int i = Calc.generateRN(5);
		//System.out.println(i); //디버깅용
		
		switch(i) {
		
		case 0 :
			
			int j = Calc.generateWRN(GamePanel.difficulty);
			int jj = Calc.generateRN(4);
			
			switch(j) {
			case 0: mino = new Mino_Bar(jj); break;
			case 1: mino = new Mino_L1(jj); break;
			case 2: mino = new Mino_L2(jj); break;
			case 3: mino = new Mino_Square(jj); break;
			case 4: mino = new Mino_T(jj); break;
			case 5: mino = new Mino_Z1(jj); break;
			case 6: mino = new Mino_Z2(jj); break;
			}
			
			break;
		
		case 1 : //무게추 아이템
			
			mino = new Mino_Item_Weight(); break;
			
		case 2 : //유령아이템
			
			mino = new Mino_Item_Ghost(); break;
			
		case 3 : //스코어뻥핑아이템 + 아이템 쿨초기화.
			int k = Calc.generateWRN(GamePanel.difficulty);
			int kk = Calc.generateRN(4);
			
			switch(k) {
			case 0: mino = new Mino_Bar(kk, 0); break;
			case 1: mino = new Mino_L1(kk, 0); break;
			case 2: mino = new Mino_L2(kk, 0); break;
			case 3: mino = new Mino_Square(kk, 0); break;
			case 4: mino = new Mino_T(kk, 0); break;
			case 5: mino = new Mino_Z1(kk, 0); break;
			case 6: mino = new Mino_Z2(kk, 0); break;
			}
			
			break;
		
		case 4 : //스왑아이템
			mino = new Mino_Item_Swap(); break;
		}
		
		return mino;
	}
	
	//게임오버 확인용 stactblocks이용
	public boolean isgameOver() {
		
		boolean full = false;
		
		for(int i = 0; i < staticBlocks.size(); i++) {
			//쌓인블록 하나씩 꺼내기 y값 알아내기위함.
			int targetY = staticBlocks.get(i).y;
			
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
	
	
	//업데이트
	public void update() {
		
		timer ++;
		if (timer >= 60) {
			GamePanel.time --;
			timer = 0;
		}
		
		if(currentMino.swap) {
			currentMino.swap = false;
			Mino tempMino = currentMino;
			
			currentMino = nextMino;
			
			nextMino = tempMino;
			nextMino.battleMino();
			
			currentMino.setXY(MINO_START_X, MINO_START_Y);
			nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
		}
		
		//Active상태라서 현재블록으로 계속 플레이해도 되는지 확인.
		if(currentMino.active == false) {
			
			//무게추아이템일 경우 무시해줌.
			if(currentMino.b[0].W == false) {
				
				if(currentMino.b[0].G) {
					staticBlocks.add(currentMino.center);
				}
				
				staticBlocks.add(currentMino.b[0]);
				staticBlocks.add(currentMino.b[1]);
				staticBlocks.add(currentMino.b[2]);
				staticBlocks.add(currentMino.b[3]);
				
			}
			
			
			
			currentMino.deactivating = false;
			
			
			
			if(underAttack) {
				//System.out.println("공습경보");
				underAttack = false;
				underAttack();
			}
			
			//블록을 놓은후 라인이 지워지는지 확인해주자.
			checkDelete();
			
			//테스트용
			//System.out.println(staticBlocks.get(0).x);
			
			
			//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 
			//나중에 들어온 놈만들기.
			oldSet(); //old 블록과 new 블록 구분.
			
			
			//게임오버 확인후 테트리스 게임 종료
			//staticBlocks 상태 보고 결정
			//System.out.println(isgameOver()); 확인용
			gameOver = isgameOver();
			
			if(gameOver) {
				GamePanel.p1Win = true;
			}
			
			//게임오버 아니면 계속 게임 진행.
			//옛것바꾸고, 새거 넣어주고
			currentMino = nextMino;
			currentMino.setXY(MINO_START_X, MINO_START_Y);
			
			nextMino = pickMino();
			nextMino.battleMino();
			nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
			

			
			
		}
		else {
			currentMino.update();
		}
		
	}
	
	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 
	private void oldSet() {
		for(Block b : staticBlocks) {
			b.oldSet();
		}
	}
	
	private void staticUpper(List<Block> Blocks) {
		for(Block b : Blocks) {
			b.y -= Block.SIZE;;
		}
	}
	
	
	
	//라인지우기
	private void checkDelete() {
		
		int x = left_x;
		int y = top_y;
		int blockCount = 0;
		int lineCount = 0;
		
		boolean checkBlock;
		
		//itemS 로서, 점수 증폭 및 아이템 쿨타임감소.
		boolean checkS;
		
		//배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 
		//공격전용라인생성
		ArrayList<Block> staticBlocks_Attack = new ArrayList<>();
		int attackTemp_y = bottom_y - GamePanel.blockSize;
		int attackTemp_x = (GamePanel.SIZE == 0 ? 415 : (GamePanel.SIZE == 1 ? 620: 880));
		
		
		while(x < right_x && y < bottom_y) {
			checkBlock = false;
			
			for(int i = 0; i < staticBlocks.size(); i++) {
				
				
				if(staticBlocks.get(i).x == x && staticBlocks.get(i).y == y) {
					//우리가 쌓은블록을 스캔하는 과정임 만약에 있으면 blockcount를 올려줄것.
					//블록카운트가 한줄(10)을 다채워주면 줄 삭제하면 되겠죠?
					//한줄 올라갈때에는 blockcount를 초기화시켜줘서 다시 반복.
					
					
					checkBlock = true;
					
					//만약에 itemL이 있으면 바로 blockCount의 값을 10 추가. 
					//그러면 무조건 삭제됨
					if(staticBlocks.get(i).L) {
						blockCount += 10;
					}
				}
				
			}
			if (checkBlock) {
				blockCount ++;
			}
			
			
			//우리 게임을 한네모 단위로 다 훑어주는 거임. (맨아래부터 왼쪽에서 오른쪽으로 한줄보고 올라가기)
			x +=Block.SIZE;
			
			if(x == right_x) {
				
				
				
				//현재게임에서는 한줄에 10블록이 들어감
				//그래서 한줄을 훑었는데 블록카운트가 10라는것은 한줄을 완성했다는 뜻.
				
				//ghost블록은 겹쳐서 들어가서 10줄 확인할 때, 중복체크해줘야함.
				if(blockCount >= 10) {
					
					staticUpper(staticBlocks_Attack);
					
					checkS = false;
					
					//System.out.println(blockCount);
					
					effectCounterOn = true;
					effectY.add(y); //n개의 줄의 삭제정보 얻기
					
					for(int i = staticBlocks.size() - 1; i > -1; i--) {
						//해당줄 찾아서 삭제해주기.
						if(staticBlocks.get(i).y == y ) {
							if(staticBlocks.get(i).S) {
								checkS = true;
							}
							
							//배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 
							if(staticBlocks.get(i).old) {
								staticBlocks.get(i).x += attackTemp_x;
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
					
					//배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 //배틀용 

					
					
					if(checkS) {
						chanceS = checkS;
						activateS =checkS;
					}
					
					lineCount++;
					lines++;
					
					//점수얻으면 그에 따라 속도갱신(흠냐륑;ㅁ;)
					if(lines % 10 == 0 && dropInterval > 1) {
						
						setDropInterval();
						
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
			int singleLineScore = 100 * level;
			
			//2줄이상 연속으로 지웠을 시, 점수 제곱으로 부여.
			score += singleLineScore * lineCount * lineCount; 
			
			//itemS 를 지웠을 때 점수 증폭. 밸런스를 위해 제곱은 안함.
			score += (activateS? 500 * level * lineCount : 0);
			
			
			//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 
			if(lineCount >= attackChance ) {
				
				PlayManager.underAttack = true;
				
				for(int i = 1; i <=lineCount; i++) {
					staticUpper(PlayManager.staticBlocks_UnderAttack);
				}
				
				
				
				for(Block b : staticBlocks_Attack) {
					PlayManager.staticBlocks_UnderAttack.add(b);
				}
				
				//공격받는거
				int checklist = bottom_y - GamePanel.blockSize * 10 ; //10번쨰 블록. 즉 가장 높게 차있을 수 있는 위치
				//10줄 벗어나면 없애기
				for(int i = PlayManager.staticBlocks_UnderAttack.size() - 1; i > -1; i--) {
					//해당줄 찾아서 삭제해주기.
					if(PlayManager.staticBlocks_UnderAttack.get(i).y <  checklist) {
						PlayManager.staticBlocks_UnderAttack.remove(i);
					}
					
				}
				
				/*
				for(Block b : staticBlocks_Attack) {
					System.out.println(b.x);
				}
				*/
			}
			
		}
		
	}
	
	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 	//배틀용 
	private void underAttack() {
		int cnt = bottom_y;
		
		for(Block b : staticBlocks_UnderAttack) {
			if (b.y < cnt) {
				cnt = b.y;
			}
		}
		
		cnt = (bottom_y - cnt) / GamePanel.blockSize;
		
		for(int i = 1; i<= cnt ; i++) {
			staticUpper(staticBlocks);
		}
		
		//System.out.println(cnt);
		
		for(Block b : staticBlocks_UnderAttack) {
			staticBlocks.add(b);
		}
		
		//공격받은 블록 초기화
		staticBlocks_UnderAttack = new ArrayList<>();
		
	}
	
	
	//점수 갱신 후 dropInterval 변화.
	private void setDropInterval() {
		
		int speed = (int)(10 * (2 - Calc.difficulty_To_num(GamePanel.difficulty)));
		
		level++;
		if(dropInterval > 12) {
			dropInterval -= speed;
			//System.out.println(Calc.difficulty_To_num(GamePanel.difficulty));
		}
		else {
			dropInterval -= 1;
		}
	}


	//초기화용
	protected void allDelete() {
		
		//쌓은블록초기화
		staticBlocks = new ArrayList<Block>();
		staticBlocks_UnderAttack = new ArrayList<Block>();
	}
	
	
	
	//그리기
	public void draw(Graphics2D g2) {
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
		
		for(Block b : PlayManager_Battle.staticBlocks_UnderAttack) {
			int point_x = (b.x - left_x) / GamePanel.blockSize;
			int point_y = 10 - (bottom_y - b.y)/GamePanel.blockSize + 1;
			
			g2.drawString("■", x + (9 + 9 * GamePanel.SIZE) * point_x, y + (9 + 9 * GamePanel.SIZE)* point_y);
			
		}
		
		
		
		//쌓은 블록 (staticblock) 표시해주기
		for(int i = 0; i < staticBlocks.size(); i++) {
			//한블록씩가져와서 표시해줍시다.
			staticBlocks.get(i).draw(g2);
		}
		
		//현재블록 그려주기 오류없으면 그려주세요
		if((currentMino != null) && (gameOver == false)) {
			currentMino.draw(g2);
		}
		
		//다음블록 표시해주기
		nextMino.draw(g2);
		
		
		//이펙트 그려주기
		if(effectCounterOn) {
			effectCounter++;
			
			g2.setColor(activateS? Color.red : Color.white);
			for(int i = 0; i< effectY.size(); i++) {
				g2.fillRect(left_x, effectY.get(i), WIDTH, Block.SIZE);
			}
			
			if(effectCounter == 10) {
				chanceS = false;
				effectCounterOn = false;
				effectCounter = 0;
				effectY.clear();
			}
			
		}
		
		
		//타이머용
		if (GamePanel.timelimitMode) {
			g2.setColor(Color.white);
			g2.setFont(new Font("Arial", Font.PLAIN, 25 + 25 * GamePanel.SIZE ));
			x = (GamePanel.SIZE == 0? GamePanel.WIDTH / 9 * 4 : GamePanel.WIDTH / 11 * 5) ;
			y =(GamePanel.SIZE == 0? bottom_y : bottom_y + GamePanel.HEIGHT / 15);
			g2.drawString(""+GamePanel.time + "", x, y);
		}
		
		
		//퍼즈상태 or 게임오버 표시
		g2.setColor(Color.yellow);
		g2.setFont(new Font("Arial", Font.PLAIN, 25 + 25 * GamePanel.SIZE ));
		
		if(gameOver || GamePanel.p1Win) {
			x = left_x + 10 + 15 * GamePanel.SIZE;
			y = top_y + 160 + 160 * GamePanel.SIZE;
			g2.drawString((GamePanel.battle ? "You Lose..." : "GAME OVER"), x, y);
			
		}
		
		else if(GamePanel.p2Win) {
			x = left_x + 10 + 15 * GamePanel.SIZE;
			y = top_y + 160 + 160 * GamePanel.SIZE;
			g2.drawString(("YOU WIN!!!"), x, y);
		}
		
		
	}
	
	
}
