import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Baekjoon19644 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int N = Integer.parseInt(br.readLine());
            String[] tokens = br.readLine().split("\\s+");
            int ML = Integer.parseInt(tokens[0]);
            int MK = Integer.parseInt(tokens[1]);
            int CAMMO = Integer.parseInt(br.readLine());

            Deque<Integer> killZombiesQueue = new ArrayDeque<>();
            String result = "YES";
            int damage = 0;

            for (int i = 1; i <= N; i++) {
                int hp = Integer.parseInt(br.readLine());

                if (killZombiesQueue.size() == ML) {
                    damage -= killZombiesQueue.poll();
                }

                if (hp <= damage + MK) {
                    killZombiesQueue.add(MK);
                    damage += MK;
                } else {
                    if (CAMMO > 0) {
                        killZombiesQueue.add(0);
                        CAMMO--;
                    } else {
                        result = "NO";
                        break;
                    }
                }
            }

            bw.write(result + "\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
