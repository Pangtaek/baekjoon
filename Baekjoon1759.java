import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Baekjoon1759 {

    static final char[] VOWELS = { 'a', 'e', 'i', 'o', 'u' };

    static int L;
    static int C;
    static char[] chars;
    static List<Character> password = new LinkedList<>();
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        L = tokens[0];
        C = tokens[1];
        chars = br.readLine().replaceAll(" ", "").toCharArray();

        Arrays.sort(chars); // 사전 순 정렬

        dfs(0, 0);

        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int start, int depth) throws IOException {
        if (depth == L) {
            if (isValid(password)) {
                for (char ch : password) {
                    bw.write(ch);
                }
                bw.newLine();
            }
            return;
        }

        for (int i = start; i < C; i++) {
            password.add(chars[i]);
            dfs(i + 1, depth + 1);
            password.remove(password.size() - 1);
        }
    }

    static boolean isValid(List<Character> password) {
        int vowels = 0;
        int consonants = 0;

        for (char c : password) {
            boolean isVowel = false;
            for (char v : VOWELS) {
                if (c == v) {
                    isVowel = true;
                    break;
                }
            }

            if (isVowel)
                vowels++;
            else
                consonants++;
        }

        return vowels >= 1 && consonants >= 2;
    }

}
