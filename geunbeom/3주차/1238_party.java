import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int idx, w;

	Node() {
	}

	Node(int idx, int w) {
		this.idx = idx;
		this.w = w;
	}

	@Override
	public int compareTo(Node o) {
		return this.w - o.w;
	}
}

public class Main {

	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// N : 노드의 갯수, M : 간선의 갯수, X : 목표노드
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		int INF = Integer.MAX_VALUE;
		int max = 0;

		// 일반 그래프와 역방향 그래프
		ArrayList<Node>[] graph = new ArrayList[N + 1];
		ArrayList<Node>[] reverseGraph = new ArrayList[N + 1];

		// 일반 dp 와 역방향 dp
		int[] dist = new int[N + 1];
		int[] reverseDist = new int[N + 1];

		// 방문배열
		visited = new boolean[N + 1];


		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<Node>();
			reverseGraph[i] = new ArrayList<Node>();
			dist[i] = INF;
			reverseDist[i] = INF;
		}

		dist[X] = 0;
		reverseDist[X] = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			// a 는 startNode, b 는 EndNode, w 는 가중치
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			// 일반에는 입력대로 넣고, 역방향에는 반대로 넣는다.
			graph[a].add(new Node(b, w));
			reverseGraph[b].add(new Node(a, w));
		}

		Node startNode = new Node(X, 0);

		// 일반 노드를 이용한 다익스트라.
		pq.offer(startNode);
		dijkstra(dist, graph);

		// 방문배열을 false 로 초기화
		Arrays.fill(visited, false);

		// 역방향 다익스트라.
		pq.offer(startNode);
		dijkstra(reverseDist, reverseGraph);

		// 최단 경로의 합 중 최대치를 max 에 담는다.
		for (int i = 1; i <= N; i++) {
			
			if (dist[i] == INF)
				dist[i] = 0;
			
			if (reverseDist[i] == INF)
				reverseDist[i] = 0;

			max = Math.max(max, dist[i] + reverseDist[i]);
		}

		System.out.println(max);

	}

	// 다익스트라. dp, graph 를 parameter로 담는다.
	static void dijkstra(int[] dp, ArrayList<Node>[] graph1) {

		int idx = 0;
		int next = 0;
		int w = 0;
		int cost = 0;

		while (!pq.isEmpty()) {

			Node now = pq.poll();
			idx = now.idx;
			visited[idx] = true;

			for (Node nextNode : graph1[idx]) {

				if (visited[nextNode.idx])
					continue;

				next = nextNode.idx;
				w = nextNode.w;
				cost = dp[idx] + w;
				
				if (dp[next] > cost) {
					dp[next] = cost;
					pq.offer(new Node(next, dp[next]));
				}
			}

		}

	}

}
