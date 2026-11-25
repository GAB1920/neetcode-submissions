class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        // Step 1: Initialize the deque and result array

        // Deque stores INDICES, not values
        Deque<Integer> deque = new ArrayDeque<>();

        int[] result = new int[n - k + 1];
        // Step 2: Setup deque for the first k elements

        for(int i = 0; i < k; i++){
            // Step 2: Setup deque for the first k elements
            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }
            deque.offerLast(i);
        }
       
        result[0] = nums[deque.peekFirst()];

        for(int i = k; i < n; i++){
            // Remove the element that has slid out of the window
            if (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            //// Remove all elements smaller than the incoming element

            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }
            // Add current element's index
            deque.offerLast(i);
            // The front of the deque is always the max of the current window
            result[i - k + 1] = nums[deque.peekFirst()];
        }

        return result;

    }
}
