import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1459 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] tokens = br.readLine().split("\\s+");
            long X = Long.parseLong(tokens[0]);
            long Y = Long.parseLong(tokens[1]);
            long W = Long.parseLong(tokens[2]); // 한 블록 가는데 걸리는 시간
            long S = Long.parseLong(tokens[3]); // 대각선으로 한 블록을 가로지르는 시간

            long max = Math.max(X, Y);
            long min = Math.min(X, Y);

            // 1) 직선만 사용하는 경우
            long t1 = (X + Y) * W;

            long answer;

            if (S >= 2 * W) {
                // 대각선이 직선 두 번보다 비싸면, 직선만 사용
                answer = t1;
            } else if (S >= W) {
                // 대각선이 직선 두 번보단 싸지만, 직선 한 번보다는 비싸거나 같을 때
                // 공통 부분은 대각선, 나머지는 직선
                long t2 = min * S + (max - min) * W;
                answer = Math.min(t1, t2);
            } else {
                // 대각선이 직선 한 번보다도 쌀 때
                // 최대한 대각선 사용
                long t3;
                if ((X + Y) % 2 == 0) {
                    // 짝수이면 전부 대각선으로 도달 가능
                    t3 = max * S;
                } else {
                    // 홀수이면 마지막 한 칸은 직선으로
                    t3 = (max - 1) * S + W;
                }
                answer = Math.min(t1, t3);
            }

            bw.write(answer + "\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
