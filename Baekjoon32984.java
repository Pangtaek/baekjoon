import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Baekjoon32984 {

    static class Tree {
        int countOfLeafs; // 현재 나뭇잎 개수
        int dailyLeafDrop; // 하루마다 떨어지는 나뭇잎 수
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = 0;
        int N = Integer.parseInt(br.readLine()); // 나무의 그루 수
        List<Tree> treeList = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            treeList.add(new Tree()); // 1-base indexing
        }

        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int i = 0; i < tokens.length; i++) {
            treeList.get(i + 1).countOfLeafs = tokens[i];
        }

        tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int i = 0; i < tokens.length; i++) {
            treeList.get(i + 1).dailyLeafDrop = tokens[i];
        }

        while (true) {
            // 마법을 사용할 가장 최적의 나무를 선택
            int index = findOptimalIndex(treeList);
            treeList.get(index).dailyLeafDrop *= 2;

            // 마법 사용 후 하루 경과
            count++;
            dropLeafs(treeList);

            if (checkLeafs(treeList)) {
                break;
            }
        }

        // 출력
        bw.write(Integer.toString(count));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean checkLeafs(List<Tree> treeList) {
        for (int i = 1; i < treeList.size(); i++) {
            if (treeList.get(i).countOfLeafs != 0) {
                return false;
            }
        }
        return true;
    }

    static void dropLeafs(List<Tree> treeList) {
        for (int i = 1; i < treeList.size(); i++) {
            Tree tree = treeList.get(i);
            tree.countOfLeafs = Math.max(0, tree.countOfLeafs - tree.dailyLeafDrop);
        }
    }

    static int findOptimalIndex(List<Tree> treeList) {
        int index = -1;
        double max = -Double.MAX_VALUE;
        double[] values = new double[treeList.size() + 1];

        for (int i = 1; i < treeList.size(); i++) {
            Tree tree = treeList.get(i);

            // 마법을 쓸 경우 퍼센트 감소량 계산
            double afterMagic = tree.dailyLeafDrop * 2;
            double percent = ((tree.countOfLeafs - afterMagic) / tree.countOfLeafs) * 100;

            values[i] = percent;
        }

        for (int i = 1; i < treeList.size(); i++) {
            if (values[i] > max) {
                max = values[i];
                index = i;
            }
        }

        return index;
    }
    
}
