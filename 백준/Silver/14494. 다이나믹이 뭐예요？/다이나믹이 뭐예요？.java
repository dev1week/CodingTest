

import java.io.*;
import java.util.*; 

public class Main {
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	
	static long[][] dp; 
	static int n; 
	static int m; 
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine());
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken()); 
		
		dp = new long[n+1][m+1]; 
		
		for(int x=2; x<=n; x++) {
			dp[x][1] = 1; 
		}
		
		Arrays.fill(dp[1], 1);
		dp[1][0] = 0; 
		dp[1][1] = 1; 
		
	}
	
	
	public static void main(String[] args) throws IOException{
		
		
		init(); 
		
		
		
		
		for(int x=2; x<=n; x++)
		{
			for(int y=2; y<=m; y++) {
				dp[x][y] = (dp[x][y-1]+dp[x-1][y]+dp[x-1][y-1])%1000000007; 
			}
		}
		
//		for(long[] dps: dp) {
//			for(long d : dps) {
//				System.out.print(d+" ");
//			}System.out.println();
//		}
		
		System.out.println(dp[n][m]); 
		
		
	}
	
	
	
	
}
