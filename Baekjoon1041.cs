using System;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon1041
    {
        public void Solve()
        {
            var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput()));
            var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));

            int N = int.Parse(reader.ReadLine());
            int[] values = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);

            long answer = 0;

            if (N == 1)
            {
                Array.Sort(values);
                for (int i = 0; i < 5; i++)
                {
                    answer += values[i];
                }
            }
            else
            {
                int min1 = int.MaxValue;
                int min2 = int.MaxValue;
                int min3 = int.MaxValue;

                for (int i = 0; i < 6; i++)
                {
                    min1 = Math.Min(min1, values[i]);
                    for (int j = i + 1; j < 6; j++)
                    {
                        if (i + j == 5) // 마주보는 면
                        {
                            continue;
                        }
                        min2 = Math.Min(min2, values[i] + values[j]);
                        for (int k = j + 1; k < 6; k++)
                        {
                            if (j + k == 5 || k + i == 5) // 마주보는 면
                            {
                                continue;
                            }
                            min3 = Math.Min(min3, values[i] + values[j] + values[k]);
                        }
                    }
                }

                long face1 = 5L * N * N - 16 * N + 12;
                long face2 = 8L * N - 12;
                long face3 = 4;

                answer = face1 * min1 + face2 * min2 + face3 * min3;
            }

            writer.WriteLine(answer);

            writer.Close();
            reader.Close();
        }
    }
}
