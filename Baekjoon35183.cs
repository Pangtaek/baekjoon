using System;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon35183
    {
        public void Solve()
        {
            var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput()));
            var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));

            var resultOfGame = false;

            int N = int.Parse(reader.ReadLine());
            int[] L = new int[N]; // 각 광역기의 최소 위치
            int[] R = new int[N]; // 각 광역기의 최대 위치
            for (int i = 0; i < N; i++)
            {
                int[] tokens = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);
                L[i] = tokens[0];
                R[i] = tokens[1];
            }

            for (int turnOfUsingDivineShield = -1; turnOfUsingDivineShield < N; turnOfUsingDivineShield++) // -1: Don't use divine shield
            {
                if (CanSurive(N, L, R, turnOfUsingDivineShield))
                {
                    resultOfGame = true;
                    break;
                }
            }

            writer.WriteLine(resultOfGame ? "World Champion" : "Surrender");
            writer.Close();
            reader.Close();
        }

        private bool CanSurive(int N, int[] L, int[] R, int turnOfUsingDivineShield)
        {
            const int INF = 2000;
            int minPos = -INF;
            int maxPos = INF;

            if (turnOfUsingDivineShield != 0) // 1번째 턴에 천상에 보호막을 사용하지 않는 경우
            {
                minPos = Math.Max(minPos, L[0]);
                maxPos = Math.Min(maxPos, R[0]);
            }

            if (minPos > maxPos) return false;

            for (int turn = 1; turn < N; turn++)
            {
                int newMin = minPos - 1;
                int newMax = maxPos + 1;

                if (turnOfUsingDivineShield != turn)
                {
                    newMin = Math.Max(newMin, L[turn]);
                    newMax = Math.Min(newMax, R[turn]);
                }

                if (newMin > newMax) return false;

                minPos = newMin;
                maxPos = newMax;
            }

            return true;
        }
    }
}
