package main.baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class G5_개똥벌레_BigO {
    private static int[] findShortestSection(int height, int[] obstructionArr) {
        int[] prefix = new int[height + 1];

        //O(N)
        for (int odd = 1; odd <= obstructionArr.length; odd += 2) {
            int endIdx = obstructionArr[odd - 1];
            prefix[0]++;
            prefix[endIdx]--;
        }
        for (int even = 2; even <= obstructionArr.length; even += 2) {
            int startIdx = height - obstructionArr[even - 1];
            prefix[startIdx]++;
            prefix[height]--;
        }

        //O(H)
        for (int i = 1; i < height; i++) {
            prefix[i] += prefix[i - 1];
        }

        //O(H)
        int[] result = {Integer.MAX_VALUE, 0};
        for (int i = 0; i < height; i++) {
            if (prefix[i] < result[0]) {
                result[0] = prefix[i];
                result[1] = 1;
            } else if (prefix[i] == result[0]) {
                result[1]++;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] obstructionArr = new int[N];
        for (int i = 0; i < N; i++) {
            obstructionArr[i] = Integer.parseInt(br.readLine());
        }

        //로직
        int[] shortestSection = findShortestSection(H, obstructionArr);

        //출력
        bw.write(shortestSection[0] + " " + shortestSection[1] + "\n");
        bw.close();
    }
}
