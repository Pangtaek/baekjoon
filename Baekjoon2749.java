import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon2749 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            long n = Long.parseLong(br.readLine());
            final int PISANO = 1_500_000;
            final int MOD = 1_000_000;
            int[] fibonacci = new int[PISANO];

            fibonacci[0] = 0;
            fibonacci[1] = 1;

            for (int i = 2; i < PISANO; i++) {
                fibonacci[i] = (fibonacci[i - 2] + fibonacci[i - 1]) % MOD;
            }

            bw.write(String.valueOf(fibonacci[(int) (n % PISANO)]) + "\n");
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}