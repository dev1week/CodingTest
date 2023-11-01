import java.io.*;
import java.util.*; 

public class Main {
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	
	static int[] dp; //dp[i] = i번째 수를 고려했을 때 가장 증가하는 부분 수열의 길이 
	static int[] arr; 
	static int n; 
	
	
	
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine()); 
		
		arr = new int[n+1]; 
		dp = new int[n+1]; 
		
		
		tokens = new StringTokenizer(buffer.readLine()); 
		
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.valueOf(tokens.nextToken());
		}
	}
	
	
	
	public static void main(String[] args) throws IOException{
		init(); 
		
		for(int cur=1; cur<=n; cur++) {
			dp[cur] = 1; 
			
			for(int prev=1; prev<cur; prev++) {
				if(arr[cur]>arr[prev]) {
					dp[cur] = Math.max(dp[cur], dp[prev]+1); 
				}
			}
		}
		
//		System.out.println(Arrays.toString(dp)); 
		
		int result = 0; 
		for(int i=1; i<=n; i++) {
			result = Math.max(result, dp[i]); 
		}
		
		System.out.println(result); 
	}
}
