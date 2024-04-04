package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
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

                // 각 열의 데이터를 출력 또는 처리
                for (String datum : data) {
                    System.out.print(datum);
                }
                System.out.println(); // 줄 바꿈
            }
        } catch (IOException e) {
        	
            e.printStackTrace();
        }
        
        
        
        
	}
	
	
	
	
	
	
	public void update() {
		
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
			if (pointer_x >= 3 ) {
				pointer_x = 0;
			}
			KeyHandler.rightPressed = false;
		}
		if (KeyHandler.leftPressed == true) {
			pointer_x -= 1;
			if (pointer_x < 0) {
				pointer_x = 3;
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
				
				switch(pointer_x) {
				case 0 :
					keySet();
					//0 = 돌리기
					break;
				case 1 :
					// 1 = 내리기
					break;
				case 2 : 
					//2 = 왼쪽
					break;
				case 3 : 
					//3 = 오른쪽
					break;
				}
				
				
				
				break;
			case 2 : //키보드 설정 2
			
				break;
			
			case 3 : //키보드 세팅 변경 확인용도.
				break;
				
			case 4 : //색맹모드
				
				break;
			case 5 : //스크어보드초기화.
				
				
				break;
			
			}
		
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
		g2.drawString("resolution", x, y);
		
		y += GamePanel.HEIGHT / 15;
		g2.setColor((pointer_y == 0 && pointer_x == 0) ? Color.yellow : Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 30 + 30 * GamePanel.SIZE));
		g2.drawString("small", x + 5 + 5 * GamePanel.SIZE , y);
		
		x = GamePanel.WIDTH * 5 / 13;
		g2.setColor((pointer_y == 0 && pointer_x == 1) ? Color.yellow : Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 30 + 30 * GamePanel.SIZE));
		g2.drawString("middle", x + 5 + 5 * GamePanel.SIZE , y);
		
		x = GamePanel.WIDTH * 2 / 3;
		g2.setColor((pointer_y == 0 && pointer_x == 2) ? Color.yellow : Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 30 + 30 * GamePanel.SIZE));
		g2.drawString("large", x + 5 + 5 * GamePanel.SIZE , y);
		
		
		//확인용 2
		x = PlayManager.left_x;
		y = PlayManager.bottom_y;
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 20 + 20 * GamePanel.SIZE));
		g2.drawString("height:"+pointer_y+" // and width:" + pointer_x, x, y);
		
		
	}
	
	
	
}
	
	
	
	
	
	