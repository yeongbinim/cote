package main.programmers;

import java.util.*;

public class L2_캐시_DataStructure {
    class Cache {
        private Queue<String> queue = new ArrayDeque<>();
        private int size;
        private final int HIT_TIME = 1;
        private final int MISS_TIME = 5;

        Cache(int size) {
            this.size = size;
        }

        //O(캐시 크기 x 문자열 길이)
        public int add(String value) {
            if (queue.contains(value)) {
                queue.remove(value);
                queue.add(value);
                return HIT_TIME;
            }
            queue.add(value);
            if (size <= 0) {
                queue.poll();
            }
            return MISS_TIME;
        }
    }
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Cache cache = new Cache(cacheSize);
        //O(city개수 x add()시간복잡도)
        for (String city: cities) {
            answer += cache.add(city.toUpperCase());
        }
        return answer;
    }
    public static void main(String[] args) {
        L2_캐시_DataStructure test = new L2_캐시_DataStructure();
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        int answer = test.solution(cacheSize, cities);
        System.out.println(answer);
    }
}
