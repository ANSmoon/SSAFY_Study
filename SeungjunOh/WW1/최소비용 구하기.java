import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int w;
	long cost;

	public Edge(int w, long dist) {
		this.w = w;
		this.cost = dist;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return (int) (this.cost - o.cost);
	}

}

public class Main {
	static int N, M;
	static int v1, v2;
	static boolean[] visited;
	static List<Edge>[] list;
	static long dist[];
	static int INF = 1_200_000_000;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		dist = new long[N + 1];
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new Edge(b, c));
		}
		st = new StringTokenizer(br.readLine());
		int dep = Integer.parseInt(st.nextToken());
		int arr = Integer.parseInt(st.nextToken());

		Dijkstra(dep);

		bw.write(dist[arr] + "");
		bw.flush();
		bw.close();

	}

	private static void Dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		Arrays.fill(dist, INF);
		dist[start] = 0;
		pq.add(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge node = pq.poll();
			if(visited[node.w]) continue; 
			visited[node.w]= true;
			for (Edge e : list[node.w]) {
				if (dist[e.w] < dist[node.w] + e.cost)
					continue;
//					System.out.println(node.w +" "+e.w+" "+e.cost);
				dist[e.w] = dist[node.w] + e.cost;
//					System.out.println(Arrays.toString(dist));
				pq.offer(new Edge(e.w , dist[e.w]));

			}
		}

	}
}
