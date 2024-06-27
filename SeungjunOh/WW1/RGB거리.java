import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	// 점의 개수 N 선언
	static int N;
	static int[][] map;
	static int[][] memoization;

	// 최소값
	static int ans = Integer.MAX_VALUE;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// N 입력
		N = Integer.parseInt(br.readLine());

		// 각 인덱스 별 RGB 비용을 저장할 map생성
		map = new int[N][3];
		memoization = new int[N][3];

		// N만큼 반복 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
			map[i][2] = Integer.parseInt(st.nextToken());
		}

		// 인덱스 별로 최솟값을 구한다
		// 0번 인덱스는 각 항의 값이 최솟값
		// 1번부터 N번까지의 인덱스는 이전까지의 RGB 값중 최솟값을 저장한다
		memoization[0][0] =map[0][0];
		memoization[0][1] =map[0][1];
		memoization[0][2] =map[0][2];
		for(int i=1;i<N;i++) {
			// R은 이전 인덱스 중 G의 최소값과 B의 최소값에 R을 더한 값 중 작은 값을 저장
			memoization[i][0] = Math.min(memoization[i-1][1]+map[i][0], memoization[i-1][2]+map[i][0]);
			// G는 이전 인덱스 중 R의 최소값과 B의 최소값에 G을 더한 값 중 작은 값을 저장
			memoization[i][1] = Math.min(memoization[i-1][0]+map[i][1], memoization[i-1][2]+map[i][1]);
			// B은 이전 인덱스 중 G의 최소값과 R의 최소값에 B을 더한 값 중 작은 값을 저장
			memoization[i][2] = Math.min(memoization[i-1][0]+map[i][2], memoization[i-1][1]+map[i][2]);
		}
		// N-1까지 진행되었으면 rgb값 중 최솟값을 비교한다
		for(int i=0;i<3;i++) {
			if(ans>memoization[N-1][i]) {
				ans =memoization[N-1][i];
			}
		}
		
		bw.write(ans + "");
		bw.flush();
		bw.close();
	}

}