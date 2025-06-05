import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int N = tokens[0];
        int M = tokens[1];

        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int left = 0;
        int right = 0;
        int sum = 0;
        int count = 0;

        while (true) {
            if (sum >= M) {
                sum -= arr[left++];
            } else if (right == N) {
                break;
            } else {
                sum += arr[right++];
            }

            if (sum == M) {
                count++;
            }
        }

        bw.write(Integer.toString(count));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
