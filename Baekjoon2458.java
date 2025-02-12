import java.io.*;
import java.util.*;

public class Baekjoon2458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int M = Integer.parseInt(st.nextToken()); // 비교 횟수

        boolean[][] dist = new boolean[N + 1][N + 1]; // 키 비교 여부

        // 방향 그래프 입력 (i → j : i < j)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = true; // a가 b보다 작음
        }

        // 플로이드-워셜 알고리즘 수행
        for (int k = 1; k <= N; k++) { // 중간 노드
            for (int i = 1; i <= N; i++) { // 출발 노드
                for (int j = 1; j <= N; j++) { // 도착 노드
                    if (dist[i][k] && dist[k][j]) {
                        dist[i][j] = true; // i → k → j 경로가 존재하면 i → j 가능
                    }
                }
            }
        }

        int answer = 0; // 키 순서를 정확히 아는 학생 수
        for (int i = 1; i <= N; i++) {
            int count = 0; // 자신보다 크거나 작은 학생의 수
            for (int j = 1; j <= N; j++) {
                if (dist[i][j] || dist[j][i]) {
                    count++;
                }
            }
            if (count == N - 1) { // N-1명이랑 비교 가능하면 순서 알 수 있음
                answer++;
            }
        }

        System.out.println(answer);
    }
}
