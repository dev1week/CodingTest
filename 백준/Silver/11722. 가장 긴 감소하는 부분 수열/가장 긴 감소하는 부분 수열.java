

import java.io.*;
import java.util.*; 

public class Main {
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	
	static int[] dp; //dp[i] = i번째 수를 고려했을 때 가장 긴 감소하는 부분 수열의 길이 
	static int[] arr; 
	static int n; 
	
	//감소하고 있는중일 때 
		//dp[i], dp[j] + 1
	
	
	
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine()); 
		
		dp = new int[n+1]; 
		arr = new int[n+1]; 
		
		tokens = new StringTokenizer(buffer.readLine()); 
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.valueOf(tokens.nextToken());
		}
		
	}
	
	
	
	public static void main(String[] args) throws IOException{
		init(); 
		
		
		for(int current=1; current<=n; current++) {
			dp[current] = 1; 
			
			for(int prev=1; prev<current; prev++) {
				if(arr[current]<arr[prev]) {
					dp[current] = Math.max(dp[current],dp[prev]+1); 
				}
			}
		}
		
		
		int max = 0; 
		for(int i=1; i<=n; i++) {
			max = Math.max(dp[i], max); 
		}
		System.out.println(max); 
	}
}
