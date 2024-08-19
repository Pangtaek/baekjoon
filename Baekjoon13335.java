import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*4 2 10
7 4 5 6*/

public class Baekjoon13335 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N;
            int w;
            int l;

            String input = br.readLine();
            String[] tokens = input.split(" ");
            N = Integer.parseInt(tokens[0]);
            w = Integer.parseInt(tokens[1]);
            l = Integer.parseInt(tokens[2]);

            String truckInput = br.readLine();
            tokens = truckInput.split(" ");
            int[] truckWeight = new int[N];

            for (int i = 0; i < tokens.length; i++) {
                truckWeight[i] = Integer.parseInt(tokens[i]);
            }

            System.out.println(solution(w, l, Arrays.stream(truckWeight).boxed().toArray(Integer[]::new)));
        } catch (IOException e) {
            System.out.println("유근웅");
        }
    }

    public static int solution(int w, int l, Integer[] truckWeight) {
        // 트럭이 담긴 큐
        Queue<Integer> trucks = new LinkedList<>(Arrays.asList(truckWeight));
        // 다리의 상태를 표현하는 큐
        Queue<Integer> bridge = new LinkedList<>();
        // 다리 초기화
        for (int i = 0; i < w; i++) {
            bridge.offer(0);
        }

        int time = 0;
        int currentLoad = 0;

        // 트럭이 다리를 모두 지나갈 때까지 반복
        while (!bridge.isEmpty()) {
            // 한 번의 반복당 한번의 단위 시간이 소요 된다고 가정하여 시간은 증가하고 다리 큐는 제거 된다.
            time++;
            currentLoad -= bridge.poll();

            // 아직 다리에 진입해야할 트럭이 남았다면
            if (!trucks.isEmpty()) {
                // 현재 다리의 하중과 진입할 트럭의 무게를 더했을 때 최대 하중보다 작다면
                if (currentLoad + trucks.peek() <= l) {
                    // 트럭이 다리에 진입
                    int newTruck = trucks.poll();
                    bridge.offer(newTruck);
                    currentLoad += newTruck;
                } else {
                    // 트럭이 진입하지 못함
                    bridge.offer(0);
                }
            }
        }

        return time;
    }
}
