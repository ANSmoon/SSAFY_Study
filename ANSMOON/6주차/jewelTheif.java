/*
 * Author : 문범수
 * Date : 2024-07-12
 * subject : Priority Queue
 * page : https://www.acmicpc.net/problem/1202
 * main : Greedy Algorithm
 * issue : 시간초과 (O(N*M))
 * name : 보석도둑
 * duration : 120m
 * no : 1202
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class jewelry {
    int m, v; // 보석의 무게와 가격

    public jewelry(int m, int v) {
        this.m = m;
        this.v = v;
    }
}

public class jewelTheif {
    static StringTokenizer token;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        token = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(token.nextToken()); // 총 보석 갯수
        int K = Integer.parseInt(token.nextToken()); // 한 가방에 넣을 수 있는 무게 제한

        List<jewelry> jewelries = new ArrayList<>(); // 각 보석들의 정보 저장
        for (int i = 0; i < N; ++i) {
            token = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(token.nextToken());
            int v = Integer.parseInt(token.nextToken());

            jewelries.add(new jewelry(m, v));
        }
        
        // 핵심
        jewelries.sort((o1, o2) -> {
            if (o1.m == o2.m) { // m 값이 같을 경우 v 값에 따라 내림차순으로 정렬
                return o2.v - o1.v;
            }

            return o1.m - o2.m; // m 값에 따라 오름차순으로 정렬
        });

        List<Integer> bags = new ArrayList<>(); // 가방 용량 저장
        for (int i = 0; i < K; ++i) {
            bags.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(bags);

        int jewelry_idx = 0;
        int jewelry_size = jewelries.size();

        // 역정렬 pq 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long answer = 0;

        for (int bag : bags) {
            // 보석갯수 만큼 & 용량이 해당 보석보다 클동안
            while (jewelry_idx < jewelry_size && bag >= jewelries.get(jewelry_idx).m){
                pq.add(jewelries.get(jewelry_idx++).v);
            }
            if (!pq.isEmpty()) answer += pq.poll();
        }
        System.out.println(answer);
    }
}