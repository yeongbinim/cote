package main.programmers;

import java.util.PriorityQueue;

public class L3_PriorityQ_징검다리건너기 {
    private class Stone implements Comparable<Stone>{
        int index;
        int value;

        Stone(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public int compareTo(Stone o) {
            return Integer.compare(o.value, value);
        }
    }
    public int solution(int[] stones, int k) {
        PriorityQueue<Stone> maxHeap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            maxHeap.add(new Stone(i, stones[i]));
        }

        int answer = maxHeap.peek().value;
        for (int i = 1; i <= stones.length - k; i++) {
            maxHeap.add(new Stone(i + k - 1, stones[i + k - 1]));
            while(maxHeap.peek().index < i) {
                maxHeap.poll();
            }
            answer = Math.min(answer, maxHeap.peek().value);
        }

        return answer;
    }

    public static void main(String[] args) {
        L3_PriorityQ_징검다리건너기 sol = new L3_PriorityQ_징검다리건너기();
        int answer = sol.solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3);
        System.out.println(answer);
    }
}
