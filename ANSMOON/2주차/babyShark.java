/*
 * Author : 문범수
 * Date : 2024-07-02
 * subject : BFS
 * page : https://www.acmicpc.net/problem/16236
 * main : Greedy Algorithm
 * issue : Intellij Issue
 * name : babyShark.java
 * duration : 4h
 * no : 16236
 */

package test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class babyShark {
    static int[][] section; // 현재 위치 입력 란
    static int sectionSize, sharkSize, sharkRow, sharkCol, eatingCount, totalTime; // sectionSize : 공간 크기 / sharkSize : 아기 상어 크기
    // sharkRow : 상어 행 위치 / sharkCol : 상어 열 위치 / eatingCount : 현재까지 먹은 물고기 / totalTime = 현재까지 먹은 시간
    static int[] dr = {-1, 0, 1, 0}; // 행 방향 탐색
    static int[] dc = {0, 1, 0, -1}; // 열 방향 탐색
    static StringTokenizer token;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        sectionSize = Integer.parseInt(reader.readLine());

        section = new int[sectionSize][sectionSize];
        sharkSize = 2; // 초기 상어 크기 2
        totalTime = 0;
        eatingCount = 0;

        for (int i = 0; i < sectionSize; i++) {
            token = new StringTokenizer(reader.readLine());
            for (int j = 0; j < sectionSize; j++) {
                section[i][j] = Integer.parseInt(token.nextToken());
                if (section[i][j] == 9) {
                    sharkRow = i;
                    sharkCol = j;
                    section[i][j] = 0;
                }
            }
        }

        while (true) {
            int minDist = bfs();
            if (minDist == -1) {
                break;
            }
            totalTime += minDist;
            eatingCount++;

            if (eatingCount == sharkSize) {
                sharkSize++;
                eatingCount = 0;
            }
        }

        System.out.println(totalTime);

    }

    // 주변 탐색 => 먹을 수 있는지 판단 => 물고기 변화 체크 => 반복
    // 더이상 먹을 수 있는 물고기 없을 시 지금까지의 시간 출력
    // bfs

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[sectionSize][sectionSize];

        queue.offer(new int[]{sharkRow, sharkCol, 0});
        visited[sharkRow][sharkCol] = true;

        int minDist = Integer.MAX_VALUE;
        int minRow = sectionSize, minCol = sectionSize;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], dist = current[2];

            if (section[x][y] > 0 && section[x][y] < sharkSize) {
                if (dist < minDist) {
                    minRow = x;
                    minCol = y;
                    minDist = dist;
                } else if (dist == minDist) {
                    if (x < minRow) { // x 값이 작은 녀석들 위주
                        minRow = x;
                        minCol = y;
                    } else if (x == minRow && y < minCol) { // x 값이 같다면 다음으로 y값이 작은 녀석들 위주
                        minCol = y;
                    }
                }
            }

            for (int d = 0; d < 4; d++) {
                int row = x + dr[d];
                int col = y + dc[d];

                if (row >= 0 && row < sectionSize && col >= 0 && col < sectionSize && !visited[row][col] && section[row][col] <= sharkSize) {
                    queue.offer(new int[]{row, col, dist + 1});
                    visited[row][col] = true;
                }
            }

        }
        if (minRow != sectionSize && minCol != sectionSize) {
            section[minRow][minCol] = 0; // 물고기를 먹었으므로 빈칸으로 처리
            sharkRow = minRow;
            sharkCol = minCol;
            return minDist;
        }
        return -1;
    }
}