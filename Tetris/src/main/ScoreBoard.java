package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ScoreBoard {
	
	public static String[] Name = new String[11];
	public static String[] Score = new String[11];
	
	//생성자
	public ScoreBoard() {
		readscoreBoard();
	}
	
	public void update() {
		//스코어보드는 눈으로만 볼 수 있게 만듦.
		//확인후 나가는 키만 확실히 표현하면 될듯.
		if (KeyHandler.enterPressed == true) {
			
			KeyHandler.enterPressed = false;
			GamePanel.screenRefresh = true;
			GamePanel.screen = 0; //메인메뉴로
		}
		
		if(KeyHandler.menuPressed) {
			
			GamePanel.screen = 0;
			
			KeyHandler.menuPressed = false;
		}
	}
	
	//업데이트 필요시 이 메소드 호출바람.
	public void readscoreBoard() {
		
    	//파일을 읽어오는 과정.
    	
    	String currentDirectory = System.getProperty("user.dir");
        String csvFile = currentDirectory + "/src/data/scoreBoard.csv";
        String line = "";
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	
        	int i = 0;
            while ((line = br.readLine()) != null) {
                // CSV 파일의 각 라인을 쉼표로 분리하여 배열로 저장
                String[] data = line.split(csvSplitBy);
                
                Name[i] = data[0];
                Score[i] = data[1];
                
                i++;
                System.out.println(); // 줄 바꿈
                
            }
            
        } catch (IOException e) {
        	
            e.printStackTrace();
        }
		
	}
	
	
	public void draw(Graphics2D g2) {
		
		
		
		int x = GamePanel.WIDTH / 8;
		int y = GamePanel.HEIGHT / 8;
		
		g2.setColor(Color.red);
		g2.setStroke(new BasicStroke(4f)); //테두리크기 4픽셀이라는 뜻
		g2.drawRect(x, y, GamePanel.WIDTH * 3 / 4, GamePanel.HEIGHT * 4 / 5); 
		
		g2.setColor(Color.white);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 60 + 60 * GamePanel.SIZE));
		g2.drawString("ScoreBoard", x, y);
		
		
		//이름 점수 기입
		y += GamePanel.HEIGHT / 14;
		g2.setColor(Color.yellow);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 20 + 20 * GamePanel.SIZE));
		
		g2.drawString(Name[0], x, y); g2.drawString(Score[0], x + GamePanel.WIDTH * 1 / 3, y); 
		
		g2.setColor(Color.white);
		for(int k = 1 ; k <= 10 ; k++) {
			y += GamePanel.HEIGHT / 14;
			
			g2.drawString(Name[k], x, y); g2.drawString(Score[k], x + GamePanel.WIDTH * 1 / 3, y);
			
		}
		
		
		
		
	}
}
