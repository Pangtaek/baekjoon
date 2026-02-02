using System;
using System.IO;
using System.Collections.Generic;

namespace Baekjoon
{
    internal class Baekjoon35184
    {
        // 0: 상, 1: 좌, 2: 하, 3: 우 (반시계 방향 순서)
        private static int[] dy = { -1, 0, 1, 0 };
        private static int[] dx = { 0, -1, 0, 1 };
        private class State : IComparable<State>
        {
            public int Cost { get; set; }
            public int Y { get; set; }
            public int X { get; set; }
            public int Dir { get; set; }
            public int Id { get; set; }

            private static int _counter = 0;

            public State(int cost, int y, int x, int dir)
            {
                Cost = cost;
                Y = y;
                X = x;
                Dir = dir;
                Id = _counter++;
            }

            public int CompareTo(State other)
            {
                if (Cost != other.Cost) return Cost.CompareTo(other.Cost);
                if (Y != other.Y) return Y.CompareTo(other.Y);
                if (X != other.X) return X.CompareTo(other.X);
                if (Dir != other.Dir) return Dir.CompareTo(other.Dir);
                return Id.CompareTo(other.Id);
            }
        }

        public void Solve()
        {
            var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput()));
            var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));

            var tokens = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);
            int N = tokens[0];
            int M = tokens[1];
            int T = tokens[2];

            char[,] map = new char[N, M];
            int startY = 0;
            int startX = 0;
            int startDir = 0;

            for (int y = 0; y < N; y++)
            {
                string line = reader.ReadLine();
                for (int x = 0; x < M; x++)
                {
                    map[y, x] = line[x];
                    if ('0' <= line[x] && line[x] <= '3')
                    {
                        startDir = line[x] - '0';
                        startY = y;
                        startX = x;
                        map[y, x] = '.';
                    }
                }
            }

            writer.WriteLine(Dijkstra(N, M, T, map, startY, startX, startDir));
            writer.Close();
            reader.Close();
        }

        private int Dijkstra(int N, int M, int T, char[,] map, int startY, int startX, int startDir)
        {
            var priorityQueue = new SortedSet<State>();
            int[,,] distance = new int[N, M, 4];

            for (int y = 0; y < N; y++)
            {
                for (int x = 0; x < M; x++)
                {
                    for (int dir = 0; dir < 4; dir++)
                    {
                        distance[y, x, dir] = int.MaxValue;
                    }
                }
            }

            distance[startY, startX, startDir] = 0;
            priorityQueue.Add(new State(0, startY, startX, startDir));

            while (priorityQueue.Count > 0)
            {
                var curr = priorityQueue.Min;
                priorityQueue.Remove(curr);

                int cost = curr.Cost;
                int r = curr.Y;
                int c = curr.X;
                int dir = curr.Dir;

                // 이미 더 짧은 경로로 방문했다면 스킵
                if (distance[r, c, dir] < cost) continue;

                // 목적지 도착 확인
                if (map[r, c] == 'S') return cost;

                // 1. 게걸음 이동
                int[] directions = ((dir & 1) == 0)
                    ? new int[] { 1, 3 }  // 좌우 방향
                    : new int[] { 0, 2 };  // 상하 방향
                foreach (int d in directions)
                {
                    int nr = r + dy[d];
                    int nc = c + dx[d];
                    int nextCost = cost + 1;

                    var result = Slide(N, M, map, nr, nc, dir);
                    if (result == null) continue;

                    int finalR = result.Value.y;
                    int finalC = result.Value.x;
                    int finalDir = result.Value.dir;

                    if (distance[finalR, finalC, finalDir] > nextCost)
                    {
                        distance[finalR, finalC, finalDir] = nextCost;
                        priorityQueue.Add(new State(nextCost, finalR, finalC, finalDir));
                    }
                }

                // 2. 제자리 회전
                int nextDir = (dir + 1) % 4;
                int rotateCost = cost + T;

                if (distance[r, c, nextDir] > rotateCost)
                {
                    distance[r, c, nextDir] = rotateCost;
                    priorityQueue.Add(new State(rotateCost, r, c, nextDir));
                }
            }

            return -1;
        }

        // 수돗물 처리 (무한 루프 방지 포함)
        private (int y, int x, int dir)? Slide(int N, int M, char[,] map, int r, int c, int dir)
        {
            if (r < 0 || r >= N || c < 0 || c >= M) return null;
            if (map[r, c] != 'T') return (r, c, dir);

            // (y, x, dir) 상태 방문 체크 - 무한 루프 방지
            var visited = new HashSet<(int, int, int)>();

            int curR = r;
            int curC = c;
            int curDir = dir;

            while (map[curR, curC] == 'T')
            {
                // 이미 방문한 상태면 무한 루프
                if (!visited.Add((curR, curC, curDir))) return null;

                // 1. 반시계 90도 회전
                curDir = (curDir + 1) % 4;

                // 2. 바라보는 방향으로 1칸 전진
                curR += dy[curDir];
                curC += dx[curDir];

                // 격자 밖으로 나가면 실패
                if (curR < 0 || curR >= N || curC < 0 || curC >= M) return null;
            }

            return (curR, curC, curDir);
        }
    }
}
