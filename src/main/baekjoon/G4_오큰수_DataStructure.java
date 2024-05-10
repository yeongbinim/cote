package main.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class G4_오큰수_DataStructure {
    private static int[] solution(int[] sequence) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int[] answer = new int[sequence.length];

        for (int i = sequence.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peekLast() <= sequence[i]) {
                stack.pollLast();
            }
            if (stack.isEmpty()) {
                answer[i] = -1;
            } else {
                answer[i] = stack.peekLast();
            }
            stack.addLast(sequence[i]);
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] sequence = new int[N];
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        int answer[] = solution(sequence);

        StringBuilder sb = new StringBuilder();
        for (int num : answer) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}
