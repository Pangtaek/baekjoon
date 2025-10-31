import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 백준 1834번: 나머지와 몫이 같은 수
 * https://www.acmicpc.net/problem/1834
 */

public class Baekjoon1834 {
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            // 1. N을 long 타입으로 읽어옵니다.
            // (N+1) * N * (N-1) / 2 연산은 int 범위를 초과할 수 있습니다.
            long N = Long.parseLong(br.readLine());

            // 2. 1부터 (N-1)까지의 합을 계산합니다.
            // (N-1) * (N-1 + 1) / 2 = (N-1) * N / 2
            long sumOfR = (N * (N - 1)) / 2;

            // 3. 몫과 나머지가 같은 수들의 총합을 계산합니다.
            // A = r * (N+1) 이므로
            // 총합 = (1 + 2 + ... + (N-1)) * (N+1)
            long totalSum = sumOfR * (N + 1);

            // 4. 결과를 출력합니다.
            bw.write(Long.toString(totalSum));
            bw.newLine();
            bw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}