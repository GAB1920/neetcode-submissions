/**
Flower Bouquets (Oracle on-site, from 1point3acres; not on LeetCode/NeetCode)

You are given a binary string s representing a row of flowers:
  0 = Rose, 1 = Cosmos

You may make either type of bouquet:
  1. Three consecutive Roses in the original string ("000"), earning p.
  2. Two consecutive flowers of different types ("01" or "10"), earning q.

Each flower can be used at most once, and bouquets must not overlap.
Flowers may be left unused, and making no bouquets is allowed.
Leaving flowers unused does NOT make originally nonadjacent flowers adjacent.
Return the maximum total revenue.

Input: s, p, q        Output: the maximum revenue

Constraints (practice):
  0 <= len(s) <= 200000, s contains only '0' and '1', 0 <= p, q <= 10^9

Example:
  s = "00010", p = 5, q = 4  ->  9
  Take "000" at the start (+5) and "10" at the end (+4).
**/
class Solution {
    public long maxRevenue(String s, long p, long q) {
        int n = s.length();

        // dp[i] = most money from the first i flowers. dp[0] = 0.
        long[] dp = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            long skip  = dp[i - 1];   // option 1: leave the last flower unused
            long two   = 0;           // option 2: last 2 flowers make "01"/"10"
            long three = 0;           // option 3: last 3 flowers make "000"

            if (i >= 2 && s.charAt(i - 1) != s.charAt(i - 2)) {
                two = q + dp[i - 2];
            }

            if (i >= 3 && s.charAt(i - 1) == '0'
                       && s.charAt(i - 2) == '0'
                       && s.charAt(i - 3) == '0') {
                three = p + dp[i - 3];
            }

            dp[i] = Math.max(skip, Math.max(two, three));
        }

        return dp[n];
    }
}

//Clarifying question
/**
1. can s be empty? what should we return? -> 0
2. are p and q always non-negative? -> yes, so making nothing (0) is always allowed
3. can skipping a flower make its neighbours adjacent? -> no, bouquets must be
   consecutive in the original string ("0100" has no "000")

edge cases
1. empty string -> 0
2. all cosmos "111" -> 0
3. "010" -> only one bouquet, both need the middle flower
4. "0001", p = 1, q = 10 -> 10 (greedy "000" first gives only 1)
5. big p and many "000" -> answer passes int range, so use long

Pattern: 1-D DP like House Robber. At each flower: skip it (jump back 1),
end a 2-bouquet (jump back 2), or end a 3-bouquet (jump back 3). Take the max.
Time O(n), space O(n).
**/
