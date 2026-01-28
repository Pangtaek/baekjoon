using System;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon20002
    {
        public void Solve()
        {
            var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput()));
            var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));

            int N = int.Parse(reader.ReadLine());

            bool areAllPositive = true;
            int sumOfAllElements = 0;
            int[,] prefixSum = new int[N + 1, N + 1]; // 1-indexed

            for (int y = 1; y <= N; y++)
            {
                int[] tokens = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);
                for (int x = 1; x <= N; x++)
                {
                    if (areAllPositive)
                    {
                        if (tokens[x - 1] <= 0)
                        {
                            areAllPositive = false;
                        }

                        sumOfAllElements += tokens[x - 1];
                    }

                    prefixSum[y, x] = tokens[x - 1]
                        + prefixSum[y - 1, x]      // 위쪽 누적합
                        + prefixSum[y, x - 1]      // 왼쪽 누적합
                        - prefixSum[y - 1, x - 1]; // 중복 제거
                }
            }

            int maxEarnings = 0;
            if (areAllPositive)
            {
                maxEarnings = sumOfAllElements;
            }
            else
            {
                maxEarnings = int.MinValue;

                for (int K = 1; K <= N; K++)
                {
                    for (int y = K; y <= N; y++)
                    {
                        for (int x = K; x <= N; x++)
                        {
                            int earnings = prefixSum[y, x]
                                - prefixSum[y - K, x]      // 왼쪽 제거
                                - prefixSum[y, x - K]      // 위쪽 제거
                                + prefixSum[y - K, x - K]; // 중복 복원

                            maxEarnings = Math.Max(maxEarnings, earnings);
                        }
                    }
                }
            }

            writer.WriteLine(maxEarnings);
            writer.Close();
            reader.Close();
        }
    }
}
