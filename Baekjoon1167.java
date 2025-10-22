import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
 * 트리의 지름
 * https://www.acmicpc.net/problem/1167
 * 
 * 트리의 지름은 임의의 정점에서 가장 먼 정점 A를 찾고,
 * A에서 가장 먼 정점 B까지의 거리를 구하면 된다.
 * DFS를 이용하여 구현한다.
 */

public class Baekjoon1167 {

    private static class Edge {
        private final int to;
        private final int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public int getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }
    }

    private static class Node {
        private final int vertex;
        private final int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public int getVertex() {
            return vertex;
        }

        public int getDistance() {
            return distance;
        }
    }

    static class Result {
        private int node;
        private int distance;

        Result(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        public int getNode() {
            return node;
        }

        public int getDistance() {
            return distance;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int V = Integer.parseInt(br.readLine());
            List<List<Edge>> tree = new ArrayList<>();

            for (int i = 0; i <= V; i++) {
                tree.add(new ArrayList<>());
            }

            for (int i = 0; i < V; i++) {
                int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int u = tokens[0];

                for (int j = 1; j < tokens.length - 1; j += 2) {
                    int v = tokens[j];
                    int w = tokens[j + 1];
                    tree.get(u).add(new Edge(v, w));
                }
            }

            // 1단계: 임의의 정점(1번)에서 가장 먼 정점 찾기
            Result first = dfs(tree, 1);

            // 2단계: 찾은 정점에서 가장 먼 정점까지의 거리 계산 (트리의 지름)
            Result second = dfs(tree, first.getNode());

            bw.write(String.valueOf(second.getDistance()));
            bw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Result dfs(List<List<Edge>> tree, int start) {
        boolean[] visited = new boolean[tree.size()];
        Stack<Node> stack = new Stack<>();
        Result maxResult = new Result(start, 0);

        // 시작 노드를 스택에 추가
        stack.push(new Node(start, 0));
        visited[start] = true;

        while (!stack.isEmpty()) {
            Node current = stack.pop();

            // 현재 노드까지의 거리가 최대거리보다 크면 갱신
            if (current.getDistance() > maxResult.getDistance()) {
                maxResult.distance = current.getDistance();
                maxResult.node = current.getVertex();
            }

            // 인접한 노드들을 스택에 추가
            for (Edge edge : tree.get(current.getVertex())) {
                if (!visited[edge.getTo()]) {
                    visited[edge.getTo()] = true;
                    int newDistance = current.getDistance() + edge.getWeight();
                    stack.push(new Node(edge.getTo(), newDistance));
                }
            }
        }

        return maxResult;
    }
}
