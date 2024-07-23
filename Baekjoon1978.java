import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1978 {
    public static void main(String[] args) throws IOException {
        System.out.println(new Baekjoon1978().answer());
    }

    public int answer() throws IOException {
        int answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] token = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(token[i]);
            if (isPrime(n))
                answer++;
        }

        br.close();

        return answer;
    }

    public boolean isPrime(int input) {
        if (input <= 1)
            return false; // 1 이하의 수는 소수가 아님
        if (input == 2)
            return true; // 2는 소수
        if (input % 2 == 0)
            return false; // 2를 제외한 짝수는 소수가 아님

        for (int i = 3; i <= Math.sqrt(input); i += 2) { // 3부터 홀수만 검사
            if (input % i == 0) {
                return false; // 나누어 떨어지는 수가 있으면 소수가 아님
            }
        }

        return true; // 나누어 떨어지는 수가 없으면 소수
    }
}
