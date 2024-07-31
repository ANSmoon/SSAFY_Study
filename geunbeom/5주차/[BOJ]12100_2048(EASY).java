import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, MAX;
	static char[] direction = {'U', 'D', 'R', 'L'};
	static char[] map = new char[5];
	static int[][] arrR;

	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arrR = new int[N][N];
		
		for (int r=0; r<N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c=0; c<N; c++) {
				arrR[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		System.out.println(MAX);
		
	}
	
	// 5번의 경우의수
	static void dfs (int t) {
		if (t == 5) {
			int[][] arr = new int[N][N];
			for (int r=0; r<N; r++) {
				for (int c=0; c<N; c++) {
					arr[r][c] = arrR[r][c];
				}
			}
			move(arr);
			return;
		}
		for (int i=0; i<4; i++) {
			map[t] = direction[i];
			dfs(t+1);
		}
	}
	
	// 이동 및 최댓값 메서드
	static void move(int[][] arr) {
		for (int i=0; i<5; i++) {
			boolean[][] visited = new boolean[N][N];
			if (map[i] == 'U') {
				for (int r=1; r<N; r++) {
					for (int c=0; c<N; c++) {
						
						if (arr[r][c] != 0) {
							int X = r-1;
							while (X >= 0  && arr[X][c] == 0) {
								arr[X][c] = arr[X+1][c];
								arr[X+1][c] = 0;
								X--;
							}
							if (X >= 0 && arr[X][c] == arr[X+1][c] && !visited[X][c]) {
								visited[X][c] = true;
								arr[X][c] *= 2;
								arr[X+1][c] = 0;
							}
							
						}
					}
				}
			}else if (map[i] == 'D') {
				for (int r=N-2; r>=0; r--) {
					for (int c=0; c<N; c++) {
						
						if (arr[r][c] != 0) {
							int X = r+1;
							while (X < N  && arr[X][c] == 0) {
								arr[X][c] = arr[X-1][c];
								arr[X-1][c] = 0;
								X++;
							}
							if (X < N && arr[X][c] == arr[X-1][c] && !visited[X][c]) {
								visited[X][c] = true;
								arr[X][c] *= 2;
								arr[X-1][c] = 0;
							}
						}
					}
				}
			}else if (map[i] == 'R') {
				for (int r=0; r<N; r++) {
					for (int c=N-2; c>=0; c--) {

						if (arr[r][c] != 0) {
							int X = c+1;
							while (X < N  && arr[r][X] == 0) {
								arr[r][X] = arr[r][X-1];
								arr[r][X-1] = 0;
								X++;
							}
							if (X < N && arr[r][X] == arr[r][X-1] && !visited[r][X]) {
								visited[r][X] = true;
								arr[r][X] *= 2;
								arr[r][X-1] = 0;
							}
						}
					}
				}
			}else if (map[i] == 'L') {
				for (int r=0; r<N; r++) {
					for (int c=1; c<N; c++) {
						
						if (arr[r][c] != 0) {
							int X = c-1;
							while (X >= 0  && arr[r][X] == 0) {
								arr[r][X] = arr[r][X+1];
								arr[r][X+1] = 0;
								X--;
							}
							if (X >=0 && arr[r][X] == arr[r][X+1] && !visited[r][X]) {
								visited[r][X] = true;
								arr[r][X] *= 2;
								arr[r][X+1] = 0;
							}
						}
					}
				}
			}	
		}

		int max = 0;
		for (int r=0; r<N; r++) {
			for (int c=0; c<N; c++) {
				max = Math.max(max, arr[r][c]);
			}
		}
		MAX = Math.max(max, MAX);
	}
}
