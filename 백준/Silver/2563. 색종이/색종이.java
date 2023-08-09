import java.io.*; 

import java.util.*; 

class Main
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static final int SIZE = 100; 
	static final int BLACK = 1; 
	
	static int n; // 색종이의 수 ~100
	
	static int[][] paper = new int[SIZE][SIZE]; 
	
	
	//색종이 왼쪽변, 도화지 왼쪽변 사이의 거리 
	//색종이 아래쪽 변, 도하지 아래쪽 변 사이의 거리 
	public static void main(String[] args) throws IOException{
		int paperNum = Integer.valueOf(buffer.readLine()); 
		
		for(int p =0; p<paperNum; p++) {
			tokens = new StringTokenizer(buffer.readLine());
			int y = Integer.valueOf(tokens.nextToken());
			int x = Integer.valueOf(tokens.nextToken()); 
			for(int offsetX=0; offsetX<10; offsetX++) {
				for(int offsetY=0; offsetY<10; offsetY++) {
					paper[x+offsetX][y+offsetY] = BLACK;
				}
 
			}
		}

		System.out.println(getSize());
		
	}
	
	static int getSize() {
		int count = 0; 
		for(int[] pa :paper) {
			for(int p : pa) {
				if(p==BLACK) {
					count++;
				}
			}
		}
		return count; 
	}
	static void print(int[][] arr) {
		for(int[] a :arr) {
			System.out.println(Arrays.toString(a)); 
		}
	}
	
	
}