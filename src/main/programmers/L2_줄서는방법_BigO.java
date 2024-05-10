package main.programmers;

import java.util.ArrayList;
import java.util.List;

public class L2_줄서는방법_BigO {
    public int[] solution(int n, long k) {
        long fact = 1;
        for (int i = 1; i <= n; i++) { //O(n)
            fact *= i;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) { //O(n)
            list.add(i);
        }

        k -= 1;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) { //O(n)
            fact /= n - i;
            int idx = (int) (k / fact);
            answer[i] = list.remove(idx); // 직접 idx에서 값을 가져와서 제거
            k %= fact; // 다음 순번을 위한 k 값 조정
        }

        return answer;
    }
}
