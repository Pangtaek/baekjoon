import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baekjoon2501 {
    public static void main(String[] args) throws IOException {
        Baekjoon2501 baekjoon2501 = new Baekjoon2501();
        System.out.println(baekjoon2501.answer());
    }

    public int answer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int k = Integer.parseInt(tokens[1]);

        ArrayList<Integer> divisors = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
            }
        }

        if (divisors.size() < k) {
            return 0;
        } else {
            return divisors.get(k - 1);
        }
    }
}
