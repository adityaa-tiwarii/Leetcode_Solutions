class Solution {
    private int corridorLength;
    private char[] corridorArray;
    private Integer[][] memoization;  // memoization[position][seatsInCurrentSection]
    private final int MOD = (int) 1e9 + 7;

    public int numberOfWays(String corridor) {
        // Convert string to char array for faster access
        corridorArray = corridor.toCharArray();
        corridorLength = corridorArray.length;
      
        // Initialize memoization table
        // memoization[i][j] represents the number of ways starting from position i 
        // with j seats already in the current section
        memoization = new Integer[corridorLength][3];
      
        // Start DFS from position 0 with 0 seats in the current section
        return dfs(0, 0);
    }

    /**
     * Recursively calculates the number of ways to divide the corridor
     * @param currentPosition - current index in the corridor
     * @param seatsInSection - number of seats in the current section (0, 1, or 2)
     * @return number of valid ways to divide from current position
     */
    private int dfs(int currentPosition, int seatsInSection) {
        // Base case: reached end of corridor
        if (currentPosition >= corridorLength) {
            // Valid only if the last section has exactly 2 seats
            return seatsInSection == 2 ? 1 : 0;
        }
      
        // Check memoization cache
        if (memoization[currentPosition][seatsInSection] != null) {
            return memoization[currentPosition][seatsInSection];
        }
      
        // Update seat count if current position has a seat
        int updatedSeatCount = seatsInSection;
        if (corridorArray[currentPosition] == 'S') {
            updatedSeatCount++;
        }
      
        // Invalid case: more than 2 seats in a section
        if (updatedSeatCount > 2) {
            return 0;
        }
      
        // Option 1: Don't place a divider, continue with current section
        int totalWays = dfs(currentPosition + 1, updatedSeatCount);
      
        // Option 2: Place a divider (only valid if current section has exactly 2 seats)
        if (updatedSeatCount == 2) {
            // Place divider and start new section with 0 seats
            totalWays = (totalWays + dfs(currentPosition + 1, 0)) % MOD;
        }
      
        // Store result in memoization table and return
        memoization[currentPosition][seatsInSection] = totalWays;
        return totalWays;
    }
}
