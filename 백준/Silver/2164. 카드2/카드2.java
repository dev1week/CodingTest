import java.io.*; 

import java.util.*; 


class Main
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static int n; 
	static ArrayDeque<Integer> que = new ArrayDeque<>(); 
	//카드가 1장 남을 때까지 반복 
		//제일 위에 있는 카드를 버린다. 
		//제일 위에 있는 카드를 제일 아래로 옮긴다. 
	public static void init() throws IOException{
		
		n = Integer.valueOf(buffer.readLine()); 
		
		for(int i=1; i<=n; i++) {
			que.add(i);
		}
		

	}
	
	public static void main(String[] args) throws IOException{
		init(); 
		while(que.size()!=1) {
			que.pollFirst();
			que.addLast(que.pollFirst());

		}
		
		System.out.println(que.poll()); 
		
		
	}
	
	
}