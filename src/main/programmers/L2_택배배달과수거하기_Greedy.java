package main.programmers;

public class L2_택배배달과수거하기_Greedy {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long distSum = 0;

        int moveCount = 0;
        int deliverySum = 0;
        int pickupSum = 0;

        for (int dist = n; dist > 0; dist--) {
            deliverySum += deliveries[dist - 1];
            pickupSum += pickups[dist - 1];
            int maxQuotient = (int) Math.max(Math.ceil(deliverySum / (double) cap), Math.ceil(pickupSum / (double) cap));
            if (maxQuotient > moveCount) {
                distSum += dist * (maxQuotient - moveCount) * 2L;
                moveCount = maxQuotient;
            }
        }

        return distSum;
    }
}
