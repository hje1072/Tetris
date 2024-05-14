package Test;

import static org.junit.Assert.assertArrayEquals;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.GamePanel;

public class GamePanelTest {

    // 임시 CSV 파일 경로
    private final String csvFilePath = "temp_difficulty.csv";

    @Before
    public void difficulty_test () throws IOException {
        // 임시 CSV 파일 생성
        FileWriter writer = new FileWriter(csvFilePath);
        writer.write("Easy");
        writer.close();
    }

    @After
    public void tearDown() {
        // 테스트 이후에 임시 CSV 파일 삭제
        File file = new File(csvFilePath);
        file.delete();
    }
    ////
    
    
    
    // 임시 CSV 파일 경로
    private final String csvFilePath2 = "temp_colorBlind.csv";

    @Before
    public void colorBlind_test () throws IOException {
        // 임시 CSV 파일 생성
        FileWriter writer = new FileWriter(csvFilePath2);
        writer.write("2"); // 색맹 모드 설정 값
        writer.close();
    }

    @After
    public void tearDown2() {
        // 테스트 이후에 임시 CSV 파일 삭제
        File file = new File(csvFilePath2);
        file.delete();
    }
    ////
   

    // readDifficulty() 메서드 테스트
    @Test
    public void testReadDifficulty() {
        GamePanel gamePanel = new GamePanel();
        // 임시 CSV 파일 경로를 설정
        System.setProperty("user.dir", new File("").getAbsolutePath());
        
        try {
            gamePanel.readDifficulty();// 함수가 잘 실행되는지 확인
        } catch (Exception e) {
            fail("Unexpected exception occurred: " + e.getMessage());
        }

    }
    // readcolor() 메서드 테스트
    @Test
    public void testReadColor() {
        GamePanel gamePanel = new GamePanel();
        // 임시 CSV 파일 경로를 설정
        System.setProperty("user.dir", new File("").getAbsolutePath());
        try {
            gamePanel.readcolor(); // 함수가 잘 실행되는지 확인
        } catch (Exception e) {
            fail("Unexpected exception occurred: " + e.getMessage());
        }
    }
    @Test
    public void testReadSize() {
        GamePanel gamePanel = new GamePanel();
        // 임시 CSV 파일 경로를 설정
        System.setProperty("user.dir", new File("").getAbsolutePath());
        try {
            gamePanel.readSize();// 함수가 잘 실행되는지 확인
        } catch (Exception e) {
            fail("Unexpected exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void testKeySetting() {
        GamePanel gamePanel = new GamePanel();
        // 임시 CSV 파일 경로를 설정
        System.setProperty("user.dir", new File("").getAbsolutePath());
        // 테스트할 메서드 호출
        gamePanel.keySetting();
        // 기대되는 값들
        int[] expectedKeySetting = {38,37,40,39,38,81,32,80,27,10}; // 일단 기본값으로 비교하기로 설정
        // 실제 메서드에서 반환된 값들과 기대되는 값들 비교
        assertArrayEquals(expectedKeySetting, GamePanel.keySetting);
        assertArrayEquals(expectedKeySetting, GamePanel.userkeySetting);
    }
    
    @Test
    public void testStartGame_Battle() {
        GamePanel gamePanel = new GamePanel();

        // Initially, ensure that battleMode is false
        assertFalse(GamePanel.battleMode);
        
        // Call the method
        gamePanel.startGame_Battle();

        // Check if battleMode is set to false and battle is set to true
        assertFalse(GamePanel.battleMode);
        assertTrue(GamePanel.battle);

        // Check if PlayManager and PlayManager_Battle are initialized
        assertNotNull(gamePanel.pm); // pm과 pm2의 값을 테스트하기 위해서 public으로 변수를 설정해놨음
        assertNotNull(gamePanel.pm2);
    }
    
    @Test
    public void testLaunchGame() {
        GamePanel gamePanel = new GamePanel();

        // Initially, gameThread should be null
        assertNull(gamePanel.gameThread); // gameThread의 값을 테스트하기 위해서 public으로 설정해놨음

        // Call the method
        gamePanel.launchGame();

        // Check if gameThread is initialized and started
        assertNotNull(gamePanel.gameThread);
        assertTrue(gamePanel.gameThread.isAlive());
    }
    
  
    


    @Test
    public void testChangeResolution() {
        // Create a  JFrame
        JFrame frame = new JFrame();
        GamePanel gamePanel = new GamePanel();

        // Call changeResolution method with frame
        gamePanel.changeResolution(800, 600);
      

    }
    
    
    @Test
    public void testRun() {
        // Create a  JFrame
        JFrame frame = new JFrame();
        GamePanel gamePanel = new GamePanel();
        
        // Set up the values for testing (e.g., FPS)
        int FPS = 60;
        double drawInterval = 1000000000.0 / FPS;
        
        // Call the run method with the mocked gamePanel
        gamePanel.run();
        
        // Sleep for a short time to allow the loop to execute (optional)
        // This is to avoid an infinite loop in the test
        // You may need to adjust this sleep time based on your FPS
        try {
            Thread.sleep(1000); // Sleep for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
    }
    
    @Test
    public void testUpdate_SizeChange() {
        JFrame frame = new JFrame();
        GamePanel gamePanel = new GamePanel();
        // Set up initial conditions for size change
        GamePanel.sizeChange = true;
        GamePanel.SIZE = 1; // Set SIZE to 1 for testing
        
        // Call the update method
        gamePanel.update();
        
        // Assertions for size change
        assertEquals(1280, gamePanel.WIDTH); // Assuming SIZE == 1, WIDTH should be 1280
        assertEquals(720, gamePanel.HEIGHT); // Assuming SIZE == 1, HEIGHT should be 720
        assertEquals(30, gamePanel.blockSize); // Assuming SIZE == 1, blockSize should be 30
       

    }
    
    @Test
    public void testUpdate_0() {
        JFrame frame = new JFrame();
        GamePanel gamePanel = new GamePanel();
        // Set screen to main menu case (screen = 0)
        gamePanel.screen = 0;       
        // Call the update method
        gamePanel.update();    
    }
    
//    @Test // update함수의 case 1번
//    public void testUpdate_1() {
//        
//    }
    @Test
    public void testUpdate_2() {
        JFrame frame = new JFrame();
        GamePanel gamePanel = new GamePanel();
        gamePanel.screen = 2;
        gamePanel.update();
    }
    @Test
    public void testUpdate_3() {
        JFrame frame = new JFrame();
        GamePanel gamePanel = new GamePanel();
        gamePanel.screen = 3;
        gamePanel.update();  
    }
    
    //	public void paintComponent(Graphics g) 테스트 함수 추가 필요
    
   }
    
   
    


