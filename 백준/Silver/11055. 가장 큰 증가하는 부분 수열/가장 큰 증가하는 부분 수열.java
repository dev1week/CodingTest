import java.io.*;
import java.util.*; 

public class Main {
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	
	static int[] dp; //DP[I] = i번째 수까지 고려했을 때 증가 수열중 최대합 
	static int[] arr;
	static int n; 
	
	
	
	static void init() throws IOException{
		n= Integer.valueOf(buffer.readLine()); 
		arr = new int[n]; 
		dp = new int[n]; 
		
		tokens = new StringTokenizer(buffer.readLine());
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.valueOf(tokens.nextToken()); 
		}
		
		
	}
	
	
	public static void main(String[] args) throws IOException{
		
		
		init(); 
		int result = 0; 
		for(int i=0; i<n; i++) {
			dp[i]  = arr[i]; 
			for(int j=0; j<i; j++) {

				if(arr[j]<arr[i]) {
					dp[i] = Math.max(dp[i], dp[j]+arr[i]); 
					result = Math.max(result, dp[i]);
				}
			}
			
			
		}
		
		for(int i=0; i<n; i++) {
			result = Math.max(result, dp[i]);
		}
		
		System.out.println(result); 

	}
}
