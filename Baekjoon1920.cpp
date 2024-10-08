
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int n, m;
    vector<int> v1, v2;

    cin >> n;
    for (int i = 0; i < n; i++)
    {
        int num;
        cin >> num;
        v1.push_back(num);
    }
    cin >> m;
    for (int i = 0; i < m; i++)
    {
        int num;
        cin >> num;
        v2.push_back(num);
    }
    sort(v1.begin(), v1.end());
    for (int i = 0; i < m; i++)
    {
        if (binary_search(v1.begin(), v1.end(), v2[i]))
        {
            cout << 1 << '\n';
        }
        else
            cout << 0 << '\n';
    }
}
