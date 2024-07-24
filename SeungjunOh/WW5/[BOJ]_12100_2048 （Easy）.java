import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static int ans;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		ans = map[0][0];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// map에서 각 자리수가 업데이트 없이 최댓값이 나올 수 있으니 최댓값을 저장
				if(map[i][j]>ans) ans = map[i][j];
			}
		}
		
		find2048(0, map);

		System.out.println(ans);
	}

	private static void find2048(int idx, int[][] arr) {
		// 5번까지 이동할거임
		if (idx == 5) return;

		// 깊은 복사 무조건 필요
		int[][] copyMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = arr[i][j];
			}
		}
		
		// 5번 이동하기 전까지는 재귀로 상하좌우를 모두 이동하면서 최댓값을 찾아준다
		right(idx, copyMap);
		top(idx, copyMap);
		left(idx, copyMap);
		bottom(idx, copyMap);
	}

	private static void right(int idx, int[][] arr) {
		// 여기서도 깊은 복사
		// 그렇지 않으면 맨 처음 깊은 복사를 한 copyMap이랑 겹쳐짐
		// right에서 바뀐 copyMap이 그 밑의 top, left, bottom에도 영향
		int[][] copyArr = new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				copyArr[i][j] = arr[i][j];
			}
		}
		
		//right메서드는 왼쪽에서 오른쪽으로 이동
		for (int i = 0; i < N; i++) {
			// flag는 현재 블록이 어디까지 쌓였는지 표시
			int flag = N-1;
			// flag가 맨 처음 지점에 있으니 2번째 값부터 봐주면서 탐색
			for (int j = N-2; j >= 0; j--) {
				// 0이 아닌 지점을 찾으면
				if (copyArr[i][j] != 0) {
					// 현재 flag가 0이 아닌지 보고
					if (copyArr[i][flag] != 0) {
						// flag의 값과 현재 보고 있는 블럭의 값이 같으면 flag의 값을 2배로 한다
						if (copyArr[i][flag] == copyArr[i][j]) {
							copyArr[i][flag] *= 2;
							if(copyArr[i][flag] > ans ) ans = copyArr[i][flag];
							// 지금 보고 있는 블럭은 올라가서 flag 위치에서 합쳐졌으니 0으로 변경
							copyArr[i][j] = 0;
							// flag는 이미 블럭이 합쳐졌으니 그 다음으로 이동
							flag--;
						} else {
							// flag의 값과 다르면 그 값은 냅두고 한칸 뒤에다가 이동시켜줘야된다.
							copyArr[i][--flag] = copyArr[i][j];
							// flag가 N-1에서 N-2로 이동했을 때 j = N-2라면??
							if(flag!=j)copyArr[i][j] = 0;
						}
					} else {						
						// flag값이 0이면 그 자리에 현재 보고 있는 블럭을 채워준다
						// 이제 다음 j값에서 다시 0이 아닌 값을 찾으면 방금 채워진 블럭(flag)과 비교
						copyArr[i][flag] = copyArr[i][j];
						copyArr[i][j] = 0;
					}
				}
			}
		}
		
		find2048(idx+1,copyArr);
		
	}

	private static void bottom(int idx, int[][] arr) {
		int[][] copyArr = new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				copyArr[i][j] = arr[i][j];
			}
		}
		
		for (int j = 0; j < N; j++) {
			int flag = N-1;
			for (int i = N-2; i >= 0; i--) {
				if (copyArr[i][j] != 0) {
					if (copyArr[flag][j] != 0) {
						if (copyArr[flag][j] == copyArr[i][j]) {
							copyArr[flag][j] *= 2;
							if(copyArr[flag][j] > ans ) ans = copyArr[flag][j];
							copyArr[i][j] = 0;
							flag--;
						} else {
							copyArr[--flag][j] = copyArr[i][j];
							if(flag!=i)copyArr[i][j] = 0;
							
						}
					} else {						
						copyArr[flag][j] = copyArr[i][j];
						copyArr[i][j] = 0;
					}
				}
			}
		}
		
		find2048(idx+1,copyArr);
		
	}

	private static void left(int idx, int[][] arr) {
		
		int[][] copyArr = new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				copyArr[i][j] = arr[i][j];
			}
		}
		for (int i = 0; i < N; i++) {
			int flag = 0;
			for (int j = 1; j < N; j++) {
				if (copyArr[i][j] != 0) {
					if (copyArr[i][flag] != 0) {
						if (copyArr[i][flag] == copyArr[i][j]) {
							copyArr[i][flag] *= 2;
							if(copyArr[i][flag] > ans ) ans = copyArr[i][flag];
							copyArr[i][j] = 0;
							flag++;
						} else {
							copyArr[i][++flag] = copyArr[i][j];
							if(flag!=j)copyArr[i][j] = 0;
						}
					} else {						
						copyArr[i][flag] = copyArr[i][j];
						copyArr[i][j] = 0;
					}
				}
			}
		}
		
		find2048(idx+1,copyArr);
		
	}

	private static void top(int idx, int[][] arr) {
		int[][] copyArr = new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				copyArr[i][j] = arr[i][j];
			}
		}
		for (int j = 0; j < N; j++) {
			int flag = 0;
			for (int i = 1; i < N; i++) {
				if (copyArr[i][j] != 0) {
					if (copyArr[flag][j] != 0) {
						if (copyArr[flag][j] == copyArr[i][j]) {
							copyArr[flag][j] *= 2;
							if(copyArr[flag][j] > ans ) ans = copyArr[flag][j];
							copyArr[i][j] = 0;
							flag++;
						} else {
							copyArr[++flag][j] = copyArr[i][j];
							if(flag!=i)copyArr[i][j] = 0;
						}
					} else {						
						copyArr[flag][j] = copyArr[i][j];
						copyArr[i][j] = 0;
					}
				}
			}
		}
			
		find2048(idx+1,copyArr);

	}
}
