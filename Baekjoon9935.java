import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baekjoon9935 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String str = br.readLine();
            String boom = br.readLine();
            StringBuilder answer = new StringBuilder();

            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < str.length(); i++) {
                stack.push(str.charAt(i));

                while (isBoom(stack, boom)) {
                    for (int j = 0; j < boom.length(); j++) {
                        stack.pop();
                    }
                }
            }

            if (stack.isEmpty()) {
                System.out.println("FRULA");
            } else {
                for (char ch : stack) {
                    answer.append(ch);
                }
                System.out.println(answer.toString());
            }

        } catch (IOException e) {
            System.out.println("유근웅");
        }
    }

    public static boolean isBoom(Stack<Character> stack, String boom) {
        if (stack.size() < boom.length()) {
            return false;
        }

        for (int i = 0; i < boom.length(); i++) {
            if (stack.get(stack.size() - 1 - i) != boom.charAt(boom.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }
}
