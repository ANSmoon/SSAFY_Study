/*
 * Author : 문범수
 * Date : 2024-09-03
 * subject : recursive
 * page : https://www.acmicpc.net/problem/1074
 * main : 몇 사분면에 위치하는지 먼저 파악 후 더해주는 값을 처리
 * issue : 메모리 초과 => 배열 생성 필요 없음 & 사분면 바뀔 때 마다 행, 열값을 바꿔줘야 함
 * name : Z
 * duration : m
 * no : 1074
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Z {
    static int N, R, C, answer, size; // N : size, R : 행, C : 열
    static StringTokenizer token;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        R = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());

        size = (int) Math.pow(2, N);

        solve();
        System.out.println(answer);
    }

    // 어느 사분면에 위치하는지 파악 후 재귀를 돌며 값을 더해준다.
    static void solve() {
        // base line
        if(size == 1){
            return;
        }

        // recursive line
        // 1사분면 위치 [R, C 변경 필요 없음]
        if(R < size/2 && C < size/2){
            size /= 2;
            solve();
        }
        // 2사분면 위치
        else if(R < size/2 && C >= size/2){
            size /= 2;
            answer += size * size;
            C -= size;
            solve();
        }
        // 3사분면 위치
        else if(R >= size/2 && C < size/2){
            size /= 2;
            answer += size * size * 2;
            R -= size;
            solve();
        }
        // 4사분면 위치
        else{
            size /= 2;
            answer += size * size * 3;
            R -= size;
            C -= size;
            solve();
        }
    }
}