import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon15817 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 입력 받기
        int[] tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = tokens[0]; // 파이프 종류 수
        int X = tokens[1]; // 만들고자 하는 파이프의 총 길이

        int[] L = new int[N]; // 각 파이프의 길이
        int[] C = new int[N]; // 각 파이프의 수량

        for (int i = 0; i < N; i++) {
            tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            L[i] = tokens[0];
            C[i] = tokens[1];
        }

        // 2. dp[i] = 길이 i를 만드는 방법의 수
        int[] dp = new int[X + 1];
        dp[0] = 1; // 아무것도 안 쓰면 길이 0을 만드는 한 가지 방법 존재

        // 3. 파이프 종류별로 처리
        for (int pipe = 0; pipe < N; pipe++) {
            int length = L[pipe];
            int count = C[pipe];

            for (int x = X; x >= 0; x--) {
                if (dp[x] == 0)
                    continue;

                for (int c = 1; c <= count; c++) {
                    int next = x + length * c;
                    if (next > X)
                        break;

                    dp[next] += dp[x];
                }
            }
        }

        // 4. 출력
        bw.write(Integer.toString(dp[X]));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
