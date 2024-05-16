package main.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_용액_TwoPointer {
    private static int[] solution(int[] arr) {
        int minLeft = arr[0], minRight = arr[arr.length - 1], min = Integer.MAX_VALUE;
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (Math.abs(sum) <= min) {
                min = Math.abs(sum);
                minLeft = arr[left];
                minRight = arr[right];
            }

            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
        return new int[] {minLeft, minRight};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = solution(arr);
        System.out.printf("%d %d\n", answer[0], answer[1]);
    }
}
