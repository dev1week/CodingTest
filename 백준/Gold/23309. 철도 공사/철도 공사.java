import java.io.*; 

import java.util.*; 

class Main
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static final int MAX_STATION_NUM = 1000000; 
	
	static int[] next = new int[MAX_STATION_NUM+1];
	static int[] prev = new int[MAX_STATION_NUM+1];
	static int[] route; 
	
	static int n; 
	static int m; 
	
	// idx 1 2 3 4 5 6 7 
	//next - 7 5 - 2 - 3
	//prev - 5 7 - 3 - 2
	static StringBuilder result = new StringBuilder();
	
	static void BN(int i, int j) {
		result.append(next[i]).append("\n");
		//i의 다음역 번호를 출력하기 
		//i - j - i의 다음역
		int nextOfI = next[i];
		
		next[i] = j;
		prev[j] = i; 
		
		if(nextOfI !=0) {
			next[j] = nextOfI;
			prev[nextOfI] = j; 
		}
			 
			//next[i] = j
			//prev[j] = i
		
			//if(i의 다음역 !=0)
				//next[j] = i의 다음역 
				//prev[i의 다음역] = j
		
	}
	
	static void BP(int i, int j) {
		result.append(prev[i]).append("\n");
		//i의 이전역 고유 번호 출력하기 
		
		int prevOfI = prev[i]; 
		
		prev[i] = j; 
		next[j] = i; 
		
		if(prevOfI !=0) {
			prev[j] = prevOfI; 
			next[prevOfI] = j; 
		}
		//i의 이전역 - j - i
			
			//prev[i] = j 
			//j의 next = i 
			
			//if i의 이전역 != 0
				//prev[j] = i의 이전역
				//next[i이전역] = j
			
	}
	
	static void CN(int i) {
		result.append(next[i]).append("\n");
		//i의 다음역 번호 출력하기 
		
		int nextOfI = next[i]; 
		int k = next[nextOfI]; 
		
		if(k!=0) {
			next[i] = k;
			prev[k] = i; 
		}
		
		if(nextOfI != 0) {
			next[nextOfI] = 0;
			prev[nextOfI] = 0; 
		}
		//i - 다음역 - k 
			//k!=0
				//next[i] = k 
				//prev[k]= i
		
			//next[다음역] = 0
			//prev[다음역] = 0 	
	}
	
	static void CP(int i) {
		result.append(prev[i]).append("\n");
		//i의 이전역 출력하기 
		//i의 이전역 폐쇄 
		
		
		int prevOfI = prev[i];
		int k = prev[prevOfI]; 
		
		if(k!=0) {
			prev[i] = k;
			next[k] = i; 
		}
		
		if(prevOfI!=0) {
			next[prevOfI] = 0;
			prev[prevOfI] = 0; 
		}
		// k - i의 이전역 - i 
		
		// k!= 0
			// prev[i] = k 
			// next[k] = i 
		
		//next[i의 이전역] = 0
		//prev[i의 이전역] = 0 
	}
	
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken()); 
		
		route = new int[n]; 
		tokens = new StringTokenizer(buffer.readLine());
		
		for(int i=0; i<n; i++) {
			route[i] = Integer.valueOf(tokens.nextToken());
		}
		
		//next 배열 채우기 
		for(int i=0; i<n-1; i++) {
			int cur = route[i]; 
			next[cur] = route[i+1];
		}
		next[route[n-1]] = route[0]; 
		//prev 배열 채우기 
		for(int i=1; i<n; i++) {
			int cur = route[i]; 
			prev[cur] = route[i-1]; 
		}
		prev[route[0]] = route[n-1]; 
		
	}
	public static void main(String[] args) throws IOException{
		init(); 
		
		for(int i=0; i<m; i++) {
			tokens = new StringTokenizer(buffer.readLine());
			
			String commands = tokens.nextToken();
			
			if(commands.equals("BN")) {
				int data1 = Integer.valueOf(tokens.nextToken());
				int data2 = Integer.valueOf(tokens.nextToken());
				BN(data1, data2); 
			}else if(commands.equals("BP")) {
				int data1 = Integer.valueOf(tokens.nextToken());
				int data2 = Integer.valueOf(tokens.nextToken());
				BP(data1, data2); 
			}else if(commands.equals("CP")) {
				int data1 = Integer.valueOf(tokens.nextToken());
				
				CP(data1); 
			}else if(commands.equals("CN")) {
				int data1 = Integer.valueOf(tokens.nextToken());
				
				CN(data1); 
			} 
		}
		System.out.println(result); 
	}
	
	
}