import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Baekjoon2812 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int N = tokens[0];
            int K = tokens[1];

            int[] arr = br.readLine().chars()
                    .map(Character::getNumericValue)
                    .toArray();

            int[] resultArr = solve(arr, K);

            for (int num : resultArr) {
                bw.write(num + "");
            }
            bw.write("\n");
            bw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int[] solve(int[] arr, int count) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int num : arr) {
            while (!stack.isEmpty() && count > 0 && stack.peek() < num) {
                stack.pop();
                count--;
            }
            stack.push(num);
        }

        while (count-- > 0) {
            stack.pop();
        }

        int size = stack.size();
        int[] result = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }
}