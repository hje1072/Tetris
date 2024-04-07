package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

public class ScoreBoard {
	
	public static String[] Name = new String[11];
	public static String[] Score = new String[11];
	public String name = "";
	
	//점수판 갱신용
	public int index;
	public boolean newRecord = false;
	
	//생성자
	public ScoreBoard() {
		readscoreBoard();
	}
	
	public void update() {
		
		if(GamePanel.enteringScore) {
			GamePanel.enteringScore = false;
			
			
			
	    	String name = JOptionPane.showInputDialog(null, "Please enter your name:", "Enter your name", JOptionPane.QUESTION_MESSAGE);
	    	
	    	// 입력된 이름 출력
	    	if (name != null && !name.isEmpty()) {
	    		this.name = name;
	    		
	    		//점수보드 업데이트 겸 인덱스 업데이트
	    		index = writescoreBoard();
	    		readscoreBoard();
	    		
	    		newRecord = true;
	    		
	    	} else {
	    		this.name = "UNKNOWN";
	    		
	    		index = writescoreBoard();
	    		readscoreBoard();
	    		
	    		newRecord = true;
	        }
			
		}
		
		
		//스코어보드는 눈으로만 볼 수 있게 만듦.
		//확인후 나가는 키만 확실히 표현하면 될듯.
		if (KeyHandler.enterPressed == true) {
			
			if (newRecord) {newRecord = false;} //신기록 하이라이트용 변수 꺼주기
			
			KeyHandler.enterPressed = false;
			GamePanel.screenRefresh = true;
			GamePanel.screen = 0; //메인메뉴로
		}
		
		if(KeyHandler.menuPressed) {
			
			//점수보드 초기화 예시
			removescoreBoard();
			readscoreBoard();
			
			//GamePanel.screen = 0;
			
			KeyHandler.menuPressed = false;
			GamePanel.screenRefresh = true;
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
                
            }
            
        } catch (IOException e) {
        	
            e.printStackTrace();
        }
		
	}
	
	//스코어링 정보 저장용
	public int writescoreBoard() {
		
    	//파일을 읽어오는 과정.
    	
    	String currentDirectory = System.getProperty("user.dir");
        String csvFile = currentDirectory + "/src/data/scoreBoard.csv";
        String line = "";
        String csvSplitBy = ",";
        
        
        List<String[]> lines = new ArrayList<>();
        
        
        // 1. CSV 파일에서 데이터를 읽어옴
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	
        	//헤더는 떼기
        	br.readLine();
        	
            //String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                lines.add(data);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        // 새로운 데이터
        String[] newData = {this.name, GamePanel.score};
        
        // 2. 새로운 데이터를 추가
        lines.add(newData);
        
        // 2-2.데이터가 10개 미만이면 필요한 만큼 0,0으로 데이터 추가
        if (lines.size() < 10) {
            for (int i = lines.size(); i < 10; i++) {
                lines.add(new String[]{"------", "0"});
            }
        }
        
        
        // 3. 데이터를 다시 정렬 //먼저들어온 데이터에게 더 높은 순위 부여
        Collections.sort(lines, (arr1, arr2) -> Integer.parseInt(arr2[1]) - Integer.parseInt(arr1[1]));
        
        // 4. 데이터 개수가 10개를 초과하는지 확인하고, 11번째 줄부터의 데이터를 삭제
        if (lines.size() > 10) {
            lines.subList(10, lines.size()).clear();
        }

        // 5. 새로 추가된 데이터의 위치 찾기
        int index = lines.indexOf(newData) + 1;
        
        
        // 정렬된 데이터를 CSV 파일에 쓰기
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            // 헤더 쓰기
            writer.println("Name,Score");
            for (String[] data : lines) {
                writer.println(String.join(",", data));
            }
            
            //디버깅용
            //System.out.println("CSV 파일이 새로운 데이터와 함께 다시 정렬되어 생성되었습니다.");
            //System.out.println("새로운 데이터의 위치: " + index);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return index;
        
	}
	
	public void removescoreBoard() {
		
    	String currentDirectory = System.getProperty("user.dir");
        String csvFile = currentDirectory + "/src/data/scoreBoard.csv";
        String line = "";
        String csvSplitBy = ",";
		
        List<String[]> lines = new ArrayList<>();
        
		try {
            FileWriter writer = new FileWriter(csvFile, false);
            writer.close();
            //System.out.println("CSV 파일이 초기화되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        // 1. CSV 파일에서 데이터를 읽어옴
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	
        	//헤더는 떼기
        	br.readLine();
        	
            //String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                lines.add(data);
                //System.out.println(line);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        // 2-2.데이터가 10개 미만이면 필요한 만큼 0,0으로 데이터 추가
        if (lines.size() < 10) {
            for (int i = lines.size(); i < 10; i++) {
                lines.add(new String[]{"------", "0"});
            }
        }
        
        
        // 4. 데이터 개수가 10개를 초과하는지 확인하고, 11번째 줄부터의 데이터를 삭제
        if (lines.size() > 10) {
            lines.subList(10, lines.size()).clear();
        }
        
        
        
        // 정렬된 데이터를 CSV 파일에 쓰기
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            // 헤더 쓰기
            writer.println("Name,Score");
            for (String[] data : lines) {
                writer.println(String.join(",", data));
            }
            //System.out.println("CSV 파일이 새로운 데이터와 함께 다시 정렬되어 생성되었습니다.");
            //System.out.println("새로운 데이터의 위치: " + index);
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
			
			if(newRecord && k == (index)) {g2.setColor(Color.green);;}
			
			
			g2.drawString(Name[k], x, y); g2.drawString(Score[k], x + GamePanel.WIDTH * 1 / 3, y);
			
			if(newRecord && k == (index)) {g2.setColor(Color.white);}
			
		}
		
		
		
		
	}
}
