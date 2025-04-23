import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Baekjoon13549 {
    public static final int INF = Integer.MAX_VALUE;
    public static final int MAX = 100_001;
    public static int[] visited = new int[MAX];

    public static class Node {
        int position;
        int time;

        public Node(int position, int time) {
            this.position = position;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int N = tokens[0]; // 시작 위치
        int K = tokens[1]; // 목표 위치

        Arrays.fill(visited, INF);
        bfs(N);

        System.out.println(visited[K]);
    }

    public static void bfs(int start) {
        Deque<Node> deque = new ArrayDeque<>();
        deque.offer(new Node(start, 0));
        visited[start] = 0;

        while (!deque.isEmpty()) {
            Node curr = deque.poll();

            // 순간이동 (시간 0초)
            int next = curr.position * 2;
            if (next < MAX && visited[next] > curr.time) {
                visited[next] = curr.time;
                deque.offerFirst(new Node(next, curr.time));
            }

            // 앞으로 이동 (시간 1초)
            next = curr.position + 1;
            if (next < MAX && visited[next] > curr.time + 1) {
                visited[next] = curr.time + 1;
                deque.offerLast(new Node(next, curr.time + 1));
            }

            // 뒤로 이동 (시간 1초)
            next = curr.position - 1;
            if (next >= 0 && visited[next] > curr.time + 1) {
                visited[next] = curr.time + 1;
                deque.offerLast(new Node(next, curr.time + 1));
            }
        }
    }
}
