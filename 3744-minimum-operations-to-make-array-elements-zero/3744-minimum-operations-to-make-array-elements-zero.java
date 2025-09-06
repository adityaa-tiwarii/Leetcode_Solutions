class Solution {
    public long minOperations(int[][] queries) {
        List<Long> pow4 = new ArrayList<>();
        pow4.add(1L);
        while (pow4.get(pow4.size() - 1) <= 1_000_000_000L) {
            pow4.add(pow4.get(pow4.size() - 1) * 4L);
        }

        List<Long> prefix = new ArrayList<>();
        prefix.add(0L);
        for (int i = 0; i < pow4.size() - 1; i++) {
            prefix.add(prefix.get(prefix.size() - 1) + (long)(i + 1) * 3L * pow4.get(i));
        }

        long res = 0;
        for (int[] q : queries) {
            int l = q[0], r = q[1];
            long s = F(r, pow4, prefix) - F(l - 1, pow4, prefix);
            res += (s + 1) / 2;
        }
        return res;
    }

    private long F(long n, List<Long> pow4, List<Long> prefix) {
        if (n <= 0) return 0;
        int k = Collections.binarySearch(pow4, n);
        if (k < 0) k = -k - 2;
        return prefix.get(k) + (long)(k + 1) * (n - pow4.get(k) + 1);
    };
}