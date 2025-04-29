import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Baekjoon18112 {
    public static Set<String> visited = new HashSet<>();

    public static class State {
        String binary;
        int steps;

        public State(String binary, int steps) {
            this.binary = binary;
            this.steps = steps;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String start = br.readLine();
        String target = br.readLine();

        int result = bfs(start, target);
        System.out.println(result);
    }

    public static int bfs(String start, String target) {
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(start, 0));
        visited.add(start);

        while (!queue.isEmpty()) {
            State current = queue.poll();
            String curr = current.binary;
            int step = current.steps;

            if (curr.equals(target)) {
                return step;
            }

            // 1. 보수 연산 (MSB 제외)
            for (int i = 1; i < curr.length(); i++) {
                char[] chars = curr.toCharArray();
                chars[i] = (chars[i] == '0') ? '1' : '0';
                String flipped = new String(chars);
                if (!visited.contains(flipped)) {
                    visited.add(flipped);
                    queue.offer(new State(flipped, step + 1));
                }
            }

            // 2. +1 연산
            int decimal = Integer.parseInt(curr, 2) + 1;
            String plusOne = Integer.toBinaryString(decimal);
            if (!visited.contains(plusOne)) {
                visited.add(plusOne);
                queue.offer(new State(plusOne, step + 1));
            }

            // 3. -1 연산 (단, 0이면 불가)
            decimal = Integer.parseInt(curr, 2);
            if (decimal > 0) {
                decimal -= 1;
                String minusOne = Integer.toBinaryString(decimal);
                if (!visited.contains(minusOne)) {
                    visited.add(minusOne);
                    queue.offer(new State(minusOne, step + 1));
                }
            }
        }

        return -1; // 도달 불가능
    }
}
