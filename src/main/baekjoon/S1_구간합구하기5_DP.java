package main.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_구간합구하기5_DP {
    private static int[][] prefixSumArr;
    private static void createPrefixSumArr(int[][] arr) {
        prefixSumArr = new int[arr.length + 1][arr[0].length + 1];
        for (int r = 1; r < prefixSumArr.length; r++) {
            for (int c = 1; c < prefixSumArr[r].length; c++) {
                prefixSumArr[r][c] = arr[r - 1][c - 1]
                        + prefixSumArr[r - 1][c]
                        + prefixSumArr[r][c - 1]
                        - prefixSumArr[r - 1][c - 1];
            }
        }
    }

    private static int calculateAreaSum(int startR, int startC, int endR, int endC) {
        return prefixSumArr[endR][endC]
                - prefixSumArr[endR][startC]
                - prefixSumArr[startR][endC]
                + prefixSumArr[startR][startC];
    }

    private static int[] solution(int[][] arr, int[][] pointerArr) {
        createPrefixSumArr(arr);
        int[] answer = new int[pointerArr.length];
        for (int i = 0; i < pointerArr.length; i++) {
            int[] pointer = pointerArr[i];
            answer[i] = calculateAreaSum(pointer[0] - 1, pointer[1] - 1, pointer[2], pointer[3]);
        }

        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] pointerArr = new int[M][];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            pointerArr[i] = new int[] {
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
            };
        }

        int[] answer = solution(arr, pointerArr);
        StringBuilder sb = new StringBuilder();

        for (int a : answer) {
            sb.append(a).append("\n");
        }

        System.out.println(sb);
    }
}
