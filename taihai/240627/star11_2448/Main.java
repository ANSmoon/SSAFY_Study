package star11_2448;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static char[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		map = new char[N+1][2*N+1];
		
		// map을 공백으로 초기화
		for(int i = 0; i <= N; i++) {
			for(int j = 0; j <= 2*N; j++) {
				map[i][j] = ' ';
			}
		}
		
		// 처음 세 꼭짓점 위치 저장배열 vertex
		int[][] vertex = new int[3][2];
		
		vertex[0][0] = 1;
		vertex[0][1] = N;
		vertex[1][0] = N;
		vertex[1][1] = 1;
		vertex[2][0] = N;
		vertex[2][1] = 2*N - 1;
		
 		dfs(vertex);
 		
 		for(int i = 1; i <= N; i++) {
 			for(int j = 1; j <= 2*N; j++) {
 				sb.append(map[i][j]);
 			}
 			sb.append("\n");
 		}
 		
 		System.out.println(sb);
	}
	
	public static void dfs(int[][] v) {
		// 배열로 들어온 위치에 *을 찍어줌
		map[v[0][0]][v[0][1]] = '*';
		map[v[2][0]][v[2][1]] = '*';
		map[v[1][0]][v[1][1]] = '*';
		
		// 높이 3인 삼각형이 만들어졌을 때, 남은 공간에 *을 채워줌
		// 이후 리턴
		if(v[1][0] - v[0][0] == 2) {
			map[(v[0][0] + v[1][0]) / 2][(v[0][1] + v[1][1]) / 2] = '*';
			map[(v[0][0] + v[2][0]) / 2][(v[0][1] + v[2][1]) / 2] = '*';
			map[v[1][0]][v[1][1] + 1] = '*';
			map[v[1][0]][v[1][1] + 2] = '*';
			map[v[1][0]][v[1][1] + 3] = '*';
			return;
		}
		
		// 3개의 삼각형으로 나눠 dfs 진행
		dfs(new int[][] {{v[0][0], v[0][1]}, {(v[0][0] + v[1][0]) / 2, (v[0][1] + v[1][1]) / 2 + 1}, {(v[0][0] + v[2][0]) / 2 , (v[0][1] + v[2][1]) / 2}});
		dfs(new int[][] {{(v[0][0] + v[1][0]) / 2 + 1, (v[0][1] + v[1][1]) / 2}, {v[1][0], v[1][1]}, {v[1][0] , (v[1][1] + v[2][1]) / 2 - 1}});
		dfs(new int[][] {{(v[0][0] + v[2][0]) / 2 + 1, (v[0][1] + v[2][1]) / 2 + 1}, {v[1][0], (v[1][1] + v[2][1]) / 2 + 1}, {v[2][0], v[2][1]}});
		
	}
}
