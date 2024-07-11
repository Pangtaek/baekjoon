import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Baekjoon3003
 */
public class Baekjoon3003 {

    public static void main(String[] args) throws IOException {
        Baekjoon3003 baekjoon3003 = new Baekjoon3003();
        for (int n : baekjoon3003.answer()) {
            System.out.print(n + " ");
        }
    }

    public int[] answer() throws IOException {
        String answer[] = new String[6];
        int arr[] = { 1, 1, 2, 2, 2, 8 };

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        answer = str.split(" ");

        br.close();

        for (int i = 0; i < 6; i++) {
            arr[i] -= Integer.parseInt(answer[i]);
        }

        return arr;
    }
}