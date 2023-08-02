import java.io.*; 

import java.util.*; 



class Main
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static int n; 
	static int m; 
	
	static int[] selecteds;
	static boolean[] isUsed;
	
	static StringBuilder result = new StringBuilder();
	
	static void print() {
		for(int selected : selecteds) {
			result.append(selected).append(" ");
		}result.append("\n");
	}
	
	static void bt(int cur, int last) {
		if(cur==m) {
			print();
			return; 
		}
		
		for(int next=last+1; next<=n; next++) {
			if(!isUsed[next]) {
				isUsed[next] = true; 
				selecteds[cur] = next; 
				bt(cur+1, next); 
				isUsed[next]=false; 
			}
		}
	}
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine());
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken());
		
		isUsed = new boolean[n+1];
		selecteds = new int[m]; 
	}
	
	public static void main(String[] args) throws IOException{
		init(); 
		bt(0,0); 
		System.out.println(result); 
	}
}