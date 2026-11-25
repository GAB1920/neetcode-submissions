class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int globMax = nums[0];
        int globMin = nums[0];

        int currMax = 0;
        int currMin = 0;

        int total = 0;

        for(int n : nums){
            currMax = Math.max(currMax + n, n);
            currMin = Math.min(currMin + n, n);
            total += n;
            globMax = Math.max(currMax, globMax);
            globMin = Math.min(currMin, globMin);
        }

        return globMax > 0 ? Math.max(globMax, total - globMin) : globMax;
        
    }
}