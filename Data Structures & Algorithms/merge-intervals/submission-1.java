class Solution {
    public int[][] merge(int[][] intervals) {
        // 0 or 1 interval can't overlap with anything else
        if (intervals.length <= 1) {
            return intervals;
        }

        // Sort by start time so overlapping intervals sit next to each other
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> result = new ArrayList<>();

        // Track the active interval we are currently building/merging into
        int[] newInterval = intervals[0];
        result.add(newInterval);

        for (int[] interval : intervals) {
            // Current interval starts at or before the active interval ends -> overlap
            if (interval[0] <= newInterval[1]) {
                // Extend the end time if the current interval reaches further
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            } else {
                // Gap found: start a new active interval and add it to result
                newInterval = interval;
                result.add(newInterval);
            }
        }

        return result.toArray(new int[0][]);
    }
}
