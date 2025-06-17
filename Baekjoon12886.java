import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Baekjoon12886 {
    private static boolean[][] visited;

    private static class Stone {
        int A, B, C;

        Stone(int a, int b, int c) {
            this.A = a;
            this.B = b;
            this.C = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int A = tokens[0], B = tokens[1], C = tokens[2];
        int sum = A + B + C;

        if (sum % 3 != 0) {
            bw.write("0\n");
        } else {
            visited = new boolean[1501][1501];
            if (bfs(A, B, C)) {
                bw.write("1\n");
            } else {
                bw.write("0\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean bfs(int a, int b, int c) {
        Queue<Stone> queue = new ArrayDeque<>();
        queue.add(new Stone(a, b, c));

        while (!queue.isEmpty()) {
            Stone curr = queue.poll();
            int A = curr.A;
            int B = curr.B;
            int C = curr.C;

            if (A == B && B == C)
                return true;

            int[] arr = new int[] { A, B, C };
            Arrays.sort(arr);
            A = arr[0];
            B = arr[1];
            C = arr[2];

            if (visited[A][B])
                continue;
            visited[A][B] = true;

            move(queue, A, B, C);
            move(queue, A, C, B);
            move(queue, B, C, A);
        }

        return false;
    }

    private static void move(Queue<Stone> queue, int x, int y, int z) {
        if (x == y)
            return;

        int small = Math.min(x, y);
        int big = Math.max(x, y);
        int newX = small + small;
        int newY = big - small;
        queue.add(new Stone(newX, newY, z));
    }
}
