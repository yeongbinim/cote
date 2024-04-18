package main.baekjoon;

import java.io.*;
import java.util.*;

public class G4_BFS_연구소 {

    private static final int[][] drdc = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int[][] copyMap(int[][] map) {
        int[][] newMap = new int[map.length][];
        for (int i = 0; i < newMap.length; i++) {
            newMap[i] = Arrays.copyOf(map[i], map[i].length);
        }
        return newMap;
    }

    private static int bfs(int[][] map, List<Node> virusList) {
        ArrayDeque<Node> queue = new ArrayDeque<>(virusList);
        int virusCount = virusList.size();

        while (!queue.isEmpty()) {
            Node curNode = queue.pollFirst();
            for (int[] rc : drdc) {
                int r = curNode.r + rc[0];
                int c = curNode.c + rc[1];
                boolean isInBoundary = r < 0 || c < 0 || r >= map.length || c >= map[0].length;
                if (isInBoundary && map[r][c] == 0) {
                    map[r][c] = 2;
                    virusCount++;
                    queue.addLast(new Node(r, c));
                }
            }
        }
        return virusCount;
    }

    private static int solution(int[][] map) {
        int answer = 0;
        int N = map.length;
        int M = map[0].length;

        List<Node> virusList = new ArrayList<>();
        int wallCount = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 1) {
                    wallCount += 1;
                }
                else if (map[r][c] == 2) {
                    virusList.add(new Node(r, c));
                }
            }
        }

        for (int i = 0; i < N * M - 2; i++) {
            int r1 = i / M;
            int c1 = i % M;
            if (map[r1][c1] != 0) {
                continue;
            }
            for (int j = i + 1; j < N * M - 1; j++) {
                int r2 = j / M;
                int c2 = j % M;
                if (map[r2][c2] != 0) {
                    continue;
                }
                for (int k = j + 1; k < N * M; k++) {
                    int r3 = k / M;
                    int c3 = k % M;
                    if (map[r3][c3] != 0) {
                        continue;
                    }
                    int[][] newMap = copyMap(map);
                    newMap[r1][c1] = 1;
                    newMap[r2][c2] = 1;
                    newMap[r3][c3] = 1;
                    answer = Math.max(answer, N * M - bfs(newMap, virusList) - wallCount - 3);
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(map));
    }
}
