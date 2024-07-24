import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon2581 {
    public static void main(String[] args) throws IOException {
        new Baekjoon2581().answer();

    }

    /**
     * input
     * 60
     * 100
     */

    /**
     * output
     * 620
     * 61
     */

    public void answer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int sum = 0;
        int min = Integer.MAX_VALUE;

        for (int i = N; i <= M; i++) {

            // 1은 소수가 아님
            if (i == 1)
                continue;
            // 2는 소수임
            else if (i == 2) {
                sum += i;
                min = i;
                continue;
            }
            // 2 이외의 수가 2로 나누어 떨어지면 소수가 아님
            else if (i % 2 == 0) {
                continue;
            }
            // 나머지 경우
            else {
                if (isPrime(i)) {
                    sum += i;
                    if (min > i)
                        min = i;
                }
            }

        }

        br.close();

        if (!(sum == 0)) {
            System.out.println(sum);
            System.out.println(min);
        } else
            System.out.println(-1);
    }

    private boolean isPrime(int num) {
        if (num < 2)
            return false;
        if (num == 2)
            return true;
        if (num % 2 == 0)
            return false;
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0)
                return false;
        }
        return true;
    }
}
