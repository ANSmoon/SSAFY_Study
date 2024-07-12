package pipe_17070;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, result;
	static int[][] map;
	static Queue<Pipe> queue;
	
	// Pipe 의 위치 알려주는 class
	// 처음에는 int[] 배열 2개로 했으나, 배열의 복사 이슈로 int 4개로 바꿈...
	static class Pipe{
		// 첫 번째 pipe 위치
		int row1;
		int col1;
		// 두 번째 pipe 위치
		int row2;
		int col2;
		
		Pipe(){}

		public Pipe(int row1, int col1, int row2, int col2) {
			super();
			this.row1 = row1;
			this.col1 = col1;
			this.row2 = row2;
			this.col2 = col2;
		}

		@Override
		public String toString() {
			return "Pipe [row1=" + row1 + ", col1=" + col1 + ", row2=" + row2 + ", col2=" + col2 + "]";
		};
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// map 크기
		N = Integer.parseInt(br.readLine());
		
		// map
		map = new int[N][N];
		
		// map 정보 받음
		for(int i= 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// pipe 이동 탐색위한 queue
		queue = new LinkedList<>();
		
		result = 0;
		
		// 초기 위치 queue 에 offer
		queue.offer(new Pipe(0, 0, 0, 1));
		bfs();
		
		System.out.println(result);
	}
	
	public static void bfs() {
		Pipe now;
		
		while(!queue.isEmpty()) {
			now = queue.poll();
			// pipe의 2번째 위치가 끝 점에 와있으면 경우의 수 1 증가
			if(now.row2 == N-1 && now.col2 == N-1) {
				result++;
				continue;
			}
			
			// 행 차이가 날 경우
			// 파이프가 수직으로 서 있거나 대각선인 상태
			if(now.row2 - now.row1 == 1) {
				if(0 <= now.row2 + 1 && now.row2 + 1 < N 
						&& map[now.row2 + 1][now.col2] != 1) {
					queue.offer(new Pipe(now.row2, now.col2, now.row2 + 1, now.col2));					
				}
			}
			
			// 열 차이가 날 경우
			// 파이프가 수평으로 되어있거나 대각선인 상태
			if(now.col2 - now.col1 == 1) {
				if(0 <= now.col2 + 1 && now.col2 + 1 < N 
						&& map[now.row2][now.col2 + 1] != 1) {
					queue.offer(new Pipe(now.row2, now.col2, now.row2, now.col2 + 1));		
				}
			}
			
			// 모든 경우에 대각선 방향으로 이동 가능
			// 단, 대각선 방향으로 이동 시, 3군데가 벽이 아니어야 함
			if(0 <= now.row2 + 1 && now.row2 + 1 < N 
					&& 0 <= now.col2 + 1 && now.col2 + 1 < N
					&& map[now.row2 + 1][now.col2 + 1] != 1
					&& map[now.row2 + 1][now.col2] != 1
					&& map[now.row2][now.col2 + 1] != 1) {
				queue.offer(new Pipe(now.row2, now.col2, now.row2 + 1, now.col2 + 1));
			}
		}
	}
}

