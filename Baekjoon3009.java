import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Baekjoon3009
 */
public class Baekjoon3009 {

    public static void main(String[] args) throws IOException {
        new Baekjoon3009().answer();
    }

    public void answer() throws IOException {
        int answerX = 1, answerY = 1;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int x1, x2;
        int y1, y2;
        String[] token = new String[2];
        token = br.readLine().split(" ");

        x1 = Integer.parseInt(token[0]);
        y1 = Integer.parseInt(token[1]);

        token = br.readLine().split(" ");

        x2 = Integer.parseInt(token[0]);
        y2 = Integer.parseInt(token[1]);

        token = br.readLine().split(" ");

        if (x1 == x2) {
            answerX = Integer.parseInt(token[0]);
        } else if (x1 != x2) {
            int x3 = Integer.parseInt(token[0]);

            if (x1 == x3) {
                answerX = x2;
            } else {
                answerX = x1;
            }
        }

        if (y1 == y2) {
            answerY = Integer.parseInt(token[1]);
        } else if (y1 != y2) {
            int y3 = Integer.parseInt(token[1]);

            if (y1 == y3) {
                answerY = y2;
            } else {
                answerY = y1;
            }
        }
        br.close();

        System.out.println(answerX + ", " + answerY);
    }
}