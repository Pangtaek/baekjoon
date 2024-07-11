import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon2444 {
    public static void main(String[] args) throws IOException {
        // 별의 개수: 2*(n+1) -1
        // 별이 길이: N=5 -> 2×N-1 -> 9

        Baekjoon2444 baekjoon2444 = new Baekjoon2444();
        baekjoon2444.answer();
    }

    public void answer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < N - i; j++)
                System.out.print(" ");
            for (int j = 0; j < i * 2 - 1; j++)
                System.out.print("*");
            System.out.println();
        }

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < N - i; j++)
                System.out.print(" ");
            for (int j = 0; j < i * 2 - 1; j++)
                System.out.print("*");
            System.out.println();

        }

        br.close();
    }
}
