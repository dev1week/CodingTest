import java.io.*; 

import java.util.*; 




class Main
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	

	static int n; 
	static boolean[] isNotPrime; 
	static StringBuilder result = new StringBuilder(); 
	
	static boolean isPrime(int num) {
		if(num<2) return false; 
		for(int i=2; i*i<=num; i++) {
			if(num%i==0) {
				return false;
			}
		}return true; 
	}
	
	static void bt(int cur, int num) {
		
		if(!isPrime(num)) return; 
		if(cur==n-1&&isPrime(num)) {
			
			result.append(num).append("\n"); 
			return;
		}
		
		for(int next=0; next<10; next++) {
			bt(cur+1, num*10+next);
		}
	}
	
	public static void main(String[] args) throws IOException{
		n = Integer.valueOf(buffer.readLine()); 
		for(int i=1; i<=9; i++) {
			bt(0,i);
		}
		System.out.println(result); 
		
	}
	//1~n까지 
	
		//왼쪽부터 i번째 자릿수까지의 수가 소수인지 확인 
	
}