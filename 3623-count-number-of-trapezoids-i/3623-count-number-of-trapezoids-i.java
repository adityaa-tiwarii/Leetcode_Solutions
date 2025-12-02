class Solution {
    public int countTrapezoids(int[][] points) {
        final long MOD = 1000000007;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int[] p : points){
            freq.put(p[1], freq.getOrDefault(p[1], 0) + 1);
        }
        long total = 0, pairs = 0;
        for (int f : freq.values()){
            if (f <= 1){
                continue;
            }
            long pair = (long) f * (f - 1) / 2;
            total += pair;
            pairs += pair * pair;
        }
        return (int) ((total * total - pairs) / 2 % MOD);
    }
}