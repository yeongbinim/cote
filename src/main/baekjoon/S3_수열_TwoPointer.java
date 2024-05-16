package main.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_수열_TwoPointer {
    private static long solution(int[] arr, int K) {
        //누적합 풀이
        long max = Long.MIN_VALUE;
        long[] prefixArr = new long[arr.length + 1];

        for (int i = 1; i < prefixArr.length; i++) {
            prefixArr[i] = prefixArr[i - 1] + arr[i - 1];
        }

        int left = 0;
        int right = K;
        while (right < prefixArr.length) {
            long sum = prefixArr[right++] - prefixArr[left++];
            max = Math.max(sum, max);
        }

        return max;
    }

    private static long solution2(int[] arr, int K) {
        //투포인터 풀이
        long sum = 0;
        for (int i = 0; i < K; i++) {
            sum += arr[i];
        }
        long max = sum;

        int left = 0;
        int right = K;

        while (right < arr.length) {
            sum -= arr[left++];
            sum += arr[right++];
            max = Math.max(sum, max);
        }

        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0 ; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution2(arr, K));
    }
}
