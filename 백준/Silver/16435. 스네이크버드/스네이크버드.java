import java.io.*; 

import java.util.*; 

class Main
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	
	
	//과일 하나를 먹는다
		//길이가 1 증가
	
	//과일 
		//일정 높이를 가지고 있음 
	
	
	//스네이크
		//자기보다 길이가 작거나 같은 높이에 있는 과일 먹을 수 있음 
	
	static int L; // 최초 길이 
	static int n; // 과일 개수 
	static int[] h; // 과일의 높이 길이 배열 
	
	
	//1 2 3 4 5 6 7 8 9 
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		n = Integer.valueOf(tokens.nextToken());
		L = Integer.valueOf(tokens.nextToken()); 
		
		h = new int[n]; 
		tokens = new StringTokenizer(buffer.readLine());
		for(int i=0; i<n; i++) {
			h[i] = Integer.valueOf(tokens.nextToken()); 
		}
	}
	
	public static void main(String[] args) throws IOException{
		init(); 
		
		Arrays.sort(h);
		int cur = L; 
		//배열 정렬 
		//초기 위치부터 시작 
			//내 현재 길이보다 작거나 같은가? 
				//길이 증가 
		for(int i=0; i<n; i++) {
			if(h[i]<=cur) {
				cur++; 
			}
		}
		System.out.println(cur); 
		//길이 출력 
	}
	
	
}