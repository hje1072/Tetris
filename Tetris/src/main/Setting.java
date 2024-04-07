package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//공사중
public class Setting {
	
	public int pointer_y = 0;
	public int pointer_x = 0;
	
	public boolean keyChange = false;
	
	//뒤에꺼를 앞에다 넣을꺼임
	public void keySet(int[] keySetAfter, int[] keySetBefore) {
		for(int i = 0; i < keySetBefore.length ; i++) {
			keySetAfter[i] = keySetBefore[i];
		}
		
		//그 값으로 사설키세팅 설정.
		keySave(keySetAfter);
	}
	
	public void keySave(int[] keySet) {
		
		
		//키세팅저장csv파일에 유저 키세팅 or 디폴트값 저장할거임.
		
		
    	String currentDirectory = System.getProperty("user.dir");
        String csvFile = currentDirectory + "/src/data/keySetting.csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            // CSV 파일에 데이터 쓰기
        	
        	for(int i = 0; i < GamePanel.keySetting_Origin.length; i ++) {
        		writer.println(keySet[i]);
        	}
        	
            
            
            writer.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        
	}
	
	
	
	//키보드로 입력받는 거 핸들링하는 곳.
	public void keyHandling() {
		
		//방향키조작용
		
		//아래쪽
		if (KeyHandler.downPressed == true) {
			pointer_y += 1;
			if (pointer_y >= 7 ) {
				pointer_y = 0;
			}
			
			//y값에 따른 x값 조정
			switch(pointer_y) {
			
			case 0 : //해상도 섹션은 x값이 3개임
				// 2 -> 3
				
				pointer_x = (int)(pointer_x / 2);
				System.out.println("나 x인데 :"+ pointer_x);
				
				break;
			case 1 : //키보드 섹션은 x값이 5개임
				// 3 -> 5
				
				pointer_x = (int)(pointer_x * 2) ;
				
				break;
			case 2 : //키보드 섹션은 x값 5개임
				if (pointer_x >= 5 ) {
					pointer_x = 0;
				}
				
				break;
			case 3 : //키보드사설키 변경 accept or reject 하는 구간
				//키변화가 있을 때에만 접근가능.
				if(keyChange) {
					
				}
				else {
					pointer_y += 1;
				}
				if (pointer_x >= 2 ) {
					pointer_x = 0;
				}
				
				break;
			default : //나머지는 일단 yes or no 밖에 없으니까 x값은 2개
				pointer_x = 0;
				
				break;
			}
			
			
			
			KeyHandler.downPressed = false;
		}
		
		//위쪽
		if (KeyHandler.upPressed == true) {
			pointer_y -= 1;
			if (pointer_y < 0) {
				pointer_y = 6;
			}
			
			//y값에 따른 x값 조정
			switch(pointer_y) {
			
			case 0 : //해상도 섹션은 x값이 3개임
				//5 -> 3 이라 줄여줌
				
				pointer_x = (int)(pointer_x / 2);
				
				break;
			case 1 : //키보드 섹션은 x값이 5개임
				//5 -> 5 개라 x값 변화 x
				
				break;
			case 2 : //키보드 섹션은 x값 5개임
				//2 -> 5 개라 변화시켜줌
				
				pointer_x = 4;
				
				break;
			case 3 : //키보드사설키 변경 accept or reject 하는 구간
				//키변화가 있을 때에만 접근가능.
				if(keyChange) {
					pointer_x = 0;
				}
				else {
					pointer_y -= 1;
					pointer_x = 4;
				}
				
				break;
			default : //나머지는 일단 yes or no 밖에 없으니까 x값은 2개
				if (pointer_x >= 2 ) {
					pointer_x = 1;
				}
				break;
			}
			
			KeyHandler.upPressed = false;
		}
		
		
		if (KeyHandler.rightPressed == true) {
			pointer_x += 1;
			
			switch(pointer_y) {
			
			case 0 : //해상도 섹션은 x값이 3개임
				
				if (pointer_x >= 3 ) {
					pointer_x = 0;
				}
				break;
			case 1 : //키보드 섹션은 x값이 5개임
				if (pointer_x >= 5 ) {
					pointer_x = 0;
				}
				break;
			case 2 : //키보드 섹션은 x값 5개임
				if (pointer_x >= 5 ) {
					pointer_x = 0;
				}
				break;
			default : //나머지는 일단 yes or no 밖에 없으니까 x값은 2개
				if (pointer_x >= 2 ) {
					pointer_x = 0;
				}
				break;
			}
			
			KeyHandler.rightPressed = false;
		}
		if (KeyHandler.leftPressed == true) {
			pointer_x -= 1;
			
			switch(pointer_y) {
			
			case 0 : //해상도 섹션은 x값이 3개임
				
				if (pointer_x < 0) {
					pointer_x = 2;
				}
				break;
			case 1 : //키보드 섹션은 x값이 5개임
				if (pointer_x < 0) {
					pointer_x = 4;
				}
				break;
			case 2 : //키보드 섹션은 x값 5개임
				if (pointer_x < 0) {
					pointer_x = 4;
				}
				break;
			default : //나머지는 일단 yes or no 밖에 없으니까 x값은 2개
				if (pointer_x < 0) {
					pointer_x = 1;
				}
				break;
			}
			KeyHandler.leftPressed = false;
		}
		
		
		if(KeyHandler.menuPressed) {
			GamePanel.screen = 0;
			
			KeyHandler.menuPressed = false;
		}
		
		//수정필요
		if (KeyHandler.enterPressed == true) {
			KeyHandler.enterPressed = false;
			
			switch(pointer_y) {
			
			
			case 0 :  //해상도
				switch(pointer_x) {
				case 0 :
					// 640 * 480
					GamePanel.SIZE = 0;
					GamePanel.sizeChange = true;
					
					break;
				case 1 : //디폴트값.
					// 1280 * 720
					GamePanel.SIZE = 1;
					GamePanel.sizeChange = true;
					
					break;
				case 2 : 
					//1920 * 1080
					GamePanel.SIZE = 2;
					GamePanel.sizeChange = true;
					
					break;
				}
				break;
				
				
			case 1 : //키보드설정1
				
				GamePanel.userKeyset =true;
				break;
			case 2 : //키보드 설정2
				
				GamePanel.userKeyset =true;
				break;
			
			case 3 : //키보드 세팅 변경 확인용도.
				
				switch(pointer_x) {
				case 0 :
					
					keySet(GamePanel.keySetting, GamePanel.userkeySetting);
					keyChange = false;
					//accept
					break;
				case 1 :
					
					keySet(GamePanel.userkeySetting, GamePanel.keySetting);
					keyChange = false;
					//reject
					break;
				}
				
				break;
			
			
			
			case 4 : //색맹모드
				
				break;
			case 5 : //스크어보드초기화.
				
				
				break;
			case 6 : //기본값으로 초기화.
				GamePanel.SIZE = 1;
				GamePanel.sizeChange = true;
				
				//기본 키보드값으로 초기화.
				keySet(GamePanel.keySetting, GamePanel.keySetting_Origin);
				keySet(GamePanel.userkeySetting, GamePanel.keySetting);
				
				
			}
		
		}
		
	}
	
