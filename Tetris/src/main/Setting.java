package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//공사중
public class Setting {
	
	public int pointer_y = 0;
	public int pointer_x = 0;
	
	
	public void keySet() {
		
		//키세팅저장csv파일 불러오기.
		
    	String currentDirectory = System.getProperty("user.dir");
        String csvFile = currentDirectory + "/src/data/keySetting.csv";
        String line = "";
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // CSV 파일의 각 라인을 쉼표로 분리하여 배열로 저장
                String[] data = line.split(csvSplitBy);
                
                /*
                // 각 열의 데이터를 출력 또는 처리
                for (String datum : data) {
                    System.out.print(datum);
                }
                
                */ //디버깅용
            }
        } catch (IOException e) {
        	
            e.printStackTrace();
        }
        
        
        
        
	}
	
	
	//키보드로 입력받는 거 핸들링하는 곳.
	public void keyHandling() {
		
		//방향키조작용
		if (KeyHandler.downPressed == true) {
			pointer_y += 1;
			if (pointer_y >= 5 ) {
				pointer_y = 0;
			}
			KeyHandler.downPressed = false;
		}
		if (KeyHandler.upPressed == true) {
			pointer_y -= 1;
			if (pointer_y < 0) {
				pointer_y = 3;
			}
			KeyHandler.upPressed = false;
		}
		
		
		if (KeyHandler.rightPressed == true) {
			pointer_x += 1;
			if (pointer_x >= 5 ) {
				pointer_x = 0;
			}
			KeyHandler.rightPressed = false;
		}
		if (KeyHandler.leftPressed == true) {
			pointer_x -= 1;
			if (pointer_x < 0) {
				pointer_x = 4;
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
					
					//accept
					break;
				case 1 :
					
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
			
			if(GamePanel.userKey != 0) {
				
				System.out.println(GamePanel.userKey); //디버깅용
				GamePanel.userkeySetting[(pointer_y - 1) * 5 + pointer_x] = GamePanel.userKey;
				
				//얘도 디버깅용
				System.out.println("---------");
				for (int i = 0 ; i < 10 ; i++) {
					
					System.out.println(GamePanel.userkeySetting[i]);
				}
				
				GamePanel.userKey = 0;
				GamePanel.userKeyset = false;
			}
			
		}
		
		else {
			keyHandling();
		}
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
		
		//키세팅용 첫번째 줄
		y = GamePanel.HEIGHT / 13 * 5 ;
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 15 + 15 * GamePanel.SIZE));
		for(int i = 0; i <= 4; i++) {
			
			//포인터위에 있는 값으면 노란색. 값변경이 있는데 포인터위 값이면 초록색.
			//값변경 없으면 하얀색. 값변경 있으면 초록색
			g2.setColor((pointer_y == 1 && pointer_x == i) ? (GamePanel.userkeySetting[i] == GamePanel.keySetting[i] ? Color.yellow : Color.red) : (GamePanel.userkeySetting[i] == GamePanel.keySetting[i] ? Color.white : Color.green));
			g2.drawString(whatisKey(GamePanel.userkeySetting[i]), x, y);
			
			x += GamePanel.WIDTH / 8;
		}
		
		//키세팅용 두번째 줄
		x = GamePanel.WIDTH / 8 + 5 + 5 * GamePanel.SIZE;
		y = GamePanel.HEIGHT / 2;
		for(int i = 0; i <= 4; i++) {
			
			//포인터위에 있는 값으면 노란색. 값변경이 있는데 포인터위 값이면 초록색.
			//값변경 없으면 하얀색. 값변경 있으면 초록색
			g2.setColor((pointer_y == 2 && pointer_x == i) ? (GamePanel.userkeySetting[i + 5] == GamePanel.keySetting[i + 5] ? Color.yellow : Color.red) : (GamePanel.userkeySetting[i + 5] == GamePanel.keySetting[i + 5] ? Color.white : Color.green));
			g2.drawString(whatisKey(GamePanel.userkeySetting[i + 5]), x, y);
			
			x += GamePanel.WIDTH / 8;
		}
		
		
		//확인용 2
		x = PlayManager.left_x;
		y = PlayManager.bottom_y;
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 20 + 20 * GamePanel.SIZE));
		g2.drawString("height:"+pointer_y+" // and width:" + pointer_x, x, y);
		
		
	}
	
	
	
}
	
	
	
	
	
	