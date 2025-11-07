import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * 백준 1943번: 동전 분배
 * https://www.acmicpc.net/problem/1943
 * 
 * 풀이 방법:
 * 1. 각 테스트 케이스마다 총 금액의 절반을 목표 금액으로 설정하고, 해당 금액을 만들 수 있는지 확인한다.
 * 2. dp 배열을 사용하여 각 금액을 만들 때 현재 고려 중인 동전을 최대 몇 개까지 남길 수 있는지 기록한다.
 * 3. dp 배열을 갱신하면서 목표 금액을 만들 수 있는지 확인한다.
 * 4. 목표 금액을 만들 수 있으면 1, 없으면 0을 출력한다.
 * 
 * 시간 복잡도: O(N * M), N은 동전 종류 수, M은 목표 금액
 * 공간 복잡도: O(M), M은 목표 금액
 */

public class Baekjoon1943 {

    private static class Item {
        private final int value;
        private final int amount;

        private Item(Builder builder) {
            this.value = builder.value;
            this.amount = builder.amount;
        }

        public static Builder builder() {
            return new Builder();
        }

        public int getValue() {
            return value;
        }

        public int getAmount() {
            return amount;
        }

        public static class Builder {
            private int value;
            private int amount;

            public Builder() {
            }

            public Builder value(int value) {
                this.value = value;
                return this;
            }

            public Builder amount(int amount) {
                this.amount = amount;
                return this;
            }

            public Item build() {
                return new Item(this);
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            for (int testcase = 0; testcase < 3; testcase++) {
                String line = br.readLine();
                if (line == null || line.isEmpty())
                    break;
                int N = Integer.parseInt(line);

                Item[] items = new Item[N];
                int totalSum = 0;

                for (int i = 0; i < N; i++) {
                    int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                            .mapToInt(Integer::parseInt)
                            .toArray();
                    items[i] = Item.builder()
                            .value(tokens[0])
                            .amount(tokens[1])
                            .build();

                    totalSum += tokens[0] * tokens[1];
                }

                // 총액이 홀수면 절반으로 나눌 수 없음
                if (totalSum % 2 != 0) {
                    bw.write("0\n");
                    continue;
                }

                // 목표 금액은 총액의 절반
                String result = solve(items, totalSum / 2);
                bw.write(result + "\n");
            }

            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solve(Item[] items, int target) {
        // dp[i]: 금액 i를 만들 때, '현재 고려 중인 동전'을 최대로 남길 수 있는 개수
        // -1이면 현재까지의 동전들로는 금액 i를 만들 수 없음을 의미
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0; // 0원은 항상 만들 수 있음 (초기값)

        for (Item item : items) {
            int value = item.getValue();
            int amount = item.getAmount();

            for (int j = 0; j <= target; j++) {
                if (dp[j] >= 0) {
                    dp[j] = amount;
                } else if (j >= value && dp[j - value] > 0) {
                    dp[j] = dp[j - value] - 1; // 동전 하나 사용
                } else {
                    dp[j] = -1;
                }
            }
        }

        return dp[target] >= 0 ? "1" : "0";
    }
}