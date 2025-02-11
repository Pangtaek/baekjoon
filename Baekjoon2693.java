import java.io.*;
import java.util.*;

public class Baekjoon2693 {
    public static int N = 2; // 3번째로 큰 수 (0-based index)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        List<int[]> input = new ArrayList<>();

        for (int i = 0; i < T; i++) {
            int[] arr = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            input.add(arr);
        }

        int[] result = solution(input);
        Arrays.stream(result).forEach(System.out::println);
    }

    public static int[] solution(List<int[]> list) {
        int[] answer = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            Integer[] arr = Arrays.stream(list.get(i))
                    .boxed()
                    .toArray(Integer[]::new);

            Arrays.sort(arr, Comparator.reverseOrder()); // ✅ 내림차순 정렬
            answer[i] = arr[N]; // ✅ 3번째로 큰 값 선택
        }

        return answer;
    }
}
