using System;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon2851
    {
        public void Solve()
        {
            var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput()));
            var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));

            int sum = 0;
            int minDiff = int.MaxValue;
            int result = 0;

            for (int i = 0; i < 10; i++)
            {
                int mushroom = int.Parse(reader.ReadLine());
                sum += mushroom;

                int diff = Math.Abs(100 - sum);
                if (diff <= minDiff)  // 작거나 같으면 (큰 값 우선)
                {
                    minDiff = diff;
                    result = sum;
                }

                if (sum >= 100) break;
            }

            writer.WriteLine(result);
            writer.Close();
            reader.Close();
        }
    }
}
