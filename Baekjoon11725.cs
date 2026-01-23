using System;
using System.Collections.Generic;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon11725
    {
        private static List<int>[] tree;
        private static int[] parents;
        private static bool[] visited;

        internal void Solve()
        {
            StreamReader reader = new StreamReader(new BufferedStream(Console.OpenStandardInput()));

            int N = int.Parse(reader.ReadLine());
            Init(N);

            for (int i = 0; i < N - 1; i++)
            {
                int[] tokens = Array.ConvertAll(reader.ReadLine().Split(' '), element => int.Parse(element));
                tree[tokens[0]].Add(tokens[1]);
                tree[tokens[1]].Add(tokens[0]);
            }

            Run();
        }

        private static void Run()
        {
            Dfs(1);
            PrintAnswer();
        }

        private static void Init(int N)
        {
            tree = new List<int>[N + 1];
            parents = new int[N + 1];
            visited = new bool[N + 1];

            for (int i = 1; i <= N; i++)
            {
                tree[i] = new List<int>();
            }
        }

        private static void Dfs(int node)
        {
            visited[node] = true;

            foreach (int neighbor in tree[node])
            {
                if (neighbor != 0 && !visited[neighbor])
                {
                    parents[neighbor] = node;
                    Dfs(neighbor);
                }
            }
        }

        private static void PrintAnswer()
        {
            StreamWriter writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));
            for (int i = 2; i <= tree.Length; i++)
            {
                writer.WriteLine($"{parents[i]}");
            }

            writer.Close();
        }
    }
}
