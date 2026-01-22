using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Baekjoon
{
    private static List<int>[] tree;
    private static int[] parents;
    private static bool[] visited;

    internal class Baekjoon11725
    {
        private void Solve()
        {
            int N = int.Parse(Console.ReadLine());

            tree = new List<int>[N + 1];
            parents = new int[N + 1];
            visited = new bool[N + 1];

            for (int i = 1; i <= N; i++)
            {
                tree[i] = new List<int>();
            }

            for (int i = 0; i < N - 1; i++)
            {
                int[] tokens = Array.ConvertAll(Console.ReadLine().Split(' '), element => int.Parse(element));
                tree[tokens[0]].Add(tokens[1]);
                tree[tokens[1]].Add(tokens[0]);
            }

            dfs(1);

            printAnswer(N);
        }

        private static void dfs(int node)
        {
            visited[node] = true;

            foreach (int neighbor in tree[node])
            {
                if (!visited[neighbor] && neighbor != 0)
                {
                    parents[neighbor] = node;
                    dfs(neighbor);
                }
            }
        }

        private static void printAnswer(int N)
        {
            StringBuilder answer = new StringBuilder();
            for (int i = 2; i <= N; i++)
            {
                answer.Append($"{parents[i]}\n");
            }
            Console.Write(answer.ToString());
        }
    }
}
