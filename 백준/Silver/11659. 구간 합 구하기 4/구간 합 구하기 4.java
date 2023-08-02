import java.io.*; 

import java.util.*; 



class Main
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static int[] num;
	static int[] partSum; 
	
	static int n; 
	static int m; 
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken());
		
		num= new int[n+1]; 
		
		tokens = new StringTokenizer(buffer.readLine());
		for(int i=1; i<=n; i++) {
			num[i] = Integer.valueOf(tokens.nextToken()); 
		}
		
	}
	
	
	static void makePartSum() {
		partSum = new int[n+1];
		
		partSum[0] = 0;
		
		for(int i=1; i<=n; i++) {
			partSum[i] = partSum[i-1]+ num[i]; 
		}
	}
	static StringBuilder result = new StringBuilder(); 
	public static void main(String[] args)throws IOException{
		init(); 
		makePartSum();

		for(int i=0; i<m; i++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			int idx1 = Integer.valueOf(tokens.nextToken())-1;
			int idx2 = Integer.valueOf(tokens.nextToken()); 
			
			result.append(partSum[idx2]-partSum[idx1]).append("\n"); 
		}
		System.out.println(result); 
	}
}