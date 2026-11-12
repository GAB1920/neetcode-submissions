class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a,b) -> a.getValue() - b.getValue());
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i],0)+1);
        }   
        for(Map.Entry<Integer, Integer> e : map.entrySet()){
            minHeap.offer(e);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        int[] res = new int[k];
        int i = 0;
        while(!minHeap.isEmpty()){
            res[i++] = minHeap.poll().getKey();
        }
        

        return res;

    }
}
