import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Baekjoon1766 {

    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);

        // 진입 차수
        int[] inDegree = new int[N + 1];
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            tokens = br.readLine().split(" ");
            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            graph.get(from).add(to);
            inDegree[to]++;
        }

        // 우선순위 큐: 쉬운 문제(번호가 작은 것)부터 풀기
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 진입 차수가 0인 문제들 큐에 넣기
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                pq.offer(i);
            }
        }

        // 위상 정렬 수행
        while (!pq.isEmpty()) {
            int curr = pq.poll();
            answer.append(curr).append(" ");

            for (int next : graph.get(curr)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    pq.offer(next);
                }
            }
        }

        System.out.println(answer);
    }
}
