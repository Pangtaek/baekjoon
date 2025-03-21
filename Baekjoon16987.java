import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon16987 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Egg[] eggs = new Egg[N];

        for (int i = 0; i < N; i++) {
            int[] data = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            eggs[i] = new Egg(data[0], data[1]);
        }

        System.out.println(solution(eggs));
    }

    public static int solution(Egg[] eggs) {
        return dfs(eggs, 0, 0, 0);
    }

    public static int dfs(Egg[] eggs, int index, int count, int maxBrokenEggs) {
        if (index == eggs.length) {
            return Math.max(count, maxBrokenEggs);
        }

        if (eggs[index].durability <= 0) {
            return dfs(eggs, index + 1, count, maxBrokenEggs);
        }

        boolean isHit = false;
        for (int i = 0; i < eggs.length; i++) {
            if (index == i || eggs[i].durability <= 0) continue;

            isHit = true;

            eggs[index].durability -= eggs[i].weight;
            eggs[i].durability -= eggs[index].weight;

            int broken = 0;
            if (eggs[index].durability <= 0) broken++;
            if (eggs[i].durability <= 0) broken++;

            int result = dfs(eggs, index + 1, count + broken, maxBrokenEggs);
            maxBrokenEggs = Math.max(maxBrokenEggs, result);

            // 복구
            eggs[index].durability += eggs[i].weight;
            eggs[i].durability += eggs[index].weight;
        }

        if (!isHit) {
            return dfs(eggs, index + 1, count, maxBrokenEggs);
        }

        return maxBrokenEggs;
    }

    public static class Egg {
        public int durability;
        public int weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }
}
