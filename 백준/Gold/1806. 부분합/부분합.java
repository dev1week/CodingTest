import java.io.*; 
import java.util.*; 


class Main
{
	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	
	static StringTokenizer tokens;
	static int[] arr; 
	static int n; 
	static int m; 
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine());
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken());
		
		arr = new int[n]; 
		
		tokens = new StringTokenizer(buffer.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.valueOf(tokens.nextToken()); 
		}
	}
	
	public static void main(String[] args) throws IOException{
		init();
		int result = Integer.MAX_VALUE; 
		
		int en = 0; 
		int sum = arr[0]; 
		for(int st=0; st<n; st++) {
			
			while(en<n && sum<m) {
				
				en++;
				if(n!=en)sum += arr[en];
			}
			if(en == n ) break; 
			result = Math.min(result, en-st+1);
			sum -= arr[st]; 
		}
		if(result==Integer.MAX_VALUE) {
			result = 0; 
		}
		System.out.println(result); 
	}
	
}