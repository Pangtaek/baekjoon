import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon2018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int left = 1;
        int right = 1;
        int sum = 1;
        int count = 0;

        while (right <= N) {
            if (sum == N) {
                count++;
                sum -= left++;
            } else if (sum < N) {
                sum += ++right;
            } else {
                sum -= left++;
            }
        }

        bw.write(Integer.toString(count));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
