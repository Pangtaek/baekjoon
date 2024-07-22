import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Baekjoon9506 {
    public static void main(String[] args) throws IOException {
        new Baekjoon9506().answer();
    }

    public void answer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n;

        while (true) {
            n = Integer.parseInt(br.readLine());

            if (n == -1)
                break;

            ArrayList<Integer> arr = new ArrayList<>();
            int sum = 0;

            for (int i = 1; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {

                    arr.add(i);
                    sum += i;

                    int otherDivisor = n / i;
                    if (otherDivisor != i && otherDivisor != n) {
                        arr.add(otherDivisor);
                        sum += otherDivisor;
                    }
                }
            }

            Collections.sort(arr); // 약수를 오름차순으로 정렬합니다.

            StringBuilder answer = new StringBuilder();
            if (sum == n) {
                answer.append(n).append(" = ");
                for (int i = 0; i < arr.size(); i++) {
                    if (i != arr.size() - 1) {
                        answer.append(arr.get(i)).append(" + ");
                    } else {
                        answer.append(arr.get(i));
                    }
                }
            } else {
                answer.append(n).append(" is NOT perfect.");
            }

            System.out.println(answer.toString());
        }
    }
}
