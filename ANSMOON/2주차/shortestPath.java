/*
 * Author : 문범수
 * Date : 2024-07-04
 * subject : 최단경로 구하기
 * page : https://www.acmicpc.net/problem/1753
 * main : Dijkstra
 * issue : Memory 초과, 완탐방식 불가
 * solution : Adjacency List, Priority Queue
 * name : Main.java
 * duration : 6h
 * no : 1753
 */

package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class shortestPath {
    static int V, E, K; // V : vertex / E : edge / K : 시작 vertex
    static int[] dist; // 각 정점까지 최소 거리
    static List<List<int[]>> graph; // list 내에 list 형식으로 담는다.
    static StringTokenizer token;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        token = new StringTokenizer(reader.readLine());
        V = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(reader.readLine());


        graph = new ArrayList<>();

        // 내부 list 초기화 부분
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;

        // Adjacency List Create
        // 각 정점별로 연결된 정점과 가중치를 저장하는 list
        for (int i = 0; i < E; i++) {
            token = new StringTokenizer(reader.readLine());
            int dep = Integer.parseInt(token.nextToken());
            int des = Integer.parseInt(token.nextToken());
            int weight = Integer.parseInt(token.nextToken());

            // 시작점을 u로 갖는 모든 연결 점의 정보가 list 형식으로 저장되고 있임
            graph.get(dep).add(new int[]{des, weight});
        }

        // 입력 test
        /*for(int i = 0; i <= V; i++){
            for(int j = 0; j <= V; j++){
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }*/

        dijkstra(K);

        for (int i = 1; i <= V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
    }

    static void dijkstra(int start) {
        // 우선순위 Queue 사용
        // int[] 배열에서 두번째 요소를 기준으로 정렬함. 결과적으로 거리가 가장 짧은 경로만을 가져올 수 있음
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[1]));
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] tmp = pq.poll();
            int dep = tmp[0]; // start
            int curDist = tmp[1]; // distance

            // 해당 정점까지의 거리가 기존의 거리보다 크면 넘어간다.
            if (curDist > dist[dep]) continue;

            for (int[] neighbor : graph.get(dep)) {
                int des = neighbor[0]; // 도착점
                int weight = neighbor[1]; // 가중치
                int newDist = curDist + weight;

                if (newDist < dist[des]) {
                    dist[des] = newDist;
                    pq.add(new int[]{des, newDist});
                }
            }
        }
    }
}