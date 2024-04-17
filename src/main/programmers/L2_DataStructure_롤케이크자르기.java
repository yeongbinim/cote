package main.programmers;

import java.util.*;

public class L2_DataStructure_롤케이크자르기 {
    public int solution(int[] topping) {
        HashSet<Integer> leftTopping = new HashSet<>();
        HashMap<Integer, Integer> rightTopping = new HashMap<>();

        for (int t: topping) {
            rightTopping.put(t, rightTopping.getOrDefault(t, 0) + 1);
        }

        int answer = 0;

        for (int t: topping) {
            leftTopping.add(t);
            int count = rightTopping.getOrDefault(t, 0);
            count--;
            if (count > 0) {
                rightTopping.put(t, count);
            } else {
                rightTopping.remove(t);
            }

            int diff = leftTopping.size() - rightTopping.size();
            if (diff == 0) {
                answer++;
            } else if (diff > 0) {
                break;
            }
        }

        return answer;
    }
}