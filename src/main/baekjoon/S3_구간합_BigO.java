package main.baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class S3_구간합_BigO {
    private static int[] computeIntervalSums(int[] numArr, int[][] queryArr) {
        // O(N)
        int[] prefixSum = new int[numArr.length + 1];
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + numArr[i - 1];
        }

        // O(M)
        int[] results = new int[queryArr.length];
        for (int j = 0; j < queryArr.length; j++) {
            int startIdx = queryArr[j][0] - 1;
            int endIdx = queryArr[j][1];

            results[j] = prefixSum[endIdx] - prefixSum[startIdx];
        }

        return results;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] numArr = new int[N];
        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        int[][] queryArr = new int[M][2];
        for (int j = 0; j < M; j++) {
            st = new StringTokenizer(br.readLine());
            queryArr[j][0] = Integer.parseInt(st.nextToken());
            queryArr[j][1] = Integer.parseInt(st.nextToken());
        }

        // 로직
        int[] intervalSums = computeIntervalSums(numArr, queryArr);

        // 출력
        for (int sum : intervalSums) {
            bw.write(sum + "\n");
        }

        bw.close();
    }
}
