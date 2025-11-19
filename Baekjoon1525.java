import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Baekjoon1525 {

    private static final int PUZZLE_SIZE = 3;
    private static final String GOAL = "123456780";
    private static final int[][] DIRECTIONS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } }; // 하, 상, 우, 좌

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringBuilder startStateBuilder = new StringBuilder();
            for (int i = 0; i < PUZZLE_SIZE; i++) {
                String[] line = br.readLine().split(" ");
                for (String s : line) {
                    startStateBuilder.append(s);
                }
            }
            String start = startStateBuilder.toString();

            int result = aStar(start);
            bw.write(result + "\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int aStar(String start) {
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost + s.heuristic));
        Map<String, Integer> distance = new HashMap<>();

        int startHeuristic = manhattan(start);
        pq.add(new State(start, startHeuristic, 0));
        distance.put(start, 0);

        while (!pq.isEmpty()) {
            State current = pq.poll();

            if (current.puzzle.equals(GOAL)) {
                return current.cost;
            }
            if (distance.getOrDefault(current.puzzle, Integer.MAX_VALUE) < current.cost) {
                continue;
            }

            int zeroIndex = current.puzzle.indexOf('0');
            int x = zeroIndex / PUZZLE_SIZE;
            int y = zeroIndex % PUZZLE_SIZE;

            for (int[] direction : DIRECTIONS) {
                int nextX = x + direction[0];
                int nextY = y + direction[1];
                if (nextX >= 0 && nextX < PUZZLE_SIZE && nextY >= 0 && nextY < PUZZLE_SIZE) {
                    int nextIndex = nextX * PUZZLE_SIZE + nextY;
                    String nextPuzzle = swap(current.puzzle, zeroIndex, nextIndex);
                    int nextCost = current.cost + 1;

                    if (nextCost < distance.getOrDefault(nextPuzzle, Integer.MAX_VALUE)) {
                        distance.put(nextPuzzle, nextCost);
                        int heuristic = manhattan(nextPuzzle);
                        pq.add(new State(nextPuzzle, heuristic, nextCost));
                    }
                }
            }
        }
        return -1;
    }

    private static int manhattan(String puzzle) {
        int total = 0;
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            if (puzzle.charAt(i) == '0')
                continue;
            int num = puzzle.charAt(i) - '1';
            int curX = i / PUZZLE_SIZE;
            int curY = i % PUZZLE_SIZE;
            int goalX = num / PUZZLE_SIZE;
            int goalY = num % PUZZLE_SIZE;
            total += Math.abs(curX - goalX) + Math.abs(curY - goalY);
        }
        return total;
    }

    private static String swap(String puzzle, int i, int j) {
        char[] arr = puzzle.toCharArray();
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return new String(arr);
    }

    private static class State {
        private String puzzle;
        private int heuristic;
        private int cost;

        public State(String puzzle, int heuristic, int cost) {
            this.puzzle = puzzle;
            this.heuristic = heuristic;
            this.cost = cost;
        }
    }
}
