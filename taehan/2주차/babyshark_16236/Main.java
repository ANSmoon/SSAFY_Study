package babyshark_16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/board/view/100687
public class Main {
	static int N, init_fish, shark_size, time;
	static int[][] map;
	static boolean[][] visited;
	static int[] fish;
	static Queue<Fish> queue, queue2;
	static int[] baby;
	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	static class Fish implements Comparable<Fish> {
		int row;
		int col;
		int move;

		Fish(int row, int col, int move) {
			this.row = row;
			this.col = col;
			this.move = move;
		}

		@Override
		public String toString() {
			return "Fish [row=" + row + ", col=" + col + ", move=" + move + "]";
		}

		@Override
		public int compareTo(Fish o) {
			if (this.move == o.move) {
				if (this.row == o.row) {
					return this.col - o.col;
				}
				return this.row - o.row;
			}
			return this.move - o.move;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		baby = new int[2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 9) {
					baby[0] = i;
					baby[1] = j;
					map[i][j] = 0;
				}
			}
		}

		shark_size = 2;
		int eat = 0;
		time = 0;

//		System.out.println("start : " + Arrays.toString(baby));
		
		while (findRoute()) {
			for (int i = 0; i < N; i++) {
				Arrays.fill(visited[i], false);
			}
			eat++;
			if (eat == shark_size) {
				shark_size++;
//				System.out.println("size : " + shark_size);
				eat = 0;
			}
//			System.out.println("baby : " + Arrays.toString(baby));
//			System.out.println("time : " + time);
//			System.out.println();
		}

		System.out.println(time);
	}

	public static boolean findRoute() {
		map[baby[0]][baby[1]] = 0;
		visited[baby[0]][baby[1]] = true;

		Queue<Fish> check = new PriorityQueue<>();
		check.offer(new Fish(baby[0], baby[1], 0));

		int nr, nc, nm = 0;
		Fish move;
		while (!check.isEmpty()) {
			move = check.poll();
//			System.out.println(move);
			
			if(map[move.row][move.col] != 0 && map[move.row][move.col] < shark_size) {
				time += move.move;
				map[move.row][move.col] = 0;
				baby[0] = move.row;
				baby[1] = move.col;
				return true;
			}

			for (int i = 0; i < 4; i++) {
				nr = move.row + dr[i];
				nc = move.col + dc[i];
				nm = move.move;

				if (0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc]) {
					visited[nr][nc] = true;

					if (map[nr][nc] > shark_size) {
						continue;
					}
					
					check.offer(new Fish(nr, nc, nm + 1));
				}

			}
		}

		return false;

	}
}
