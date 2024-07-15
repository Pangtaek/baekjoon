import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon2566 {
    public static void main(String[] args) throws IOException {
        Baekjoon2566 baekjoon2566 = new Baekjoon2566();
        int[] result = baekjoon2566.answer();
        System.out.println(result[0]);
        System.out.println(result[1] + " " + result[2]);
    }

    public int[] answer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] answer = new int[3];
        int max = -1;

        for (int i = 0; i < 9; i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str); // 각 줄을 공백으로 나눈다.
            for (int j = 0; j < 9; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n > max) {
                    max = n;
                    answer[0] = max;
                    answer[1] = i + 1; // 위치는 1부터 시작하므로 +1
                    answer[2] = j + 1; // 위치는 1부터 시작하므로 +1
                }
            }
        }

        br.close();

        return answer;
    }
}
