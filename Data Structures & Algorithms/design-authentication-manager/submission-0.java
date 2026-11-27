/**
Design Authentication Manager (LeetCode 1797)
https://leetcode.com/problems/design-authentication-manager/

There is an authentication system that works with tokens. Each token
expires timeToLive seconds after the currentTime it was made or last renewed.

Implement AuthenticationManager:
  AuthenticationManager(int timeToLive)  sets timeToLive.
  generate(tokenId, currentTime)         makes a new token that expires at currentTime + timeToLive.
  renew(tokenId, currentTime)            if the token exists AND is unexpired, its new expiry is
                                         currentTime + timeToLive. Otherwise, do nothing.
  countUnexpiredTokens(currentTime)      returns how many tokens are still unexpired.

Tricky rule: if a token expires at time t and an action happens at time t,
the expiry happens FIRST. So a token is alive only while currentTime < expiry.

Example (timeToLive = 5):
  renew("aaa", 1)              -> "aaa" doesn't exist, nothing happens
  generate("aaa", 2)           -> "aaa" expires at 7
  countUnexpiredTokens(6)      -> 1
  generate("bbb", 7)           -> "bbb" expires at 12 ("aaa" is now dead)
  renew("aaa", 8)              -> "aaa" is dead, nothing happens
  renew("bbb", 10)             -> "bbb" now expires at 15
  countUnexpiredTokens(15)     -> 0  (bbb dies exactly at 15)

Constraints:
  1 <= timeToLive <= 10^8, 1 <= currentTime <= 10^8
  1 <= tokenId.length <= 5 (lowercase letters)
  currentTime values are strictly increasing across calls
  at most 2000 calls in total
**/
class AuthenticationManager {
    private int timeToLive;
    HashMap<String, Integer> tokens = new HashMap<>();

    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        tokens.put(tokenId, currentTime + timeToLive);

    }

    public void renew(String tokenId, int currentTime) {
        if(tokens.containsKey(tokenId) && tokens.get(tokenId) > currentTime){
            tokens.put(tokenId, currentTime + timeToLive);
        }

    }

    public int countUnexpiredTokens(int currentTime) {
        int count = 0;
        for(int expiry : tokens.values()){
            if(currentTime < expiry){
                count++;
            }
        }
        return count;

    }
}

//Clarifying question
// - Is a token alive AT its expiry time? -> No. Expiry happens first, so alive means currentTime < expiry.
// - Can renew bring back a dead token? -> No. Dead or missing token = do nothing.
// - Does renew add time to the old expiry? -> No. It RESETS expiry to currentTime + timeToLive.
// - Is currentTime always increasing? -> Yes (lets us use the simple map; also enables cleanup).
//
// Edge cases:
// - renew on a token that never existed -> containsKey is checked FIRST, so tokens.get never
//   returns null (null > currentTime would throw a NullPointerException).
// - renew at exactly the expiry time -> token is already dead, ignored.
// - count on an empty map -> 0.
//
// Idea: we only need tokenId -> expiry time, so a HashMap<String, Integer>.
//
// Complexity:
//   generate O(1), renew O(1), countUnexpiredTokens O(n) where n = tokens stored.
//   Space O(n).
//
// Follow-up (if the interviewer asks to make count faster / save memory):
//   dead tokens stay in the map forever. Since currentTime only increases, you can
//   remove expired tokens during count (iterator.remove()), or keep tokens in a
//   LinkedHashMap ordered by expiry (remove + re-put on renew) and pop dead ones
//   from the front.
//
// Pattern: design + HashMap with expiry times. Related: LC 981 Time Based Key-Value Store.
