package main.programmers;

import java.util.*;

public class L2_디펜스게임_PriorityQ {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        k = Math.min(k, enemy.length);

        for (int i = 0; i < k; i++) {
            pq.add(enemy[i]);
        }

        int round = k;
        while (round < enemy.length) {
            int prev = pq.poll();
            int cur = enemy[round];
            int damage = Math.min(prev, cur);
            pq.add(Math.max(prev, cur));

            n -= damage;
            if (n < 0) {
                break;
            }
            round++;
        }

        return round;
    }
}
