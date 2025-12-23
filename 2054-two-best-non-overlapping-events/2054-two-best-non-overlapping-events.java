class Solution {
    public int maxTwoEvents(int[][] events) {
        // Sort events by start time in ascending order
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        int n = events.length;

        // maxValueFromIndex[i] stores the maximum value among all events starting from index i
        int[] maxValueFromIndex = new int[n + 1];

        // Build suffix maximum array from right to left
        // maxValueFromIndex[i] = max value of all events from index i to n-1
        for (int i = n - 1; i >= 0; i--) {
            maxValueFromIndex[i] = Math.max(maxValueFromIndex[i + 1], events[i][2]);
        }

        int maxSum = 0;

        // For each event, find the best non-overlapping event to pair with
        for (int[] currentEvent : events) {
            int currentValue = currentEvent[2];
            int currentEndTime = currentEvent[1];

            // Binary search to find the first event that starts after current event ends
            // feasible(mid) = events[mid][0] > currentEndTime
            // Pattern: [false, false, ..., true, true, true]
            int left = 0;
            int right = n - 1;
            int firstTrueIndex = -1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (events[mid][0] > currentEndTime) {
                    firstTrueIndex = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            // If we found a non-overlapping event, add its maximum possible value
            if (firstTrueIndex != -1) {
                currentValue += maxValueFromIndex[firstTrueIndex];
            }

            // Update the maximum sum found so far
            maxSum = Math.max(maxSum, currentValue);
        }

        return maxSum;
    }
}