class Solution {
    /**
     * Counts the number of substrings consisting only of '1's in the given binary string.
     * For a continuous segment of n '1's, the number of valid substrings is n*(n+1)/2.
     * This algorithm efficiently calculates this by accumulating counts as it traverses.
     * 
     * @param s The input binary string containing only '0's and '1's
     * @return The total count of substrings with only '1's, modulo 10^9 + 7
     */
    public int numSub(String s) {
        // Define modulo constant to prevent integer overflow
        final int MODULO = (int) 1e9 + 7;
      
        // Total count of valid substrings
        int totalCount = 0;
      
        // Current consecutive '1's count ending at current position
        int consecutiveOnes = 0;
      
        // Iterate through each character in the string
        for (int i = 0; i < s.length(); i++) {
            // If current character is '1', increment consecutive count
            // Otherwise, reset consecutive count to 0
            if (s.charAt(i) == '1') {
                consecutiveOnes++;
            } else {
                consecutiveOnes = 0;
            }
          
            // Add current consecutive count to total
            // This represents all substrings of '1's ending at position i
            totalCount = (totalCount + consecutiveOnes) % MODULO;
        }
      
        return totalCount;
    }
}