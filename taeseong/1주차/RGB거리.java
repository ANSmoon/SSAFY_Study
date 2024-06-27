import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		// 최소값을 저장할 dp 배열
		int[][] arr = new int[N][3];
		
		// 첫번째 집에 대한 비용은 미리 값을 저장해두기 
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<3; i++) {
			arr[0][i] = Integer.parseInt(st.nextToken());
		}
		
		// 2번째부터 N번째 집까지의 최소 비용 구하기
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			arr[i][0] = Integer.parseInt(st.nextToken()) + Math.min(arr[i-1][1], arr[i-1][2]);
			arr[i][1] = Integer.parseInt(st.nextToken()) + Math.min(arr[i-1][0], arr[i-1][2]);
			arr[i][2] = Integer.parseInt(st.nextToken()) + Math.min(arr[i-1][0], arr[i-1][1]);
		}
		
		// 마지막 행의 값 -> 각 case마다 비용의 총합
		// 그 중 최솟값 구하기
		int ans = 987654321;
		for(int i=0; i<3; i++) {
			if(ans > arr[N-1][i]) ans = arr[N-1][i]; 
		}
		System.out.println(ans);
	}
}