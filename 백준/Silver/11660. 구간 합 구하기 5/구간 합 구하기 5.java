import java.io.*; 

import java.util.*; 



class Main
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static int[][] num;
	static int[][] partSum; 
	
	static int n; 
	static int m; 
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		n = Integer.valueOf(tokens.nextToken()); 
		m = Integer.valueOf(tokens.nextToken()); 
		
		num = new int[n+1][n+1]; 
		partSum = new int[n+1][n+1]; 
		
		for(int x=1; x<=n; x++) {
			tokens = new StringTokenizer(buffer.readLine());
			for(int y=1; y<=n; y++) {
				num[x][y] = Integer.valueOf(tokens.nextToken());
			}
		}
		
	}
	
	static void makePartSum() {
		for(int x=1; x<=n; x++) {
			for(int y=1;y<=n; y++ ) {
				partSum[x][y] = num[x][y]+partSum[x-1][y]+partSum[x][y-1] -partSum[x-1][y-1];  
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		init();
		
		makePartSum(); 
		StringBuilder result = new StringBuilder(); 
		for(int i=0; i<m; i++) {
			tokens = new StringTokenizer(buffer.readLine());
			int x1 = Integer.valueOf(tokens.nextToken());
			int y1 = Integer.valueOf(tokens.nextToken());
			int x2 = Integer.valueOf(tokens.nextToken());
			int y2 = Integer.valueOf(tokens.nextToken());
			
			result.append(partSum[x2][y2]-partSum[x1-1][y2]-partSum[x2][y1-1]+partSum[x1-1][y1-1]).append("\n");
		}
		System.out.println(result); 
	}
}