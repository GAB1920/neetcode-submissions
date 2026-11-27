/**
1186. Maximum Subarray Sum with One Deletion (LeetCode, Medium)
https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/

Given an array of integers arr, return the maximum sum of a non-empty
subarray (contiguous elements) with at most one element deletion.
In other words, choose a subarray and optionally delete one element from it
so that there is still at least one element left, and the sum of the
remaining elements is as large as possible.

Note: the subarray must be non-empty after deleting one element.

Example 1: arr = [1,-2,0,3]           -> 4   (delete -2: [1, 0, 3])
Example 2: arr = [1,-2,-2,3]          -> 3   (just [3])
Example 3: arr = [-1,-1,-1,-1]        -> -1  (can't be empty, so [-1])

Constraints:
  1 <= arr.length <= 10^5
  -10^4 <= arr[i] <= 10^4
**/
class Solution {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        if(n == 1){
            return arr[0];
        }

        int maxNoDelete = arr[0];
        int maxOneDelete = arr[0];
        int result = arr[0];

        for(int i = 1; i < n; i++){

            int prevNoDelete = maxNoDelete;

            maxNoDelete = Math.max(maxNoDelete + arr[i], arr[i]);

            maxOneDelete = Math.max(maxOneDelete + arr[i], prevNoDelete);

            result = Math.max(result, Math.max(maxOneDelete, maxNoDelete));
        }
        return result;
    }
}

//Clarifying question
/**
1. does the subarray have to be non-empty after the deletion? -> yes
2. is the deletion optional? -> yes, "at most one"
3. can all values be negative? -> yes, then the answer is the largest single element

edge cases
1. single element -> return arr[0]
2. all negative [-1,-1,-1,-1] -> -1
3. deleting helps [1,-2,0,3] -> 4
4. deleting doesn't help [1,-2,-2,3] -> 3
5. int is enough: max sum is 10^5 * 10^4 = 10^9

How it works (Kadane with two running answers, both for subarrays ENDING at i)
- maxNoDelete  = best sum ending at i with no deletion (plain Kadane, LC 53):
                 extend the old subarray (maxNoDelete + arr[i]) or start fresh (arr[i]).
- maxOneDelete = best sum ending at i with one deletion. Two ways:
    A. the deletion happened earlier -> maxOneDelete + arr[i]
    B. delete arr[i] itself          -> prevNoDelete (the no-delete subarray just before i)
- prevNoDelete saves the OLD maxNoDelete, because the line above overwrites it
  and option B needs the subarray that does NOT include arr[i].
- result = best of both at every i.

Time O(n), space O(1).
Prerequisite: LC 53 Maximum Subarray.
**/
