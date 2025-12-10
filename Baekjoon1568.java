import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1568 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            long N = Long.parseLong(br.readLine()); // 새의 수 (1 <= N <= 1_000_000_000)

            long k = 1; // 이번 턴에 날려 보낼 새의 수
            long turns = 0; // 턴 수

            while (N > 0) {
                if (N < k) {
                    k = 1; // 남은 새보다 많이 보내려 하면, 다시 1마리부터
                }

                N -= k;
                k++;
                turns++;
            }

            bw.write(String.valueOf(turns));
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
