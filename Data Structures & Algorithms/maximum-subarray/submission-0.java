class Solution {
    public int maxSubArray(int[] nums) {
        int maxSub = nums[0];

        int currSum = 0;

        for(int n : nums){
            if(currSum < 0){
                currSum = 0;
            }
            currSum += n;
            maxSub = Math.max(maxSub, currSum);
        }
        return maxSub;   
    }
}


// //Contiguous vs. non-contiguous: "Just to confirm, does the subarray have to be contiguous (adjacent elements in order), or can I skip elements?" (Crucial—non-contiguous is a completely different problem).

// Array size & empty arrays: "Can the input array be empty? If so, what should I return—0, Integer.MIN_VALUE, or is there a guarantee of at least one element?"

// Number range & signs: "Can the numbers be negative, zero, or all negative? Should I watch out for integer overflow (int vs. long)?"

// Return value format: "Do you only need the maximum sum itself, or do you also need the start and end indices (or the subarray itself)?"

// Edge cases to check:
// 1. All negative numbers (e.g., [-5, -2, -9]) -> init maxSub = nums[0], NOT 0
// 2. Single element (e.g., [7] or [-4]) -> returns that element
// 3. All zeros or mix with zeros (e.g., [0, -1, 0]) -> handles cleanly if maxSub starts at nums[0]
// 4. Big drop followed by big jump (e.g., [5, -100, 8]) -> currSum resets to 0 after -100, then starts fresh at 8
// 5. Large sums -> consider `long` if sums could exceed 32-bit int range