import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon2979 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // fee 배열 크기를 4로 설정 (0번째 인덱스는 0원으로 설정)
        int[] fee = { 0, A, B, C };

        int[][] arr = new int[3][2];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());

            min = Math.min(min, arr[i][0]);
            max = Math.max(max, arr[i][1]);
        }

        System.out.println(solution(fee, arr, min, max));
    }

    public static int solution(int[] fee, int[][] arr, int min, int max) {
        int answer = 0;

        for (int i = min; i < max; i++) {
            int carNumber = 0;
            for (int j = 0; j < 3; j++) {
                if (between(arr[j], i))
                    carNumber++;
            }

            answer += carNumber * fee[carNumber];
        }

        return answer;
    }

    public static boolean between(int[] arr, int time) {
        return arr[0] <= time && time < arr[1];
    }
}
