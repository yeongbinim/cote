package main.programmers;

public class L2_2xN타일링_DP {
    private final int MOD = 1_000_000_007;
    public int solution(int n) {
        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }

        return (int) dp[n];
    }
}
