import java.io.*; 
import java.util.*;

class Main
{	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	
	static int n; 
	static int m; 
	
	static int[] parents; 
	
	//입력 받으면서 union 연산 실행 
	
	//여행 계획 받으면서 parents[i]를 해쉬 셋에 저장하기 
	//해쉬셋의 크기가 2이상이면 NO
	
	
	static void init() throws IOException{
		 
		n = Integer.valueOf(buffer.readLine());
		m = Integer.valueOf(buffer.readLine()); 
		
		parents = new int [n+1]; 
		
		
		for(int i=1; i<=n; i++) {
			parents[i] = i; 
		}
		
		for(int i=1; i<=n; i++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int k=1; k<=n; k++) {
				int option = Integer.valueOf(tokens.nextToken()); 
				if(option==1) {
					union(i,k); 
				}
			}
		}
	}
	
	
	static void union(int a, int b) {
		int pA = find(a);
		int pB = find(b);
		
		parents[pA] = pB; 
	}
	
	static int find(int a) {
		if(parents[a]==a) {
			return a; 
		}else {
			return parents[a] = find(parents[a]); 
		}
	}
	
	public static void main(String[] args)throws IOException{
		init(); 
		
		int[] plan= new int[m]; 
		if(isPossible()) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
	}
	
	static boolean isPossible() throws IOException {
		tokens = new StringTokenizer(buffer.readLine());
		int parent = find(Integer.valueOf(tokens.nextToken())); 
		for(int i=0; i<m-1; i++) {
			if(parent!=find(Integer.valueOf(tokens.nextToken()))) {
				return false; 
			}
		}
		
		return true; 
	}
		
}