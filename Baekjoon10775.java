import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon10775 {

    private static int G;
    private static int P;
    private static int[] parent;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            G = Integer.parseInt(br.readLine());
            P = Integer.parseInt(br.readLine());
            parent = new int[G + 1];

            int answer = 0;

            for (int i = 0; i < P; i++) {
                int plane = Integer.parseInt(br.readLine());
                int gate = find(plane);

                if (gate == 0)
                    break;

                answer++;
                union(gate, gate - 1);
            }

            bw.write(String.valueOf(answer) + "\n");
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        parent[a] = b;
    }

    private static int find(int index) {
        if (parent[index] == index)
            return index;
        return parent[index] = find(parent[index]);
    }
}