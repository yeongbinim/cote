package main.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G3_BinarySearch_세용액 {
    private static int getOptimalIdx(int[] arr, int startIdx, int target) {
        int optimalIdx = Arrays.binarySearch(arr, startIdx, arr.length, -target);
        if (optimalIdx < 0) {
            if (-optimalIdx - 1 == startIdx) {
                optimalIdx = -optimalIdx - 1;
            } else if (-optimalIdx - 1 == arr.length) {
                optimalIdx = -optimalIdx - 2;
            } else {
                optimalIdx = Math.abs(target + arr[-optimalIdx - 2]) <= Math.abs(target + arr[-optimalIdx - 1])
                        ? -optimalIdx - 2
                        : -optimalIdx - 1;
            }
        }
        return optimalIdx;
    }

    private static int[] solution(int[] arr) {
        int pick1 = 0, pick2 = 1, pick3 = 2;
        long picksSum = Long.MAX_VALUE;
        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                int target = (arr[i] + arr[j]);
                int optimalIdx = getOptimalIdx(arr, j + 1, target);
                long optimalValue = Math.abs(target + (long) arr[optimalIdx]);
                if (picksSum > optimalValue) {
                    pick1 = i;
                    pick2 = j;
                    pick3 = optimalIdx;
                    picksSum = optimalValue;
                }
            }
        }

        return new int[]{arr[pick1], arr[pick2], arr[pick3]};
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = solution(arr);
        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }
}
