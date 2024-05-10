package main.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_평범한배낭_DP {
    private static class Item {
        int weight;
        int value;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    private static int solution(Item[] items, int maxWeight) {
        int[][] dp = new int[items.length + 1][maxWeight + 1];
        for (int r = 1; r < dp.length; r++) {
            Item cur = items[r - 1];
            for (int c = 1; c < dp[r].length; c++) {
                dp[r][c] = dp[r - 1][c];
                if (c >= cur.weight) {
                    dp[r][c] = Math.max(dp[r - 1][c], cur.value + dp[r - 1][c - cur.weight]);
                }
            }
        }

        return dp[items.length][maxWeight];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Item[] items = new Item[N];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            items[n] = new Item(weight, value);
        }

        int answer = solution(items, K);
        System.out.println(answer);
    }
}
