import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Baekjoon1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        // LinkedList를 사용하여 원형 큐 구현
        LinkedList<Integer> people = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            people.add(i);
        }

        StringBuilder result = new StringBuilder();
        result.append("<");

        int index = 0;  // 현재 인덱스
        while (!people.isEmpty()) {
            // (K-1)만큼 이동한 후 K번째 사람 제거
            index = (index + K - 1) % people.size();
            result.append(people.remove(index)); // 제거한 사람 추가
            if (!people.isEmpty()) {
                result.append(", "); // 다음 사람과 구분
            }
        }

        result.append(">");
        System.out.println(result);
    }
}
