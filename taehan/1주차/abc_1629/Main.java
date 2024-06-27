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
		
		map.put((long)1, A % C);
		
		System.out.println(remain(B, B % 2));;
	}
	
	public static long remain(long power, long bool) {
//		System.out.println("power : " + power);
		if(power == 1) {
			return map.get(power);
		}
		
		if(!map.containsKey(power / 2)) {
			map.put(power / 2, remain(power / 2, (power / 2) % 2));
//			System.out.println(map.toString());
		}
		
		if(bool == 0) {
			return (map.get(power / 2) * map.get(power / 2)) % C;
		}else {
			return (map.get(power / 2) * map.get(power / 2) * (A % C)) % C;
		}
		
		
	}
}
