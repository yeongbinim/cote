package main.programmers;

public class L3_거스름돈_DP {
    public int solution(int n, int[] money) {
        long[][] dp = new long[money.length + 1][n + 1];

        for (int r = 0; r < dp.length; r++) {
            dp[r][0] = 1;
        }

        for (int r = 1; r < dp.length; r++) {
            for (int c = 1; c < dp[r].length; c++) {
                dp[r][c] = dp[r - 1][c];
                if (c >= money[r - 1]) {
                    dp[r][c] += dp[r][c - money[r - 1]];
                    dp[r][c] %= 1_000_000_007;
                }
            }
        }

        return (int) dp[money.length][n];
    }
}
