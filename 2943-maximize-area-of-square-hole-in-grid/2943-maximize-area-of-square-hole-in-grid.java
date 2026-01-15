class Solution {
    /**
     * Calculates the maximum square hole area that can be created by removing bars.
     * The area is determined by the minimum of the maximum consecutive gaps in both directions.
     * 
     * @param n Number of horizontal positions
     * @param m Number of vertical positions
     * @param hBars Array of removable horizontal bar positions
     * @param vBars Array of removable vertical bar positions
     * @return The area of the maximum square hole
     */
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        // Find the maximum consecutive gap in both horizontal and vertical directions
        int maxHorizontalGap = findMaxConsecutiveGap(hBars);
        int maxVerticalGap = findMaxConsecutiveGap(vBars);
      
        // The square hole's side length is limited by the smaller gap
        int maxSquareSide = Math.min(maxHorizontalGap, maxVerticalGap);
      
        // Return the area of the square
        return maxSquareSide * maxSquareSide;
    }

    /**
     * Finds the maximum number of consecutive bars that can be removed.
     * The gap size is the number of consecutive bars plus 1.
     * 
     * @param bars Array of bar positions that can be removed
     * @return The maximum gap size (consecutive bars + 1)
     */
    private int findMaxConsecutiveGap(int[] bars) {
        // Sort the bars array to find consecutive sequences
        Arrays.sort(bars);
      
        // Track the maximum consecutive sequence and current sequence length
        int maxConsecutive = 1;
        int currentConsecutive = 1;
      
        // Iterate through sorted bars to find consecutive sequences
        for (int i = 1; i < bars.length; ++i) {
            // Check if current bar is consecutive to the previous one
            if (bars[i] == bars[i - 1] + 1) {
                // Extend current consecutive sequence
                currentConsecutive++;
                maxConsecutive = Math.max(maxConsecutive, currentConsecutive);
            } else {
                // Reset counter when sequence breaks
                currentConsecutive = 1;
            }
        }
      
        // Return gap size (consecutive bars + 1)
        return maxConsecutive + 1;
    }
}