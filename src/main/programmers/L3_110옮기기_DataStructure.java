package main.programmers;

import java.util.*;

public class L3_110옮기기_DataStructure {
    private boolean lastThreeIs110(ArrayDeque<String> stack) {
        if (stack.size() < 3) {
            return false;
        }
        String str3 = stack.pollLast();
        String str2 = stack.pollLast();
        String str1 = stack.pollLast();

        String str = str1 + str2 + str3;
        if (str.equals("110")) {
            return true;
        }
        stack.addLast(str1);
        stack.addLast(str2);
        stack.addLast(str3);
        return false;
    }

    public String[] solution(String[] strArr) {
        String[] answer = new String[strArr.length];

        for (int i = 0; i < strArr.length; i++) {
            ArrayDeque<String> stack = new ArrayDeque<>();
            StringBuilder str110s = new StringBuilder();

            for (int j = 0; j < strArr[i].length(); j++) {
                stack.addLast("" + strArr[i].charAt(j));
                if (lastThreeIs110(stack)) {
                    str110s.append("110");
                }
            }

            String coreStr = String.join("", stack);
            int indexZero = coreStr.lastIndexOf("0");
            if (indexZero == -1) {
                answer[i] = String.format("%s%s", str110s, coreStr);
            } else {
                answer[i] = String.format("%s%s%s", coreStr.substring(0, indexZero + 1), str110s, coreStr.substring(indexZero + 1));
            }
        }

        return answer;
    }
}