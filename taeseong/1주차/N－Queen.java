import java.util.Scanner;

public class Main {
	public static int N, result;
	public static int[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		arr = new int[N];
		
		nQueen(0);
		System.out.println(result);
	}

	public static void nQueen(int depth) {
		if(depth == N) {
			result++;
			return;
		}
		
		// i번째 열의 몇번째 행에 퀸을 놓을지?
		for(int i=0; i<N; i++) {
			arr[depth] = i;
			
			if(isPossible(depth)) {
				nQueen(depth + 1);
			}
		}
	}
	
	public static boolean isPossible(int depth) {
		for(int i=0; i<depth; i++) {
			// 검사하는 열과 같은 위치에 퀸이 이미 있으면
			if(arr[depth] == arr[i]) {
				return false;
			}
			
			// 대각선에 이미 퀸이 있으면
			else if(Math.abs(depth - i) == Math.abs(arr[depth] - arr[i])) {
				return false;
			}
		}
		// 퀸을 둘 수 있다면
		return true;
	}
}