package main;

import java.util.Random;

public class Calc {
	
	//특정 변수에 가중치를 준 상태에서 뽑기. 
    public static int generateWRN(String str) {
    	
    	double num = difficulty_To_num(str);
    	
    	
        Random random = new Random();
        double probability = random.nextDouble(); // 0.0에서 1.0 사이의 double 값 생성
        
        if (probability < (double)1/7 * num) {
            // 0이 나오는 경우임. (double)1/7 * num 만족시 1반환.
            return 0;
        } else {
            // 1을 제외한 경우,1부터 6까지의 난수 반환
            return random.nextInt(6) + 1;
        }
    }
    
    // 그냥 랜덤하게 뽑기. [0, cnt - 1] 사이의 숫자를 뽑는다.
    public static int generateRN(int cnt) {
    	int num = new Random().nextInt(cnt);
    	return  num;
    }
    
    //숫자 바꿔주는놈.
    public static double difficulty_To_num(String str) {
    	
    	double num;
    	
    	if(str == "easy") {
    		num = 1.2;
    	}
    	else if (str == "normal") {
    		num = 1.0;
    	}
    	else { //"hard"임.
    		num = 0.8; 
    	}
    	return num;
    }
    
    
    
    
    
}
