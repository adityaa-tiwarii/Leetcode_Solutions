class Solution {
    public int countPartitions(int[] nums, int k) {
        final int MOD = (int) 1e9 + 7;
      
        // TreeMap to maintain sorted order of elements in current window
        TreeMap<Integer, Integer> windowElements = new TreeMap<>();
      
        int n = nums.length;
      
        // dp[i] = number of valid partitions ending at index i
        int[] dp = new int[n + 1];
      
        // prefixSum[i] = cumulative sum of dp values up to index i
        int[] prefixSum = new int[n + 1];
      
        // Base case: empty array has one valid partition
        dp[0] = 1;
        prefixSum[0] = 1;
      
        // Left pointer for sliding window
        int left = 1;
      
        // Process each position as potential partition end
        for (int right = 1; right <= n; right++) {
            int currentNum = nums[right - 1];
          
            // Add current element to the window
            windowElements.merge(currentNum, 1, Integer::sum);
          
            // Shrink window from left while range exceeds k
            while (windowElements.lastKey() - windowElements.firstKey() > k) {
                int leftNum = nums[left - 1];
              
                // Remove leftmost element from window
                if (windowElements.merge(leftNum, -1, Integer::sum) == 0) {
                    windowElements.remove(leftNum);
                }
                left++;
            }
          
            // Calculate number of valid partitions ending at current position
            // This equals sum of all valid partitions from positions [left-1, right-1]
            int previousSum = (left >= 2) ? prefixSum[left - 2] : 0;
            dp[right] = (prefixSum[right - 1] - previousSum + MOD) % MOD;
          
            // Update prefix sum
            prefixSum[right] = (prefixSum[right - 1] + dp[right]) % MOD;
        }
      
        return dp[n];
    }
}
