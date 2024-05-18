package main.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G3_텀프로젝트_DFS {
    private static int[] nodeArr;
    private static final int VISIT = 1;
    private static final int NOT_CYCLE = 2;
    private static final int CYCLE = 3;

    private static void dfs(int cur, int[] check) {
        switch (check[cur]) {
            case NOT_CYCLE:
            case CYCLE:
                return;
            case VISIT:
                check[cur] = CYCLE;
                break;
            default:
                check[cur] = VISIT;
        }

        dfs(nodeArr[cur] - 1, check);

        if (check[cur] == VISIT) { //사이클이 아닌 것들은 전부
            check[cur] = NOT_CYCLE;
        }
    }

    private static int solution(int[] arr) {
        nodeArr = arr;

        int[] check = new int[arr.length];
        for (int i = 0; i < nodeArr.length; i++) {
            dfs(i, check);
        }

        int answer = check.length;
        for (int sig : check) {
            if (sig == CYCLE) {
                answer--;
            }
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        for (int T = Integer.parseInt(br.readLine()); T > 0; T--) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            sb.append(solution(arr)).append("\n");
        }
        System.out.println(sb);
    }
}
