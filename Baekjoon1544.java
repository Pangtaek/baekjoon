import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Baekjoon1544 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> wordList = new ArrayList<>();
        Set<String> unique = new HashSet<>();

        for (int i = 0; i < N; i++) {
            wordList.add(br.readLine());
        }

        for (String word : wordList) {
            boolean isNew = true;

            for (String saved : unique) {
                if (isSameCycle(word, saved)) {
                    isNew = false;
                    break;
                }
            }

            if (isNew)
                unique.add(word);
        }

        System.out.println(unique.size());
    }

    // word1이 word2의 회전된 형태인지 확인
    public static boolean isSameCycle(String w1, String w2) {
        if (w1.length() != w2.length())
            return false;

        String doubled = w1 + w1; // picturepicture
        return doubled.contains(w2); // turepic 포함 여부
    }
}