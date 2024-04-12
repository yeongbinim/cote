package main.programmers;

public class L3_BinarySearch_입국심사 {
    static final long MAX = 1_000_000_000;

    private boolean isPossible(long n, int[] times, long target) {
        for (int time : times) {
            n -= target / time;
        }
        return n <= 0;
    }

    public long solution(int n, int[] times) {
        long left = 1;
        long right = MAX * MAX;

        while (left < right) {
            long mid = (left + right) / 2;
            if (isPossible(n, times, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}