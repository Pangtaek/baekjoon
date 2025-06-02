import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Baekjoon13913 {
    static final int MAX = 100_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int N = tokens[0];
        int K = tokens[1];

        int[] visited = new int[MAX];
        int[] prev = new int[MAX];
        Arrays.fill(visited, -1);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(N);
        visited[N] = 0;
        prev[N] = -1; // 시작점

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (curr == K)
                break;

            for (int next : new int[] { curr * 2, curr - 1, curr + 1 }) {
                if (next >= 0 && next < MAX && visited[next] == -1) {
                    visited[next] = visited[curr] + 1;
                    prev[next] = curr;
                    queue.offer(next);
                }
            }
        }

        // 경로 복원
        List<Integer> path = new ArrayList<>();
        for (int i = K; i != -1; i = prev[i]) {
            path.add(i);
        }
        Collections.reverse(path);

        bw.write(Integer.toString(visited[K]));
        bw.newLine();
        for (int pos : path) {
            bw.write(pos + " ");
        }
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
