import java.io.*; 

import java.util.*; 

class Top{
	int idx;
	int height; 
	
	public Top(int idx, int height) {
		this.idx = idx;
		this.height = height; 
	}
}

class Main
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static Stack<Top> tops;
	static int n; 
	static Top[] datas; 
	static int[] results; 
	
	static void init()throws IOException{
		tops = new Stack<>(); 
		n = Integer.valueOf(buffer.readLine()); 
		results = new int[n+1]; 
		tokens = new StringTokenizer(buffer.readLine()); 
		for(int i=1; i<=n; i++) {
			Top top = new Top(i, Integer.valueOf(tokens.nextToken())); 
			
			while(!tops.isEmpty()) {
				if(tops.peek().height >= top.height) {
					results[top.idx] =  tops.peek().idx; 
					tops.add(top);
					break;
				}else {
					tops.pop();
					
				}
			}
			
			tops.add(top); 
		}
	}
	
	public static void main(String[] args)throws IOException{
		init();
		StringBuilder result = new StringBuilder(); 
		for(int i=1; i<=n; i++) {
			result.append(results[i]).append(" ");
		}
		System.out.println(result);
	}
	
}