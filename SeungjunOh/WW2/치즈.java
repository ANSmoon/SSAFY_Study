import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int r, c;

	Node(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

class Main {
	static int N, M;
	static int[][] map;
	static int ans, left;
	static List<Node> cheeze;
	static final int[] dr = { 0, -1, 0, 1 };
	static final int[] dc = { -1, 0, 1, 0 };

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// (0,0)부터 BFS 시작
		// 바깥에서 1을 만나면 그 1에서 상하좌우 탐색, 0이 2개 이상 있으면 3으로 변경
		// BFS가 완료되면 3인 지점을 0으로 바꾸고 다시 큐에 넣는다
		// (수정) Queue 두개를 만들면 넣고 빼는 과정에 의해서 메모리 초과 발생
		// 따라서 1이 있는 좌표를 리스트에 넣고 녹을 때마다 하나씩 빼면서 확인
		// 깊이마다 카운트가 증가하고, 새로운 0들이 다시 1들을 찾으면 위의 과정을 반복한다

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 초기화
		// visited대신 0을 만나면 4로 만들자
		map = new int[N][M];
		cheeze = new ArrayList<>();
		ans = 0;
		left = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					cheeze.add(new Node(i, j));
					left++;
				}
			}
		}

		// (0,0)부터 BFS 실행
		takeTime();


		bw.write(ans + "");
		bw.flush();
		bw.close();
	}

	private static void takeTime() {
		Queue<int[]> q = new LinkedList<>();
		int nr, nc;

		// 모서리 4개 다 해도 될 거 같은데.. 일단 풀어보자
		q.add(new int[] { 0, 0 });
		q.add(new int[] { N - 1, 0 });
		q.add(new int[] { 0, M - 1 });
		q.add(new int[] { N - 1, M - 1 });
		map[0][0] = 2; // visited 처리
		map[N - 1][0] = 2;
		map[0][M - 1] = 2;
		map[N - 1][M - 1] = 2;
		wp :while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] node = q.poll();
				for (int j = 0; j < 4; j++) {
					nr = node[0] + dr[j];
					nc = node[1] + dc[j];
					if (nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] != 2) {
						if (map[nr][nc] == 0) {
							map[nr][nc] = 2;
							q.add(new int[] { nr, nc });
						} else if (map[nr][nc] == 1) {
							// 노출된 치즈는 3으로 처리해놓고 사이클이 마무리되면 처리
							map[nr][nc] = 3;
						}
					}
				}
			}
			// 사이클이 다 돌고 나서 cheeze에서 3이 된 애들을 찾는다
			// 3인 애들 중에서 상하좌우 중 외부(2)노출되는 경우가 2보다 많으면 처리
			// 외부 노출이 안됐으면 다시 1로 돌려놓기
			if (q.isEmpty()) {
				ans++;
				//System.out.println(ans);
				for (int i = 0; i < cheeze.size(); i++) {
					if (map[cheeze.get(i).r][cheeze.get(i).c] == 3) {
						int o2 = 0;
						for (int j = 0; j < 4; j++) {
							nr = cheeze.get(i).r + dr[j];
							nc = cheeze.get(i).c + dc[j];
							if (map[nr][nc] == 2)
								o2++;
						}
						if (o2 >= 2) {
							left--;
							if(left==0) break wp;
							map[cheeze.get(i).r][cheeze.get(i).c] = 4;
							q.add(new int[] { cheeze.get(i).r, cheeze.get(i).c });
							// 외부공기로 노출된 치즈는 녹아 없어지므로 없앤다
							//System.out.println(cheeze.get(i).r+" "+cheeze.get(i).c);
						} else {
							map[cheeze.get(i).r][cheeze.get(i).c] = 1;
						}
					}
				}
				// 4로 설정된 치즈는 없애주고..
				for(int i = 0; i < cheeze.size(); i++) {
					if (map[cheeze.get(i).r][cheeze.get(i).c] == 4) {
						map[cheeze.get(i).r][cheeze.get(i).c] = 2;
						cheeze.remove(i);
						i--;
					}
				}

			}
		}
	}
}