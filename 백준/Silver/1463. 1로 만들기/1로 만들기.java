import java.io.*;
import java.math.BigInteger;
import java.util.*; 




class Main
{	
	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	
	
	static final int MAX_SIZE = 1000001; 
	
	public static void main(String[] args) throws IOException{
		
		int n = Integer.valueOf(buffer.readLine());
		int[] dp = new int[MAX_SIZE+1];
		for(int i=2; i<=5; i++) {
			dp[i] =1;
		}
		
		dp[5] =3;
		dp[4] = 3; 
		
		for(int i=6; i<=n; i++) {
			if(i%6==0) {
				dp[i] = Math.min(dp[i-1], dp[i/3]);
				dp[i] = Math.min(dp[i], dp[i/2]);
				dp[i]++; 
			}
			else if(i%3==0) {
				dp[i] = Math.min(dp[i-1], dp[i/3])+1;
			}else if(i%2==0) {
				dp[i] = Math.min(dp[i-1], dp[i/2])+1; 
			}else {
				dp[i] = dp[i-1]+1;
			}
			  
		}

		System.out.println(dp[n]); 
		
		
		
	}
	
	
}