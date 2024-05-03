package main.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_DP_동전1 {
    private static int solution(int[] coinArr, int amount) {
        int[][] dp = new int[coinArr.length + 1][amount + 1];

        dp[0][0] = 1;

        for (int r = 1; r < dp.length; r++) {
            int coin = coinArr[r - 1];
            for (int c = 0; c < dp[r].length; c++) {
                dp[r][c] = dp[r - 1][c];
                if (c >= coin) {
                    dp[r][c] += dp[r][c - coin];
                }
            }
        }

        return dp[coinArr.length][amount];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coinArr = new int[N];
        for (int i = 0; i < N; i++) {
            coinArr[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solution(coinArr, K));
    }
}
