package main.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G5_용액_BinarySearch {
    private static int bsearch(int[] arr, int leftIdx, int rightIdx, int target) {
        rightIdx -= 1;
        while (leftIdx < rightIdx) {
            int pickIdx = (leftIdx + rightIdx) / 2;
            if (arr[pickIdx] >= target) {
                rightIdx = pickIdx;
            } else {
                leftIdx = pickIdx + 1;
            }
        }
        return leftIdx;
    }

    private static int[] solution(int[] arr) {
        int leftIdx = 0;
        int rightIdx = arr.length - 1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            int optimalIdx = bsearch(arr, i + 1, arr.length, -arr[i]);
            int optimalValue = Math.abs(arr[i] + arr[optimalIdx]);
            if (i < optimalIdx - 1 && optimalValue >= Math.abs(arr[i] + arr[optimalIdx - 1])) {
                optimalValue = Math.abs(arr[i] + arr[optimalIdx - 1]);
                optimalIdx = optimalIdx - 1;
            }
            if (min > optimalValue) {
                leftIdx = i;
                rightIdx = optimalIdx;
                min = optimalValue;
            }
        }

        return new int[]{arr[leftIdx], arr[rightIdx]};
    }

    private static int getOptimalIdx(int[] arr, int idx) {
        int optimalIdx = Arrays.binarySearch(arr, idx + 1, arr.length, -arr[idx]);
        if (optimalIdx < 0) {
            if (2 + optimalIdx == -idx) {
                optimalIdx = -optimalIdx - 1;
            } else if (-optimalIdx == arr.length + 1) {
                optimalIdx = -optimalIdx - 2;
            } else {
                optimalIdx = Math.abs(arr[idx] + arr[-optimalIdx - 1]) <= Math.abs(arr[idx] + arr[-optimalIdx - 2])
                        ? -optimalIdx - 1
                        : -optimalIdx - 2;
            }
        }
        return optimalIdx;
    }

    private static int[] solutionApi(int[] arr) {
        int leftIdx = 0;
        int rightIdx = arr.length - 1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            int optimalIdx = getOptimalIdx(arr, i);
            int optimalValue = Math.abs(arr[i] + arr[optimalIdx]);
            if (min > optimalValue) {
                leftIdx = i;
                rightIdx = optimalIdx;
                min = optimalValue;
            }
        }

        return new int[]{arr[leftIdx], arr[rightIdx]};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = solutionApi(arr);
        System.out.printf("%d, %d\n", answer[0], answer[1]);
    }
}
