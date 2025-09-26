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
            
            String[] input = br.readLine().split("-");
            
            // 첫 번째 부분 (양수로 더하기)
            String[] firstPart = input[0].split("\\+");
            int result = 0;
            for (String num : firstPart) {
                result += Integer.parseInt(num);
            }
            
            // 나머지 부분들 (빼기)
            for (int i = 1; i < input.length; i++) {
                String[] parts = input[i].split("\\+");
                for (String num : parts) {
                    result -= Integer.parseInt(num);
                }
            }
            
            bw.write(String.valueOf(result));
            bw.flush();
        }
    }
}
