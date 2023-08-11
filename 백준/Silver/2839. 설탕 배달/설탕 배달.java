import java.io.*; 

import java.util.*; 

class Main
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static int n;  
	
	
	public static void main(String[] args) throws IOException{
		int count = 0; 
		
		n = Integer.valueOf(buffer.readLine()); 
		while(true) {
			//어처피 무게 종류 2개이므로 가장 큰값(5)으로 바로 나눠지는 경우 전부 더해주고 종료 
			if(n%5==0) {
				count += n/5;
				n = n/5; 
				break;
			}
			
			//될 때마다 -3해준다. 
			n-=3; 
			
			//3kg 봉지 하나 취한것과 같으므로 count++; 
			count ++; 
			// 진행 후 0kg일 경우 완벽하게 가져올 수 있는 경우 -> break 
			if(n==0) {
				break; 
			}//진행 후 음수로 갈 경우 완벽하게 가져올 수 없는 조합이므로 count -1로 설정 
			else if(n<0) {
				
				count=-1; 
				break; 
			}
		}
		System.out.println(count); 
	}
}