import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static class Node implements Comparable<Node>{
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
		// 가중치 기준으로 오름차순 정렬
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	static final int INF = 987654321;
	static int V, E;
	static List<Node>[] adjList;
	static int[] dist;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		V = sc.nextInt();
		E = sc.nextInt();
		
		adjList = new ArrayList[V + 1];
		for(int i = 1; i < V + 1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		dist = new int[V + 1];
		for(int i = 1; i < V + 1; i++) {
			dist[i] = INF;
		}
		
		// 출발점
		int start = sc.nextInt();
		
		for(int i = 0; i < E; i++) {
			adjList[sc.nextInt()].add(new Node(sc.nextInt(), sc.nextInt()));
		}
		
		dijkstra(start);
		
		for(int i = 1; i < V + 1; i++) {
			if(dist[i] == INF) System.out.println("INF");
			else System.out.println(dist[i]);
		}
	}

	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V + 1];
		
		dist[start] = 0;
		
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if(visited[curr.v]) continue;
			
			visited[curr.v] = true;
			
			for(Node node : adjList[curr.v]) {
				if(!visited[node.v] && dist[node.v] > dist[curr.v] + node.w) {
					dist[node.v] = dist[curr.v] + node.w;
					pq.offer(new Node(node.v, dist[node.v]));
				}
			}
		}
	}
}
