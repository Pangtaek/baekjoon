import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baekjoon10799 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input = br.readLine();
            System.out.println(solution(input));
        } catch (IOException e) {
            System.out.println("입력 오류 발생");
        }
    }

    public static int solution(String input) {
        char[] carr = input.toCharArray();
        Stack<Character> stack = new Stack<>();
        int answer = 0;

        for (int i = 0; i < carr.length; i++) {
            if (carr[i] == '(') {
                // 쇠막대기 생성
                stack.push('(');
            } else if (carr[i] == ')') {
                if (i > 0 && carr[i - 1] == '(') {
                    // 레이저 절단
                    stack.pop();
                    answer += stack.size(); // 현재 스택의 크기만큼 조각이 추가됨
                } else {
                    // 쇠막대기의 끝
                    stack.pop();
                    answer++; // 조각 하나 추가
                }
            }
        }

        return answer;
    }
}
