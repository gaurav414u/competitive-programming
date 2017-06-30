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
    int n, a;
    int arr[100];
    bool ones = false;
    bool consecutive = false;
    
    int currentLength = 0;
    int seriesNum = 0;
    int maxLength = 0;
    
    int maxConsecutiveLength = 0;
    int lastLength = 0;
    
    
    cin>>n;
    cin.ignore();
    
    for (int i = 0 ; i < n ; i++ ) {
        cin>>a;
        arr[i] = a;
        
        if (a == 1) {
            if (ones == false) {
                //started counting this series
                if (i > 1) {
                    if (arr[i-2] == 1) {
                        consecutive = true;
                    }
                }
                seriesNum++;
            }
            ones = true;
            currentLength++;
            if (currentLength > maxLength) {
                maxLength = currentLength;
            }
        } else {
            //a is 0
            if (ones) {
                if (consecutive) {
                    //if this series was part of consecutive series
                    if (lastLength + currentLength + 1 > maxConsecutiveLength) {
                        maxConsecutiveLength = lastLength + currentLength + 1;
                    }
                    consecutive = false;
                }
                ones = false;
                lastLength = currentLength;
                currentLength = 0;
            }
        }
    }
    
    if (ones) {
        if (consecutive) {
            //if this series was part of consecutive series
            if (lastLength + currentLength + 1> maxConsecutiveLength) {
                maxConsecutiveLength = lastLength + currentLength + 1;
            }
            consecutive = false;
        }
        ones = false;
        lastLength = currentLength;
        currentLength = 0;
    }
    
    if (seriesNum == 0) {
        cout << 0;
    } else if (seriesNum == 1) {
        cout<<maxLength;
    } else if (seriesNum == 2) {
        if (maxConsecutiveLength > 0) {
            cout<<maxConsecutiveLength - 1;
        } else {
            cout<<maxLength + 1;
        }
    } else {
        cout<<std::max(maxLength + 1, maxConsecutiveLength);
    }
    return 0;
}
