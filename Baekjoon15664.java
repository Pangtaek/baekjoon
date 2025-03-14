import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon15664 {

    public static int N;
    public static int M;
    public static int[] arr;
    public static int[] data;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        data = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr); // 오름차순 정렬

        solution(0, 0);

        System.out.println(sb);
    }

    public static void solution(int depth, int start) {
        if (depth == M) {
            for (int num : data) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        int prevNum = -1; // 이전 숫자를 저장하여 중복 방지
        for (int i = start; i < N; i++) {
            if (arr[i] != prevNum) { // 이전 값과 다를 때만 선택
                prevNum = arr[i]; // 중복 방지용 값 업데이트
                data[depth] = arr[i];
                solution(depth + 1, i + 1); // 비내림차순 조건 유지 (i + 1)
            }
        }
    }
}
