import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon10101 {
    public static void main(String[] args) throws IOException {
        System.out.println(new Baekjoon10101().answer());
    }

    public String answer() throws IOException {
        String answer = "Error";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = new int[3];

        for (int i = 0; i < 3; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        br.close();

        if (input[0] == 60 && input[0] == input[1] && input[1] == input[2]) {
            return "Equilateral";
        }

        else if (input[0] + input[1] + input[2] == 180
                && (input[0] == input[1] || input[0] == input[2] || input[1] == input[2])) {
            return "Isosceles";
        }

        else if (input[0] + input[1] + input[2] == 180
                && (input[0] != input[1] && input[0] != input[2] && input[1] != input[2])) {
            return "Scalene";
        }

        return answer;
    }
}
