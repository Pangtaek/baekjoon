import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon20044 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int N = Integer.parseInt(br.readLine()); // 팀의 수
            int[] W = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Arrays.sort(W);

            int answer = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                answer = Math.min(answer, W[i] + W[2 * N - i - 1]);
            }

            bw.write(answer + "\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
