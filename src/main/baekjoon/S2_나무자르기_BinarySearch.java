package main.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_나무자르기_BinarySearch {
    private static boolean isPossible(int[] heightArr, int M, int pick) {
        long sum = 0;
        for (int height : heightArr) {
            if (height > pick) {
                sum += height - pick;
            }
        }
        return sum <= M;
    }

    private static int solution(int[] heightArr, int M) {
        int left = 0;
        int right = Integer.MAX_VALUE;

        while (left < right) {
            int pick = (left + right) / 2;
            if (isPossible(heightArr, M - 1, pick)) {
                right = pick;
            } else {
                left = pick + 1;
            }
        }
        return left - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] heightArr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < heightArr.length; i++) {
            heightArr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(heightArr, M));
    }
}
