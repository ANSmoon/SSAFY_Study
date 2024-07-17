import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class Main {
	static int R, C, T;
	static int[][] map;
	static Queue<int[]> q = new LinkedList<>();
	static int upper, lower;
	static int ans;
	static int[] dr = {0,-1,0,1};
	static int[] dc = {-1,0,1,0};

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		// ans 는 -1이 2개 잇으니까 감안해서 시작
		ans = 2;
		boolean flag = false;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				ans += map[i][j];
			}
			// -1인 지점을 공기 청정기로 저장(위 아래 나눠 저장)
			if (map[i][0] == -1 && !flag) {
				upper = i;
				lower = i+1;
				flag = true;				
			}
		}
		
		// 확산이 진행되고 난 후 (difusion)
		// 공기청정기의 순환이 진행된다 (circulation)
		difusion();
		
		System.out.println(ans);
	}

	private static void difusion() {
		// T 만큼의 시간 동안 확산
		for(int t=0;t<T;t++) {

			// 전체 맵 훑어도 안 터질듯?
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					if(map[i][j]>4) {
						q.add(new int[] {i,j, map[i][j]/5});
					}
				}
			}
			
			// q에 들어가있는 값들을 확산
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] node = q.poll();
				for(int j=0;j<4;j++) {
					int nr = node[0]+dr[j];
					int nc = node[1]+dc[j];
					if(nr>=0&&nr<R&&nc>=0&&nc<C&&map[nr][nc]>=0) {
						map[nr][nc] += node[2];
						map[node[0]][node[1]] -= node[2];
					}
				}
			}
			
			circulation();
		}
	}
	
	private static void circulation() {
		//System.out.println( map[upper-1][0]+" "+map[lower+1][0]);
		// upper의 경우
		ans -= map[upper-1][0];
		for(int i=upper-1;i>=1;i--) {
			map[i][0] = map[i-1][0];
		}
		for(int j=0;j<C-1;j++) {
			map[0][j] = map[0][j+1];
		}
		for(int i=0;i<upper;i++) {
			map[i][C-1] = map[i+1][C-1];
		}
		for(int j=C-1;j>0;j--) {
			map[upper][j] = map[upper][j-1];
		}
		// map[upper][1]은 -1이 되므로 한번 수정
		map[upper][1] = 0;
		
		//lower의 경우
		ans -= map[lower+1][0];
		for(int i=lower+1;i<R-1;i++) {
			map[i][0] = map[i+1][0];
		}
		for(int j=0;j<C-1;j++) {
			map[R-1][j] = map[R-1][j+1];
		}
		for(int i=R-1;i>lower;i--) {
			map[i][C-1] = map[i-1][C-1];
		}
		for(int j=C-1;j>0;j--) {
			map[lower][j] = map[lower][j-1];
		}
		map[lower][1] = 0;
	}
}
