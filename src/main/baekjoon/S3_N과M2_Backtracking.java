package main.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S3_N과M2_Backtracking {
    private static boolean[] used;
    private static int[] numArr;

    // 사전순, 중복X
    private static List<String> solution5(int maxLevel, ArrayDeque<Integer> stack) {
        List<String> list = new ArrayList<>();
        if (stack.size() == maxLevel) {
            StringBuilder sb = new StringBuilder();
            for (int num : stack) {
                sb.append(num).append(" ");
            }
            list.add(sb.toString().trim());
            return list;
        }

        for (int i = 0; i < numArr.length; i++) {
            if (!used[i]) {
                used[i] = true;
                stack.addLast(numArr[i]);
                list.addAll(solution5(maxLevel, stack));
                stack.pollLast();
                used[i] = false;
            }
        }
        return list;
    }

    // 오름차순 조합, 사전순, 중복X
    private static List<String> solution6(int maxLevel, ArrayDeque<Integer> stack) {
        List<String> list = new ArrayList<>();
        if (stack.size() == maxLevel) {
            StringBuilder sb = new StringBuilder();
            for (int num : stack) {
                sb.append(num).append(" ");
            }
            list.add(sb.toString().trim());
            return list;
        }

        for (int i = stack.isEmpty() ? 0 : Arrays.binarySearch(numArr, stack.peekLast()) + 1; i < numArr.length; i++) {
            stack.addLast(numArr[i]);
            list.addAll(solution6(maxLevel, stack));
            stack.pollLast();
        }
        return list;
    }

    // 사전순, 중복O
    private static List<String> solution7(int maxLevel, ArrayDeque<Integer> stack) {
        List<String> list = new ArrayList<>();
        if (stack.size() == maxLevel) {
            StringBuilder sb = new StringBuilder();
            for (int num : stack) {
                sb.append(num).append(" ");
            }
            list.add(sb.toString().trim());
            return list;
        }

        for (int i = 0; i < numArr.length; i++) {
            stack.addLast(numArr[i]);
            list.addAll(solution7(maxLevel, stack));
            stack.pollLast();
        }
        return list;
    }

    // 오름차순 조합, 사전순, 중복O
    private static List<String> solution8(int maxLevel, ArrayDeque<Integer> stack) {
        List<String> list = new ArrayList<>();
        if (stack.size() == maxLevel) {
            StringBuilder sb = new StringBuilder();
            for (int num : stack) {
                sb.append(num).append(" ");
            }
            list.add(sb.toString().trim());
            return list;
        }

        for (int i = stack.isEmpty() ? 0 : Arrays.binarySearch(numArr, stack.peekLast()); i < numArr.length; i++) {
            stack.addLast(numArr[i]);
            list.addAll(solution8(maxLevel, stack));
            stack.pollLast();
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        used = new boolean[N];
        numArr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numArr);

        List<String> answerList = solution6(M, new ArrayDeque<>());

        StringBuilder sb = new StringBuilder();
        for (String answer : answerList) {
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
