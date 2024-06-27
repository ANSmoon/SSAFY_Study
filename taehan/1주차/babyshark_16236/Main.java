package babyshark_16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, init_fish;
	static int[][] map;
	static int[] fish;
	static Queue<Fish> queue, queue2;
	
	static class Fish implements Comparable<Fish> {
		int row;
		int col;
		int size;
		
		Fish(int row, int col, int size){
			this.row = row;
			this.col = col;
			this.size = size;
		}

		@Override
		public String toString() {
			return "Fish [row=" + row + ", col=" + col + ", size=" + size + "]";
		}

		@Override
		public int compareTo(Fish o) {
			if(this.row + this.col == o.row + o.col) {
				if(this.row == o.row) {
					return this.col - o.col;
				}
				return this.row - o.row;
			}
			return (this.row + this.col) - (o.row + o.col);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		init_fish = 0;
		fish = new int[7];
		queue = new PriorityQueue<>();
		queue2 = new PriorityQueue<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(0 < map[i][j] && map[i][j] <= 6) {
					fish[map[i][j]]++;
					init_fish++;
				}
				
				queue.offer(new Fish(i, j, map[i][j]));
			}
		}
		
		int shark_size = 2;
		int eat;
		int size = queue.size();
		
		for(int i = 0; i < size; i++) {
			Fish tmp = queue.poll();
			
			if(tmp.size != 0) {
				
			}
		}
	}
}
