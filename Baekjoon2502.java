import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon2502 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int D = tokens[0]; // D일차
        int K = tokens[1]; // D일차에 준 떡 개수

        int[] a = new int[31]; // A의 계수
        int[] b = new int[31]; // B의 계수

        a[1] = 1;
        b[1] = 0;
        a[2] = 0;
        b[2] = 1;

        for (int i = 3; i <= D; i++) {
            a[i] = a[i - 1] + a[i - 2];
            b[i] = b[i - 1] + b[i - 2];
        }

        for (int x = 1; x <= K; x++) {
            int By = K - a[D] * x;
            if (By % b[D] == 0) {
                int y = By / b[D];
                if (y >= x) {
                    bw.write(Integer.toString(x) + "\n" + Integer.toString(y));
                    bw.newLine();
                    break;
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
