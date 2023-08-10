import java.io.*; 

import java.util.*; 

class Main
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	
	static Stack<Character> left;
	static ArrayDeque<Character> right;
	static char[] datas ; 
	
	static void init() throws IOException{
		datas = buffer.readLine().toCharArray();
		left = new Stack<>();
		right = new ArrayDeque<>(); 
	}
	
	static void moveLeft() {
		if(left.size()==0) {
			return;
		}
		char d = left.pop(); 
		right.addFirst(d);
	}
	
	static void moveRight() {
		if(right.size()==0) {
			return; 
		}
		char d = right.pop(); 
		left.add(d); 
	}
	
	static void remove() {
		if(left.size()==0) {
			return; 
		}
		left.pop(); 
	}
	static void add(char d) {
		left.add(d); 
	}
	
	
	public static void main(String[] args)throws IOException{
		int T = Integer.valueOf(buffer.readLine());
		StringBuilder sb = new StringBuilder(); 
		for(int t=0; t<T; t++) {
			init();
			
			for(char data : datas) {
				if(data=='<') {
					moveLeft();
				}else if(data=='>') {
					moveRight();
				}else if(data =='-'){
					remove();
				}else {
					add(data);
				}
			}
			
			for(char d: left) {
				sb.append(d); 
			}
			while(!right.isEmpty()) {
				sb.append(right.poll());
			}
			sb.append("\n");
		}
		System.out.println(sb); 
	}
}