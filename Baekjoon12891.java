import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Baekjoon12891 {
    public static void main(String[] args) throws IOException {
        Baekjoon12891 main = new Baekjoon12891();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split("\\s+");
        int S = Integer.parseInt(line1[0]);
        int P = Integer.parseInt(line1[1]);
        String DNA_SENTENCE = br.readLine();
        String[] line3 = br.readLine().split("\\s+");
        int[] minimum = Arrays.stream(line3)
                .mapToInt(Integer::parseInt)
                .toArray();
        Map<Character, Integer> map = main.init(minimum);

        System.out.println(main.solution(S, P, DNA_SENTENCE, map));
    }

    public int solution(int S, int P, String DNA_SENTENCE, Map<Character, Integer> map) {
        int answer = 0;
        Map<Character, Integer> visited = init();
        // 1. P 범위까지 확인
        boolean isDNAWord = true;
        int index = 0;
        for (int i = 0; i < P; i++) {
            char curr = DNA_SENTENCE.charAt(i);
            if (!isCorrectSpell(curr)) {
                isDNAWord = false;
                continue;
            } else {
                visited.put(curr, visited.get(curr) + 1);
            }
        }

        if (isDNAWord && checkCount(visited, map)) {
            answer++;
        }

        // 2. 이후 새로운 값만 확인
        for (int i = P; i < DNA_SENTENCE.length(); i++) {
            // 2-1. 맨앞에 있는 Spell을 제거하고 visited 조정
            char curr = DNA_SENTENCE.charAt(index++);
            removeFirst(curr, visited);

            // 2-2. 맨 뒤에 Spell 추가
            char newSpell = DNA_SENTENCE.charAt(i);
            if (!isCorrectSpell(newSpell)) {
                continue;
            } else {
                addLast(newSpell, visited);
            }

            if (checkCount(visited, map)) {
                answer++;
            }
        }

        return answer;
    }

    public void addLast(char c, Map<Character, Integer> visited) {
        visited.put(c, visited.get(c) + 1);
    }

    public void removeFirst(char c, Map<Character, Integer> visited) {
        visited.put(c, Math.max(visited.get(c) - 1, 0));
    }

    public boolean checkCount(Map<Character, Integer> visited, Map<Character, Integer> count) {
        if (visited.get('A') < count.get('A'))
            return false;
        else if (visited.get('C') < count.get('C'))
            return false;
        else if (visited.get('G') < count.get('G'))
            return false;
        else if (visited.get('T') < count.get('T'))
            return false;

        return true;
    }

    public boolean isCorrectSpell(char c) {
        if (c == 'A' || c == 'C' || c == 'G' || c == 'T') {
            return true;
        }

        return false;
    }

    public Map<Character, Integer> init() {
        Map<Character, Integer> map = new HashMap<>();

        map.put('A', 0);
        map.put('C', 0);
        map.put('G', 0);
        map.put('T', 0);

        return map;
    }

    public Map<Character, Integer> init(int[] arr) {
        Map<Character, Integer> map = new HashMap<>();

        map.put('A', arr[0]);
        map.put('C', arr[1]);
        map.put('G', arr[2]);   
        map.put('T', arr[3]);

        return map;
    }
}
