import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon32984 {
    static class Tree {
        int leaves;
        int dropRate;

        Tree(int leaves, int dropRate) {
            this.leaves = leaves;
            this.dropRate = dropRate;
        }
    }

    static int N;
    static Tree[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        trees = new Tree[N];

        String[] leafCounts = br.readLine().split(" ");
        String[] dropRates = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            int leaves = Integer.parseInt(leafCounts[i]);
            int dropRate = Integer.parseInt(dropRates[i]);
            trees[i] = new Tree(leaves, dropRate);
        }

        int left = 0, right = 1_000_000_000;
        int result = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (canAllLeavesFall(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(result);
    }

    static boolean canAllLeavesFall(int days) {
        int magicUses = 0;

        for (Tree tree : trees) {
            long normalDrop = (long) tree.dropRate * days;
            if (normalDrop >= tree.leaves) {
                continue;
            }

            long magicDrop = (long) tree.dropRate * 2 * days;
            if (magicDrop < tree.leaves) {
                return false;
            }

            magicUses++;
        }

        return magicUses <= days;
    }
}