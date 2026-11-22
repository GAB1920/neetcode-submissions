class TimeMap {
    private record Entry(int timestamp, String value){};
    private final Map<String, List<Entry>> map;

    public TimeMap() {
        this.map = new HashMap<>();
        
    }
    
    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new ArrayList<>())
        .add(new Entry(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
        List<Entry> entries = map.get(key);
        if(entries == null || entries.isEmpty()){
            return "";
        }
        String result = "";
        
        int left = 0;
        int right = entries.size() - 1;

        while(left <= right){
            int mid = left + (right - left)/2;
            Entry current = entries.get(mid);

            if(current.timestamp() <= timestamp){
                result = current.value();
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return result;


    }
}
