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
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.valueOf(buffer.readLine()); 
		}
	}
	
	public static void main(String[] args) throws IOException{
		init();
		int result = Integer.MAX_VALUE; 
		Arrays.sort(arr);
		int en = 0; 
		for(int st=0; st<n; st++) {
			while(en<n && arr[en]-arr[st]<m) {
				en++;
			}
			if(en == n ) break; 
			result = Math.min(result, arr[en]-arr[st]);
		}
		
		System.out.println(result); 
	}
	
}