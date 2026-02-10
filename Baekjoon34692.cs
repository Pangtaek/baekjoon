using System;
using System.Collections.Generic;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon34692
    {
        public void Solve()
        {
            using (var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput())))
            using (var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput())))
            {
                int[] tokens = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);
                int N = tokens[0];  // 대기 인원
                int M = tokens[1];  // 오락실 A의 기기 수
                int K = tokens[2];  // 오락실 B까지 가는 데 걸리는 정수 시간
                int[] T = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);   // 사람들의 예상 플레이 시간

                // SortedSet으로 우선순위 큐(끝나는 시간, id) 구현
                SortedSet<(long time, int id)> pq = new SortedSet<(long time, int id)>();

                // M개의 게임 기기를 초기화 (초기 작업 시간 0)
                for (int id = 0; id < M; id++)
                {
                    pq.Add((0L, id));
                }

                // N명의 대기 인원에게 작업 배정
                for (int i = 0; i < N; i++)
                {
                    var earliest = pq.Min;
                    pq.Remove(earliest);
                    long newFinishTime = earliest.time + T[i];
                    pq.Add((newFinishTime, earliest.id));
                }

                // 가장 마지막에 끝나는 시간
                long maxFinishTime = pq.Min.time;

                bool shouldGo = maxFinishTime > K;
                writer.WriteLine(shouldGo ? "GO" : "WAIT");
            }
        }
    }
}
