import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Baekjoon4195 {

    private static int[] parent;
    private static int[] size;

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int testcase = Integer.parseInt(br.readLine());

            while (testcase-- > 0) {
                int F = Integer.parseInt(br.readLine());

                // 최대 F개의 친구 관계 → 등장하는 사람 수는 최대 2F
                parent = new int[2 * F];
                size = new int[2 * F];
                for (int i = 0; i < 2 * F; i++) {
                    parent[i] = i;
                    size[i] = 1;
                }

                Map<String, Integer> indexByName = new HashMap<>();
                int index = 0;

                for (int f = 0; f < F; f++) {
                    String[] tokens = br.readLine().split("\\s+");
                    String name1 = tokens[0];
                    String name2 = tokens[1];

                    // 이름을 정수 인덱스로 매핑
                    if (!indexByName.containsKey(name1)) {
                        indexByName.put(name1, index++);
                    }
                    if (!indexByName.containsKey(name2)) {
                        indexByName.put(name2, index++);
                    }

                    int a = indexByName.get(name1);
                    int b = indexByName.get(name2);

                    int networkSize = union(a, b);
                    bw.write(String.valueOf(networkSize));
                    bw.write("\n");
                }
            }

            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]); // 경로 압축
    }

    private static int union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return size[rootX];
        }

        // 한쪽으로 합치기 (크기 기준으로 합쳐도 되고, 단순 합쳐도 됨)
        parent[rootY] = rootX;
        size[rootX] += size[rootY];

        return size[rootX];
    }
}
