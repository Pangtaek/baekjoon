using System;
using System.Collections.Generic;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon1697
    {
        public void Solve()
        {
            using (var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput())))
            using (var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput())))
            {
                int[] tokens = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);
                int N = tokens[0];
                int K = tokens[1];

                int maxSize = 100_001;
                int[] visited = new int[maxSize];
                for (int i = 0; i < maxSize; i++) visited[i] = -1;

                LinkedList<int> deque = new LinkedList<int>();
                deque.AddLast(N);
                visited[N] = 0;

                while (deque.Count > 0)
                {
                    int current = deque.First.Value;
                    deque.RemoveFirst();

                    if (current == K)
                    {
                        writer.WriteLine(visited[K]);
                        return;
                    }

                    int nextPos = current * 2;
                    if (nextPos < maxSize && visited[nextPos] == -1)
                    {
                        visited[nextPos] = visited[current] + 1;
                        deque.AddFirst(nextPos);
                    }

                    nextPos = current - 1;
                    if (nextPos >= 0 && visited[nextPos] == -1)
                    {
                        visited[nextPos] = visited[current] + 1;
                        deque.AddLast(nextPos);
                    }

                    nextPos = current + 1;
                    if (nextPos < maxSize && visited[nextPos] == -1)
                    {
                        visited[nextPos] = visited[current] + 1;
                        deque.AddLast(nextPos);
                    }
                }
            }
        }
    }
}
