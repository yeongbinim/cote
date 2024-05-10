package main.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_부분합_TwoPointer {
    private static int solution(int[] arr, int target) {
        int answer = Integer.MAX_VALUE;
        long[] prefixArr = new long[arr.length + 1];
        for (int i = 1; i < prefixArr.length; i++) {
            prefixArr[i] = prefixArr[i - 1] + arr[i - 1];
        }

        int i = 0, j = 1;
        while (i < prefixArr.length - 1 && j < prefixArr.length) {
            if (prefixArr[j] - prefixArr[i] >= target) {
                answer = Math.min(answer, j - i);
                i++;
            }
            else {
                j++;
            }
        }

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0 ; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(arr, S));
    }
}
