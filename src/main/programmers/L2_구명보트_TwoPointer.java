package main.programmers;

import java.util.*;

public class L2_구명보트_TwoPointer {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int count = 0;
        int left = 0;
        int right = people.length - 1;

        while (left <= right) {
            if (people[left] + people[right] > limit) {
                right--;
            } else {
                left++;
                right--;
            }
            count++;
        }

        return count;
    }
}
