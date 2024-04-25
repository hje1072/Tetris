package mino_item;

import java.awt.Color;
import java.awt.Graphics2D;

import main.KeyHandler;
import mino.Block;
import mino.Mino;

//skill pressed 눌렀을 때, next미노와자리change총 3번가능.
public class Mino_Item_Swap extends Mino {
	
	int swapping;
	
	public Mino_Item_Swap() {
		extracted(Color.white);
		swapping = 3;
	}
	
	public void setXY(int x, int y) {
		// ■ 
		
		// 0 
		
		b[0].x = x;
		b[0].y = y;
		b[1].x = b[0].x;
		b[1].y = b[0].y;
		b[2].x = b[0].x;
		b[2].y = b[0].y;
		b[3].x = b[0].x;
		b[3].y = b[0].y;
		
		
	}
	
	
	public void update() {
		
		if(deactivating) {
			deactivating();
		}
		
		
		//스킬 
		if(KeyHandler.skillPressed) {
			
			if(swapping > 0) {
				swapping --;
				swap = true;
			}
			else {
				while (bottomCollision == false) {
					
					//일단 4블럭기준인데 여러블럭일경우 보정필요.
					for(int i = 0 ; i < 4 ; i++) {
						b[i].y += Block.SIZE;
					}
					
					checkMovementCollision(); 
				}
				//떨어지는 속도 조절시 오토드랍카운터는 초기화시켜줍시다.
				deactivateCounter = 1557;
				
				KeyHandler.skillPressed = false;
			}
			
		}
		
		handling();
		handling_Auto();
	}
	
	
	public void draw(Graphics2D g2) {
		
		int margin = 2;
		g2.setColor(b[0].c);
		
		
		g2.setColor(b[0].c);
		g2.fillRect(b[0].x + margin, b[0].y + margin, Block.SIZE - (margin*2), Block.SIZE - (margin*2));
		
		g2.setColor(Color.black);
		g2.drawString(""+swapping, b[0].x + margin*2, b[0].y + Block.SIZE - margin * 2);
		g2.setColor(b[0].c);
		
	}
	
	
}
