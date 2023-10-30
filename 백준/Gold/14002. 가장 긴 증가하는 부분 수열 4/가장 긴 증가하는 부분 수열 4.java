

import java.io.*;
import java.util.*; 

public class Main {
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	
	static int[] dp; //DP[I] = i번째 수까지 고려했을 때 증가 수열의 최대길이 
	static int[] arr;
	static int n; 
	
	
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine());
		tokens = new StringTokenizer(buffer.readLine()); 
		
		
		arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.valueOf(tokens.nextToken()); 
					
		}
		
	}
	
	
	
	public static void main(String[] args) throws IOException{
		init(); 
		dp = new int[n]; 
		
		int[] prev = new int[n]; 
		
		for(int i=1; i<n; i++) {
			prev[i] = i; 
		}
		
		
		for(int i=0; i<n; i++) {
			dp[i] = 1; 
			for(int j=0; j<i; j++) {
				if(arr[i]>arr[j]) {
					if(dp[j]+1>dp[i]) {
						dp[i] = dp[j]+1;
						prev[i] = j; 
					}

				}
			}

		}
		
		
		int result = 0; 
		int start = 0; 
		
		for(int i=0; i<n; i++) {
			if(result<dp[i]) {
				result = dp[i]; 
				start = i; 
			}
		}
		
		int[] numbers = new int[result]; 

		int cur = start; 
		numbers[result-1] = arr[start]; 
		
		for(int i=result-2; i>=0; i--) {
			cur = prev[cur]; 
			numbers[i] = arr[cur]; 
		}
		
		StringBuilder sb = new StringBuilder(); 
		sb.append(result).append("\n"); 
		for(int i=0; i<result; i++) {
			sb.append(numbers[i]).append(" "); 
		}
		System.out.println(sb); 
		
		

	}
}
