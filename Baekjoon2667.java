import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon2667 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            int[][] town = new int[N][N];
            visit = new boolean[N][N];

            char[] carr;
            for (int i = 0; i < N; i++) {
                carr = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    town[i][j] = Character.getNumericValue(carr[j]);
                }
            }

            int[] answer = solution(town);
            System.out.println(answer.length);
            for(int i: answer){
                System.out.println(i);
            }

        } catch (IOException e) {
            System.out.println("유근웅");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int[] solution(int[][] town) {
        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 0; i < town.length; i++) {
            for (int j = 0; j < town[i].length; j++) {
                if (town[i][j] == 1
                        && !visit[i][j]) {
                    answer.add(bfs(town, i, j));
                }
            }
        }

        return answer.stream().mapToInt(i -> i).sorted().toArray();
    }

    static Queue<Position2D> queue = new LinkedList<>();
    static boolean[][] visit;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static int bfs(int[][] town, int i, int j) {
        int count = 1;
        visit[i][j] = true;
        queue.add(new Position2D(i, j));

        while (!queue.isEmpty()) {
            Position2D current = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nx = current.x + dx[k];
                int ny = current.y + dy[k];

                if (checkBound(town, nx, ny) && town[nx][ny] == 1 && !visit[nx][ny]) {
                    queue.add(new Position2D(nx, ny));
                    visit[nx][ny] = true;
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean checkBound(int[][] town, int i, int j) {
        return i >= 0 && j >= 0 && i < town.length && j < town[i].length;
    }

    public static class Position2D {
        int x;
        int y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
