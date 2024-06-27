import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	
	public static class Node implements Comparable<Node>{
		int v, w; // 정점과 가중치
		
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
		// 가중치 기준으로 오름차순 정렬
		@Override
		public int compareTo(Node o) {
			// 현재 node - this, 새로 들어온 node - o
			// 둘의 가중치를 비교해서 더 작은 가중치를 가진 노드가 먼저 poll
			return Integer.compare(this.w, o.w);
		}
	}
	
	static final int INF = Integer.MAX_VALUE;
	static int V, E;
	static List<Node>[] adjList;
	static int[] dist;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		V = sc.nextInt();
		E = sc.nextInt();
		
		// 정점부터 뻗어나가는 길이 몇개일지 모르니 ArrayList 형태의 배열 생성
		adjList = new ArrayList[V + 1];
		for(int i = 1; i< V + 1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// 거리 배열도 최댓값으로 초기화
		dist = new int[V + 1];
		for(int i = 1; i<V + 1; i++) {
			dist[i] = INF;
		}
		
		// 어느 한 점의 도착점과 가중치 저장 (v,w)
		// adjList의 인덱스가 즉 출발점이 된다
		for(int i = 0; i < E; i++) {
			adjList[sc.nextInt()].add(new Node(sc.nextInt(), sc.nextInt())); 
		}
		
		int start = sc.nextInt();
		int finish = sc.nextInt();
		
		dijkstra(start);

		int ans = dist[finish] - dist[start];
		System.out.println(ans);
	}

	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V + 1];
		
		// 시작 노드의 거리는 0
		dist[start] = 0;
		
		// 처음 출발지인 1은 가중치를 0으로 고정
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			// 이미 방문했다면 그곳은 최소 거리로 확정되어 있음!!
			if(visited[curr.v]) continue;
			
			// 방문하지 않았다면
			visited[curr.v] = true;
			
			// 현재 점으로부터 시작하는 버스 이동에 대해서 for문
			for(Node node : adjList[curr.v]) {
				// 만약 도착지가 최소비용으로 확정되지 않았고
				// 도착지까지의 거리보다 출발지까지 오는데 걸린 거리 + 도착지로 이동하는 비용의 합이 더 작으면
				// 도착지까지의 거리를 초기화 해주고 우선순위 큐에 넣는다!
				if(!visited[node.v] && dist[node.v] > dist[curr.v] + node.w) {
					dist[node.v] = dist[curr.v] + node.w;
					pq.offer(new Node(node.v, dist[node.v]));
				}
			}
		}
	}
}