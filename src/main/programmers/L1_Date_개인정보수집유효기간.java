package main.programmers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class L1_Date_개인정보수집유효기간 {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public int[] solution(String today, String[] terms, String[] privacies) {
        LocalDate todayDate = LocalDate.parse(today, formatter);
        List<Integer> answerList = new ArrayList<>();

        HashMap<String, Integer> termTable = new HashMap<>();
        for (String term: terms) {
            StringTokenizer st = new StringTokenizer(term);
            termTable.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < privacies.length; i++) {
            StringTokenizer st = new StringTokenizer(privacies[i]);
            LocalDate prevDate = LocalDate.parse(st.nextToken(), formatter);
            LocalDate endDate = prevDate.plusMonths(termTable.get(st.nextToken()));
            if (!endDate.isAfter(todayDate)) {
                answerList.add(i + 1);
            }
        }

        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }
}
