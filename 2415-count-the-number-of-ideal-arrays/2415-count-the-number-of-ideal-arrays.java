class Solution {
    private int[][] memoization;
    private int[][] combinationMatrix;
    private int numElements;
    private int maxValue;
    private static final int MOD = (int) 1e9 + 7;

    public int idealArrays(int n, int maxValue) {
        this.numElements = n;
        this.maxValue = maxValue;

        // Initialize memoization array with -1 to indicate uncalculated states
        this.memoization = new int[maxValue + 1][16];
        for (int[] row : memoization) {
            Arrays.fill(row, -1);
        }

        // Pre-calculate the combination values (n choose k) up to n this combinationMatrix will be used in dfs
        combinationMatrix = new int[n][16];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i && j < 16; ++j) {
                combinationMatrix[i][j] = (j == 0 ? 1 : (combinationMatrix[i - 1][j] + combinationMatrix[i - 1][j - 1]) % MOD);
            }
        }

        int answer = 0;

        // Calculate the total number of ideal arrays for all starting values
        for (int i = 1; i <= maxValue; ++i) {
            answer = (answer + dfs(i, 1)) % MOD;
        }

        return answer;
    }

    // Depth First Search to find the number of ideal arrays starting with 'startValue'
    // and having 'length' number of distinct integers sorted in strictly increasing order.
    private int dfs(int startValue, int length) {
        // If previously computed, retrieve the result from memoization table
        if (memoization[startValue][length] != -1) {
            return memoization[startValue][length];
        }

        // Base value is 'n choose length-1'
        int count = combinationMatrix[numElements - 1][length - 1];

        // If length is less than the number of elements, continue to find all possible arrays
        if (length < numElements) {
            for (int k = 2; startValue * k <= maxValue; ++k) {
                count = (count + dfs(startValue * k, length + 1)) % MOD;
            }
        }

        // Store the result in the memoization table before returning
        memoization[startValue][length] = count;
        return count;
    }
}