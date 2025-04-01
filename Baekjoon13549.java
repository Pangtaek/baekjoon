import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Baekjoon13549 {
    static final int MAX = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dist = new int[MAX];
        Arrays.fill(dist, -1);

        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(N);
        dist[N] = 0;

        while (!dq.isEmpty()) {
            int curr = dq.poll();

            if (curr == K)
                break;

            // 순간이동: 0초
            int next = curr * 2;
            if (next < MAX && dist[next] == -1) {
                dist[next] = dist[curr];
                dq.offerFirst(next); // 0초이기 때문에 앞에 넣음
            }

            // -1 이동: 1초
            next = curr - 1;
            if (next >= 0 && dist[next] == -1) {
                dist[next] = dist[curr] + 1;
                dq.offerLast(next);
            }

            // +1 이동: 1초
            next = curr + 1;
            if (next < MAX && dist[next] == -1) {
                dist[next] = dist[curr] + 1;
                dq.offerLast(next);
            }
        }

        System.out.println(dist[K]);
    }
}
