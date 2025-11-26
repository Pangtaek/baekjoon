import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon1486 {

    private static final int[] dx = { -1, 0, 1, 0 };
    private static final int[] dy = { 0, -1, 0, 1 };

    private static int N, M, T, D;
    private static int[][] heightMap;

    private static class Node {
        public int y, x;
        public int time;

        Node(int y, int x, int time) {
            this.y = y; // 행
            this.x = x; // 열
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 행
            M = Integer.parseInt(st.nextToken()); // 열
            T = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            heightMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    char c = line.charAt(j);
                    if ('A' <= c && c <= 'Z') {
                        heightMap[i][j] = c - 'A';
                    } else {
                        heightMap[i][j] = c - 'a' + 26;
                    }
                }
            }

            int answer = 0;
            int[][] distGo = dijkstra(true);
            int[][] distBack = dijkstra(false);

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    long sum = (long) distGo[y][x] + distBack[y][x];
                    if (distGo[y][x] != Integer.MAX_VALUE && distBack[y][x] != Integer.MAX_VALUE && sum <= D) {
                        answer = Math.max(answer, heightMap[y][x]);
                    }
                }
            }

            bw.write(String.valueOf(answer));
            bw.flush();
        }
    }

    private static int[][] dijkstra(boolean forward) {
        int[][] distance = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((node1, node2) -> node1.time - node2.time);
        distance[0][0] = 0;
        pq.offer(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (distance[cur.y][cur.x] < cur.time)
                continue;

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M)
                    continue;
                if (Math.abs(heightMap[ny][nx] - heightMap[cur.y][cur.x]) > T)
                    continue;

                int nextTime;
                if (forward) {
                    // 호텔 → 산
                    if (heightMap[ny][nx] <= heightMap[cur.y][cur.x]) {
                        nextTime = cur.time + 1;
                    } else {
                        int diff = heightMap[ny][nx] - heightMap[cur.y][cur.x];
                        nextTime = cur.time + diff * diff;
                    }
                } else {
                    // 산 → 호텔
                    if (heightMap[ny][nx] >= heightMap[cur.y][cur.x]) {
                        nextTime = cur.time + 1;
                    } else {
                        int diff = heightMap[cur.y][cur.x] - heightMap[ny][nx];
                        nextTime = cur.time + diff * diff;
                    }
                }

                if (nextTime < distance[ny][nx]) {
                    distance[ny][nx] = nextTime;
                    pq.offer(new Node(ny, nx, nextTime));
                }
            }
        }

        return distance;
    }
}
