import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Baekjoon1371 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<Character, Integer> map = new TreeMap<>();
        String line;
        while ((line = br.readLine()) != null) {
            for (char c : line.toCharArray()) {
                if (c >= 'a' && c <= 'z') { // 소문자만 카운트
                    map.put(c, map.getOrDefault(c, 0) + 1);
                }
            }
        }

        int max = Collections.max(map.values());
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                bw.write(entry.getKey());
            }
        }
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
