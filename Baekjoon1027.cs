using System;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon1027
    {
        public void Solve()
        {
            StreamReader reader = new StreamReader(new BufferedStream(Console.OpenStandardInput()));
            StreamWriter writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));

            int N = int.Parse(reader.ReadLine()); // 빌딩의 수
            int[] buildings = Array.ConvertAll(reader.ReadLine().Split(' '), int.Parse); // 빌딩의 높이

            int maxVisible = 0;

            for (int i = 0; i < N; i++)
            {
                int visible = 0;
                double leftDegree = int.MaxValue;
                for (int j = i - 1; j >= 0; j--)
                {
                    double degree = (double)(buildings[i] - buildings[j]) / (i - j);
                    if (degree < leftDegree)
                    {
                        visible++;
                        leftDegree = degree;
                    }
                }

                double rightDegree = int.MinValue;
                for (int j = i + 1; j < N; j++)
                {
                    double degree = (double)(buildings[j] - buildings[i]) / (j - i);
                    if (degree > rightDegree)
                    {
                        visible++;
                        rightDegree = degree;
                    }
                }
                maxVisible = Math.Max(maxVisible, visible);
            }
            writer.WriteLine(maxVisible);

            writer.Close();
        }
    }
}
