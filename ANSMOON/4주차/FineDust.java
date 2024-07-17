/*
 * Author : 문범수
 * Date : 2024-07-12
 * subject : Implementation
 * page : https://www.acmicpc.net/problem/17144
 * main : 시뮬레이션
 * issue : NullPointException
 * name : 미세먼지 안녕!
 * duration : 100m
 * no : 17144
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class FineDust {
    static int R, C, T, ans, airRow1, airRow2; // T는 경과된 시간 / airRow : 공기청정기 위치
    static int[][] matrix;
    static Queue<int[]> dustQueue; //미세먼지 담을 Queue
//    static Queue<int[]> airCleaner; // 공기청정기 위치
    static StringTokenizer token;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        token = new StringTokenizer(reader.readLine());
        R = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());
        T = Integer.parseInt(token.nextToken());
        matrix = new int[R][C];
        dustQueue = new LinkedList<>();
//        airCleaner = new LinkedList<>();
        ans = 0;
        airRow1 = 0;
        airRow2 = 0;

        // 행렬 입력
        for (int i = 0; i < R; i++) {
            token = new StringTokenizer(reader.readLine());
            for (int j = 0; j < C; j++) {
                matrix[i][j] = Integer.parseInt(token.nextToken());
                if (matrix[i][j] == -1) {
//                    airCleaner.add(new int[]{i, j});
                    if (airRow1 == 0) {
                        airRow1 = i;
                    } else if (airRow2 == 0) {
                        airRow2 = i;
                    }
                } else if (matrix[i][j] > 0) {
                    dustQueue.add(new int[]{i, j, matrix[i][j]});
                }
            }
        }
//        airCleaner.add(new int[]{2, 0});

        // 공기청정기 위치 점검용
//        System.out.println(Arrays.toString(airCleaner.poll()) + " " + Arrays.toString(airCleaner.poll()));

        for (int i = 0; i < T; i++) {
            BFS();
            setAirCleaner();
            reset();
//            for(int j = 0; j < R; j++) {
//                for (int k = 0; k < C; k++) {
//                    System.out.print(matrix[j][k] + " ");
//                }
//                System.out.println();
//            }
        }

        System.out.println(total() + 2);
    }

    // 각 먼지가 먼저 확산 => 공기청정기 순환
    // method 먼지 확산 : BFS
    static void BFS() {
        while (!dustQueue.isEmpty()) {
            int[] arr = dustQueue.poll();
            int rowIndex = arr[0];
            int colIndex = arr[1];
            int dust = arr[2];

            // 4방 탐색
            for (int i = 0; i < 4; i++) {
                int row = rowIndex + dr[i];
                int col = colIndex + dc[i];
                if (check(row, col)) {
                    matrix[row][col] += dust / 5;
                    matrix[rowIndex][colIndex] -= dust / 5;
                }
            }
        }
    }

    // method 공기청정기 작동 : airCleaner
    static void setAirCleaner() {
        // 위쪽 공기청정기
        for (int i = airRow1 - 1; i > 0; i--) {
            matrix[i][0] = matrix[i - 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            matrix[0][i] = matrix[0][i + 1];
        }
        for (int i = 0; i < airRow1; i++) {
            matrix[i][C - 1] = matrix[i + 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            matrix[airRow1][i] = matrix[airRow1][i - 1];
        }
        matrix[airRow1][1] = 0;

        // 아래쪽 공기청정기
        for (int i = airRow2 + 1; i < R - 1; i++) {
            matrix[i][0] = matrix[i + 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            matrix[R - 1][i] = matrix[R - 1][i + 1];
        }
        for (int i = R - 1; i > airRow2; i--) {
            matrix[i][C - 1] = matrix[i - 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            matrix[airRow2][i] = matrix[airRow2][i - 1];
        }
        matrix[airRow2][1] = 0;
//        int[] upper = airCleaner.poll();
//        int row1 = upper[0];
//        int col1 = upper[1];
//
//        int[] lower = airCleaner.poll();
//        int row2 = lower[0];
//        int col2 = lower[1];
//
//        if (row1 == airRow1 && col1 < C - 1) { // 오른쪽 방향 유지
//            if (row1 == airRow1 && col1 == 0) { // 맨처음 시작
//                airCleaner.add(new int[]{row1, col1 + 1});
//            } else { // 맨처음을 제외하고
//                matrix[row1][col1 + 1] += matrix[row1][col1];
//                matrix[row1][col1] = 0;
//                airCleaner.add(new int[]{row1, col1 + 1});
//            }
//        } else if (col1 == C - 1 && row1 != 0) { // 위로 이동
//            matrix[row1 - 1][col1] += matrix[row1][col1];
//            matrix[row1][col1] = 0;
//            airCleaner.add(new int[]{row1 - 1, col1});
//        } else if (row1 == 0 && col1 != 0) { // 왼쪽으로 이동
//            matrix[row1][col1 - 1] += matrix[row1][col1];
//            matrix[row1][col1] = 0;
//            airCleaner.add(new int[]{row1, col1 - 1});
//        } else if (col1 == 0) { // 아래쪽으로 이동
//            if(row1 == R){ // 공기청정기에 도착한 경우
//                matrix[row1][col1] = 0;
//                airCleaner.add(new int[]{row1 + 1, col1});
//            }else{
//                matrix[row1 + 1][col1] += matrix[row1][col1];
//                matrix[row1][col1] = 0;
//                airCleaner.add(new int[]{row1 + 1, col1});
//            }
//        }
//
//        if (row2 == airRow2 && col2 < C - 1) { // 오른쪽 방향 유지
//            if (row2 == airRow2 && col2 == 0) {
//                airCleaner.add(new int[]{row2, col2 + 1});
//            }else{
//                matrix[row2][col2 + 1] += matrix[row2][col2];
//                matrix[row2][col2] = 0;
//                airCleaner.add(new int[]{row2, col2 + 1});
//            }
//        } else if (col2 == C - 1 && row2 < R - 1) { // 아래쪽으로 이동
//            matrix[row2 + 1][col2] += matrix[row2][col2];
//            matrix[row2][col2] = 0;
//            airCleaner.add(new int[]{row2 + 1, col2});
//        } else if (row2 == R - 1 && col2 > 0) { // 왼쪽으로 이동
//            matrix[row2][col2 - 1] += matrix[row2][col2];
//            matrix[row2][col2] = 0;
//            airCleaner.add(new int[]{row2, col2 - 1});
//        } else if (col2 == 0 && row2 > airRow2) { // 위로 이동
//            if(row2 == airRow2){
//                matrix[row2][col2] = 0;
//                airCleaner.add(new int[]{row2 - 1, col2});
//            }else{
//                matrix[row2 - 1][col2] += matrix[row2][col2];
//                matrix[row2][col2] = 0;
//                airCleaner.add(new int[]{row2 - 1, col2});
//            }
//        }
    }

    // method : 퍼질수 있는지 판별
    static boolean check(int row, int col) {
        return row >= 0 && row < R && col >= 0 && col < C && matrix[row][col] != -1;
    }

    static int total() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ans += matrix[i][j];
            }
        }
        return ans;
    }

    static void reset(){
        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ; j++){
                if(matrix[i][j] > 0){
                    dustQueue.add(new int[]{i, j, matrix[i][j]});
                }
            }
        }
    }
}