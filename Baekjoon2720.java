import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon2720 {
    public static void main(String[] args) throws IOException {
        Baekjoon2720 baekjoon2720 = new Baekjoon2720();
        int[][] arr = baekjoon2720.answer();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] answer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[][] answer = new int[T][4];

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            answer[i][0] = n / 25;
            n = n % 25;

            answer[i][1] = n / 10;
            n = n % 10;

            answer[i][2] = n / 5;

            answer[i][3] = n % 5;
        }
        br.close();

        return answer;
    }
}
