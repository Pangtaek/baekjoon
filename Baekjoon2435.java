import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon2435 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = tokens[0];
        int K = tokens[1];

        int[] temps = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int sum = 0;
        for (int i = 0; i < K; i++) {
            sum += temps[i];
        }

        int max = sum;

        for (int i = K; i < N; i++) {
            sum = sum - temps[i - K] + temps[i];
            max = Math.max(max, sum);
        }

        bw.write(Integer.toString(max));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
