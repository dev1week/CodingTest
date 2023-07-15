import java.util.*; 
import java.io.*;



class Main
{	
	
	static Scanner sc = new Scanner(System.in); 
	
	
	static int n; 
	
	static int k; 
	static int[] w;
	static int[] v; 
	static int[] dp; // dp[i] = 현재 무 합이 i일 때 얻을 수 있는 가치의 최대치 
	
	
	static void init() {
		n = sc.nextInt();
		k = sc.nextInt(); 
		
		w = new int[n+1];
		v = new int[n+1]; 
		
		dp = new int[k+1];
		
		for(int i=1; i<=n; i++) {
			w[i] = sc.nextInt();
			v[i] = sc.nextInt(); 
		}
	}
	
	public static void main(String[] args) {
		init(); 
		
		for(int item=1; item<=n; item++) {
			for(int weight=k; weight>=w[item]; weight--) {
				dp[weight] = Math.max(dp[weight], dp[weight-w[item]]+v[item]);
			}
		}
		
		int max = 0;
		for(int  weight = 0; weight<=k; weight++) {
			max = Math.max(max, dp[weight]);
		}
		System.out.println(max); 
	}
}
	