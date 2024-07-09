
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
    int n, k;
    vector<int> v;
    cin >> n >> k;
    for (int i = 0; i < n; i++)
    {
        int num;
        cin >> num;
        v.push_back(num);
    }

    sort(v.begin(), v.end(), greater<int>());

    cout << v[k - 1] << '\n';
}
