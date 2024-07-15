import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Baekjoon10798
 */
public class Baekjoon10798 {

    public static void main(String[] args) throws IOException {
        Baekjoon10798 baekjoon10798 = new Baekjoon10798();
        System.out.println(baekjoon10798.answer());
    }

    public StringBuilder answer() throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] arr = new char[5][15];

        for (int i = 0; i < 5; i++) {
            Arrays.fill(arr[i], ' ');
        }

        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                if (arr[j][i] != ' ') {
                    answer.append(arr[j][i]);
                }
            }
        }

        return answer;
    }
}