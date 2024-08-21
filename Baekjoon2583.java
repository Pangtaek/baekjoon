import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 문제: 영역 구하기
 * 링크: https://www.acmicpc.net/problem/2583
 * 후기: 간단한 BFS 문제
 * */

public class Baekjoon2583 {

    private static int M;
    private static int N;
    private static int K;
    private static int[][] map;

    private static Queue<Node> queue = new LinkedList<>();
    private static boolean[][] visit;

    private static int[] dirX = {0, 0, -1, 1}; // 좌우
    private static int[] dirY = {-1, 1, 0, 0}; // 상하

    private static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[M][N];
            visit = new boolean[M][N];

            int x1, y1, x2, y2;
            for (int i = 0; i < K; i++) {

                st = new StringTokenizer(br.readLine());

                y1 = Integer.parseInt(st.nextToken());
                x1 = Integer.parseInt(st.nextToken());
                y2 = Integer.parseInt(st.nextToken());
                x2 = Integer.parseInt(st.nextToken());

                for (int j = x1; j < x2; j++) {
                    for (int k = y1; k < y2; k++) {
                        map[j][k] = 1;
                    }
                }
            }

            solution(map);

            Collections.sort(answer);
            System.out.println(answer.size());
            for (int area : answer) {
                System.out.print(area + " ");
            }

        } catch (IOException e) {
            System.out.println("유근웅");
        }
    }

    private static void solution(int[][] map) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 && !visit[i][j]) {
                    bfs(i, j);
                }
            }
        }
    }

    private static void bfs(int x, int y) {
        int count = 1;
        visit[x][y] = true;
        queue.add(new Node(x, y));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dirX[i];
                int ny = node.y + dirY[i];

                if (checkBound(nx, ny) && !visit[nx][ny] && map[nx][ny] == 0) {
                    count++;
                    queue.add(new Node(nx, ny));
                    visit[nx][ny] = true;
                }
            }
        }

        answer.add(count);
    }

    private static boolean checkBound(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    public static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
