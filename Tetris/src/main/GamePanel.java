package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	
	//스크린 사이즈
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	//현재화면용도
	
	// 0 : 메뉴, 1 : 게임화면
	public static int screen = 0; //초기 0번은 메뉴화면
	public static boolean screenRefresh = false; //화면전환용
	
	
	//게임 루프용 clock
	final int FPS = 60;
	Thread gameThread; //게임 돌아가는 스레드
	PlayManager pm; //게임룰 관리자
	Menu mn; //메뉴
	
	public GamePanel() {
		
		//윈도우창 세팅
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.black);
		this.setLayout(null);
		
		//키보드입력값 받아주기 세팅
		this.addKeyListener(new KeyHandler());
		this.setFocusable(true);
		
		pm = new PlayManager();
		mn = new Menu();
	}
	
	//게임 실행용 이거 실행할 때 자동적으로 run 메소드 불러올거임
	public void launchGame() {
		gameThread = new Thread(this);
		gameThread.start();
		
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
	private void update() {
		
		switch(screen) {
		
		case 0 : //메인메뉴
			KeyHandler.keyCheck();
			mn.update(); break;
		
		case 1 : //게임화면
			
			//q누르면 꺼짐
			if (KeyHandler.QPressed) {
				System.out.println(screen);
				System.exit(0);
			}
			//퍼즈 or 게임오버아닌경우 계속진행
			else if(KeyHandler.pausePressed == false && pm.gameOver == false) {
				pm.update();
				KeyHandler.keyCheck();
			}
			else {
				if(KeyHandler.escPressed) {
					screen = 0;
					
					//게임 재시작을 대비한 리셋장치.
					pm.allReset();
					KeyHandler.escPressed = false;
				}
			}
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
		
		
		//잠시꺼둠 메뉴개발
		
		
		
		switch(screen) {
		case 0 : //메인메뉴
			mn.draw(g2);
			break;
		case 1 : 
			pm.draw(g2);
			break;
		}
		
		
	}
	
	
	
	
	
	
}
