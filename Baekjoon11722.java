import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon11722 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 수열의 크기
        int[] arr = new int[N + 1]; // 1-based index
        int[] dp = new int[N + 1]; // dp[i] = i번째 수로 끝나는 LDS 길이

        int[] origin = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int i = 0; i < N; i++) {
            arr[i + 1] = origin[i];
        }

        for (int i = 1; i <= N; i++) {
            dp[i] = 1; // 최소 자기 자신
            for (int j = 1; j < i; j++) {
                if (arr[j] > arr[i]) { // 감소하는 경우만 고려
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int result = Arrays.stream(dp)
                .max()
                .getAsInt();
        bw.write(Integer.toString(result));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
