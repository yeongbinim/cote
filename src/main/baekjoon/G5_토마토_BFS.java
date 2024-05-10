package main.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G5_토마토_BFS {
    private static class Pos {
        int h;
        int r;
        int c;
        Pos(int h, int r, int c) {
            this.h = h;
            this.r = r;
            this.c = c;
        }
    }
    private static final int[][] dhdrdc = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};

    private static int solution(int[][][] box3d, List<Pos> tomatoPosList, int emptyCount) {
        ArrayDeque<Pos> queue = new ArrayDeque<>(tomatoPosList);
        int count = 0;
        int answer = 1;

        while (!queue.isEmpty()) {
            Pos cur = queue.pollFirst();
            for (int[] delta : dhdrdc) {
                int[] next = {delta[0] + cur.h, delta[1] + cur.r, delta[2] + cur.c};
                boolean isInBoundary = (next[0] >= 0 && next[1] >= 0 && next[2] >= 0)
                        && (next[0] < box3d.length && next[1] < box3d[0].length && next[2] < box3d[0][0].length);
                if (isInBoundary && box3d[next[0]][next[1]][next[2]] == 0) {
                    answer = box3d[cur.h][cur.r][cur.c] + 1;
                    box3d[next[0]][next[1]][next[2]] = answer;
                    queue.addLast(new Pos(next[0], next[1], next[2]));
                    count++;
                }
            }
        }

        int accCount = emptyCount + count + tomatoPosList.size();
        int cellCount = box3d.length * box3d[0].length * box3d[0][0].length;
        if (accCount < cellCount) {
            return -1;
        } else {
            return answer - 1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[][][] box3d = new int[H][N][M];
        ArrayList<Pos> tomatoPosList = new ArrayList<>();
        int emptyCount = 0;
        for (int h = 0; h < H; h++) {
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; c++) {
                    int id = Integer.parseInt(st.nextToken());
                    box3d[h][r][c] = id;
                    if (id == 1) {
                        tomatoPosList.add(new Pos(h, r, c));
                    } else if (id == -1){
                        emptyCount++;
                    }
                }
            }
        }
        System.out.println(solution(box3d, tomatoPosList, emptyCount));
    }
}
