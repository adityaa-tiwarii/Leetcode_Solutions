class Solution {
    public int countPermutations(int[] complexity) {
        // Define modulo constant for preventing integer overflow
        final int MOD = 1_000_000_007;
      
        // Initialize result to 1 (representing one valid permutation initially)
        long result = 1;
      
        // Iterate through all elements starting from index 1
        for (int i = 1; i < complexity.length; i++) {
            // Check if current element is less than or equal to the first element
            // If so, no valid permutations exist (first element must be the smallest)
            if (complexity[i] <= complexity[0]) {
                return 0;
            }
          
            // Multiply result by current index (calculating factorial-like product)
            // Apply modulo to prevent overflow
            result = (result * i) % MOD;
        }
      
        // Cast long result back to int and return
        return (int) result;
    }
}
