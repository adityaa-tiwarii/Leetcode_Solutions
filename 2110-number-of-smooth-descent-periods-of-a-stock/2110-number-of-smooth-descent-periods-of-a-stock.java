class Solution {
    public long getDescentPeriods(int[] prices) {
        long totalDescentPeriods = 0;
        int arrayLength = prices.length;
      
        // Iterate through the array, finding consecutive descent sequences
        int startIndex = 0;
        while (startIndex < arrayLength) {
            // Find the end of current descent sequence
            int endIndex = startIndex + 1;
          
            // Extend the sequence while consecutive elements decrease by exactly 1
            while (endIndex < arrayLength && prices[endIndex - 1] - prices[endIndex] == 1) {
                endIndex++;
            }
          
            // Calculate the length of the current descent sequence
            int sequenceLength = endIndex - startIndex;
          
            // Add the number of subarrays in this sequence
            // For a sequence of length n, there are n*(n+1)/2 subarrays
            // This includes all possible contiguous subarrays within the sequence
            totalDescentPeriods += (1L + sequenceLength) * sequenceLength / 2;
          
            // Move to the next potential sequence
            startIndex = endIndex;
        }
      
        return totalDescentPeriods;
    }
}