import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int N;
	static final int[] dr = { -1, -1, 1, 1 };
	static final int[] dc = { 1, -1, 1, -1 };
	static boolean[][] chess;
	static boolean[] row;
	static int count;

	static BufferedReader br;
	static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

//			System.out.println("t: "+t);
		N = Integer.parseInt(br.readLine());
		// queen을 둘 공간인 chess배열 생성
		// 배치 가능한 수인 count 초기화
		chess = new boolean[N][N];
		row = new boolean[N];
		count = 0;
		queen(0);

		bw.write( count + "\n");

		bw.flush();
		bw.close();
	}

	static void queen(int idx) {
		// N-1까지 idx가 도착했으면 모든 퀸이 배치 가능하므로 카운트 증가
		if (idx == N) {
//			System.out.println(count);
			count++;
			return;
		}

		// 같은 열에서 가능한지 확인
		cp: for (int j = 0; j < N; j++) {
			if (row[j]) {
//					System.out.println("i: "+i+" j: "+j +" row");
				continue cp;
			}
			// 그 위치에서 대각선에 다른 퀸 있는지 확인
			for (int k = 0; k < 4; k++) {
				int nr = idx + dr[k];
				int nc = j + dc[k];
				while (nr >= 0 && nc >= 0 && nr < N && nc < N) {
					if (chess[nr][nc]) {
//							System.out.println("i: "+i+" j: "+j +" 대각");
						continue cp;
					}
					nr += dr[k];
					nc += dc[k];
				}
			}
			// 위에서 안걸러지면 배치 가능
			chess[idx][j] = true;
			row[j] = true;
			// 다음 인덱스에 대한 재귀함수 실행
			queen(idx + 1);
			// 재귀 끝나면 돌려놓고
			chess[idx][j] = false;
			row[j] = false;
		}

	}
}