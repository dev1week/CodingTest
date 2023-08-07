import java.io.*; 

import java.util.*; 


class Main
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	
	static int n; 
	static int k; 
	
	static ArrayList<Integer> circle;

	
	static int convertIdx(int idx) {
		if(idx>circle.size()-1) {
			return 1;
		}else {
			return idx;
		}
	}
	
	public static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine());
		n = Integer.valueOf(tokens.nextToken());
		k= Integer.valueOf(tokens.nextToken());

		circle = new ArrayList<>(); 

		circle.add(0); 
		
		for(int i=1; i<=n; i++) {
			circle.add(i); 
		}
	}
	

	
	
	 
	
	
	public static void main(String[] args) throws IOException{
		
		init(); 
		int startIdx = 0; 
		//circle.size() != 1 
			// 3회 반복 
				// startIdx ++; 
				// convert(startIdx);
				// 리스트에서 가져와서 큐에 저장 
				// 리스트에서 삭제 
		StringBuilder sb = new StringBuilder(); 
		sb.append("<");
		
		while(circle.size()!=1) {
			for(int i=0; i<k; i++) {
				startIdx++; 
				startIdx = convertIdx(startIdx);
			}
			if(circle.size()==2) {
				sb.append(circle.get(startIdx));
			}else {
				sb.append(circle.get(startIdx)).append(", ");
				
			}
			
			circle.remove(startIdx);
			startIdx--; 
		}
	
		
		sb.append(">");
		System.out.println(sb); 

		
	}
	
	
	
}