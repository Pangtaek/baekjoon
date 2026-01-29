using System;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon3020
    {
        public void Solve()
        {
            var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput()));
            var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));

            int[] tokens = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);
            int N = tokens[0]; // 동굴의 길이
            int H = tokens[1]; // 동굴의 높이

            int[] diff = new int[H + 1];

            for (int n = 0; n < N; n++)
            {
                int length = int.Parse(reader.ReadLine()); // 장애물의 크기

                if ((n & 1) == 0) // 석순
                {
                    diff[0]++;
                    diff[length]--;
                }
                else // 종유석
                {
                    diff[H - length]++;
                    diff[H]--;
                }
            }

            int minHuddles = int.MaxValue;
            int result = 0;
            int current = 0;

            for (int h = 0; h < H; h++)
            {
                current += diff[h];

                if(current < minHuddles)
                {
                    minHuddles = current;
                    result = 1;
                }
                else if(current == minHuddles)
                {
                    result++;
                }
            }

            writer.WriteLine($"{minHuddles} {result}");
            writer.Close();
            reader.Close();
        }
    }
}
