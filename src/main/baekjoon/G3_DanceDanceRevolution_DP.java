package main.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G3_DanceDanceRevolution_DP {
    private static int calculateCost(int prev, int cur) {
        if (prev == cur) {
            return 1;
        } else if (prev == 0) {
            return 2;
        } else if ((prev + cur) % 2 == 1) {
            return 3;
        }
        return 4;
    }

    private static int solution(int[] arr) {
        int[][][] dp = new int[arr.length][5][5];
        for (int[][] table : dp) {
            for (int[] row : table) {
                Arrays.fill(row, Integer.MAX_VALUE / 2);
            }
        }

        dp[0][arr[0]][0] = 2;
        dp[0][0][arr[0]] = 2;

        for (int i = 1; i < arr.length; i++) {
            for (int l = 0; l <= 4; l++) {
                for (int r = 0; r <= 4; r++) {
                    dp[i][l][arr[i]] = Math.min(dp[i][l][arr[i]], dp[i - 1][l][r] + calculateCost(r, arr[i]));
                    dp[i][arr[i]][r] = Math.min(dp[i][arr[i]][r], dp[i - 1][l][r] + calculateCost(l, arr[i]));
                }
            }
        }

        int min = Integer.MAX_VALUE;

        for (int[] row : dp[dp.length - 1]) {
            for (int cell: row) {
                min = Math.min(min, cell);
            }
        }

        return min;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Integer> list = new ArrayList<>();
        while (true) {
            int num = Integer.parseInt(st.nextToken());
            if (num == 0) break;
            list.add(num);
        }

        int[] arr = list.stream().mapToInt(Integer::intValue).toArray();;
        System.out.println(solution(arr));
    }
}
