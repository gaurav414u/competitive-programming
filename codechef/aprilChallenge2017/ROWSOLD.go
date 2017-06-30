package main

import (
	"fmt"
)

func main(){
	var t int64
	fmt.Scanf("%d", &t)

	var ts int64 = 0

    for ts=0 ; ts < t; ts++ {
		var a string
        fmt.Scanf("%s", &a)
		var n int64 = int64(len(a))
		var time int64 = 0
		var count1 int64 = 0
		var i int64 = 0
        for i < n-1 {
            if a[i] == '1' {
                count1 += 1
                if (a[i + 1]) == '0' {
					var j int64
					j = i + 1
					for j = i + 1 ; j < n-1; j++ {
                        if j + 1 < n && (a[j+1]) != '0' {
                            break
						} else {
							continue
						}
					}
                    time += (j-i+1)*count1
                    i = j
				}
			}

            i += 1
		}

        fmt.Println(time)
	}
}
