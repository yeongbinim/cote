package main.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G4_공유기설치_BinarySearch {
    private static int[] sortedArr;
    private static boolean isPossible(int pick, int count) {
        int cur = sortedArr[0];
        for (; count > 0; count--) {
            int idx = Arrays.binarySearch(sortedArr, cur);
            if (idx >= 0) {
                cur += pick;
            }
            else if (-idx <= sortedArr.length){
                cur = sortedArr[-idx - 1] + pick;
            }
            else {
                return false;
            }
        }
        return true;
    }

    private static int solution(int[] arr, int C) {
        Arrays.sort(arr);
        sortedArr = arr;

        int left = 1;
        int right = arr[arr.length - 1];

        while (left < right) {
            int pick = (left + right + 1) / 2;
            if (isPossible(pick, C)) {
                left = pick;
            } else {
                right = pick - 1;
            }
        }

        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solution(arr, C));
    }
}
