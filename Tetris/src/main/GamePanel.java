package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main_battle.KeyHandler_2;
import main_battle.PlayManager_Battle;
import mino.Block;
import mino.Mino;
public class GamePanel extends JPanel implements Runnable {
	
	//해상도 변경용도 0 : small, 1 : middle0, 2 : large
	//디폴드값은 1 
	public static int SIZE;
	public static boolean sizeChange = false;
	
	//스크린 사이즈
	public static int WIDTH;
	public static int HEIGHT;
	
	//해상도에 맞는 사이즈설정.
	public static int blockSize;
	
	//모드설정 
	public static boolean basicMode;
	
	//배틀모드용
	public static boolean battleMode = false;
	public static boolean battle = false;
	
	public static boolean p1Win = false;
	public static boolean p2Win = false;
	
	//대전 타이머용
	public static boolean timelimitMode = false;
	public static boolean timelimit = false;
	public static int time;
	
	// 0 : 메뉴, 1 : 게임화면, 2 : 스코어보드, 3 : 설정
	public static int screen = 0; //초기 0번은 메뉴화면
	public static boolean screenRefresh = false; //화면전환용 
	
	//게임 루프용 clock
	final int FPS = 60;
	Thread gameThread; //게임 돌아가는 스레드
	PlayManager pm; //게임룰 관리자
	PlayManager pm2;
	
	Menu mn; //메뉴
	ScoreBoard sc; //스코어보드
	Setting st; //세팅
	
	//키보드세팅용도
	public static int keySetting[] = new int[10];
	//0 = 위, 1 = 왼쪽, 2 = 중간, 3 = 오른쪽, 4 = 돌리기
	//5 = 게임중 종료, 6 = 즉시낙하, 7 = 퍼즈, 8 = 메뉴, 9 = 확인
	
	//기본값용도
	public static int keySetting_Origin[] = new int[10];
	
	//2인모드 키보드 세팅용도
	public static int keySetting_battle[] = new int[10];
	
	//기본값용도도
	public static int keySetting_battle_Origin[] = new int[10];

	public static int userkeySetting_battle[] = new int[10];
	public static boolean userKeyset_battle = false;
	public static int userKey_battle = 0;
	
	//유저키세팅 받는거 확인용도
	public static int userkeySetting[] = new int[10];
	
	public static boolean userKeyset = false;
	public static int userKey = 0;
	
	//색맹모드 확인용.
	public static int colorMode;
	
	private String readFile(String file) {
		return "src/data/" + file;
	}
	
	private void readDFile(String file) {
		try (BufferedReader br = new BufferedReader(new FileReader(readFile(file)))) {
        	difficulty = br.readLine();
            
        } catch (IOException e) {
        	
            e.printStackTrace();
        }
	}
	
	private int readCFile(String file) {
		String num = "";
        int size = 1;
		try (BufferedReader br = new BufferedReader(new FileReader(readFile(file)))) {
			num = br.readLine();
            size = Integer.parseInt(num);
            
        } catch (IOException e) {
        	
            e.printStackTrace();
        }
		
		return size;
	}
	
