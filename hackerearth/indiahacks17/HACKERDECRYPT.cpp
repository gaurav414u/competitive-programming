//
//  main.cpp
//  Testcpp
//
//  Created by Gaurav Bhola on 16/05/17.
//
//
#include <iostream>
#include <vector>

#define MAXN 1000000

using namespace std;

int spf[MAXN];

// Calculating SPF (Smallest Prime Factor) for every
// number till MAXN.
// Time Complexity : O(nloglogn)
void sieve()
{
    spf[1] = 1;
    for (int i=2; i<=MAXN; i++)
        
        // marking smallest prime factor for every
        // number to be itself.
        spf[i] = i;
    
    // separately marking spf for every even
    // number as 2
    for (int i=4; i<=MAXN; i+=2)
        spf[i] = 2;
    
    for (int i=3; i*i<=MAXN; i++)
    {
        // checking if i is prime
        if (spf[i] == i)
        {
            // marking SPF for all numbers divisible by i
            for (int j=i*i; j<=MAXN; j+=i)
                
                // marking spf[j] if it is not
                // previously marked
                if (spf[j]==j)
                    spf[j] = i;
        }
    }
}

// A O(log n) function returning primefactorization
// by dividing by smallest prime factor at every step
vector<int> getFactorization(int x)
{
    vector<int> ret;
    while (x != 1 && spf[x] != 0)
    {
        ret.push_back(spf[x]);
        x = x / spf[x];
    }
    return ret;
}

int main(int argc, const char * argv[]) {
    int n, q;
    int presence[1000000] = {0};
    
    cin>>n>>q;
    sieve();
    
    presence[1] = 1;
    for (int i = 0 ; i < n; i++) {
        int x;
        cin>>x;
        
        int j=x;
        presence[j] = 1;
        //create the exponential presence array
        if (x <= 1000 && x > 1) {
            for (j = x * x ; j <= MAXN ; j*=x) {
                presence[j] = 1;
            }
        }
    }
    
    for (int i = 0 ; i < q ; i++) {
        int x;
        cin>>x;
        bool yes = false;
        
        vector<int> factors = getFactorization(x);
        int size = factors.size();
        for (int j = 0 ; j < size ; j++) {
            for (int k = j + 1 ; k < size ; k++) {
                if(presence[x / (factors[j] * factors[k])]) {
                    yes = true;
                    break;
                }
            }
        }
        if (yes) {
            cout<<"YES"<<endl;
        } else {
            cout<<"NO"<<endl;
        }
    }
    
    
    return 0;
}
