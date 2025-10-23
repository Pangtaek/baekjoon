import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * 백준 2631번: 줄세우기
 * https://www.acmicpc.net/problem/2631
 * 
 * LIS(최장 증가 부분 수열) 알고리즘을 활용한 문제 풀이
 * - 아이들의 번호를 입력받아 최장 증가 부분 수열의 길이를 구하고,
 *  전체 아이들 수에서 이를 빼서 최소 이동 횟수를 계산
 */
public class Baekjoon2631 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int N = Integer.parseInt(br.readLine()); // 아이들의 수
            int[] children = new int[N]; // 아이들의 번호 배열

            for (int i = 0; i < N; i++) {
                children[i] = Integer.parseInt(br.readLine());
            }

            // LIS(최장 증가 부분 수열) 길이 구하기
            int lisLength = findLIS(children);

            // 최소 이동 횟수 = 전체 인원 - 움직이지 않아도 되는 최대 인원(LIS)
            int answer = N - lisLength;

            bw.write(String.valueOf(answer));
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int findLIS(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];

        // 모든 위치에서 최소 길이는 1 (자기 자신)
        Arrays.fill(dp, 1);

        // 각 위치에서 가능한 최장 증가 부분 수열 길이 계산
        for (int end = 1; end < n; end++) {
            for (int start = 0; start < end; start++) {
                // arr[j] < arr[i]이면 arr[j] 뒤에 arr[i]를 이어붙일 수 있음
                if (arr[start] < arr[end]) {
                    dp[end] = Math.max(dp[end], dp[start] + 1);
                }
            }
        }

        // dp 배열에서 최댓값 찾기 (가장 긴 LIS 길이)
        return Arrays.stream(dp).max().orElse(1);
    }
}
