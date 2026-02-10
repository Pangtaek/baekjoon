using System;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon34693
    {
        public void Solve()
        {
            using (var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput())))
            using (var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput())))
            {
                int T = int.Parse(reader.ReadLine());
                for (int testcase = 0; testcase < T; testcase++)
                {
                    long K = long.Parse(reader.ReadLine());
                    SolveCase(K, writer);
                }
            }
        }

        private void SolveCase(long K, StreamWriter writer)
        {
            // a, b의 범위를 더 넓게 설정 (K보다 큰 제곱수도 가능)
            long maxVal = (long)Math.Sqrt(K * 2) + 10;

            for (long a = 1; a <= maxVal; a++)
            {
                long a2 = a * a;

                for (long b = 1; b <= maxVal; b++)
                {
                    long b2 = b * b;

                    // 케이스 1: K = a² + b² + c²
                    long c2 = K - a2 - b2;
                    if (c2 > 0 && isPerfectSquare(c2))
                    {
                        long c = (long)Math.Sqrt(c2);
                        writer.WriteLine($"{a} + {b} + {c}");
                        return;
                    }

                    // 케이스 2: K = a² + b² - c²
                    c2 = a2 + b2 - K;
                    if (c2 > 0 && isPerfectSquare(c2))
                    {
                        long c = (long)Math.Sqrt(c2);
                        writer.WriteLine($"{a} + {b} - {c}");
                        return;
                    }

                    // 케이스 3: K = a² - b² + c²
                    c2 = K - a2 + b2;
                    if (c2 > 0 && isPerfectSquare(c2))
                    {
                        long c = (long)Math.Sqrt(c2);
                        writer.WriteLine($"{a} - {b} + {c}");
                        return;
                    }

                    // 케이스 4: K = a² - b² - c²
                    c2 = a2 - b2 - K;
                    if (c2 > 0 && isPerfectSquare(c2))
                    {
                        long c = (long)Math.Sqrt(c2);
                        writer.WriteLine($"{a} - {b} - {c}");
                        return;
                    }

                    // 최적화: 너무 큰 값은 건너뜀
                    if (a2 > K * 2 && b2 > K * 2)
                    {
                        break;
                    }
                }
            }
        }

        private bool isPerfectSquare(long n)
        {
            if (n <= 0)
            {
                return false;
            }

            long sqrt = (long)Math.Sqrt(n);

            for (long i = Math.Max(1, sqrt - 1); i <= sqrt + 1; i++)
            {
                if (i * i == n)
                {
                    return true;
                }
            }
            return false;
        }
    }
}
