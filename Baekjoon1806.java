import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int N = tokens[0];
        int S = tokens[1];

        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int left = 0;
        int right = 0;
        int sum = arr[0];
        int minLength = Integer.MAX_VALUE;

        while (true) {
            if (sum >= S) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= arr[left++];
            } else {
                if (right == N - 1)
                    break;
                sum += arr[++right];
            }
        }

        bw.write(Integer.toString((minLength == Integer.MAX_VALUE ? 0 : minLength)));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
