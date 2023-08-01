import java.io.*; 

import java.util.*; 



class Main
{
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	static long k = 0; 
	
	static void hanoi(int n, int from, int to, StringBuilder process) {
		if(n==1) {
			process.append(from +" "+to+"\n");
			k++; 
			return; 
		}
		
		int empty = 6-from-to; 
		
		hanoi(n-1, from, empty, process);
		hanoi(1, from, to, process); 
		hanoi(n-1, empty,to, process); 
	}
	
	public static void main(String[] args) throws IOException{
		int n = Integer.valueOf(buffer.readLine());
		
		StringBuilder result = new StringBuilder(); 
		
		hanoi(n,1,3,result);
		System.out.println(k); 
		System.out.println(result.toString()); 
	}
	
}