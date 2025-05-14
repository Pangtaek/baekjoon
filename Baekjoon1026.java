import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Baekjoon1026 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        int[] B = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        int sum = 0;

        for (int i = 0; i < N; i++) {
            sum += A[i] * B[i];
        }

        bw.write(Integer.toString(sum));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}