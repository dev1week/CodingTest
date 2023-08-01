import java.io.*; 

import java.util.*; 



class Solution
{
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	//평탄화 모두 수행 후  
		//가장 높은 곳과 가장 낮은 곳의 차이가 최대 1이내 
	
	
	//제한된 횟수만큼 옮기는 작업을 한 후 최고점과 최저점의 차이를 반환 
	
	
	//덤프 
		//가장 높은 곳에 있는 상자를 가장 낮은 곳으로 옮기기 
	
	//가로의 길이 100 
	
	static int T; 
	static final int SIZE = 100; 
	static int[] boxes; 
	static int dumpNum; 
	
	static void init() throws IOException{
		
		boxes = new int[SIZE];
		dumpNum = Integer.valueOf(buffer.readLine()); 
		tokens = new StringTokenizer(buffer.readLine()); 
		for(int i=0; i<SIZE; i++) {
			boxes[i] = Integer.valueOf(tokens.nextToken()); 
		}
		
	}
	
	static void dump(){
		Arrays.sort(boxes);
		boxes[0]++;
		boxes[SIZE-1]--; 
		Arrays.sort(boxes);
	}
	
	public static void main(String[] args)throws IOException{
		T = 10; 
		StringBuilder sb = new StringBuilder();
		for(int test=1; test<=T; test++) {
			init(); 
			
			int result = Integer.MAX_VALUE; 
			
			for(int round=0; round<dumpNum; round++) {
				
				dump(); 
				
				result = boxes[99] - boxes[0];
				
				if(result<=1) {
					break; 
				}
			}
			
			
			sb.append("#").append(test).append(" ").append(result).append("\n"); 
		}
		System.out.println(sb); 
		//테스트 케이스 반복 
			//덤프횟수 입력받기 
			//상자 높이값 입력 받기 
		
			//시간복잡도 m*nlogn -> 1000*100log100 
			//덤프 
				//상자 높이 정렬하기 
				//첫번째 요소 -- 
				//마지막 요소 ++
				//상자 높이 정렬하기
				//최고점과 최저점 높이 차 계산해보기 1이내면 break 
			
	}
	
}