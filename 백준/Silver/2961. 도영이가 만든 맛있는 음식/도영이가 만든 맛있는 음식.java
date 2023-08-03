import java.io.*; 

import java.util.*; 




class Main
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static int n; 
	static long[] s;
	static long[] b; 
	static boolean[] isUsed; 
	static ArrayList<Integer> selecteds = new ArrayList<>(); 
	
	static long result = Long.MAX_VALUE; 
	//중복을 뽑지 않는 조합 
	static void bt(int cur, long sumS, long sumB, boolean flag) {
		if(cur==n) {

			if(flag) {
				result = Math.min(result, Math.abs(sumS-sumB));
			}
			 
			return;
		}
		if(!isUsed[cur]) {

			isUsed[cur] = true; 
			bt(cur+1, sumS*s[cur],sumB+b[cur], true); 
			isUsed[cur] = false; 

		}
		if(flag) {
			bt(cur+1, sumS,sumB, true);
		}else {
			bt(cur+1, sumS, sumB, false);
		}
		 
	}
	
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine());
		b = new long[n];
		s = new long[n]; 
		isUsed = new boolean[n]; 
		for(int i=0; i<n; i++) {
			tokens = new StringTokenizer(buffer.readLine());
			s[i] = Integer.valueOf(tokens.nextToken());
			b[i] = Integer.valueOf(tokens.nextToken()); 
		}
		
	}
	
	public static void main(String[] args)throws IOException{
		init(); 
		bt(0,1,0,false); 
		System.out.println(result); 
	}
	
}