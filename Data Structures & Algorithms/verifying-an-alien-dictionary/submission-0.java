class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        

        for(int i = 0; i < order.length(); i++){
            map.put(order.charAt(i),i);
        }

        for(int i = 0 ; i< words.length - 1;i++){
            String word1 = words[i];
            String words2 = words[i+1];
            boolean foundDifference = false;
            for (int j = 0; j < Math.min(word1.length(), words2.length()); j++) {
                char c1 = word1.charAt(j);
                char c2 = words2.charAt(j);
                int c1int = map.get(c1);
                int c2int = map.get(c2);
                if (c1int < c2int){
                    foundDifference = true;
                    break;
                }
                if((c1int > c2int)){
                    return false;
                }
            }
            if (!foundDifference && word1.length() > words2.length()) return false;
        }
        return true;
        
    }
}