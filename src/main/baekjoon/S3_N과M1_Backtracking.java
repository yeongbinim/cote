package main.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S3_N과M1_Backtracking {
    private static boolean[] used;
    private static int N;

    // 사전순, 중복X
    private static List<String> solution1(int maxLevel, ArrayDeque<Integer> stack) {
        List<String> list = new ArrayList<>();
        if (stack.size() == maxLevel) {
            StringBuilder sb = new StringBuilder();
            for (int num : stack) {
                sb.append(num).append(" ");
            }
            list.add(sb.toString().trim());
            return list;
        }

        for (int i = 1; i <= N; i++) {
            if (!used[i - 1]) {
                used[i - 1] = true;
                stack.addLast(i);
                list.addAll(solution1(maxLevel, stack));
                stack.pollLast();
                used[i - 1] = false;
            }
        }
        return list;
    }

    // 오름차순 조합, 사전순, 중복X
    private static List<String> solution2(int maxLevel, ArrayDeque<Integer> stack) {
        List<String> list = new ArrayList<>();
        if (stack.size() == maxLevel) {
            StringBuilder sb = new StringBuilder();
            for (int num : stack) {
                sb.append(num).append(" ");
            }
            list.add(sb.toString().trim());
            return list;
        }

        for (int i = stack.isEmpty() ? 1 : stack.peekLast() + 1; i <= N; i++) {
            stack.addLast(i);
            list.addAll(solution2(maxLevel, stack));
            stack.pollLast();
        }
        return list;
    }

    // 사전순, 중복O
    private static List<String> solution3(int maxLevel, ArrayDeque<Integer> stack) {
        List<String> list = new ArrayList<>();
        if (stack.size() == maxLevel) {
            StringBuilder sb = new StringBuilder();
            for (int num : stack) {
                sb.append(num).append(" ");
            }
            list.add(sb.toString().trim());
            return list;
        }

        for (int i = 1; i <= N; i++) {
            stack.addLast(i);
            list.addAll(solution3(maxLevel, stack));
            stack.pollLast();
        }
        return list;
    }

    // 오름차순 조합, 사전순, 중복O
    private static List<String> solution4(int maxLevel, ArrayDeque<Integer> stack) {
        List<String> list = new ArrayList<>();
        if (stack.size() == maxLevel) {
            StringBuilder sb = new StringBuilder();
            for (int num : stack) {
                sb.append(num).append(" ");
            }
            list.add(sb.toString().trim());
            return list;
        }

        for (int i = stack.isEmpty() ? 1 : stack.peekLast(); i <= N; i++) {
            stack.addLast(i);
            list.addAll(solution4(maxLevel, stack));
            stack.pollLast();
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        used = new boolean[N];

        List<String> answerList = solution1(M, new ArrayDeque<>());

        StringBuilder sb = new StringBuilder();
        for (String answer : answerList) {
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
