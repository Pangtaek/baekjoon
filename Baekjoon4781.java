import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon4781 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String input = br.readLine();
            if (input.equals("0 0.00"))
                break;

            String[] tokens = input.split(" ");
            int N = Integer.parseInt(tokens[0]); // 사탕 종류 수
            float M = Float.parseFloat(tokens[1]); // 가진 돈

            int money = floatToInt(M);
            int[] C = new int[N]; // 칼로리
            int[] P = new int[N]; // 가격 (정수로 변환)

            for (int candy = 0; candy < N; candy++) {
                tokens = br.readLine().split(" ");
                C[candy] = Integer.parseInt(tokens[0]);
                P[candy] = floatToInt(Float.parseFloat(tokens[1]));
            }

            int[] dp = new int[money + 1];

            for (int candy = 0; candy < N; candy++) {
                for (int price = P[candy]; price <= money; price++) {
                    dp[price] = Math.max(dp[price], dp[price - P[candy]] + C[candy]);
                }
            }

            bw.write(Integer.toString(dp[money]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int floatToInt(float num) {
        return (int) (num * 100 + 0.5);
    }
}
