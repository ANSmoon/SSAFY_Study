import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N,K;
	static int[] W,V;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		W = new int[N];
		V = new int[N];
		dp = new int[K+1];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<N;i++) {
			for(int j=K;j-W[i]>=0;j--) {
				dp[j] = Math.max(dp[j], dp[j-W[i]]+V[i]);
			}
		}
		
		System.out.println(dp[K]);
		
	}

}
