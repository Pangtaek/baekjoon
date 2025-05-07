import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Baekjoon4195 {

    public static Map<String, Integer> nameToId;
    public static int[] parent;
    public static int[] size;
    public static int idCounter;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int test = 0; test < TC; test++) {
            int F = Integer.parseInt(br.readLine()); // 친구 관계 수

            // 전역변수 초기화
            nameToId = new HashMap<>();
            parent = new int[F * 2];
            size = new int[F * 2];
            idCounter = 0;

            for (int i = 0; i < F; i++) {
                String[] tokens = br.readLine().split(" ");
                String name1 = tokens[0];
                String name2 = tokens[1];

                int id1 = getId(name1);
                int id2 = getId(name2);

                union(id1, id2);

                System.out.println(size[find(id1)]);
            }
        }
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }
    }

    public static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    // 이름은 ID로 매핑하고 초기화
    public static int getId(String name) {
        if (!nameToId.containsKey(name)) {
            nameToId.put(name, idCounter);
            parent[idCounter] = idCounter;
            size[idCounter] = 1;

            return idCounter++;
        }

        return nameToId.get(name);
    }
}
