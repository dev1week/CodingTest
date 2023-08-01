import java.io.*; 

import java.util.*; 


class Main
{
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static final int FEMALE = 2;
	static final int MALE =1; 
	static boolean[] isTurnOn; 
	static int size; 
	static int studentNum; 
	
	
	//남학생 
		//자기가 받은 수의 배수 = 스위치 번호 
			//상태를 바꾼다. 
	//여학생 
		//자기가 받은 수와 같은 번호가 붙은 스위치를 중심 
		//좌우가 대칭이면서
		//가장 많은 스위치를 포함하는 구간 
			//그 구간에 속한 스위치의 상태를 모두 바꾼다. 
	
	public static void main(String[] args) throws IOException{
		size = Integer.valueOf(buffer.readLine());
		isTurnOn = new boolean[size+1];
		tokens = new StringTokenizer(buffer.readLine()); 
		for(int idx=1; idx<=size; idx++) {
			int data = Integer.valueOf(tokens.nextToken());
			if(data==1) {
				isTurnOn[idx] = true;
			}else if(data==0) {
				isTurnOn[idx] = false; 
			}
		}
		
		studentNum = Integer.valueOf(buffer.readLine()); 
		
		for(int student=0; student<studentNum; student++) {
			tokens = new StringTokenizer(buffer.readLine());
			int gender = Integer.valueOf(tokens.nextToken());
			int num = Integer.valueOf(tokens.nextToken());
			
			process(gender, num); 
			
		}
		System.out.println(print(isTurnOn)); 
		
	}
	
	static void switchByMale(int num) {
		for(int idx=1; idx<=size; idx++) {
			if(idx%num==0) {
				isTurnOn[idx] = !isTurnOn[idx]; 
			}
		}
	}
	
	static void process(int gender, int num) {
		if(gender==MALE) {
			switchByMale(num); 
		}else if(gender==FEMALE) {
			switchByFeMale(num);
		}
	}
	
	static boolean inRange(int left, int right) {
		return left>=1&&right<=size;
	}
	
	static void switchByFeMale(int num) {
		int left = num-1;
		int right = num+1; 
		
		 
		while(inRange(left, right)) {
			 
			if(isTurnOn[left]!=isTurnOn[right]) {
				break; 
			}
			left--;
			right++;
			
		}
		for(int change=left+1; change<right; change++ ) {
			isTurnOn[change] = !isTurnOn[change]; 
		}
	}
	
	static String print(boolean[] arr) {
		StringBuilder result = new StringBuilder(); 
		for(int idx=1; idx<arr.length; idx++) {
			if(arr[idx]) {
				result.append(1+" ");
			}else {
				result.append(0+" ");
			}
			if(idx%20==0) {
				result.append("\n");
			}
		}
		return result.toString(); 
	}
	
}