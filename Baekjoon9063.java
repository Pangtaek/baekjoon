import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon9063 {
    public static void main(String[] args) throws IOException {
        System.out.println(new Baekjoon9063().answer());
    }

    public int answer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int answer = 0;

        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;

        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;

        int inputX;
        int inputY;

        int N = Integer.parseInt(br.readLine());

        if (N == 0) {
            return 0;
        }

        String[] token;

        for (int i = 0; i < N; i++) {
            token = br.readLine().split(" ");
            inputX = Integer.parseInt(token[0]);
            inputY = Integer.parseInt(token[1]);

            if (inputX < xMin) {
                xMin = inputX;
            }
            if (inputX > xMax) {
                xMax = inputX;
            }
            if (inputY < yMin) {
                yMin = inputY;
            }
            if (inputY > yMax) {
                yMax = inputY;
            }
        }
        br.close();

        answer = (xMax - xMin) * (yMax - yMin);

        return answer;
    }
}
