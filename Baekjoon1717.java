import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon1717 {
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int n = tokens[0];
        int m = tokens[1];

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++)
            parent[i] = i; // 자기 자신이 부모

        while (m-- > 0) {
            tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int op = tokens[0];
            int a = tokens[1];
            int b = tokens[2];

            if (op == 0) {
                union(a, b);
            } else {
                System.out.println(find(a) == find(b) ? "YES" : "NO");
            }
        }
    }

    public static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]); // 경로 압축
        return parent[x];
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB)
            parent[rootB] = rootA;
    }
}
