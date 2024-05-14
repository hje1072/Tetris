package Test;

import static org.junit.Assert.*;
import org.junit.Test;

import main.KeyHandler;
import main.GamePanel;

public class MenuTest {
    private int pointer = 1;
    private int pointer2 = 0;
    private boolean check = false;
    private int timer = 1;
    private boolean timeMode = true;

    @Test
    public void testUpdate_BasicModeSelected() {
        KeyHandler.upPressed = true;
        update();
        assertFalse(check);
        assertEquals(5, pointer);
    }

    @Test
    public void testUpdate_TimeModeSelected() {
        KeyHandler.enterPressed = true;
        update();
        assertTrue(timeMode);
        assertEquals(1, timer);
        assertFalse(check);
        assertEquals(1, pointer);
    }

    // 현재 여기 테스트중

    private void update() {
        if (timeMode) {
            if (KeyHandler.upPressed) {
                timer++;
                if (timer >= 6) {
                    timer = 1;
                }
                KeyHandler.upPressed = false;
            }
            if (KeyHandler.downPressed) {
                timer--;
                if (timer < 1) {
                    timer = 5;
                }
                KeyHandler.downPressed = false;
            }
        } else if (check) {
            if (KeyHandler.downPressed) {
                pointer2++;
                if (pointer2 >= 3) {
                    pointer2 = 0;
                }
                KeyHandler.downPressed = false;
            }
            if (KeyHandler.upPressed) {
                pointer2--;
                if (pointer2 < 0) {
                    pointer2 = 2;
                }
                KeyHandler.upPressed = false;
            }
        } else {
            if (KeyHandler.downPressed) {
                pointer++;
                if (pointer >= 6) {
                    pointer = 0;
                }
                KeyHandler.downPressed = false;
            }
            if (KeyHandler.upPressed) {
                pointer--;
                if (pointer < 0) {
                    pointer = 5;
                }
                KeyHandler.upPressed = false;
            }
        }

        if (KeyHandler.enterPressed) {
            KeyHandler.enterPressed = false;

            switch (pointer) {
                case 0:  //대전
                    if (check) {
                        switch (pointer2) {
                            case 0:
                                check = false;
                                pointer2 = 0;
                                GamePanel.basicMode = true;
                                GamePanel.screenRefresh = true;
                                GamePanel.screen = 1; //대전모드.
                                GamePanel.battleMode = true;
                                break;
                            case 1:
                                check = false;
                                pointer2 = 0;
                                GamePanel.basicMode = false;
                                GamePanel.screenRefresh = true;
                                GamePanel.screen = 1; //대전모드.
                                GamePanel.battleMode = true;
                                break;
                            case 2:
                                if (timeMode) {
                                    GamePanel.timelimitMode = true;
                                    GamePanel.time = timer * 60;
                                    check = false;
                                    pointer2 = 0;
                                    timer = 1;
                                    timeMode = false;
                                    GamePanel.basicMode = true;
                                }
                        }
                    }
            }
        }
    }
}
