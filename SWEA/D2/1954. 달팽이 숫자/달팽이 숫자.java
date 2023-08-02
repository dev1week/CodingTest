import java.io.*; 

import java.util.*; 



class Solution
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static final int[] dx = {0,1,0,-1};
	static final int[] dy = {1,0,-1,0}; 
	
	static boolean canGo(int x, int y, int n) {
		return x>=0&&y>=0&&x<n&&y<n&&!isVisited[x][y]; 
	}
	static boolean[][] isVisited; 
	static int[][] makeMap(int n){
		int[][] map = new int[n][n]; 
		isVisited = new boolean[n][n]; 
		int count =1;
		int tmp = 0; 
		int x= 0;
		int y = 0; 
		int dir = 0; 
		isVisited[0][0] = true; 
		map[0][0] = count; 
		while(count<=n*n&&tmp<300) {
			int nX = x + dx[dir];
			int nY = y + dy[dir];
			if(canGo(nX, nY, n)) {
				count++;
				map[nX][nY] = count;
				x = nX;
				y = nY; 
				isVisited[nX][nY]= true; 
			}else {
				dir ++;
				if(dir>3) {
					dir=0; 
				}
			}
			tmp++; 
		}
		
		
		return map; 
	}
	
	static StringBuilder result = new StringBuilder(); 
	public static void main(String[] args)throws IOException{
		int T = Integer.valueOf(buffer.readLine()); 
		
		for(int testCase =1; testCase<=T; testCase++) {
			int n= Integer.valueOf(buffer.readLine()); 
			int[][] map = makeMap(n);
			result.append("#").append(testCase).append("\n");
			print(map);
		}
		System.out.println(result); 
		
	}
	
	
	static void print(int[][] arr) {
		for(int[] ar : arr) {
			for(int a: ar) {
				result.append(a+" ");
			}result.append("\n"); 
		}
	}
	
	
}