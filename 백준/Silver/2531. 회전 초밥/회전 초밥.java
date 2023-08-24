

import java.io.*;
import java.util.*; 

public class Main {
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	//임의의 위치로부터 k개를 연속으로 먹을 경우 할인된 정액 가격으로 제공 
	
	//각 고객에게 초밥의 종류가 하나 쓰인 쿠폰 발행 
	
	//k개 연속으로 먹을 경우 적혀진 종류의 초밥을 먹을 경우 적혀진 종류의 초밥하나를 무료로 제공한다. 
	
		//현재 벨트에 없을 경우 새로 만들어서 손님에게 제공한다. 
	
	static int n; //접시의 수   
	static int d; //초밥의 가짓수 
	static int k; //연속해서 먹을 경우 
	static int c; //쿠폰 번호 

	static int[] shshi;
	
	static HashMap<Integer, Integer> kind; 
	public static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		
		n = Integer.valueOf(tokens.nextToken());
		d = Integer.valueOf(tokens.nextToken());
		k = Integer.valueOf(tokens.nextToken()); 
		c = Integer.valueOf(tokens.nextToken()); 
		
		shshi = new int[n]; 
		
		
		kind = new HashMap<>();
		shshi = new int[n]; 
		
		for(int i=0; i<n; i++) {
			shshi[i] = Integer.valueOf(buffer.readLine()); 
		}

		
		
		
	}
	
	
	public static void main(String[] args) throws IOException{
		init();
		int result = 0; 
		//0에서부터 k 개 사전등록해놓기 
		for(int i=0; i<k; i++) {
			kind.put(shshi[i], kind.getOrDefault(shshi[i], 0)+1);
		}
		
		
		kind.put(c, kind.getOrDefault(c, 0)+1); 
		
		if(kind.get(c)==1) {
			kind.remove(c);
		}else {
			kind.put(c, kind.get(c)-1);
		}
		
		for(int start = 0; start<n; start++) {
			if(kind.get(shshi[start])==1) {
				kind.remove(shshi[start]);
			}else {
				kind.put(shshi[start], kind.get(shshi[start])-1);
			}
			int tail = convert(start+k); 
			//System.out.println(tail + " "+shshi[tail]); 
			kind.put(shshi[tail],kind.getOrDefault(shshi[tail], 0)+1);
			
			kind.put(c, kind.getOrDefault(c, 0)+1); 
			result = Math.max(result, kind.size());
			//System.out.println(kind); 
			if(kind.get(c)==1) {
				kind.remove(c);
			}else {
				kind.put(c, kind.get(c)-1);
			}
			
		}
		System.out.println(result);
	}
	
	static int convert(int num) {
		if(num>=n) {
			return num%n; 
		}else {
			return num;
		}
		
	}
}
