import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Baekjoon14725 {

    private static class Node {
        Map<String, Node> children = new TreeMap<>();
    }

    private static void insert(Node root, String[] food) {
        Node current = root;
        for (String item : food) {
            current.children.putIfAbsent(item, new Node());
            current = current.children.get(item);
        }
    }

    private static void print(Node node, int depth, BufferedWriter bw) throws IOException {
        for (String key : node.children.keySet()) {
            for (int i = 0; i < depth; i++) {
                bw.write("--");
            }
            bw.write(key);
            bw.newLine();
            print(node.children.get(key), depth + 1, bw);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        Node root = new Node();
        for (int i = 0; i < N; i++) {
            String[] tokens = br.readLine().split(" ");
            int K = Integer.parseInt(tokens[0]);
            String[] path = Arrays.copyOfRange(tokens, 1, 1 + K);
            insert(root, path);
        }

        print(root, 0, bw);
        bw.flush();
        bw.close();
        br.close();
    }
}
