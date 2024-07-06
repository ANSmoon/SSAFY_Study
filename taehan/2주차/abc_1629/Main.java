package abc_1629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static long A, B, C;
	static Map<Long, Long> map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());
		map = new HashMap<>();
		
		map.put(1l, A % C);
		
		System.out.println(remain(A, B));;
	}
	
	public static long remain(long e, long power) {
		if(power == 1) {
			return A % C;
		}
		long tmp = remain(e, power/2);
		
		if(power % 2 == 1) {
			// 모듈러 연산에 대한 이해 필요
			return (((tmp * tmp) % C) * (e % C)) % C;
		}
		return (tmp * tmp) %C;
		
	}
}
