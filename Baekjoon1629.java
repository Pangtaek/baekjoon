import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon1629 {

    public static long A, B, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split("\\s+"))
                            .mapToInt(Integer::parseInt).toArray();

        A = input[0];
        B = input[1];
        C = input[2];

        System.out.println(power(A, B) % C);
    }

    // 모듈러 연산: (a * b) % c = ((a % c) * (b % c)) % c
    public static long power(long base, long exp) {
        if (exp == 1) {
            return base;
        }

        long temp = power(base, exp / 2);
        long result = (temp * temp);

        if (exp % 2 == 1) {
            result = result * base;
        }

        return result;
    }
}
