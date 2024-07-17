import java.io.*;
import java.util.*;

public class Main {
    
	static int R, C, X1, X2;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        X1 = 0;
        X2 = 0;
        
        
        int[][] arr = new int[R][C];
        int[][] arrSum;
        
        for (int r=0; r<R; r++) {
        	st = new StringTokenizer(br.readLine());
        	for (int c=0; c<C; c++) {
        		arr[r][c] = Integer.parseInt(st.nextToken());
        		if (arr[r][c] == -1 && X1 == 0) X1 = r;
        		else if (arr[r][c] == -1 && X1 != 0) X2 = r;
        	}
        }
        while (T > 0) {
        	arrSum  = new int[R][C];
        	clone(arr, arrSum);
            wind(arr);
        	T--;
        }
        
        int sum = 0;
        for (int r=0; r<R; r++) {
        	for (int c=0; c<C; c++) {
        		if (arr[r][c] != -1 && arr[r][c] != 0) {
        			sum += arr[r][c];
        		}
        	}
        }
        System.out.println(sum);
        
        
    }
    
    static void clone(int[][] arr, int[][] arrSum) {
    	
    	for (int r=0; r<R; r++) {
    		for (int c=0; c<C; c++) {
    			if (arr[r][c] == -1) {
    				arrSum[r][c] = -1;
    				continue;
    			}
    			if (arr[r][c] != 0) {
    				int s = arr[r][c]/5;
    				int k = 0;
    				
    				if (r > 0 && arr[r-1][c] != -1) {
    					arrSum[r-1][c] += s;
    					k += s;
    				}
    				if (c > 0 && arr[r][c-1] != -1) {
    					arrSum[r][c-1] += s;
    					k += s;
    				}
    				if (r < R-1 && arr[r+1][c] != -1) {
    					arrSum[r+1][c] += s;
    					k += s;
    				}
    				if (c < C-1 && arr[r][c+1] != -1) {
    					arrSum[r][c+1] += s;
    					k += s;
    				}
    				arrSum[r][c] += (arr[r][c]-k);
    				
    			}
    			
    		}
    	}
    	
    	for (int r=0; r<R; r++) {
    		for (int c=0; c<C; c++) {
    			arr[r][c] = arrSum[r][c];
    		}
    	}
    	
    	
    	
    }
    
    static void wind(int[][] arr) {
    	int r1 = X1-1;
    	int r2 = X2+1;
    	int c1 = 0;
    	int c2 = 0;
    	
    	while (true) {
    		if (c1 == 0 && r1 > 0) arr[r1][c1] = arr[--r1][c1];
    		else if (r1 == 0 && c1 < C-1) arr[r1][c1] = arr[r1][++c1];
    		else if (c1 == C-1 && r1 < X1) arr[r1][c1] = arr[++r1][c1];
    		else if (r1 == X1 && c1 > 1) arr[r1][c1] = arr[r1][--c1];
    		
    		if (c2 == 0 && r2 < R-1) arr[r2][c2] = arr[++r2][c2];
    		else if (r2 == R-1 && c2 < C-1) arr[r2][c2] = arr[r2][++c2];
    		else if (c2 == C-1 && r2 > X2) arr[r2][c2] = arr[--r2][c2];
    		else if (r2 == X2 && c2 > 1) arr[r2][c2] = arr[r2][--c2];
    		
    		if (r1 == X1 && r2 == X2 && c1 == 1 && c2 == 1) {
    			arr[r1][c1] = 0;
    			arr[r2][c2] = 0;
    			break;
    		}
    		
    	}
    	
    	
    	
    }
    


}
