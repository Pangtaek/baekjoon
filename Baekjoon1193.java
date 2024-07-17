import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1193 {
    public static void main(String[] args) throws IOException {
        Baekjoon1193 baekjoon1193 = new Baekjoon1193();
        baekjoon1193.answer();
    }

    public void answer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        br.close();

        int count = 1;
        int sum = 0;

        while (true) {
            if (X <= count + sum) {
                if (count % 2 == 1) {
                    // count가 홀수면 방향은 위(분자는 작아진다)
                    System.out.println(count - (X - sum - 1) + "/" + (X - sum));
                    break;
                } else {
                    // count가 짝수면 방향은 아래(분자는 커진다)
                    System.out.println((X - sum) + "/" + (count - (X - sum - 1)));
                    break;
                }
            } else {
                sum += count;
                count++;
            }
        }
    }
}
