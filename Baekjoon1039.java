import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Baekjoon1039 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        String N = Integer.toString(tokens[0]);
        int K = tokens[1];

        if (N.length() == 1 || (N.length() == 2 && N.contains("0"))) {
            bw.write("-1\n");
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(N);

        for (int k = 0; k < K; k++) {
            Set<String> visited = new HashSet<>();
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                char[] arr = current.toCharArray();

                for (int a = 0; a < arr.length - 1; a++) {
                    for (int b = a + 1; b < arr.length; b++) {
                        swap(arr, a, b);
                        if (arr[0] != '0') {
                            String next = new String(arr);
                            if (!visited.contains(next)) {
                                visited.add(next);
                                queue.offer(next);
                            }
                        }
                        swap(arr, a, b); // 원상복구
                    }
                }
            }

            // 한 단계 끝날 때 방문 기록 업데이트
        }

        int max = -1;
        while (!queue.isEmpty()) {
            int val = Integer.parseInt(queue.poll());
            max = Math.max(max, val);
        }

        bw.write(Integer.toString(max));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }

    private static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
