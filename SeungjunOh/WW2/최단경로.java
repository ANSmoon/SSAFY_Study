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

// 우선 순위 큐를 사용하기 위한 Node 클래스 생성
class Node implements Comparable<Node> {

	// 끝점과 이동 비용을 Node에 저장(시작점은 한 리스트에 모아둘 예정)
	int end, weight;

	// 생성자 선언
	public Node(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}

	// weight에 따른 우선순위 큐를 진행하기 위해 compareTo의 리턴값을 weight를 비교해준다
	@Override
	public int compareTo(Node o) {
		return weight - o.weight;
	}

}

public class Main {
	// 점의 개수 N 선언
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	// 거리 배열의 초기값은 매우 큰값으로 설정
	static final int INF = 100_000_000;
	static int v, e, k;
	// 간선의 정보를 담을 list 배열
	static List<Node>[] list;
	// 출력할 가장 짧은 거리의 배열
	static int[] dist;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(br.readLine());

		// 숫자를 받는대로 넣기 위해 v+1로 배열을 생성
		list = new ArrayList[v + 1];
		dist = new int[v + 1];
		// dist는 초기값을 INF로 설정해준다
		Arrays.fill(dist, INF);

		for (int i = 1; i <= v; i++) {
			// 정점 마다 연결 정보를 담을 리스트의 리스트를 생성
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			// start 정점에서 end 정점으로 가는 비용 weight를 저장
			list[start].add(new Node(end, weight));
		}

		// 다익스트라 진행
		Dijkstra(k);

		// 출력 하고 끝
		for (int i = 1; i <= v; i++) {
			// k에서 갈 수 있는 방법이 없으면 INF로 출력
			if (dist[i] == INF)
				bw.write("INF" + "\n");
			else
				bw.write(dist[i] + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static void Dijkstra(int idx) {
		// 우선순위 큐 생성 -> Node에서 우선 순위가 weight로 되도록 설정했음
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		// 방문 체크를 위한 visited 생성
		boolean[] visited = new boolean[v + 1];
		
		// start 지점을 체크
		pq.offer(new Node(idx, 0));
		dist[idx] = 0;

		while (!pq.isEmpty()) {
			Node curNode = pq.poll();
			// 출발하고자 하는 지점을 cur로 설정
			int cur = curNode.end;
			
			// visited[cur]이 아직 방문 안했다면 체크 해주고
			if (visited[cur])
				continue;
			visited[cur] = true;
			
			// list에 cur에서 이동할 수 있는 지점과 비용을 node로 불러온다
			for (Node node : list[cur]) {
				// 시작지점에서 node에 연결된 지점(node.end)으로 가는 비용(dist)이
				// 시작지점에서 현재지점(cur)로 오는 비용+현재지점에서 node.end로 가는 비용보다 크면
				// 비용을 갱신해준다
				// 해당 노드를 pq에 offer해줘서 다음에는 시작지점이 node.end로 되어 다음 지점을 찾는다
				if (dist[node.end] > dist[cur] + node.weight) {
					dist[node.end] = dist[cur] + node.weight;
					pq.offer(new Node(node.end, dist[node.end]));
				}
			}
		}

	}

}