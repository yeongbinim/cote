package main.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P4_DP_평범한배낭2 {
    private static class Item {
        int weight;
        int value;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    private static int solution(Item[] items, int maxWeight) {
        // 기존 배낭과 로직 동일, 하지만 일차원 배열 사용하여 뒤에서부터 값 추가
        int[] dp = new int[maxWeight + 1];
        for (int r = 0; r < items.length; r++) {
            Item cur = items[r];
            for (int c = maxWeight; c >= cur.weight; c--) {
                dp[c] = Math.max(dp[c], dp[c - cur.weight] + cur.value);
            }
        }
        return dp[maxWeight];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Item> items = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            for (int c = 1; c < count; c = c << 1) { // i를 2의 제곱으로 증가
                items.add(new Item(weight * c, value * c));
                count -= c;
            }
            if (count > 0) { // 아직 남은 개수가 있다면 그 개수만큼 아이템 추가
                items.add(new Item(weight * count, value * count));
            }
        }

        int answer = solution(items.toArray(Item[]::new), M);
        System.out.println(answer);
    }
}