	public void usertempKey() {
		if(GamePanel.userKey != 0) {
			
			GamePanel.userkeySetting[(pointer_y - 1) * 5 + pointer_x] = GamePanel.userKey;
			
			
			GamePanel.userKey = 0;
			GamePanel.userKeyset = false;
			
			keyChange = false;
			
			for(int i = 0; i < GamePanel.keySetting.length; i ++) {
				if(GamePanel.keySetting[i] != GamePanel.userkeySetting[i]) {
					keyChange = true;
				}
			}
			
		}
	}
	
	//현재 키보드설정이 어떻게 되어있는지 알려주는놈.
	public String whatisKey(int key) {
		String word = "";
		
		//화살표
		if(key == KeyEvent.VK_UP) {
			word = "↑";
		}
		else if(key == KeyEvent.VK_DOWN) {
			word = "↓";
		}
		else if(key == KeyEvent.VK_LEFT) {
			word = "←";
		}
		else if(key == KeyEvent.VK_RIGHT) {
			word = "→";
		}
		
		else if(key == KeyEvent.VK_F1) {
			word = "F1";
		}
		else if(key == KeyEvent.VK_F2) {
			word = "F2";
		}
		else if(key == KeyEvent.VK_F3) {
			word = "F3";
		}
		else if(key == KeyEvent.VK_F4) {
			word = "F4";
		}
		else if(key == KeyEvent.VK_F5) {
			word = "F5";
		}
		else if(key == KeyEvent.VK_F6) {
			word = "F6";
		}
		else if(key == KeyEvent.VK_F7) {
			word = "F7";
		}
		else if(key == KeyEvent.VK_F8) {
			word = "F8";
		}
		else if(key == KeyEvent.VK_F9) {
			word = "F9";
		}
		else if(key == KeyEvent.VK_F10) {
			word = "F10";
		}
		else if(key == KeyEvent.VK_F11) {
			word = "F11";
		}
		else if(key == KeyEvent.VK_F12) {
			word = "F12";
		}
		
		else if(key == KeyEvent.VK_ENTER) {
			word = "ENTER";
		}
		else if(key == KeyEvent.VK_ESCAPE) {
			word = "ESC";
		}
		else if(key == KeyEvent.VK_TAB) {
			word = "TAB";
		}
		else if(key == KeyEvent.VK_SHIFT) {
			word = "SHIFT";
		}
		else if(key == KeyEvent.VK_CONTROL) {
			word = "CTRL";
		}
		else if(key == KeyEvent.VK_ALT) {
			word = "ALT";
		}
		else if(key == KeyEvent.VK_SPACE) {
			word = "SPACE";
		}
		else if(key == KeyEvent.VK_BACK_SPACE) {
			word = "BACK_SP";
		}
		else if(key == KeyEvent.VK_DELETE) {
			word = "DEL";
		}
		else if(key == KeyEvent.VK_HOME) {
			word = "HOME";
		}
		else if(key == KeyEvent.VK_END) {
			word = "END";
		}
		else if(key == KeyEvent.VK_PAGE_UP) {
			word = "PG_UP";
		}
		else if(key == KeyEvent.VK_PAGE_DOWN) {
			word = "PG_DOWN";
		}
		
		
		//특수키
		else if(key == KeyEvent.VK_CAPS_LOCK) {
			word = "CAPS_LOCK";
		}
		else if(key == KeyEvent.VK_NUM_LOCK) {
			word = "NUM_LOCK";
		}
		else if(key == KeyEvent.VK_SCROLL_LOCK) {
			word = "SCROLL_LOCK";
		}
		else if(key == KeyEvent.VK_PRINTSCREEN) {
			word = "PRINTSCREEN";
		}
		else if(key == KeyEvent.VK_PAUSE) {
			word = "PAUSE";
		}
		else if(key == KeyEvent.VK_WINDOWS) {
			word = "WINDOWS";
		}
		else if(key == KeyEvent.VK_CONTEXT_MENU) {
			word = "MENU";
		}
		
		//그외의 일반키들은 char로 바꾸기.
		
		else {
			word = "" + (char)key;
		}
		
		return word;
	}
	
	
	
