import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baekjoon9012 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            for (int i = 0; i < N; i++) {
                String result = solution(br.readLine());

                System.out.println(result);
            }

        } catch (IOException e) {
            System.out.println("유근웅");
        }
    }

    public static String solution(String str) {
        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return "NO";
                }
                stack.pop();
            }
        }

        // 모든 여는 괄호가 짝이 맞으면 스택이 비어야 함
        return stack.isEmpty() ? "YES" : "NO"; // 스택이 비어있으면 "YES", 아니면 "NO"
    }
}
