import java.io.*; 

import java.util.*; 

class Number implements Comparable<Number>{
	int num;
	int origin; 
	
	
	public Number(int num) {
		this.num = Math.abs(num);
		 this.origin = num; 
	}
	
	@Override
	public int compareTo(Number other) {
		if(this.num == other.num) {
			return this.origin - other.origin;
		}
		return this.num - other.num; 
	}
	
	public String toString() {
		return String.valueOf(origin); 
	}
}

class Main
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static PriorityQueue<Number> pq = new PriorityQueue<>(); 
	
	static int n; 
	
	static final int REMOVE = 0; 
	
	
	
	public static void main(String[] args) throws IOException{
		n = Integer.valueOf(buffer.readLine());
		StringBuilder result = new StringBuilder(); 
		for(int i=0; i<n; i++) {
			int command = Integer.valueOf(buffer.readLine());
			if(command==REMOVE) {
				if(pq.size()==0) {
					result.append(0).append("\n");
				}else {
					Number data = pq.poll();
					result.append(data).append("\n");  
				}
			}else {
				pq.add(new Number(command)); 
				
			}
		}
		
		System.out.println(result); 
	}
	
}