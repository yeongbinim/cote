package main.programmers;

import java.util.*;

public class L2_DataStructure_오픈채팅방 {
    private static final Map<String, String> commandMap = new HashMap<>(){{
        put("Enter", "님이 들어왔습니다.");
        put("Leave", "님이 나갔습니다.");
    }};

    public String[] solution(String[] record) {
        ArrayList<String> answer = new ArrayList<>();
        Map<String, String> nicknameMap = new HashMap<>();

        for (String line : record) {
            String[] splited = line.split(" ");
            if (splited.length == 3) { // Enter나 Change인 경우
                nicknameMap.put(splited[1], splited[2]);
            }
        }

        for (String line : record) {
            String[] splited = line.split(" ");
            if (commandMap.containsKey(splited[0])) { // Enter나 Leave인 경우
                answer.add(nicknameMap.get(splited[1]) + commandMap.get(splited[0]));
            }
        }

        return answer.toArray(String[]::new);
    }
}
