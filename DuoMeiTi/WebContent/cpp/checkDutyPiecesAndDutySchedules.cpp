#include <regex>
#include <iterator>
#include <iostream>
#include <string>
#include <algorithm>
#include <functional>
#include <unordered_set>
#include <tuple>
#include <fstream>
#include <list>
using namespace std;


int main()
{
    freopen("in.txt", "r", stdin);
    int a, b, c, d, e;
//    vector<tuple<int, int, int> > dutySchedules;
    map<int, int> dutyPiecesNumber;

    while(1)
    {
        ~scanf("%d,%d,%d", &a, &b, &c);
        if(a == 0) break;
//        dutySchedules.push_back(make_pair(a, b, c));

        dutyPiecesNumber[b] ++;
    }



    vector<tuple<int, int, int, int, int> > dutyPieces;

    while(~scanf("%d,%d,%d,%d,%d", &a, &b, &c, &d, &e))
    {
        if(c - b != dutyPiecesNumber[a])
        {
            cout << a << " " << b << " " << c << endl;
        }
    }







    return 0;
}



















