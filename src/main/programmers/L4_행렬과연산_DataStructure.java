package main.programmers;

import java.util.*;

public class L4_행렬과연산_DataStructure {
    class MoveMatrix {
        private final Deque<Integer> leftDeque = new ArrayDeque<>();
        private final Deque<Integer> rightDeque = new ArrayDeque<>();
        private final Deque<Deque<Integer>> midDeque = new ArrayDeque<>();
        private final int rowLength;
        private final int colLength;

        MoveMatrix(int[][] matrix) {
            this.rowLength = matrix.length;
            this.colLength = matrix[0].length;
            initDeque(matrix);
        }

        private void initDeque(int[][] matrix) {
            for (int[] record : matrix) {
                leftDeque.add(record[0]);
                rightDeque.add(record[colLength - 1]);

                Deque<Integer> deque = new ArrayDeque<>();
                midDeque.add(deque);
                for (int col = 1; col < colLength - 1; col++) {
                    deque.add(record[col]);
                }
            }
        }

        public void shiftRow() {
            leftDeque.addFirst(leftDeque.pollLast());
            rightDeque.addFirst(rightDeque.pollLast());
            midDeque.addFirst(midDeque.pollLast());
        }

        public void rotate() {
            Deque<Integer> topDeque = midDeque.getFirst();
            Deque<Integer> downDeque = midDeque.getLast();

            topDeque.addFirst(leftDeque.pollFirst());
            rightDeque.addFirst(topDeque.pollLast());
            downDeque.addLast(rightDeque.pollLast());
            leftDeque.addLast(downDeque.pollFirst());
        }

        public int[][] toArray() {
            int[][] matrix = new int[rowLength][colLength];
            for (int row = 0; row < rowLength; row++) {
                matrix[row][0] = leftDeque.pollFirst();
                matrix[row][colLength - 1] = rightDeque.pollFirst();

                Deque<Integer> deque = midDeque.pollFirst();
                for (int col = 1; col < colLength - 1; col++) {
                    matrix[row][col] = deque.pollFirst();
                }
            }
            return matrix;
        }
    }

    public int[][] solution(int[][] rc, String[] operations) {
        MoveMatrix moveMatrix = new MoveMatrix(rc);
        Map<String, Runnable> operationMap = new HashMap<>(){{
            put("ShiftRow", moveMatrix::shiftRow);
            put("Rotate", moveMatrix::rotate);
        }};
        for (String operation : operations) {
            operationMap.get(operation).run();
        }
        return moveMatrix.toArray();
    }

    public static void main(String[] args) {
        L4_행렬과연산_DataStructure test = new L4_행렬과연산_DataStructure();
        int[][] rc = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        String[] operations = {"Rotate", "ShiftRow"};
        System.out.println(Arrays.deepToString(test.solution(rc, operations)));
    }
}
