package minroute_1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge> {
		int to;
		int cost;
		
		Edge(int to, int cost){
			this.to = to;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Edge [to=" + to + ", cost=" + cost + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
	
	static int V, E, start;
	static int[] costArr;
	static List<PriorityQueue<Edge>> edges;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());
		
		costArr = new int[V+1];
		Arrays.fill(costArr, Integer.MAX_VALUE);
		
		visited = new boolean[V+1];
		
		edges = new ArrayList<>();
		
		for(int i = 0; i <= V; i++) {
			edges.add(new PriorityQueue<>());
		}
		
		int u, v, w;
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			edges.get(u).offer(new Edge(v, w));
		}
		
		
		costArr[start] = 0;
		minRoute(start);
		
		for(int i = 1; i <= V; i++) {
			if(costArr[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			}else {
				System.out.println(costArr[i]);
			}
		}
	}
	
	public static void minRoute(int node) {
		Edge check;
		while(!edges.get(node).isEmpty()) {
			check = edges.get(node).poll();
			
			if(costArr[check.to] > costArr[node] + check.cost) {
				costArr[check.to] = costArr[node] + check.cost; 
			}
		}
		
		int next = 0;
		int min = Integer.MAX_VALUE;
		for(int i = 1; i <= V; i++) {
			if(!visited[i] && min > costArr[i]) {
				min = costArr[i];
				next = i;
			}
		}
		
		if(next == 0) return;
		visited[next] = true;
		minRoute(next);
	}
}
