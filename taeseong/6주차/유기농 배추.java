import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][] visited;
	static int M, N, K;
	
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	StringBuilder sb = new StringBuilder();
    	
    	int T = Integer.parseInt(br.readLine());
    	for(int t=0; t<T; t++) {
    		st = new StringTokenizer(br.readLine());
    		M = Integer.parseInt(st.nextToken());
    		N = Integer.parseInt(st.nextToken());
    		K = Integer.parseInt(st.nextToken());
    		map = new int[M][N];
    		visited = new boolean[M][N];
    	
    		for(int i=0; i<K; i++) {
    			st = new StringTokenizer(br.readLine());
    			int r = Integer.parseInt(st.nextToken());
    			int c = Integer.parseInt(st.nextToken());
    			map[r][c] = 1;
    		}
    		
    		int count = 0;
    		for(int r=0; r<M; r++) {
    			for(int c=0; c<N; c++) {
    				if(map[r][c] == 1 && !visited[r][c]) {
    					visited[r][c] = true;
    					bfs(r, c);
    					count++;
    				}
    			}
    		}
    		sb.append(count+"\n");
    	} // for testcase
    	System.out.println(sb);
	} // main

	public static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {r, c});
		
		while(!queue.isEmpty()) {
			int[] arr = queue.poll();
			int x = arr[0];
			int y = arr[1];
			
			for(int d=0; d<4; d++) {
				int nr = x + dr[d];
				int nc = y + dc[d];
				if(nr >= 0 && nr < M && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] == 1) {
					queue.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}
	}
} // class