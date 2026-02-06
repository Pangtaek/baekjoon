using System;
using System.IO;
using System.Linq;

namespace Baekjoon
{
    internal class Baekjoon11048
    {
        public void Solve()
        {
            using (var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput())))
            using (var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput())))
            {
                int[] tokens = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);
                int N = tokens[0];
                int M = tokens[1];
                int[,] maze = new int[N, M];
                int[,] dp = new int[N, M];

                for (int y = 0; y < N; y++)
                {
                    tokens = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);
                    for (int x = 0; x < M; x++)
                    {
                        maze[y, x] = tokens[x];
                    }
                }

                dp[0, 0] = maze[0, 0];
                for (int x = 1; x < M; x++)
                {
                    dp[0, x] = dp[0, x - 1] + maze[0, x];
                }

                for (int y = 1; y < N; y++)
                {
                    dp[y, 0] = dp[y - 1, 0] + maze[y, 0];
                }

                for (int y = 1; y < N; y++)
                {
                    for (int x = 1; x < M; x++)
                    {
                        int[] prevSumOfCookies = new int[] { dp[y - 1, x - 1], dp[y - 1, x], dp[y, x - 1] };
                        dp[y, x] = prevSumOfCookies.Max() + maze[y, x];
                    }
                }

                writer.WriteLine(dp[N - 1, M - 1]);
            }
        }
    }
}
