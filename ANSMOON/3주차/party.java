/*
 * Author : 문범수
 * Date : 2024-07-12
 * subject :
 * page : https://www.acmicpc.net/problem/1238
 * main :
 * issue :
 * name :
 * duration : m
 * no : 1238
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class party {
    static int N, M, X, ans; // N : 학생수 / M : 도로개수 / X : 도착 위치
    static StringTokenizer token;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        X = Integer.parseInt(token.nextToken());
        ans = 0;
        visited = new boolean[N + 1];

        reverseDijkstra();

        System.out.println(ans);
    }

    static void reverseDijkstra() {
        
    }
}
