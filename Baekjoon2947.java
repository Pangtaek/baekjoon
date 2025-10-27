import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * 백준 2947번: 나무 조각
 * https://www.acmicpc.net/problem/2947
 * 
 * - 다섯 개의 나무 조각이 주어졌을 때, 문제에서 설명하는 방식으로 정렬하는 과정을 출력
 */

public class Baekjoon2947 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            // 1. 입력을 받아 정수 배열로 변환
            int[] arr = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            StringBuilder sb = new StringBuilder();

            // 2. [1, 2, 3, 4, 5]가 될 때까지 반복
            while (true) {

                // 3. 문제에서 설명하는 4단계의 비교 및 교환
                // 1번째 조각 > 2번째 조각
                if (arr[0] > arr[1]) {
                    int temp = arr[0];
                    arr[0] = arr[1];
                    arr[1] = temp;

                    // 출력 문자열 생성
                    sb.setLength(0); // StringBuilder 초기화
                    for (int val : arr)
                        sb.append(val).append(" ");
                    bw.write(sb.toString().trim() + "\n");
                }

                // 2번째 조각 > 3번째 조각
                if (arr[1] > arr[2]) {
                    int temp = arr[1];
                    arr[1] = arr[2];
                    arr[2] = temp;

                    sb.setLength(0);
                    for (int val : arr)
                        sb.append(val).append(" ");
                    bw.write(sb.toString().trim() + "\n");
                }

                // 3번째 조각 > 4번째 조각
                if (arr[2] > arr[3]) {
                    int temp = arr[2];
                    arr[2] = arr[3];
                    arr[3] = temp;

                    sb.setLength(0);
                    for (int val : arr)
                        sb.append(val).append(" ");
                    bw.write(sb.toString().trim() + "\n");
                }

                // 4번째 조각 > 5번째 조각
                if (arr[3] > arr[4]) {
                    int temp = arr[3];
                    arr[3] = arr[4];
                    arr[4] = temp;

                    sb.setLength(0);
                    for (int val : arr)
                        sb.append(val).append(" ");
                    bw.write(sb.toString().trim() + "\n");
                }

                // 4. 정렬이 완료되었는지 확인 (종료 조건)
                boolean sorted = true;
                for (int i = 0; i < 5; i++) {
                    if (arr[i] != i + 1) {
                        sorted = false;
                        break;
                    }
                }

                if (sorted) {
                    break; // while 루프 종료
                }
            }

            bw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}