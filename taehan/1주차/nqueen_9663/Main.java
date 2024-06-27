package nqueen_9663;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[][] visited;
	static int[] dr = {1, 1, 1}; // 상 하 좌 우 좌상 우상 우하 좌하
	static int[] dc = {0, 1, -1};
	static int count;
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		visited = new int[N][N];
		count = 0;			
		
		permutation(0, 0);

		System.out.println(count);
		
	}
    
    public static void permutation(int row, int idx) {
		// base
		if(idx >= N) {
			count++;
			return;
		}
		
		// recursive
		next: for(int i = 0; i < N; i++) {
			if(visited[row][i] < 1) {
				
				for(int j = 0; j < 3; j++) {
					int nr = row;
					int nc = i;
					while(nr >= 0 && nr < N && nc >= 0 && nc < N) {
						visited[nr][nc] += 1;
						
						nr += dr[j];
						nc += dc[j];
					}			
				}
				
				permutation(row+1, idx+1);
                
				for(int j = 0; j < 3; j++) {
					int nr = row;
					int nc = i;
					while(nr >= 0 && nr < N && nc >= 0 && nc < N) {
						visited[nr][nc] -= 1;
						
						nr += dr[j];
						nc += dc[j];
					}			
				}
			}
		}
	}
}
