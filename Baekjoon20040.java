import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon20040 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = tokens[0]; // 점의 개수
        int m = tokens[1]; // 진행된 차례의 수
        int[] parent = new int[n]; // 부모의 인덱스

        // 부모 배열 초기화
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int answer = 0;
        for (int gameTurn = 1; gameTurn <= m; gameTurn++) {
            tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int a = tokens[0];
            int b = tokens[1];

            if (findRoot(a, parent) == findRoot(b, parent)) {
                answer = gameTurn;
                break;
            }

            union(a, b, parent);
        }

        System.out.println(answer);
    }

    public static void union(int a, int b, int[] parent) {
        int rootA = findRoot(a, parent);
        int rootB = findRoot(b, parent);
        
        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }

    public static int findRoot(int num, int[] parent) {
        if (parent[num] != num) {
            parent[num] = findRoot(parent[num], parent);
        }

        return parent[num];
    }
}