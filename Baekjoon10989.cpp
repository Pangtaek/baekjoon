#include <iostream>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, array[10001] = {0};
    cin >> n;

    for (int i = 0; i < n; i++)
    {
        int num;
        cin >> num;
        array[num] += 1;
    }

    for (int i = 1; i < 10001; i++)
    {
        for (int j = 0; j < array[i]; j++)
            cout << i << '\n';
    }
}