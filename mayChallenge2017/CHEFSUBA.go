package main

import (
	"fmt"
	"syscall"
)


var result []int32
var windowDict []int32
var processed []bool

func getResult(s int32, n int32, k int32) int32 {
	if (processed[s]) {
		return result[s]
	}
	//generate result array
	max := int32(0)
	//fmt.Println("shift ============== ", i)
	for j := int32(0) ; j < n ; j++ {
		if (j >= n-(k-1)-s) && (j < n-s ) {
			//fmt.Println("for i",i," break at", j)
			continue
		} else {
			//find max
			//fmt.Println("checking at",j," val",windowDict[j]," max", max)
			if (windowDict[j] > max) {
				max = windowDict[j]
				//fmt.Println("setting max", max)
			}

		}
	}
	result[s] = max
	processed[s] = true
	return result[s]
	//fmt.Println("result", result)
}

func main() {
	var i int32 = 0
	var n int32
	var k int32
	var p int32

	fmt.Scanf("%d", &n)
	fmt.Scanf("%d", &k)
	fmt.Scanf("%d", &p)
	a := make([]int32, n, n)
	windowDict = make([]int32, n, n)
	if (n < p) {
		result = make([]int32, n, n)
		processed = make([]bool, n, n)
	} else {
		result = make([]int32, p, p)
		processed = make([]bool, p, p)

	}

	for i=0 ; i < n; i++ {
		fmt.Scanf("%d", &a[i])
	}


	j := int32(0)
	var count int32 = 0
	for i = 0 ; i < n ; i++ {
		if (i == 0) {
			for j = 0 ; j < k ; j++ {
				if (a[j] == 1) {
					count++
				}
			}
		} else {
			//i > 0
			rightEnd := i + k - 1
			if (rightEnd >= n)  {
				rightEnd = rightEnd - n
			}
			//fmt.Println(rightEnd)
			if a[rightEnd] == 1 {
				count++
			}
		}

		windowDict[i] = count

		if (a[i] == 1) {
			//decrease the count because this element will be left behind for the next movement of window
			count -= 1
		}

	}


	//fmt.Println(windowDict)

	/*
	for  i = 0 ; i < n ; i++ {
		var count int32 = 0
		for j := int32(0) ; j < k; j++ {
			el := i + j
			if (el >= n) {
				el = el % n
			}
			if (a[el] == 1) {
				count++
			}
		}
		windowDict[i] = count
	}*/


	//fmt.Println(windowDict)
	//fmt.Println(result)

	var s string
	fmt.Scanf("%s", &s)
	shifts := int32(0)
	for i = 0 ; i < p ; i++ {
		if (s[i] == '!') {
			shifts++
			if (shifts == n) {
				shifts = 0
			}
		}  else {
			fmt.Println(getResult(shifts, n, k))
		}
	}
	syscall.Exit(0)
}
