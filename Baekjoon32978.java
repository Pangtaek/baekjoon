import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Baekjoon32978 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] originalIngredients = br.readLine().split(" ");
        String[] selectedIngredients = br.readLine().split(" ");
        Set<String> set = new HashSet<>();

        for (String ingredient : selectedIngredients) {
            set.add(ingredient);
        }
        

        for (String ingredient : originalIngredients) {
            if (!set.contains(ingredient)) {
                bw.write(ingredient);
                bw.newLine();

                break;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
