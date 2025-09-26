import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1541 {

    /*
     * 백준 1541 - 잃어버린 괄호
     * https://www.acmicpc.net/problem/1541
     * 
     * 문제 접근
     * - '-' 연산자를 기준으로 문자열을 분리
     * - 첫 번째 부분은 모두 더하고, 이후 부분들은 모두 빼기
     * - 각 부분에서 '+' 연산자를 기준으로 다시 분리하여 숫자들을 처리
     */
    
    public static void main(String[] args) throws IOException {
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            
            int sum = 0;
            String[] input = br.readLine().split("-");

            for (int i = 0; i < input.length; i++) {
                int partSum = 0;
                String[] numbers = input[i].split("\\+");
                
                for (String number : numbers) {
                    partSum += Integer.parseInt(number);
                }
                
                if (i == 0) {
                    sum += partSum; // 첫 번째 부분은 더하기
                } else {
                    sum -= partSum; // 이후 부분은 빼기
                }
            }
            
            bw.write(String.valueOf(sum));
            bw.flush();
        }
    }
}
