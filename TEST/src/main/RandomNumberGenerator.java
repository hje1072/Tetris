package main;

import java.text.DecimalFormat;
import java.util.Random;

public class RandomNumberGenerator {
	
	
	//이부분을 떼어내서 사용할 것.
	
    public static int generateRandomNumber(double num) {
        Random random = new Random();
        double probability = random.nextDouble(); // 0.0에서 1.0 사이의 double 값 생성
        
        if (probability < (double)1/7 * num) {
            // 1이 나오는 경우, 80% 확률로 1을 반환
            return 1;
        } else {
            // 1을 제외한 경우, 20% 확률로 2에서 7까지의 난수 반환
            return random.nextInt(6) + 2;
        }
    }
    
    //아래는 테스트기
    
    public static void main(String[] args) {
    	//몇번 나오게 할지 정하기 
    	
    	//0.8 일 경우 20퍼센트 덜 나오게
    	//1.2 일 경우 20퍼센트 더 나오게
    	//0.0 일 경우 equal likely
    	double num = 1.2;
    	
    	
        // 테스트를 위해 10000000번의 난수 생성 후 결과 출력
        int[] countNumbers = new int[8]; // 0번 인덱스는 사용하지 않음
        int sum = 0;
        
        for (int i = 0; i < 10000000; i++) {
            int randomNumber = generateRandomNumber(num);
            countNumbers[randomNumber]++; // 해당 숫자가 나온 횟수 증가
            sum++;
        }
        
        // 결과 출력
        System.out.println("Number\tCount");
        for (int i = 1; i <= 7; i++) {
            System.out.println(i + "\t\t" + countNumbers[i] + "|| 확률 = " + (double)countNumbers[i] / sum);
        }
        
        // 총 횟수 출력
        System.out.println("Total count: " + sum);
        
        DecimalFormat df = new DecimalFormat("#.####");
        //검사
        System.out.println("모두 무작위일 때 확률: " + df.format((double)1/7));
        
        double less = (double)1/7 * 0.8;
        double more = (double)1/7 * 1.2;
        
        System.out.println("20퍼센트 덜 나오는 확률: " + df.format(less) + "||"+ "20퍼센트 더 나오는 확률: " + df.format(more));
        
        System.out.print("20퍼센트 덜 나올 때 오차범위: " +df.format(( Math.abs(less - (double)countNumbers[1] / sum) / less * 100))
        	+  "||" + "20퍼센트 더 나올 때 오차범위: " + df.format(( Math.abs(more - (double)countNumbers[1] / sum) / more * 100)));
        

    }
}
