import java.io.*;
import java.util.*;

public class Baekjoon10845 {
    public static void main(String[] args) throws IOException {
        MyQueue queue = new MyQueue();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("\\s+");
            if (input[0].equals("push")) {
                queue.push(Integer.parseInt(input[1]));
            } else {
                queue.command(input[0]);
            }
        }
    }
}

class MyQueue {
    Queue<Integer> queue;

    MyQueue() {
        queue = new LinkedList<>();
    }

    public void push(int value) {
        queue.offer(value); // ✅ `push()` 대신 `offer()`
    }

    public void command(String command) {
        switch (command) {
            case "pop":
                System.out.println(queue.isEmpty() ? -1 : queue.poll());
                break;
            case "size":
                System.out.println(queue.size());
                break;
            case "empty":
                System.out.println(queue.isEmpty() ? 1 : 0);
                break;
            case "front":
                System.out.println(queue.isEmpty() ? -1 : queue.peek());
                break;
            case "back":
                System.out.println(queue.isEmpty() ? -1 : getLast());
                break;
            default:
                System.out.println("Invalid command");
        }
    }

    private int getLast() {
        return queue.isEmpty() ? -1 : ((LinkedList<Integer>) queue).getLast(); // ✅ `LinkedList`로 캐스팅 후 `getLast()`
    }
}
