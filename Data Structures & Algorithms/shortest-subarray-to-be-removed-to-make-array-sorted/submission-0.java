class Solution {
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int right = arr.length - 1;

        // while(left < arr.length - 1 && arr[left] <= arr[left + 1]){
        //     left++;
        // }

        while(right > 0 && arr[right - 1] <= arr[right]){
            right--;
        }

        if (right == 0) return 0;

        int answer = right;

        for(int left = 0; left < n; left++){
            if(left > 0 && arr[left - 1] > arr[left])break;

            while(right < n && arr[right] < arr[left]){
                right++;
            }

            answer = Math.min(answer, right - left - 1);
        }
        return answer;
    }
}
