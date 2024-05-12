package main.programmers;

public class L2_쿼드압축후개수세기_DFS {
    private int isSameNumber(int[][] arr, int startR, int startC, int endR, int endC) {
        int sum = 0;
        for (int r = startR; r < endR; r++) {
            for (int c= startC; c < endC; c++) {
                sum += arr[r][c];
            }
        }
        if (sum == 0) {
            return 0;
        } else if (sum == (endR - startR) * (endC - startC)) {
            return 1;
        }
        return -1;
    }

    private int[] recur(int[][] arr, int startR, int startC, int endR, int endC) {
        int count = isSameNumber(arr, startR, startC, endR, endC);
        int[] countArr = new int[2];
        if (count != -1) {
            countArr[count] += 1;
            return countArr;
        }

        int half = (endR - startR) / 2;
        for (int r = startR; r < endR; r += half) {
            for (int c = startC; c < endC; c += half) {
                int[] result = recur(arr, r, c, r + half, c + half);
                countArr[0] += result[0];
                countArr[1] += result[1];
            }
        }
        return countArr;
    }
    public int[] solution(int[][] arr) {
        return recur(arr, 0, 0, arr.length, arr[0].length);
    }
}