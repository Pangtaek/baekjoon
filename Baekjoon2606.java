import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/*
 * 백준 2606번: 바이러스
 * https://www.acmicpc.net/problem/2606
 * 
 * - 문제 풀이:
 * 1. 그래프를 인접 리스트로 표현합니다.
 * 2. BFS(너비 우선 탐색)를 사용하여 1번 컴퓨터로부터 감염된 모든 컴퓨터를 탐색합니다.
 * 3. 방문한 컴퓨터의 수를 세어 출력합니다.
 * 
 * - 시간 복잡도: O(V + E), V는 컴퓨터 수, E는 연결된 쌍의 수
 * - 공간 복잡도: O(V + E)
 */

public class Baekjoon2606 {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
            int M = Integer.parseInt(br.readLine()); // 연결된 쌍의 수

            // 1. 그래프(인접 리스트) 생성
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            // 2. 그래프에 간선 정보 입력
            for (int i = 0; i < M; i++) {
                int[] tokens = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int from = tokens[0];
                int to = tokens[1];

                // 양방향 그래프
                graph.get(from).add(to);
                graph.get(to).add(from);
            }

            // 3. BFS를 사용하여 1번 컴퓨터로부터 감염된 컴퓨터 찾기
            boolean[] visited = new boolean[N + 1]; // 1-based indexing

            // 분리된 BFS 메서드 호출
            // 1번 컴퓨터를 시작 노드로 하여, graph를 탐색하고 visited 배열을 채웁니다.
            bfs(1, graph, visited);

            // --- 4. 감염된 컴퓨터 수 계산 ---
            // 1번 컴퓨터를 제외하고, 방문(visited[i] == true)한 컴퓨터의 수를 셈
            int infectedCount = 0;
            for (int i = 2; i <= N; i++) { // 1번을 제외해야 하므로 2부터 시작
                if (visited[i]) {
                    infectedCount++;
                }
            }

            // 5. 결과 출력
            bw.write(Integer.toString(infectedCount));
            bw.newLine();
            bw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void bfs(int startNode, List<List<Integer>> graph, boolean[] visited) {
        Deque<Integer> dq = new ArrayDeque<>();

        visited[startNode] = true; // 시작 노드 방문 처리
        dq.offer(startNode); // 큐에 시작 노드 추가

        while (!dq.isEmpty()) {
            int current = dq.pollFirst();

            // 현재 컴퓨터에 연결된 모든 컴퓨터(next)를 확인
            for (int next : graph.get(current)) {
                if (!visited[next]) { // 아직 방문하지 않았다면
                    visited[next] = true; // 방문 처리
                    dq.offerLast(next); // 큐에 추가
                }
            }
        }
    }
}