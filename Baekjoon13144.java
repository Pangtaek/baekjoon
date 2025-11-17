import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Baekjoon13144 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int N = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            Map<Integer, Integer> countMap = new HashMap<>();
            long result = 0;
            int left = 0;

            for (int right = 0; right < N; right++) {
                int num = arr[right];
                countMap.put(num, countMap.getOrDefault(num, 0) + 1);

                while (countMap.get(num) > 1) { // 중복 발생 시 left 이동
                    int leftNum = arr[left];
                    countMap.put(leftNum, countMap.get(leftNum) - 1);
                    if (countMap.get(leftNum) == 0) {
                        countMap.remove(leftNum);
                    }
                    left++;
                }

                // 지금까지 중복없는 부분 수열 개수 누적
                result += right - left + 1;
            }

            bw.write(result + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
