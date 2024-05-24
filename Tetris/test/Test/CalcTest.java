package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import main.Calc;

public class CalcTest {

	 @Test
	    public void testGenerateWRN() {
	        // "easy" 모드일 때의 테스트
	        int easyResult = Calc.generateWRN("easy");
	        assertTrue(easyResult >= 0 && easyResult <= 6); // 결과가 0부터 6 사이의 값이어야 함

	        // "normal" 모드일 때의 테스트
	        int normalResult = Calc.generateWRN("normal");
	        assertTrue(normalResult >= 0 && normalResult <= 6); // 결과가 0부터 6 사이의 값이어야 함

	        // "hard" 모드일 때의 테스트
	        int hardResult = Calc.generateWRN("hard");
	        assertTrue(hardResult >= 0 && hardResult <= 6); // 결과가 0부터 6 사이의 값이어야 함


	    }
	

    @Test
    public void testGenerateRN() {
        // 테스트할 카운트 수
        int count = 10;
        
        // 테스트
        int result = Calc.generateRN(count);
        
        // 예상 결과와 비교
        assertTrue(result >= 0 && result < count); // 결과가 0부터 count-1 사이여야 함
    }

    @Test
    public void testDifficulty_To_num() {

        
        // 테스트
        double result_1 = Calc.difficulty_To_num("easy");
        double result_2 = Calc.difficulty_To_num("normal");
        double result_3 = Calc.difficulty_To_num("hard");
        
        // 예상 결과와 비교
        assertEquals(1.2, result_1, 0.0); // normal 모드일 때는 1.0이 나와야 함
        assertEquals(1.0, result_2, 0.0); // normal 모드일 때는 1.0이 나와야 함
        assertEquals(0.8, result_3, 0.0); // normal 모드일 때는 1.0이 나와야 함
    }
}
