class Solution {
  
    /**
     * Finds the final value after doubling the original value whenever it exists in the array.
     * The process continues until the value is not found in the array.
     * 
     * @param nums     The input array of integers
     * @param original The starting value to check and potentially double
     * @return The final value after all doubling operations
     */
    public int findFinalValue(int[] nums, int original) {
        // Create a HashSet to store all unique values from the array for O(1) lookup
        Set<Integer> numSet = new HashSet<>();
      
        // Add all elements from the array to the set
        for (int num : nums) {
            numSet.add(num);
        }
      
        // Keep doubling the original value while it exists in the set
        while (numSet.contains(original)) {
            // Double the original value using left shift operation (equivalent to multiplying by 2)
            original <<= 1;
        }
      
        // Return the final value that is not present in the set
        return original;
    }
}