	private void readKFile(String file, boolean bt) {
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(readFile(file)))) {
        	int i = 0;
        	int num ;
        	if (bt) {
        		while ((line = br.readLine()) != null) {
        			num = Integer.parseInt(line);
        			keySetting_battle[i] = num;
        			i++;
        		}
        		for(i = 0; i < 10 ; i++)
        			userkeySetting_battle[i] = keySetting_battle[i];
        	}
        	else {
        		 while ((line = br.readLine()) != null) {
                 	num = Integer.parseInt(line);
                 	keySetting[i] = num;
                 	i++;
                 }
                 for(i = 0; i < 10 ; i++)
                 	userkeySetting[i] = keySetting[i];
        	}
            //userkeySetting = keySetting;
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	//난이도 불러오기
	public void readDifficulty() {
        readDFile("difficulty.csv");
	}
	
	//색깔불러오기.
	public int readcolor() {
        return readCFile("colorBlind.csv");
	}
	
	//해상도 불러오기
	public int readSize() {
        return readCFile("size.csv");
	}
	
	//저장해놓은 키설정 불러오기
	public void keySetting() {
		//키세팅저장 csv파일 불러오기.
        readKFile("keySetting.csv", false);
        
        //확인용
        //System.out.println("=================="); // 줄 바꿈
	}
	
	//저장해놓은 키설정 불러오기(대전몯)
	public void keySetting_battle() {
		//키세팅저장 csv파일 불러오기.
		readKFile("keySetting_battle.csv", true);
		
        //확인용
        //System.out.println("=================="); // 줄 바꿈
	}
	
	//점수기입용
	public static boolean enteringScore;
	public static String score = "0";
	public static String difficulty;
	public static Mino currentMino;
	
	//생성자.
	public GamePanel() {
		//난이도 설정.
		readDifficulty();
		
		//사이즈설정.
		SIZE = readSize();
		blockSize = 20 + 10 * SIZE;
		Block.SIZE = blockSize;
		WIDTH = SIZE == 0 ? 860 : 640 + 640 * SIZE;
		HEIGHT = SIZE == 0 ? 480 : 360 + SIZE * 360;
		
		//색맹설정.
		colorMode = readcolor();
		
		//윈도우창 세팅
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.black);
		this.setLayout(null);
		
		//키보드입력값 받아주기 세팅
		this.setFocusable(true);
		this.addKeyListener(new KeyHandler());
		this.addKeyListener(new KeyHandler_2());
		
		//기본키세팅 설정
		keySetting();
		keySetting_battle();
		
		//디폴트값용 키세팅
		keySetting_Origin[0] = KeyEvent.VK_UP;
		keySetting_Origin[1] = KeyEvent.VK_LEFT;
		keySetting_Origin[2] = KeyEvent.VK_DOWN;
		keySetting_Origin[3] = KeyEvent.VK_RIGHT;
		keySetting_Origin[4] = KeyEvent.VK_UP; //돌리기
		
		keySetting_Origin[5] = KeyEvent.VK_Q; //종료
		keySetting_Origin[6] = KeyEvent.VK_SPACE; //한번에 쾅
		keySetting_Origin[7] = KeyEvent.VK_P; //퍼즈
		keySetting_Origin[8] = KeyEvent.VK_ESCAPE; //메뉴
		keySetting_Origin[9] = KeyEvent.VK_ENTER; //확인
		
		//디폴트값용 키세팅 (대전모드)
		keySetting_battle_Origin[0] = KeyEvent.VK_LEFT;
		keySetting_battle_Origin[1] = KeyEvent.VK_DOWN;
		keySetting_battle_Origin[2] = KeyEvent.VK_RIGHT;
		keySetting_battle_Origin[3] = KeyEvent.VK_UP;
		keySetting_battle_Origin[4] = KeyEvent.VK_PERIOD; //돌리기
		
		keySetting_battle_Origin[5] = KeyEvent.VK_A; //종료
		keySetting_battle_Origin[6] = KeyEvent.VK_S; //한번에 쾅
		keySetting_battle_Origin[7] = KeyEvent.VK_D; //퍼즈
		keySetting_battle_Origin[8] = KeyEvent.VK_W; //메뉴
		keySetting_battle_Origin[9] = KeyEvent.VK_V; //확인
		
		pm = new PlayManager();
		mn = new Menu();
		sc = new ScoreBoard();
		st = new Setting();
	}
	
	//대전모드용
	public void startGame_Battle() {
		battleMode = false;
		battle = true;
		
		pm = new PlayManager();
		pm2 = new PlayManager_Battle();	
	}
	
	//게임 실행용 이거 실행할 때 자동적으로 run 메소드 불러올거임
	public void launchGame() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
    // 해상도 변경 메서드
    public void changeResolution(int width, int height) {
        JFrame frame = (JFrame) getTopLevelAncestor();
        if (frame != null) {
            frame.setPreferredSize(new Dimension(width + 16, height + 39));
            frame.setBackground(Color.black);
            frame.setLayout(null);
            frame.pack();
        }
    }

	@Override
	public void run() {
		//게임루프 과정
		double drawInterval = 1000000000 / FPS ;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		//실행되는한 계속 움직이기
		while(gameThread != null) {
			
			//현재시간
			currentTime = System.nanoTime();
			
			//델타값을 조정
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			//델타값이 일정값에 도달할 때 마다 정보 업데이트 및 업데이트 적용
			//현재 60 fps로 업데이트
			if(delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	
	//게임내용 업데이트용
	@SuppressWarnings("deprecation") //해상도 바꾸기.
	//비권장기능사용해버려서 일단 무시하라고 함
	//비권장 해상도 어떻게 바꿀 지몰라서 비권장기능쓴거라 frfactoring 가능하면 바꿔주셈
	private void update() {
		if (sizeChange) {
			sizeChange = false;
			
			//해상도 변경 업데이트
			WIDTH = SIZE == 0 ? 860 : 640 + 640 * SIZE;
			HEIGHT = SIZE == 0 ? 480 : 360 + SIZE * 360;
			blockSize = 20 + 10 * SIZE;
			
			this.resize(new Dimension(WIDTH, HEIGHT));
			changeResolution(WIDTH, HEIGHT);
			
			Block.SIZE = blockSize;
			pm = new PlayManager();
			mn = new Menu();
		}
		
		if(battleMode)
			startGame_Battle();
		
		switch(screen) {
		
		case 0 : //메인메뉴
			mn.update();
			KeyHandler.keyCheck();
			break;
		
		case 1 : //게임화면
			
			//q누르면 꺼짐
			if (KeyHandler.quitPressed)
				System.exit(0);

			//퍼즈 or 게임오버아닌경우 계속진행
			else if(KeyHandler.pausePressed == false && pm.gameOver == false && p1Win == false) {
				
				pm.update();
				
				if(battle) {pm2.update();}
				
				//System.out.println(p1Win);
				
				if(timelimitMode) {
					if(time <= 0) {
						
						if(pm.score < PlayManager_Battle.score) {
							pm.gameOver = true;
							p2Win = true;
						}
						else
							p1Win = true;
					}
				}
				
				KeyHandler.keyCheck();
				
			}
			else {
				
				if(battle) {
					if(pm.gameOver || p1Win) {
						//결과만 보고 끝.
						if(KeyHandler.enterPressed || KeyHandler.menuPressed) {
							KeyHandler.enterPressed = false; KeyHandler.menuPressed = false;
							screen = 0;
							
							pm.allReset();
							pm2.allReset();
							battle = false; p1Win = false; p2Win = false; timelimitMode = false;
							
							pm = new PlayManager();
						}
						
						KeyHandler.keyCheck();
					}
				}

				//게임오버인경우 메뉴로 안가는 경우 점수기입하러 감.
				if(KeyHandler.enterPressed && pm.gameOver == true) {
					KeyHandler.enterPressed = false;
					screen = 2;
					
					//점수기입용
					enteringScore = true;
					score = Integer.toString(pm.score);
					
					pm.allReset();
					if(battle) pm2.allReset();
					battle = false; p1Win = false; p2Win = false; timelimitMode = false;
				}
				
				if(KeyHandler.menuPressed) {
					KeyHandler.menuPressed = false;
					screen = 0;
					
					pm.allReset();
					if(battle) pm2.allReset();
					battle = false; p1Win = false; p2Win = false; timelimitMode = false;
					
				}
				KeyHandler.keyCheck();
			}
			break;
		case 2 : //스코어보드
			sc.update();
			KeyHandler.keyCheck();
			break;
		
		case 3 : //설정
			st.update();
			KeyHandler.keyCheck();
			break;
		}
	}
	
	//업데이트를 기반으로 정보들을 적용해주기
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//g를 받아서 2d그림으로 바꿔주기
		Graphics2D g2 = (Graphics2D)g;
		
		//스크린화면 바뀔시 한번 화면 초기화시켜주기.
		if(screenRefresh) {
			screenRefresh = false;
			
			g2.setColor(Color.black);
			g2.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
		
		switch(screen) {
		case 0 : //메인메뉴
			mn.draw(g2);
			break;
		case 1 : //게임화면
			if(battle) {pm2.draw(g2);}
			pm.draw(g2);
			break;
		case 2 : //스코어보드
			sc.draw(g2);
			break;
		case 3 : //설정
			st.draw(g2);
			break;
		}
	}
}