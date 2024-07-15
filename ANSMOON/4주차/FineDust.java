/*
 * Author : 문범수
 * Date : 2024-07-12
 * subject : Implementation
 * page : https://www.acmicpc.net/problem/17144
 * main : 시뮬레이션
 * issue :
 * name : 미세먼지 안녕!
 * duration : m
 * no : 17144
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class FineDust {
    static int R, C, T, ans; // T는 경과된 시간
    static int[][] matrix;
    static Queue<int[]> dust; //미세먼지 담을 Queue
    static Queue<int[]> airCleaner; // 공기청정기 위치
    static StringTokenizer token;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        token = new StringTokenizer(reader.readLine());
        R = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());
        T = Integer.parseInt(token.nextToken());
        matrix = new int[R][C];
        dust = new LinkedList<>();
        airCleaner = new LinkedList<>();
        ans = 0;

        // 행렬 입력
        for (int i = 0; i < R; i++) {
            token = new StringTokenizer(reader.readLine());
            for (int j = 0; j < C; j++) {
                matrix[i][j] = Integer.parseInt(token.nextToken());
                if(matrix[i][j] == -1){
                    airCleaner.add(new int[]{i, j});
                }
            }
        }

        // 공기청정기 위치 점검용
//        System.out.println(Arrays.toString(airCleaner.poll()) + " " + Arrays.toString(airCleaner.poll()));


    }
    // 각 먼지가 먼저 확산 => 공기청정기 순환
    // method 먼지 확산 : BFS
    static void BFS(){

    }


    // method 공기청정기 작동 : airCleaner
    static void setAirCleaner(Queue<int[]> airCleaner){

    }

}