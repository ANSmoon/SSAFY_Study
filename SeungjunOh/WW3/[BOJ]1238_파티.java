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
class Node implements Comparable<Node>{
	int w;
	int cost;
	public Node( int w, int cost) {
	
		this.w = w;
		this.cost = cost;
	}
	@Override
	public int compareTo(Node o) {
		return this.cost-o.cost;
	}
	
	
}
class Main {
	static int N,M,X;
	static List<int[]>[] list;
	static List<int[]>[] rlist;
	static boolean[] visited;
	static boolean[] rvisited;
	static int[] dist;
	static int[] rdist;
	static int INF = 987654321;
	static int ans;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		rlist = new ArrayList[N+1];
		dist = new int[N+1];
		rdist = new int[N+1];
		visited = new boolean[N+1];
		rvisited = new boolean[N+1];
		for(int i=1;i<=N;i++) {
			rlist[i] = new ArrayList<>();
			list[i]= new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new int[] {b,c});
			rlist[b].add(new int[] {a,c});
		}
		
		Dijkstra(X);
		RDijkstra(X);
		
		ans = 0;
		for(int i=1;i<=N;i++) {
			if(ans<dist[i]+rdist[i]) ans = dist[i]+rdist[i];
		}
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
	private static void RDijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(rdist, INF);
		rdist[start]=0;
		
		pq.offer(new Node(start,0));
		rvisited[start]=true;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			rvisited[node.w]=true;
			for(int i=0;i<rlist[node.w].size();i++) {
				if(!rvisited[rlist[node.w].get(i)[0]]) {
					if(rdist[rlist[node.w].get(i)[0]]>rdist[node.w]+rlist[node.w].get(i)[1]) {
						rdist[rlist[node.w].get(i)[0]]=rdist[node.w]+rlist[node.w].get(i)[1];
					}
					pq.offer(new Node(rlist[node.w].get(i)[0],rdist[rlist[node.w].get(i)[0]]));
				}
			}
		}
	}
	private static void Dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(dist, INF);
		dist[start]=0;
		
		pq.offer(new Node(start,0));
		visited[start]=true;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			visited[node.w]=true;
			for(int i=0;i<list[node.w].size();i++) {
				if(!visited[list[node.w].get(i)[0]]) {
					if(dist[list[node.w].get(i)[0]]>dist[node.w]+list[node.w].get(i)[1]) {
						dist[list[node.w].get(i)[0]]=dist[node.w]+list[node.w].get(i)[1];
					}
					pq.offer(new Node(list[node.w].get(i)[0],dist[list[node.w].get(i)[0]]));
				}
			}
		}
	}
}