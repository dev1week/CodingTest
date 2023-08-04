import java.io.*; 

import java.util.*; 


class Solution
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	static int[] numbers;
	static Queue<Integer> que;
	static void init() throws IOException{
		que = new ArrayDeque<>();
		int dummy = Integer.valueOf(buffer.readLine()); 
		tokens = new StringTokenizer(buffer.readLine()); 
		for(int i=0; i<8; i++) {
			que.add(Integer.valueOf(tokens.nextToken()));
		}
	}
	
	static void process() {
		int count = 1;
		while(true) {
			int data = que.poll();
			int result = data - count; 
			
			if(result<=0){
				que.add(0);
				break;
			}else{

				que.add(result); 
				count++; 
				if(count==6) {
					count = 1; 
				}
			}

		}

	}
	
	public static void main(String[] args) throws IOException{
		 
		StringBuilder sb = new StringBuilder(); 
		for(int testCase=1; testCase<=10; testCase++) {
			init(); 
			process();
			
			sb.append("#").append(testCase).append(" "); 
			
			for(Integer d : que ) {
				sb.append(d).append(" ");
			}
			sb.append("\n"); 
			
			
		}
		
		System.out.println(sb); 
	}
	
	//while true 
		//큐에서 하나 가져온다. 
		//result = 큐에서 가져온 값 - count 
		//count++; 
		//result <=0 
			//큐에 0을 넣어줌 
			//브레이크 
		//큐에 result를 넣어줌 
	
		
	
	
	
}