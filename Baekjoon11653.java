import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon11653 {
    public static void main(String[] args) throws IOException {
        new Baekjoon11653().answer();
    }

    public void answer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 2; i <= N; i++) {
            while (N % i == 0) {
                System.out.println(i);
                N /= i;
            }
        }
        br.close();
    }
}
