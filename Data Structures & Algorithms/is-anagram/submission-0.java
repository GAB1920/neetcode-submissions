class Solution {
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> h = new HashMap<>();
        if(s.length() != t.length()){
            return false;
        }
        for(int i = 0; i < s.length(); i++){
            h.put(s.charAt(i), h.getOrDefault(s.charAt(i), 0) + 1);  
        }
        for(int j = 0; j < t.length(); j++){
            if(!h.containsKey(t.charAt(j))){
                return false;
            }
            h.put(t.charAt(j), h.get(t.charAt(j)) - 1);
            if(h.get(t.charAt(j)) < 0) return false;
        }return true;
    }
}
