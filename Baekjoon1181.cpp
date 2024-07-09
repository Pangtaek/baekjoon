
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(string a, string b)
{
    int i = 0;
    if (a.length() == b.length())
    {
        for (int i = 0; i < a.length(); i++)
        {
            if (a[i] != b[i])
                return a[i] < b[i];
        }
    }
    return a.length() < b.length();
}

int main()
{
    int n;
    vector<string> strs;
    cin >> n;

    for (int i = 0; i < n; i++)
    {
        string str;
        cin >> str;
        if (find(strs.begin(), strs.end(), str) == strs.end())
            strs.push_back(str);
    }

    sort(strs.begin(), strs.end(), compare);

    for (int i = 0; i < strs.size(); i++)
    {
        cout << strs[i] << '\n';
    }
}
