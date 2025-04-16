class Solution {
    public long countGood(int[] nums, int k) {
        // HashMap to store the frequency of each number in the current subarray
        Map<Integer, Integer> frequencyCounter = new HashMap<>();
        long totalCount = 0; // Total count of good subarrays
        long currentSize = 0; // Number of times a number has been repeated in the current subarray
        int startIndex = 0; // Start index for the sliding window

        // Iterate over the array using 'num' as the current element
        for (int num : nums) {
            // Update currentSize for the current value
            currentSize += frequencyCounter.getOrDefault(num, 0);
            // Increase the frequency counter for num
            frequencyCounter.merge(num, 1, Integer::sum);

            // Shrink the window from the left until the number of repeated elements is less than k
            while (currentSize - frequencyCounter.get(nums[startIndex]) + 1 >= k) {
                // Decrease the currentSize by the number of times the number at startIndex is in the window
                currentSize -= frequencyCounter.merge(nums[startIndex], -1, Integer::sum);
                // Move the start index of the subarray window to the right
                startIndex++;
            }
          
            // If the number of repeated elements is at least k, we count this as a 'good' subarray
            if (currentSize >= k) {
                // Add to the total number (startIndex + 1 indicates that we have a 'good' subarray up to the current index i)
                totalCount += startIndex + 1;
            }
        }

        // Return the total count of good subarrays
        return totalCount;
    }
}
