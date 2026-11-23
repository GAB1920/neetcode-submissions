class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int currSum = 0;

        int left = 0;
        int right = 0;

        while(right < nums.length){
            currSum += nums[right];
            right++;

            while(currSum >= target){
                int currSize = right - left;

                minLen = Math.min(minLen, currSize);

                currSum -= nums[left];
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;

        
    }
}