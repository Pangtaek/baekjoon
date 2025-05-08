import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon2526 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int N = tokens[0];
        int P = tokens[1];

        System.out.println(solution(N, P));
    }

    public static int solution(int N, int P) {
        int[] visited = new int[P];
        int current = N;
        int index = 1;

        while (true) {
            current = (current * N) % P;

            if (visited[current] != 0) {
                return index - visited[current];
            }

            visited[current] = index;
            index++;
        }
    }

}
