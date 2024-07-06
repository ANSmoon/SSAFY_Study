package cheese_2638;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, sum;
	static int[][] cheese;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		// N : 행의 수 / M : 열의 수 
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// cheese : 치즈 정보, visited : 방문 배열
		cheese = new int[N][M];
		visited = new boolean[N][M];
		// 녹이고 난 후의 전체 cheese 개수 체크하는 변수
		sum = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
				// 초기 치즈 개수 계산 (처음에 치즈가 아예 없을수도?)
				sum += cheese[i][j];
			}
		}
		
		int year = 0;
		
		// 다 녹을 때까지 반복
		while(sum != 0) {
			sum = 0;
			// visited 배열 연도 바뀔때마다 초기화
			for(int i = 1; i < N; i++) {
				for(int j = 1; j < M; j++) {
					visited[i][j] = false;
				}
			}
			
			// 내부 공간 체크를 위한 반복
			for(int i = 1; i < N; i++) {
				for(int j = 1; j < M; j++) {
					if(cheese[i][j] == 0) {
						innerCheck(i, j);
					}
				}
			}
			
			// 치즈 녹이는 메서드
			meltCheese();

			year++;	
		}
		
		System.out.println(year);
	}
	
	public static void innerCheck(int row, int col) {
		int nr, nc;
		
		// 상, 하, 좌, 우가 모두 치즈로 막힌 공간이면 true 처리
		// 각 배열의 끝까지 탐색
		next: for(int i = 0; i < 4; i++) {
			nr = row + dr[i];
			nc = col + dc[i];
			while(0 <= nr && nr < N && 0 <= nc && nc < M) {
				if(cheese[nr][nc] == 1) {
					// 중간에 1(치즈)을 만나면 다음 탐색 방향으로 전환
					continue next;
				}
				
				nr += dr[i];
				nc += dc[i];
			}
			// 한 방향이라도 1을 못 만나면, false 그대로 유지
			return;
		}
		// 4 방향에 다 치즈가 있으면 true 처리
		visited[row][col] = true;
	}
	
	public static void meltCheese() {
		int nr, nc, count;
		
		for(int i = 1; i < N; i++) {
			for(int j = 1; j < M; j++) {
				// 치즈를 만나면 사방 탐색해서 외부 공간 수 탐색
				if(cheese[i][j] == 1) {
					count = 0;
					for(int k = 0; k < 4; k++) {
						nr = i + dr[k];
						nc = j + dc[k];
						if(0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc] && cheese[nr][nc] == 0) {
							count++;
						}
					}
					// 외부 접촉면이 2 이상이면 녹임 && 방문처리
					if(count >= 2) {
						cheese[i][j] = 0;
					}
					
					visited[i][j] = true;
				}
				
				// 남아있는 치즈 수 체크
				sum += cheese[i][j];
			}
		}
	}
}
