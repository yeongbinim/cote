package main.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_랜선자르기_BinarySearch {
    private static final long MAX_LENGTH = Integer.MAX_VALUE * 2L;

    private static boolean isPossible(int[] lengthArr, int N, long pick) {
        int sum = 0;
        for (int length : lengthArr) {
            sum += (int) (length / pick);
        }
        return sum <= N;
    }

    private static int solution(int[] lengthArr, int N) {
        long left = 1;
        long right = MAX_LENGTH;
        while (left < right) {
            long pick = (left + right) / 2;
            if (isPossible(lengthArr, N - 1, pick)) {
                right = pick;
            } else {
                left = pick + 1;
            }
        }
        return (int) left - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] lengthArr = new int[K];

        for (int i = 0; i < lengthArr.length; i++) {
            lengthArr[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solution(lengthArr, N));
    }
}
