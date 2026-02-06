using System;
using System.IO;
using System.Linq;

namespace Baekjoon
{
    internal class Baekjoon2042
    {
        public void Solve()
        {
            using (var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput())))
            using (var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput())))
            {
                string[] tokens = reader.ReadLine().Split();
                int N = int.Parse(tokens[0]);  // 줄의 수
                int M = int.Parse(tokens[1]);  // 수의 변경이 일어나는 횟수
                int K = int.Parse(tokens[2]);  // 구간의 합을 구하는 횟수

                long[] array = new long[N];

                for (int i = 0; i < N; i++)
                {
                    array[i] = long.Parse(reader.ReadLine());
                }

                SegmentTree segmentTree = new SegmentTree(array);

                for (int i = 0; i < M + K; i++)
                {
                    tokens = reader.ReadLine().Split();
                    long option = int.Parse(tokens[0]);

                    if (option == 1)
                    {
                        int index = int.Parse(tokens[1]);
                        long value = long.Parse(tokens[2]);
                        segmentTree.Update(index - 1, value);
                    }
                    else if (option == 2)
                    {
                        int left = int.Parse(tokens[1]);
                        int right = int.Parse(tokens[2]);
                        long sum = segmentTree.Query(left - 1, right - 1);
                        writer.WriteLine(sum);
                    }
                }
            }
        }

        private class SegmentTree
        {
            private long[] tree;
            private long[] array;
            private int n;

            public SegmentTree(long[] array)
            {
                this.array = array;
                n = array.Length;
                tree = new long[4 * n];
                Build(1, 0, n - 1);             
            }

            private void Build(int node, int start, int end)
            {
                if (start == end)
                {
                    tree[node] = array[start];
                }
                else
                {
                    int mid = (start + end) / 2;
                    Build(2 * node, start, mid);
                    Build(2 * node + 1, mid + 1, end);
                    tree[node] = tree[2 * node] + tree[2 * node + 1];
                }
            }

            public long Query(int left, int right)
            {
                return Query(1, 0, n - 1, left, right);
            }

            private long Query(int node, int start, int end, int left, int right)
            {
                if (right < start || end < left)
                {
                    return 0;
                }

                if (left <= start && end <= right)
                {
                    return tree[node];
                }

                int mid = (start + end) / 2;
                long leftSum = Query(2 * node, start, mid, left, right);
                long rightSum = Query(2 * node + 1, mid + 1, end, left, right);

                return leftSum + rightSum;
            }

            public void Update(int index, long value)
            {
                Update(1, 0, n - 1, index, value);
            }

            private void Update(int node, int start, int end, int index, long value)
            {
                if (start == end)
                {
                    array[index] = value;
                    tree[node] = value;
                }
                else
                {
                    int mid = (start + end) / 2;

                    if (index <= mid)
                    {
                        Update(2 * node, start, mid, index, value);
                    }
                    else
                    {
                        Update(2 * node + 1, mid + 1, end, index, value);
                    }

                    tree[node] = tree[2 * node] + tree[2 * node + 1];
                }
            }
        }
    }
}
