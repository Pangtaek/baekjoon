import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/*
 * 문제: 그룹 단어 체커
 * 링크: https://www.acmicpc.net/problem/1316
 * 해결 방법: 자료구조(Set, Stack)을 활용한 완전탐색으로 해결
 */

public class Baekjoon1316 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] arr = new String[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        System.out.println(new Baekjoon1316().solution(n, arr));
    }

    public int solution(int n, String[] arr) {
        int answer = 0;
        Set<Character> set;
        Stack<Character> stack;

        for (int i = 0; i < n; i++) {
            boolean isGroupWord = true;
            set = new TreeSet<>();
            stack = new Stack<>();

            set.add(arr[i].charAt(0));
            stack.push(arr[i].charAt(0));

            for (int j = 1; j < arr[i].length(); j++) {
                // 스택의 맨위 값이랑 현재 인덱스와 비교했을 때, 다른 경우
                if (stack.peek() != arr[i].charAt(j)) {
                    // set에서 그 값을 포함하지 않는 경우 => 새로운 문자
                    // set과 stack에 넣는다.
                    if (!set.contains(arr[i].charAt(j))) {
                        // set에 넣는다.
                        set.add(arr[i].charAt(j));
                        stack.push(arr[i].charAt(j));
                    }
                    // set이 해당 값을 가지고 있기 때문에, 그룹 단어가 아니게 된다.
                    else {
                        isGroupWord = false;
                        break;
                    }
                }
                // 스택의 값과 같은 경우 => 스택에 넣고 다음 인덱스로 넘어간다.
                else {
                    stack.push(arr[i].charAt(j));
                }
            }

            if (isGroupWord) {
                answer++;
            }
        }

        return answer;
    }
}