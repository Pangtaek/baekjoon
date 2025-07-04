import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon3108 {
    
    private static Rectangle[] rectangles;
    private static boolean[] visited;
    private static int N;
    private static int answer;

    private static class Rectangle {
        private int x1, y1, x2, y2;

        private Rectangle(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    private static boolean isOverlap(Rectangle a, Rectangle b) { // 사각형 겹침 여부 판단
        // 아래 조건 중 하나라도 TRUE라면 두 사각형은 절대 겹치지 않는다.
        // 1. A의 오른쪽이 B의 왼쪽보다 더 왼쪽에 있는 경우
        // 2. A의 왼쪽이 B의 오른쪽보다 더 오른쪽에 있는 경우
        // 3. A의 위쪽이 B의 아래쪽보다 더 아래쪽에 있는 경우
        // 4. A의 아래쪽이 B의 위쪽보다 더 위쪽에 있는 경우
        // 여기에 추가로 한 사각형이 다른 사각형 안에 내포된 경우까지 고려해야 함.

        int aLeft = a.x1;
        int aRight = a.x2;
        int aBottom = a.y1;
        int aTop = a.y2;

        int bLeft = b.x1;
        int bRight = b.x2;
        int bBottom = b.y1;
        int bTop = b.y2;

        if (aLeft > bRight || aRight < bLeft || aTop < bBottom || aBottom > bTop || // 겹치지 않는 경우
                (aLeft < bLeft && aRight > bRight && aTop > bTop && aBottom < bBottom) || // a가 b를 내포
                (bLeft < aLeft && bRight > aRight && bTop > aTop && bBottom < aBottom)) { // b가 a를 내포
            return false;
        }

        return true;
    }

    private static void dfs(int idx) { // 점 단위가 아니라 하나의 사각형을 최소단위로 하여 탐색한다.
        for (int i = 0; i < N; i++) {
            if (!visited[i] && isOverlap(rectangles[i], rectangles[idx])) { // 사각형이 겹치면 이동 가능
                visited[i] = true;
                dfs(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        rectangles = new Rectangle[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int x1 = Math.min(tokens[0], tokens[2]);
            int y1 = Math.min(tokens[1], tokens[3]);
            int x2 = Math.max(tokens[0], tokens[2]);
            int y2 = Math.max(tokens[1], tokens[3]);
            rectangles[i] = new Rectangle(x1, y1, x2, y2);

            // 시작점에 사각형이 있는 경우
            if ((x1 == 0 && (y1 <= 0 && y2 >= 0)) || (x2 == 0 && (y1 <= 0 && y2 >= 0)))
                answer = -1;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i);
                answer += 1;
            }
        }

        bw.write(Integer.toString(answer));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}