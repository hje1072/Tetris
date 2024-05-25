package main;

import java.awt.Color;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		
		//창띄우기
		JFrame window = new JFrame("Tetris");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		//게임창 크기 설정용
		GamePanel gp = new GamePanel();
		window.add(gp);
		window.pack();
		
		//중앙에 띄워서 보여줘라.
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		//스레드 돌려서 게임돌리기
		gp.launchGame();	
	}
}
