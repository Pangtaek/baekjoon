import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon9086 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] result = answer(br, n);

        for (String str : result) {
            System.out.println(str);
        }
    }

    public static String[] answer(BufferedReader br, int n) throws IOException {
        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            answer[i] = str.substring(0, 1) + str.substring(str.length() - 1);
        }

        return answer;
    }
}
