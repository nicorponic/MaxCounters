/*
You are given N counters, initially set to 0, and you have two possible 
operations on them:

increase(X) − counter X is increased by 1,
max counter − all counters are set to the maximum value of any counter.
A non-empty zero-indexed array A of M integers is given. This array represents 
consecutive operations:

if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X),
if A[K] = N + 1 then operation K is max counter.
For example, given integer N = 5 and array A such that:

    A[0] = 3
    A[1] = 4
    A[2] = 4
    A[3] = 6
    A[4] = 1
    A[5] = 4
    A[6] = 4
the values of the counters after each consecutive operation will be:

    (0, 0, 1, 0, 0)
    (0, 0, 1, 1, 0)
    (0, 0, 1, 2, 0)
    (2, 2, 2, 2, 2)
    (3, 2, 2, 2, 2)
    (3, 2, 2, 3, 2)
    (3, 2, 2, 4, 2)
The goal is to calculate the value of every counter after all operations.

Write a function:

class Solution { public int[] solution(int N, int[] A); }
that, given an integer N and a non-empty zero-indexed array A consisting of 
M integers, returns a sequence of integers representing the values of the 
counters.

The sequence should be returned as:

a structure Results (in C), or
a vector of integers (in C++), or
a record Results (in Pascal), or
an array of integers (in any other programming language).
For example, given:

    A[0] = 3
    A[1] = 4
    A[2] = 4
    A[3] = 6
    A[4] = 1
    A[5] = 4
    A[6] = 4
the function should return [3, 2, 2, 4, 2], as explained above.

Assume that:

N and M are integers within the range [1..100,000];
each element of array A is an integer within the range [1..N + 1].
Complexity:

expected worst-case time complexity is O(N+M);
expected worst-case space complexity is O(N), beyond input storage (not 
counting the storage required for input arguments).
Elements of input arrays can be modified.
Copyright 2009–2016 by Codility Limited. 
All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.

 */
package maxcounters;

import java.util.Arrays;

public class MaxCounters {

    public static void main(String[] args) {

        int A[] = {3, 4, 4, 7, 1, 4, 4};
        int N = 5;

        int counter[] = new int[N];
        int n = A.length;
        int max = -1, current_min = 0;

        for (int i = 0; i < n; i++) {
            //check if the element of array A fulfils the conditional range
            if (A[i] >= 1 && A[i] <= N) {
                //if yes, check if the element in counter array is less than the current min
                if (counter[A[i] - 1] < current_min) {
                    //if yes, set that element to the current min
                    counter[A[i] - 1] = current_min;
                }
                //and perform the addition as normal
                counter[A[i] - 1] = counter[A[i] - 1] + 1;
                //check if this element in counter array is now the max ans store it as max
                if (counter[A[i] - 1] > max) {
                    max = counter[A[i] - 1];
                }
                //else if (larger than our range), set current min to the max
            } else if (A[i] >= N + 1) {
                current_min = max;
            }
        }
        //update the rest of the elements that didn't go through the for loop
        for (int i = 0; i < N; i++) {
            if (counter[i] < current_min) {
                counter[i] = current_min;
            }
        }
        System.out.println(Arrays.toString( counter));
    }
}

class Solution1 {

    public int[] solution1(int N, int[] A) {
        final int condition = N + 1;
        int currentMax = 0;
        int upDate = 0;
        int countersArray[] = new int[N];

        for (int i = 0; i < A.length; i++) {
            int currentValue = A[i];
            //check if the element of the array is larger than the condition (N=5)
            //if yes, store the new value current max in the upDate variable
            //to perform, further on, the correct addition of the largest element(upDate)
            if (currentValue >= condition) {
                upDate = currentMax;
            } else {
                //update the countersArray..
                int position = currentValue - 1;
                //check if the element of countersArray needs to be updated correctly
                if (countersArray[position] < upDate) {
                    //perform the correct addition
                    countersArray[position] = upDate + 1;
                } else {
                    countersArray[position]++;
                }
                //check and store the largest element of countersArray to variable currentMax
                if (countersArray[position] > currentMax) {
                    currentMax = countersArray[position];
                }
            }
        }
        //correct the elements of the array that missed the for loop before
        for (int ii = 0; ii < N; ii++) {
            if (countersArray[ii] < upDate) {
                countersArray[ii] = upDate;
            }
        }
        return(countersArray);
    }
}