	public void update() {
		
		if (GamePanel.userKeyset) {
			//System.out.println(GamePanel.userKey);
			
			usertempKey();
			
		}
		
		else {
			keyHandling();
		}
	}
	
	//설정은 너무 할많 이라서 2페이지로 설정
	
	//얘는 1페이지
	public void settingPage1(Graphics2D g2) {
		
		int x = GamePanel.WIDTH / 8;
		int y = GamePanel.HEIGHT / 8;
		
		//해상도 그리기
		x += 5 + 5 * GamePanel.SIZE;
		y += GamePanel.HEIGHT / 15;
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 20 + 20 * GamePanel.SIZE));
		g2.drawString("screen size", x, y);
		
		y += GamePanel.HEIGHT / 15;
		g2.setColor((pointer_y == 0 && pointer_x == 0) ? Color.yellow : Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 30 + 30 * GamePanel.SIZE));
		g2.drawString("small", x + 5 + 5 * GamePanel.SIZE , y);
		
		x = GamePanel.WIDTH * 5 / 13;
		g2.setColor((pointer_y == 0 && pointer_x == 1) ? Color.yellow : Color.white);
		g2.drawString("middle", x + 5 + 5 * GamePanel.SIZE , y);
		
		x = GamePanel.WIDTH * 2 / 3;
		g2.setColor((pointer_y == 0 && pointer_x == 2) ? Color.yellow : Color.white);
		g2.drawString("large", x + 5 + 5 * GamePanel.SIZE , y);
		
		
		//키세팅 그리기
		x = GamePanel.WIDTH / 8 + 5 + 5 * GamePanel.SIZE;
		y = GamePanel.HEIGHT / 8 * 2 + GamePanel.HEIGHT / 15;
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 20 + 20 * GamePanel.SIZE));
		g2.drawString("keysetting", x, y);
		
		
		//키설명용 첫번째줄
		x = GamePanel.WIDTH / 8 + 5 + 5 * GamePanel.SIZE;
		y = GamePanel.HEIGHT / 8 * 2 + GamePanel.HEIGHT / 16 * 3;
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.PLAIN, 20 + 20 * GamePanel.SIZE));
		g2.drawString("[↑]", x, y);
		
		x += GamePanel.WIDTH / 8;
		g2.drawString("[←]", x, y);
		
		x += GamePanel.WIDTH / 8;
		g2.drawString("[↓]", x, y);
		
		x += GamePanel.WIDTH / 8;
		g2.drawString("[→]", x, y);
		
		x += GamePanel.WIDTH / 8;
		g2.drawString("[turn]", x, y);
		
		//키세팅용 첫번째 줄
		x = GamePanel.WIDTH / 8 + 5 + 5 * GamePanel.SIZE;
		y = GamePanel.HEIGHT / 13 * 5 ;
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 15 + 15 * GamePanel.SIZE));
		for(int i = 0; i <= 4; i++) {
			
			//포인터위에 있는 값으면 노란색. 값변경이 있는데 포인터위 값이면 초록색.
			//값변경 없으면 하얀색. 값변경 있으면 초록색
			g2.setColor((pointer_y == 1 && pointer_x == i) ? (GamePanel.userkeySetting[i] == GamePanel.keySetting[i] ? Color.yellow : Color.red) : (GamePanel.userkeySetting[i] == GamePanel.keySetting[i] ? Color.gray : Color.green));
			g2.drawString(whatisKey(GamePanel.userkeySetting[i]), x, y);
			
			x += GamePanel.WIDTH / 8;
		}
		
		//키설명용 두번째줄
		x = GamePanel.WIDTH / 8 + 5 + 5 * GamePanel.SIZE;
		y = GamePanel.HEIGHT / 2 * 10 / 9;
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.PLAIN, 20 + 20 * GamePanel.SIZE));
		g2.drawString("[quit]", x, y);
		
		x += GamePanel.WIDTH / 8;
		g2.drawString("[drop]", x, y);
		
		x += GamePanel.WIDTH / 8;
		g2.drawString("[puase]", x, y);
		
		x += GamePanel.WIDTH / 8;
		g2.drawString("[menu]", x, y);
		
		x += GamePanel.WIDTH / 8;
		g2.drawString("[OK]", x, y);
		
		
		//키세팅용 두번째 줄
		x = GamePanel.WIDTH / 8 + 5 + 5 * GamePanel.SIZE;
		y = GamePanel.HEIGHT / 2;
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 15 + 15 * GamePanel.SIZE));
		
		for(int i = 0; i <= 4; i++) {
			
			//포인터위에 있는 값으면 노란색. 값변경이 있는데 포인터위 값이면 초록색.
			//값변경 없으면 하얀색. 값변경 있으면 초록색
			g2.setColor((pointer_y == 2 && pointer_x == i) ? (GamePanel.userkeySetting[i + 5] == GamePanel.keySetting[i + 5] ? Color.yellow : Color.red) : (GamePanel.userkeySetting[i + 5] == GamePanel.keySetting[i + 5] ? Color.gray : Color.green));
			g2.drawString(whatisKey(GamePanel.userkeySetting[i + 5]), x, y);
			
			x += GamePanel.WIDTH / 8;
		}
		
		
		
		//키세팅 apply cancel 용도
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 20 + 20 * GamePanel.SIZE));
		x -= GamePanel.WIDTH / 8;
		y += GamePanel.HEIGHT / 8;
		g2.setColor(Color.white);
		g2.drawString("KeySet :", x  - GamePanel.WIDTH / 8 , y);
		
		g2.setColor(keyChange ?  ((pointer_x == 0 && pointer_y == 3) ? Color.yellow :Color.white) : Color.gray);
		g2.drawString("Accept", x, y);
		
		g2.setColor(keyChange ?  ((pointer_x == 1 && pointer_y == 3) ? Color.yellow :Color.white) : Color.gray);
		g2.drawString("Reject", x + GamePanel.WIDTH / 8, y);
		
		
		
		//확인용 2
		x = PlayManager.left_x;
		y = PlayManager.bottom_y;
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 20 + 20 * GamePanel.SIZE));
		g2.drawString("height:"+pointer_y+" // and width:" + pointer_x, x, y);
		
	}
	
	//2페이지
	public void settingPage2(Graphics2D g2) {
		int x;
		int y;
		
		//임시 default용
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;
		g2.setColor((pointer_y == 6)? Color.red : Color.gray);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 40 + 40 * GamePanel.SIZE));
		g2.drawString("All Reset!!!", x, y);
		
		//확인용 2
		x = PlayManager.left_x;
		y = PlayManager.bottom_y;
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 20 + 20 * GamePanel.SIZE));
		g2.drawString("height:"+pointer_y+" // and width:" + pointer_x, x, y);
	}
	
	
	public void draw(Graphics2D g2) {
		
		
		int x = GamePanel.WIDTH / 8;
		int y = GamePanel.HEIGHT / 8;
		
		g2.setColor(Color.yellow);
		g2.setStroke(new BasicStroke(4f)); //테두리크기 4픽셀이라는 뜻
		g2.drawRect(x, y, GamePanel.WIDTH * 3 / 4, GamePanel.HEIGHT * 3 / 4); 
		
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 60 + 60 * GamePanel.SIZE));
		g2.drawString("Setting", x, y);
		
		if(pointer_y <= 4) {
			settingPage1(g2);
		}
		else {
			settingPage2(g2);
			
		}
		
	}
	
	
	
}
	
	
	
	
	
	