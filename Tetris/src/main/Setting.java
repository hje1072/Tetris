package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//공사중
public class Setting {
	
	//대전모드 키세팅용
	public boolean battlekeySet = false;
	
	public boolean keyChange_battle = false;
	
	//현재 pointer_y == 4 에 해당하는 부분은 비워놨음. 만일을 위한 키세팅 추가가 있을까봐 그런거니까 알아두셈.
	public int pointer_y = 0;
	public int pointer_x = 0;
	
	public boolean keyChange = false;
	boolean def_Reset = false;
	boolean score_Reset = false;
	
	private void getFile(int header, String name) {
		String csvFile = "src/data/" + name;
		
		try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            // CSV 파일에 데이터 쓰기
            writer.println(header); // 헤더
            
        } catch (IOException e) {

            e.printStackTrace();
        }
	}
	
	private void getFile(String header, String name) {
		String csvFile = "src/data/" + name;
		
		try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            // CSV 파일에 데이터 쓰기
            writer.println(header); // 헤더
            
        } catch (IOException e) {

            e.printStackTrace();
        }
	}
	
	//색맹저장용
	public void colorSet(int colorMode) {
        getFile(colorMode, "colorBlind.csv");
	}
	
	//난이도 저장용
	public void difficultySet(String str) {
		getFile(str, "difficulty.csv");
	}
	
	
	//사이즈저장용
	public void sizeSet(int size) {
		getFile(size, "size.csv");
	}
	
	
	//키세팅용
	//뒤에꺼를 앞에다 넣을꺼임
	public void keySet(int[] keySetAfter, int[] keySetBefore, boolean p1) {
		for(int i = 0; i < keySetBefore.length ; i++) {
			keySetAfter[i] = keySetBefore[i];
		}
		
		//그 값으로 사설키세팅 설정.
		keySave(keySetAfter, p1);
	}
	
	public void keySave(int[] keySet ,boolean p1) {
		
		//키세팅저장csv파일에 유저 키세팅 or 디폴트값 저장할거임.	
		String csvFile = "";
        if (p1) {
        	csvFile = "src/data/keySetting.csv";
        }
        else {
        	csvFile = "src/data/keySetting_battle.csv";
        }
        
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
		
		if(def_Reset || score_Reset) {
			//오른쪽
			rightPress();
			
			//왼쪽
			leftPress();
			
			//확인버튼
			enterPress();
		}
		else {
			//아래
			downPress();
			
			//위쪽
			upPress();
			
			//오른쪽
			rightPress();
			
			//왼쪽
			leftPress();
			
			//확인버튼
			enterPress();
		}
		
		//메뉴로가기
		menuPress();
		
	}

	private void menuPress() {
		if(KeyHandler.menuPressed) {
			
			if(def_Reset || score_Reset || battlekeySet) {
				def_Reset = false;
				score_Reset = false;
				battlekeySet = false;
				
				pointer_x = 0; pointer_y = 0;
			}
			
			else {
				GamePanel.screen = 0;
				pointer_x = 0; pointer_y = 0;
			}
			
			KeyHandler.menuPressed = false;
		}
	}
	
	//확인버튼
	private void enterPress() {
		
		//수정필요
		if (KeyHandler.enterPressed == true) {
			KeyHandler.enterPressed = false;
			
			switch(pointer_y) {
			
			case 0 :  //레벨
				switch(pointer_x) {
				case 0 :// 이지
					setDifficulty("easy");
					//System.out.println(GamePanel.difficulty); //연습용
					break;
				case 1 : //노말모드
					// 1280 * 720
					setDifficulty("normal");
					//System.out.println(GamePanel.difficulty);//연습용
					break;
				case 2 : 
					setDifficulty("hard");
					//System.out.println(GamePanel.difficulty);//연습용
					break;
				}
				break;
				
			case 1 : //키보드설정1
				
				GamePanel.userKeyset = true;
				break;
			case 2 : //키보드 설정2
				
				GamePanel.userKeyset = true;
				break;
			
			case 3 : //키보드 세팅 변경 확인용도.
				
				switch(pointer_x) {
				case 0 :
					
					keySet(GamePanel.keySetting, GamePanel.userkeySetting, true);
					keyChange = false;
					//accept
					break;
				case 1 :
					
					keySet(GamePanel.userkeySetting, GamePanel.keySetting, true);
					keyChange = false;
					//reject
					break;
				}
				
				break;
			
			case 4 : //대전모드 키세팅
				
				pointer_y = 101;
				battlekeySet = true;
				break;
				
			case 5 : //해상도
				switch(pointer_x) {
				case 0 :
					// 640 * 480
					setSize(0);
					break;
				case 1 : //디폴트값.
					// 1280 * 720
					setSize(1);
					break;
				case 2 : 
					//1920 * 1080
					setSize(2);
					break;
				}
				break;
			
			case 6 : //색맹모드
				
				switch(pointer_x) {
				case 0 : //디폴트값.
					setColor(0);
					break;
				case 1 : //적록생맹
					setColor(1);
					break;
				case 2 : //청황색맹
					setColor(2);
					break;
				}
				break;
				
			case 7 : //스코어보드초기화.
				
				if (score_Reset) {
					switch(pointer_x) {
					case 0 :
						score_Reset = false;
						
						ScoreBoard.removescoreBoard();
						ScoreBoard.readscoreBoard();
						break;
					case 1 :
						score_Reset = false;
					} 
				}
				
				else {
					score_Reset = true;
				}
				
				break;
			
			case 8 : //기본값으로 초기화.
				if (def_Reset) {
					switch(pointer_x) {
					case 0 :
						//사이즈 초기화.
						def_Reset = false;
						setSize(1);
						
						//색맹 모드 초기화
						setColor(0);
						
						//난이도 초기화.
						setDifficulty("normal");
						
						//기본 키보드값으로 초기화.
						keySet(GamePanel.keySetting, GamePanel.keySetting_Origin, true);
						keySet(GamePanel.userkeySetting, GamePanel.keySetting, true);
						keySet(GamePanel.keySetting_battle, GamePanel.keySetting_battle_Origin, false);
						keySet(GamePanel.userkeySetting_battle, GamePanel.keySetting_battle, false);
						
						break;
					case 1 :
						def_Reset = false;
					}
					
					def_Reset = false;
				}
				
				else {
					def_Reset = true;
				}
				break;
			
			case 101 : //키보드설정1
				
				GamePanel.userKeyset_battle =true;
				break;
			case 102 : //키보드 설정2
				
				GamePanel.userKeyset_battle =true;
				break;
			
			case 103 :
				
				switch(pointer_x) {
				case 0 :
					
					keySet(GamePanel.keySetting, GamePanel.userkeySetting, false);
					keyChange = false;
					//accept
					break;
				case 1 :
					
					keySet(GamePanel.userkeySetting, GamePanel.keySetting, false);
					keyChange = false;
					//reject
					break;
				}
				
				break;
			}
		
		}
		
	}

	private void setColor(int num) {
		GamePanel.colorMode = num;
		colorSet(GamePanel.colorMode);
	}

	private void setSize(int num) {
		GamePanel.SIZE = num;
		GamePanel.sizeChange = true;
		sizeSet(GamePanel.SIZE);
	}

	private void setDifficulty(String diff) {
		GamePanel.difficulty = diff;
		difficultySet(diff);
	}
	
	private void setX(int num) {
		if (pointer_x < 0) {
			pointer_x = num;
		}
	}

	//왼쪽
	private void leftPress() {
		if (KeyHandler.leftPressed == true) {
			pointer_x -= 1;
			
			switch(pointer_y) {
			
			case 0 : //해상도 섹션은 x값이 3개임
				setX(2);
				break;
			case 1 : //키보드 섹션은 x값이 5개임
				setX(4);
				break;
			case 2 : //키보드 섹션은 x값 5개임
				setX(4);
				break;
			case 5 : //해상도섹션
				setX(2);
				break;
			case 6 : //색맹모드섹션
				setX(2);
				break;
			case 101 : //키보드 섹션은 x값이 5개임
				setX(4);
				break;
			case 102 : //키보드 섹션은 x값 5개임
				setX(4);
				break;
			default : //나머지는 일단 yes or no 밖에 없으니까 x값은 2개
				setX(1);
				break;
			}
			KeyHandler.leftPressed = false;
		}
	}
	
	private void setX1(int num) {
		if (pointer_x >= num )
			pointer_x = 0;
	}
	
	//오른쪽
	private void rightPress() {
		
		if (KeyHandler.rightPressed == true) {
			pointer_x += 1;
			
			switch(pointer_y) {
			
			case 0 : //난이도 조절은 x값이 3개임
				setX1(3);
				break;
			case 1 : //키보드 섹션은 x값이 5개임
				setX1(5);
				break;
			case 2 : //키보드 섹션은 x값 5개임
				setX1(5);
				break;
			case 5 : //해상도 섹션
				setX1(3);
				break;
			case 6 : //색맹모드 섹션
				setX1(3);
				break;
			case 101 : //키보드 섹션은 x값이 5개임
				setX1(5);
				break;
			case 102 : //키보드 섹션은 x값 5개임
				setX1(5);
				break;
			
			default : //나머지는 일단 yes or no 밖에 없으니까 x값은 2개
				setX1(2);
				break;
			}
			KeyHandler.rightPressed = false;
		}
	}

	//위
	private void upPress() {
		if (KeyHandler.upPressed == true) {
			pointer_y -= 1;
			if (pointer_y < 0)
				pointer_y = 8;
			
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
				if(keyChange)
					pointer_x = 0;
				else {
					pointer_y -= 1;
					pointer_x = 4;
				}
				break;
				
			case 4 : 
				//키변화가 있을 때에만 접근가능.
				if(keyChange)
					pointer_x = 0;
				else 
					pointer_x = 0;
				
				break;
			case 100 :
				
				if(keyChange_battle)
					pointer_y = 103;
				else 
					pointer_y = 102;
				
			default : //나머지는 일단 yes or no 밖에 없으니까 x값은 2개
				
				if(pointer_y >= 101 && pointer_y <= 102) {}
				else if (pointer_x >= 2 )
					pointer_x = 1;
				
				break;
			}
			KeyHandler.upPressed = false;
		}
	}

	//아래
	private void downPress() {
		//아래쪽
		if (KeyHandler.downPressed == true) {
			pointer_y += 1;
			if (pointer_y >= 9 ) {
				if (pointer_y >= 100) {}
				else {pointer_y = 0;}
			}
			
			//y값에 따른 x값 조정
			switch(pointer_y) {
			
			case 0 : //해상도 섹션은 x값이 3개임
				// 2 -> 3
				pointer_x = (int)(pointer_x / 2);
				//System.out.println("나 x인데 :"+ pointer_x);
				break;
			case 1 : //키보드 섹션은 x값이 5개임
				// 3 -> 5
				pointer_x = (int)(pointer_x * 2);
				break;
			case 2 : //키보드 섹션은 x값 5개임
				if (pointer_x >= 5 )
					pointer_x = 0;
		
				break;
			case 3 : //키보드사설키 변경 accept or reject 하는 구간
				//키변화가 있을 때에만 접근가능.
				if(keyChange) {	}
				else
					pointer_y += 1;
				
				if (pointer_x >= 2)
					pointer_x = 0;
				
				break;
			case 4 : //나중에 쓸 수 있을 까봐 만들어 놓은거 알아두기.
				pointer_x = 0;
				break;
			case 104 : 
				pointer_y = 101;
				break;
			case 103 :
				if(keyChange_battle) {}
				else
					pointer_y = 101;
			
			default : //나머지는 일단 yes or no 밖에 없으니까 x값은 2개
				if(pointer_y >= 101 && pointer_y <= 102) {}
				else
					pointer_x = 0;
				break;
			}
			KeyHandler.downPressed = false;
		}
	}

	//일시적인 키저장. 적용할지 말지 결정가능.
	public void usertempKey() {
		if(GamePanel.userKey != 0) {
			
			GamePanel.userkeySetting[(pointer_y - 1) * 5 + pointer_x] = GamePanel.userKey;
			
			
			GamePanel.userKey = 0;
			GamePanel.userKeyset = false;
			
			keyChange = false;
			
			for(int i = 0; i < GamePanel.keySetting.length; i ++)
				if(GamePanel.keySetting[i] != GamePanel.userkeySetting[i])
					keyChange = true;
		}
	}
	
	//일시적인 키저장. 대전모드용
	public void usertempKey_battle() {
		if(GamePanel.userKey_battle != 0) {
			
			GamePanel.userkeySetting_battle[(pointer_y - 101) * 5 + pointer_x] = GamePanel.userKey_battle;
			
			
			GamePanel.userKey_battle = 0;
			GamePanel.userKeyset_battle = false;
			
			keyChange_battle = false;
			
			for(int i = 0; i < GamePanel.keySetting_battle.length; i ++) {
				if(GamePanel.keySetting_battle[i] != GamePanel.userkeySetting_battle[i]) {
					keyChange_battle = true;
				}
			}
			
		}
	}
	
	//현재 키보드설정이 어떻게 되어있는지 알려주는놈.
	public static String whatisKey(int key) {
		String word = getArrowKey(key);
		if (word.isEmpty())
			word = getFunctionKey(key);
		if (word.isEmpty())
			word = getSpecialKey(key);
		//그외의 일반키들은 char로 바꾸기.
		if (word.isEmpty())
			word = "" + (char)key;
		
		return word;
	}
	
	public static String getArrowKey(int key) {
		switch(key) {
		case KeyEvent.VK_UP:
			return "↑";
		case KeyEvent.VK_DOWN:
			return "↓";
		case KeyEvent.VK_LEFT:
			return "←";
		case KeyEvent.VK_RIGHT:
			return "→";
		default:
			return "";
		}
	}
	
	public static String getFunctionKey(int key) {
		if (key >= KeyEvent.VK_F1 && key <= KeyEvent.VK_F12)
			return "F" + (key - KeyEvent.VK_F1 + 1);
		return "";
	}
	
	public static String getSpecialKey(int key) {
		switch(key) {
		case KeyEvent.VK_ENTER:
			return "ENTER";
		case KeyEvent.VK_ESCAPE:
			return "ESC";
		case KeyEvent.VK_TAB:
			return "TAB";
		case KeyEvent.VK_SHIFT:
			return "SHIFT";
		case KeyEvent.VK_CONTROL:
			return "CTRL";
		case KeyEvent.VK_ALT:
			return "ALT";
		case KeyEvent.VK_SPACE:
			return "SPACE";
		case KeyEvent.VK_BACK_SPACE:
			return "BACK_SP";
		case KeyEvent.VK_DELETE:
			return "DEL";
		case KeyEvent.VK_HOME:
			return "HOME";
		case KeyEvent.VK_END:
			return "END";
		case KeyEvent.VK_PAGE_UP:
			return "PG_UP";
		case KeyEvent.VK_PAGE_DOWN:
			return "PG_DOWN";
		case KeyEvent.VK_CAPS_LOCK:
			return "CAPS_LOCK";
		case KeyEvent.VK_NUM_LOCK:
			return "NUM_LOCK";
		case KeyEvent.VK_SCROLL_LOCK:
			return "SCROLL_LOCK";
		case KeyEvent.VK_PRINTSCREEN:
			return "PRINTSCREEN";
		case KeyEvent.VK_PAUSE:
			return "PAUSE";
		case KeyEvent.VK_WINDOWS:
			return "WINDOWS";
		case KeyEvent.VK_CONTEXT_MENU:
			return "MENU";
		default:
			return "";
		}
	}
	
	public void update() {
		if (GamePanel.userKeyset)
			usertempKey();
		else if(GamePanel.userKeyset_battle)
			//System.out.println("asdf");
			usertempKey_battle();
		else
			keyHandling();
	}
	
	private void drawSetting(Graphics2D g2, int x, int y, int size, String text) {
		g2.setFont(new Font("Times New Roman", Font.ITALIC, size + size * GamePanel.SIZE));
		g2.drawString(text, x, y);
	}
	
	private void drawSetting(Graphics2D g2, int x, int y, int size, String text, String font) {
		g2.setFont(new Font(font, Font.ITALIC, size + size * GamePanel.SIZE));
		g2.drawString(text, x, y);
	}
	
	private void setColor(Graphics2D g2, int y1, int x1, int x, int y, int size, String text) {
		g2.setColor((pointer_y == y1 && pointer_x == x1) ? Color.yellow : Color.white);
		drawSetting(g2, x, y, size, text);
	}
	
	private void setColor(Graphics2D g2, int y1, int x1, int x, int y, int size, String text, String font) {
		g2.setColor((pointer_y == y1 && pointer_x == x1) ? Color.yellow : Color.white);
		drawSetting(g2, x, y, size, text, font);
	}
	
	private int keySetting(Graphics2D g2, int x, int y, String[] key) {
		x = GamePanel.WIDTH / 8 + 5 + 5 * GamePanel.SIZE;
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.PLAIN, 20 + 20 * GamePanel.SIZE));
		g2.drawString(key[0], x, y);
		
		for (int i = 1; i < 5; i++) {
			x += GamePanel.WIDTH / 8;
			g2.drawString(key[i], x, y);
		}
		
		return x;
	} 
	
	private int setKey(Graphics2D g2, int x, int y, int num) {
		x = GamePanel.WIDTH / 8 + 5 + 5 * GamePanel.SIZE;
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 15 + 15 * GamePanel.SIZE));
		for(int i = 0; i <= 4; i++) {
			
			//포인터위에 있는 값으면 노란색. 값변경이 있는데 포인터위 값이면 초록색.
			//값변경 없으면 하얀색. 값변경 있으면 초록색
			if (num % 2 == 1) {
				g2.setColor((pointer_y == num && pointer_x == i) ? (GamePanel.userkeySetting[i] == GamePanel.keySetting[i] ? Color.yellow : Color.red) : (GamePanel.userkeySetting[i] == GamePanel.keySetting[i] ? Color.gray : Color.green));
				g2.drawString(whatisKey(GamePanel.userkeySetting[i]), x, y);
			}
			else {
				g2.setColor((pointer_y == num && pointer_x == i) ? (GamePanel.userkeySetting[i + 5] == GamePanel.keySetting[i + 5] ? Color.yellow : Color.red) : (GamePanel.userkeySetting[i + 5] == GamePanel.keySetting[i + 5] ? Color.gray : Color.green));
				g2.drawString(whatisKey(GamePanel.userkeySetting[i + 5]), x, y);
			}
			x += GamePanel.WIDTH / 8;
		}
		return x;
	}
	
	private void acRj(Graphics2D g2, int x, int y, int num) {
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 20 + 20 * GamePanel.SIZE));
		g2.setColor(Color.white);
		g2.drawString("KeySet :", x - GamePanel.WIDTH / 8 , y);
		
		g2.setColor(keyChange ?  ((pointer_x == 0 && pointer_y == num) ? Color.yellow :Color.white) : Color.gray);
		g2.drawString("Accept", x, y);
		
		g2.setColor(keyChange ?  ((pointer_x == 1 && pointer_y == num) ? Color.yellow :Color.white) : Color.gray);
		g2.drawString("Reject", x + GamePanel.WIDTH / 8, y);
	}
	
	//설정은 너무 할많 이라서 2페이지로 설정
	//얘는 1페이지
	public void settingPage1(Graphics2D g2) {
		
		int x = GamePanel.WIDTH / 8;
		int y = GamePanel.HEIGHT / 8;
		
		//레벨설정.
		x += 5 + 5 * GamePanel.SIZE;
		y += GamePanel.HEIGHT / 15;
		g2.setColor(Color.white);
		drawSetting(g2, x, y, 20, "difficulty");
		
		y += GamePanel.HEIGHT / 15;
		setColor(g2, 0, 0, x + 5 + 5 * GamePanel.SIZE, y, 30, "easy");
		
		x = GamePanel.WIDTH * 5 / 15;
		setColor(g2, 0, 1, x + 5 + 5 * GamePanel.SIZE, y, 30, "normal");
		
		x = GamePanel.WIDTH * 3 / 5;
		setColor(g2, 0, 2, x + 5 + 5 * GamePanel.SIZE, y, 30, "hard");
		
		x += GamePanel.WIDTH / 8;
		g2.setColor(Color.gray);
		drawSetting(g2, x + 5 + 5 * GamePanel.SIZE, y, 20, "["+GamePanel.difficulty+"]");
		g2.drawString("current", x + 5 + 5 * GamePanel.SIZE , y - GamePanel.HEIGHT / 15);
		
		//키세팅 그리기
		x = GamePanel.WIDTH / 8 + 5 + 5 * GamePanel.SIZE;
		y = GamePanel.HEIGHT / 8 * 2 + GamePanel.HEIGHT / 15;
		g2.setColor(Color.white);
		drawSetting(g2, x, y, 20, "keysetting");
		
		//키설명용 첫번째줄
		y = GamePanel.HEIGHT / 8 * 2 + GamePanel.HEIGHT / 16 * 3;
		String[] key = new String[] {"[↑]", "[←]", "[↓]", "[→]", "[turn]"};
		x = keySetting(g2, x, y, key);
		
		//키세팅용 첫번째 줄
		y = GamePanel.HEIGHT / 13 * 5 ;
		x = setKey(g2, x, y, 1);
		
		//키설명용 두번째줄
		y = GamePanel.HEIGHT / 2 * 10 / 9;
		String[] key1 = new String[] {"[quit]", "[skill]", "[pause]", "[menu]", "[OK]"};
		x = keySetting(g2, x, y, key1);
		
		//키세팅용 두번째 줄
		y = GamePanel.HEIGHT / 2;
		x = setKey(g2, x, y, 2);
		
		//키세팅 apply cancel 용도
		x -= GamePanel.WIDTH / 8;
		y += GamePanel.HEIGHT / 8;
		acRj(g2, x, y, 3);
		
		//대전모드용
		x = GamePanel.WIDTH / 8 + 5 + 5 * GamePanel.SIZE;
		y = GamePanel.HEIGHT * 3 / 4;
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 30 + 30 * GamePanel.SIZE));
		
		g2.setColor((pointer_y == 4) ? Color.yellow : Color.white);
		g2.drawString("Battle KeySetting", x, y);
		
		//키세팅 활성화시 움직이기.
		if(battlekeySet) {
			
			x = GamePanel.WIDTH / 8;
			y = GamePanel.HEIGHT / 8  + GamePanel.HEIGHT / 8;
			g2.setColor(Color.black);
			g2.fillRect(x, y, GamePanel.WIDTH * 3 / 4, GamePanel.HEIGHT * 2 / 4); 
			
			g2.setColor(Color.orange);
			g2.drawRect(x, y, GamePanel.WIDTH * 3 / 4, GamePanel.HEIGHT * 2 / 4); 
			
			//키설명용 첫번째줄
			y = GamePanel.HEIGHT / 8 * 2 + GamePanel.HEIGHT / 16 * 3;
			String[] p1Key = new String[] {"[P1.←]", "[P1.↓]", "[P1.→]", "[P1.turn]", "[P1.skill]"};
			x = keySetting(g2, x, y, p1Key);
			
			//키세팅용 첫번째 줄
			y = GamePanel.HEIGHT / 13 * 5 ;
			x = setKey(g2, x, y, 101);
			
			//키설명용 두번째줄
			y = GamePanel.HEIGHT / 2 * 10 / 9;
			String[] p2Key = new String[] {"[P2.←]", "[P2.↓]", "[P2.→]", "[P2.turn]", "[P2.skill]"};
			x = keySetting(g2, x, y, p2Key);
			
			//키세팅용 두번째 줄
			y = GamePanel.HEIGHT / 2;
			x = setKey(g2, x, y, 102);
		
			//키세팅 apply cancel 용도
			x -= GamePanel.WIDTH / 8;
			y += GamePanel.HEIGHT / 8;
			acRj(g2, x, y, 103);
		}
	}
	
	private void colorChange(Graphics2D g2, int x, int y, int size) {
		setColor(g2, 6, 0, x, y, 15, "기본", "맑은고딕");
		setColor(g2, 6, 1, x + GamePanel.WIDTH / 8, y, size, "[적록색맹]", "맑은고딕");
		setColor(g2, 6, 2, x + GamePanel.WIDTH / 8 * 2, y, size, "[청황색맹]", "맑은고딕");
	}
	
	//2페이지
	public void settingPage2(Graphics2D g2) {
		
		int x = GamePanel.WIDTH / 8;
		int y = GamePanel.HEIGHT / 8;
		
		//해상도 그리기
		x += 5 + 5 * GamePanel.SIZE;
		y += GamePanel.HEIGHT / 15;	
		
		g2.setColor(Color.white);
		drawSetting(g2, x, y, 20, "screen size");
		
		y += GamePanel.HEIGHT / 15;
		setColor(g2, 5, 0, x + 5 + 5 * GamePanel.SIZE, y, 30, "small");
		
		x = GamePanel.WIDTH * 5 / 13;
		setColor(g2, 5, 1, x + 5 + 5 * GamePanel.SIZE, y, 30, "middle");
		
		x = GamePanel.WIDTH * 2 / 3;
		setColor(g2, 5, 2, x + 5 + 5 * GamePanel.SIZE, y, 30, "large");
		
		//색맹모드
		x = GamePanel.WIDTH / 8 + 5 * GamePanel.SIZE;
		y = GamePanel.HEIGHT / 8 * 2 + GamePanel.HEIGHT / 15;
		
		x += 5 + 5 * GamePanel.SIZE;
		y += GamePanel.HEIGHT / 15;
		g2.setColor(Color.white);
		drawSetting(g2, x, y, 20, "Color Blind Mode");
		
		//색맹모드 apply cancel 용도
		x += GamePanel.WIDTH / 9 * 3;
		colorChange(g2, x, y, 15);
		
		//스코어보드 초기화용
		x = GamePanel.WIDTH / 8 + 5 + 5 * GamePanel.SIZE;
		y = GamePanel.HEIGHT / 3 + GamePanel.HEIGHT / 15;
		
		y += GamePanel.HEIGHT / 15;
		g2.setColor(Color.white);
		g2.drawString("ScoreBoard Reset", x, y);
		
		x += GamePanel.WIDTH / 8 * 4;
		g2.setColor(pointer_y == 7 ? Color.yellow :Color.white);
		g2.drawString("Reset", x + GamePanel.WIDTH / 8, y);
		
		//임시 default용
		x = GamePanel.WIDTH / 5;
		y = GamePanel.HEIGHT / 4 * 3;
		g2.setColor((pointer_y == 8)? Color.red : Color.gray);
		drawSetting(g2, x, y, 40, "Default Initialization!!!");
		
		//초기화 시 확인용
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 20 + 20 * GamePanel.SIZE));
		if(score_Reset) {
			x = GamePanel.WIDTH * 1/ 3;
			y = GamePanel.HEIGHT * 1/ 3;
			
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(4f)); //테두리크기 4픽셀이라는 뜻
			g2.fillRect(x, y, GamePanel.WIDTH * 1/ 3, GamePanel.HEIGHT * 1 / 3); 
			
			g2.setColor(Color.yellow);
			g2.drawRect(x, y, GamePanel.WIDTH * 1/ 3, GamePanel.HEIGHT * 1 / 3);
			
			x += 5 + 5 * GamePanel.SIZE + GamePanel.WIDTH / 16;
			g2.setColor(Color.white);
			g2.drawString("Are you Sure?", x, y + GamePanel.HEIGHT / 15);
			
			g2.setColor((pointer_x == 0) ? Color.yellow :Color.white);
			g2.drawString("Yes", x + GamePanel.WIDTH / 32, y + GamePanel.HEIGHT * 1 / 5);
			
			g2.setColor((pointer_x == 1) ? Color.yellow :Color.white);		
			g2.drawString("No", x + GamePanel.WIDTH / 8, y + GamePanel.HEIGHT * 1 / 5);
		}
		
		//디폴트 돌릴 때 확인용.
		if(def_Reset) {
			x = GamePanel.WIDTH * 1/ 3;
			y = GamePanel.HEIGHT * 1/ 3;
			
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(4f)); //테두리크기 4픽셀이라는 뜻
			g2.fillRect(x, y, GamePanel.WIDTH * 1/ 3, GamePanel.HEIGHT * 1 / 3); 
			
			g2.setColor(Color.yellow);
			g2.drawRect(x, y, GamePanel.WIDTH * 1/ 3, GamePanel.HEIGHT * 1 / 3);
			
			x += 5 + 5 * GamePanel.SIZE + GamePanel.WIDTH / 16;
			g2.setColor(Color.white);
			g2.drawString("Are you Sure?", x, y + GamePanel.HEIGHT / 15);
			
			g2.setColor((pointer_x == 0) ? Color.yellow :Color.white);
			g2.drawString("Yes", x + GamePanel.WIDTH / 32, y + GamePanel.HEIGHT * 1 / 5);
			
			g2.setColor((pointer_x == 1) ? Color.yellow :Color.white);		
			g2.drawString("No", x + GamePanel.WIDTH / 8, y + GamePanel.HEIGHT * 1 / 5);
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
		g2.drawString("Setting<"+(pointer_y<= 4 || pointer_y >=100 ? 1 :2)+"/2>", x, y);
		
		if(pointer_y <= 4 || pointer_y >=100)
			settingPage1(g2);
		else
			settingPage2(g2);
	}
}