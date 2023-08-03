import java.io.*; 

import java.util.*; 




class Main
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static final int A =0;
	static final int C = 1;
	static final int G = 2;
	static final int T =3; 
	
	static int[] counts; 
	static int[] limits; 
	static char[] dna; 
	static int p; 
	static int n; 
	static int result; 
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine());
		n = Integer.valueOf(tokens.nextToken()); 
		p = Integer.valueOf(tokens.nextToken()); 
		limits = new int[4]; 
		counts = new int[4]; 
		
		String data = buffer.readLine();
		
		dna = data.toCharArray(); 
		
		tokens = new StringTokenizer(buffer.readLine()); 
		
		for(int i=0; i<4; i++) {
			limits[i] = Integer.valueOf(tokens.nextToken()); 
		}
		result = 0; 
	}
	
	public static void main(String[] args)throws IOException{
		init();
		//처음 p개 길이의 카운트 세기 
		for(int i=0; i<p; i++) {
			if(dna[i]=='A') {
				counts[A]++; 
			}else if(dna[i]=='C') {
				counts[C] ++; 
			}else if(dna[i]=='G') {
				counts[G]++; 
			}else if(dna[i]=='T') {
				counts[T]++; 
			}
		}
		if(isChecked()) {
			result++;
		}
		int startIdx = 1;

		for(int endIdx=p; endIdx<n; endIdx++) {

			
			
			char data = dna[startIdx-1];
			if(data =='A') {
				counts[A]--;
			}else if(data=='C') {
				counts[C]--;
			}else if(data=='G') {
				counts[G]--;
			}else if(data=='T') {
				counts[T]--; 
			}
			
			data = dna[endIdx];
			if(data =='A') {
				counts[A]++;
			}else if(data=='C') {
				counts[C]++;
			}else if(data=='G') {
				counts[G]++;
			}else if(data=='T') {
				counts[T]++; 
			}
			if(isChecked()) {
				result++; 
			}

			startIdx++;

			
		}
		
		System.out.println(result); 
		//스타트 idx = 0;
		//끝 idx = p-1; 
		
		//p-1~dna의 끝까지	
			//스타트 idx ++ 
			//스타트 idx-1의 글자 카운트 감소 
			//끝 idx의 글자 카운트 증가 
			//카운트 순회 -> 조건에 맞는지 확인 -> 맞으면 전체 result ++

	}
	static boolean isChecked() {
		for(int i=0; i<4; i++) {
			if(counts[i]<limits[i]) {
				return false;
			}
		}return true; 
	}
	
}