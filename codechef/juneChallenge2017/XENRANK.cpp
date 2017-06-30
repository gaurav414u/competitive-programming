//
//  main.cpp
//  Testcpp
//
//  Created by Gaurav Bhola on 16/05/17.
//
//
#include <iostream>
using namespace std;

int main(int argc, const char * argv[]) {
    int t;
    cin>>t;
    cin.ignore();
    while (t > 0) {
        long long a, b;
        cin>>a>>b;
        long long n = a + b + 1; //+ 1 because line number is 1 based
        long long lineRank = n * (n - 1)/2 + 1;
        
        cout<<lineRank+a<<"\n";
        t--;
    }
    return 0;
}
