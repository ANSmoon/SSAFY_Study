import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int R, C, T;
	static int[][] map, copy;
	static List<Integer> cleanerR = new ArrayList<>();
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == -1) cleanerR.add(r);
			}
		} // for input
		
		for(int t=0; t<T; t++) {
			spread();
			cleaner();
		}
		
		int result = 0;
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(map[r][c] >= 1) result += map[r][c];
			}
		}
		
		System.out.println(result);
	} // main
	
	public static void spread() {
		copy = new int[R][C];
		copy[cleanerR.get(0)][0] = -1;
		copy[cleanerR.get(1)][0] = -1;
		
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				// 칸 내의 미세먼지가 5 이상이면 확산
				if(map[r][c] >= 5) {
					int tmp = 0; // 몇개의 방향으로 확산되었는지 확인할 변수
					for(int d=0; d<4; d++) {
						int nr = r + dr[d], nc = c + dc[d];
						if(nr >= 0 && nr < R && nc >=0 && nc < C && copy[nr][nc] != -1) {
							copy[nr][nc] += map[r][c] / 5;
							tmp++;
						}
					} // for dir
					// 확산이 끝난 후에 해당 지점의 남은 미세먼지 양
					int after = map[r][c] - tmp*(map[r][c] / 5);
					copy[r][c] += after;
				}
				// 그렇지 않으면 확산 x
				else if(map[r][c] > 0 && map[r][c] < 5){
					copy[r][c]  += map[r][c];
				}
			} // for c
		} // for r
		
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				map[r][c] = copy[r][c];
			}
		}
	} // spread
	
	public static void cleaner() {
		int upper = Math.min(cleanerR.get(0), cleanerR.get(1));
		int lower = Math.max(cleanerR.get(0), cleanerR.get(1));
		
		copy = new int[R][C];
		copy[upper][0] = -1;
		copy[lower][0] = -1;
		
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				// 왼쪽으로 이동
				if((r == 0 || r == R-1) && c != 0) {
					copy[r][c-1] = map[r][c];
					// 이렇게 두면 공기청정기에서 나오는 값은 0이 된다
				}
				// 오른쪽으로 이동
				else if((r == upper || r == lower) && (c != C-1 && c != 0)) {
					copy[r][c+1] = map[r][c];
				}
				// 아래로 이동
				// 위쪽
				else if(r < upper && c == 0) {
					if(copy[r+1][c] != -1) copy[r+1][c] = map[r][c];
				}
				// 아래쪽
				else if(r >= lower && r != R-1 && c == C-1) {
					copy[r+1][c] = map[r][c];
				}
				// 위로 이동
				// 위쪽
				else if(r <= upper && r != 0 && c == C-1) {
					copy[r-1][c] = map[r][c];
				}
				// 아래쪽
				else if(r > lower && c == 0) {
					if(copy[r-1][c] != -1) copy[r-1][c] = map[r][c];
				}
				// 나머지 이동하지 않는 먼지는 그대로
				else {
					copy[r][c] = map[r][c];
				}
			}
		}
		
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				map[r][c] = copy[r][c];
			}
		}
	} // cleaner
} // class