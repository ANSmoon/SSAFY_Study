package mincost_1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Bus implements Comparable<Bus>{
		int end;
		int cost;
		
		Bus(int end, int cost){
			this.end = end;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Bus [end=" + end + ", cost=" + cost + "]";
		}

		@Override
		public int compareTo(Bus o) {
			return this.cost - o.cost;
		}
	}
	
	static int N, M, A, B;
	static List<Queue<Bus>> list;
	static long[] costArr;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		costArr = new long[N+1];
		visited = new boolean[N+1];
		
		Arrays.fill(costArr, Integer.MAX_VALUE);
		
		for(int i = 0; i <= N; i++) {
			list.add(new PriorityQueue<>());
		}
		
		int start, end, cost;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			list.get(start).offer(new Bus(end, cost));
		}
		
//		for(int i = 0; i <= N; i++) {
//			System.out.println(list.get(i).toString());
//		}
		
//		while(!list.get(1).isEmpty()) {
//			System.out.println(list.get(1).poll());
//		}
		
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		costArr[A] = 0;
		visited[A] = true;
		findRoute(A);
		
		System.out.println(costArr[B]);
	}
	
	public static void findRoute(int now) {
		Bus check;
		while(!list.get(now).isEmpty()) {
			check = list.get(now).poll();

			if(costArr[check.end] > costArr[now] + check.cost) {
				costArr[check.end] = costArr[now] + check.cost;
			}
		}
		
		int next = 0;
		long min = Integer.MAX_VALUE;
		for(int i = 1; i <= N; i++) {
			if(!visited[i] && min > costArr[i]) {
				min = costArr[i];
				next = i;
			}
		}
		
		if(next == B) return;
		visited[next] = true;
		findRoute(next);
	}
}
