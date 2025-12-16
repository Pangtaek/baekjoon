import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1717 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] tokens = br.readLine().split("\\s+");
            int n = Integer.parseInt(tokens[0]); // 집합의 개수(0~n)
            int m = Integer.parseInt(tokens[1]); // 연산의 개수

            int[] parent = new int[n + 1];

            for (int i = 0; i <= n; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < m; i++) {
                tokens = br.readLine().split("\\s+");
                int option = Integer.parseInt(tokens[0]);
                int a = Integer.parseInt(tokens[1]);
                int b = Integer.parseInt(tokens[2]);

                if (option == 1) {
                    if (find(a, parent) == find(b, parent)) {
                        bw.write("YES\n");
                    } else {
                        bw.write("NO\n");
                    }
                } else if (option == 0) {
                    union(a, b, parent);
                }
            }

            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int find(int x, int[] parent) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x], parent);
    }

    private static void union(int x, int y, int[] parent) {
        int rootX = find(x, parent);
        int rootY = find(y, parent);

        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
}
