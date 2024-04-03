package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	
	//해상도 변경용도 0 : 640 * 480, 1 : 1280 * 720, 2 : 1920 * 1080
	//디폴드값은 1 
	public static int SIZE = 1;
	public static boolean sizeChange = false;
	
	
	//스크린 사이즈
	public static int WIDTH = 640 + 640 * SIZE;
	public static int HEIGHT = SIZE == 0 ? 480 : 360 + SIZE * 360;
	
	//해상도에 맞는 사이즈설정.
	public static int blockSize = 20 + 10 * SIZE;
	
	
	// 0 : 메뉴, 1 : 게임화면, 2 : 스코어보드, 3 : 설정
	public static int screen = 0; //초기 0번은 메뉴화면
	public static boolean screenRefresh = false; //화면전환용 
	
	
	//게임 루프용 clock
	final int FPS = 60;
	Thread gameThread; //게임 돌아가는 스레드
	PlayManager pm; //게임룰 관리자
	Menu mn; //메뉴
	ScoreBoard sc; //스코어보드
	Setting st; //세팅
	
	
	//키보드세팅용도
	public static int keySetting[] = new int[9];
	//0 = 돌리기, 1 = 내리기, 2 = 왼쪽, 3 = 오른쪽, 4 = 게임중 종료
	//5 = 즉시낙하, 6 = 퍼즈, 7 = 메뉴, 8 = 선택
	
	public void keySetting() {
		keySetting[0] = KeyEvent.VK_UP; 
		keySetting[1] = KeyEvent.VK_DOWN;
		keySetting[2] = KeyEvent.VK_LEFT;
		keySetting[3] = KeyEvent.VK_RIGHT;
		keySetting[4] = KeyEvent.VK_Q;
		keySetting[5] = KeyEvent.VK_SPACE;
		keySetting[6] = KeyEvent.VK_P;
		keySetting[7] = KeyEvent.VK_ESCAPE;
		keySetting[8] = KeyEvent.VK_ENTER;
	}
	
	
	//생성자.
	public GamePanel() {
		
		//윈도우창 세팅
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.black);
		this.setLayout(null);
		
		//키보드입력값 받아주기 세팅
		this.addKeyListener(new KeyHandler());
		this.setFocusable(true);
		
		//기본키세팅 설정
		keySetting();
		
		pm = new PlayManager();
		mn = new Menu();
		sc = new ScoreBoard();
		st = new Setting();
	}
	
	public void asdf() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.black);
		this.setLayout(null);
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
			WIDTH = 640 + 640 * SIZE;
			HEIGHT = SIZE == 0 ? 480 : 360 + SIZE * 360;
			blockSize = 20 + 10 * SIZE;
			
			
			this.resize(new Dimension(WIDTH, HEIGHT));
			changeResolution(WIDTH, HEIGHT);
			
			
			Block.SIZE = blockSize;
			
			pm = new PlayManager();
			mn = new Menu();
			
		}
		
		switch(screen) {
		
		case 0 : //메인메뉴
			mn.update();
			KeyHandler.keyCheck(); break;
		
		case 1 : //게임화면
			
			//q누르면 꺼짐
			if (KeyHandler.quitPressed) {
				System.exit(0);
			}
			//퍼즈 or 게임오버아닌경우 계속진행
			else if(KeyHandler.pausePressed == false && pm.gameOver == false) {
				
				pm.update();
				KeyHandler.keyCheck();
				
			}
			else {
				if(KeyHandler.menuPressed) {
					screen = 0;
					
					//게임 재시작을 대비한 리셋장치.
					pm.allReset();
					KeyHandler.menuPressed = false;
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
