import java.io.*;
import java.math.BigInteger;
import java.util.*; 

class Point{
	int x, y;
	
	public Point(int x, int y) {
		this.x=x;
		this.y = y;
	}
	
	public String toString() {
		return this.x+" : "+this.y; 
	}
}

class Solution
{	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	
	static int result;
	static int n; 
	static int[][] map; 
	
	
	static void init() throws IOException{
		result = 0;
		
		n = Integer.valueOf(buffer.readLine());
		
		map = new int[n][n]; 
		
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int y=0; y<n; y++) {
				map[x][y] = Integer.valueOf(tokens.nextToken());
			}
		}
		

	}
	
	static void print(int[][] arr) {
		for(int[] ar: arr) {
			for(int a: ar) {
				System.out.print(a+" ");
			}System.out.println(); 
		}
	}
	
	//탐색해보기 (가로 세로 길이)
		//해쉬맵 생성 
		//4방향 
			//홀수 일 경우 : 세로를 도는 경우 
			//짝수일 경우  : 가로를 도는 경우
		//해쉬맵 크기 반환
	
	static final int dx[] = {1,1,-1,-1};
	static final int dy[] = {1,-1,-1,1};
	static boolean inRange(int x, int y) {
		return x>=0&&y>=0&&x<n&&y<n; 
	}
	
	static int search(int x, int y, int garo, int sero) {
		HashSet<Integer> kinds = new HashSet<>(); 
		ArrayList<Point> route = new ArrayList<>(); 
		int nX = x;
		int nY = y; 
//		System.out.print(nX+" : "+ nY+" 시작 "); 
		for(int dir=0; dir<4; dir++) {
			//가로 
			if(dir%2==0) {
				for(int g=1; g<=garo; g++) {
					nX += dx[dir];
					nY += dy[dir]; 
//					System.out.print(nX+" "+nY+" => ");
					if(inRange(nX, nY)&&!kinds.contains(map[nX][nY])) {
						kinds.add(map[nX][nY]);
						route.add(new Point(nX, nY));
					}else {
//						System.out.println("종료");
						return 0; 
					}
				}
//				System.out.println("가로 끝"); 
			}
			//세로
			else if(dir%2==1) {
				for(int s=1; s<=sero; s++) {
					nX += dx[dir];
					nY += dy[dir];
//					System.out.print(nX+" "+nY+" => ");
					if(inRange(nX, nY)&&!kinds.contains(map[nX][nY])) {
						kinds.add(map[nX][nY]);
						route.add(new Point(nX, nY));
					}else {
//						System.out.println("종료");
						return 0; 
					}
				}
				//System.out.println("세로 끝");
			}
			
			
		}
//		System.out.println(x+" "+y);
//		System.out.println(route); 
		return kinds.size();
	}
	
	
	//시작점에서 출발 
		//가로 1~n-1 
			//세로 1~n-1 
				//해당 가로 세로 길이로 탐색해보기 
	
	
	
	static int getMaxKind(int x, int y) {
		int max = 0; 
		for(int garo=1; garo<=n-1; garo++) {
			for(int sero=1; sero<=n-1; sero++) {
				max = Math.max(max, search(x, y, garo, sero)); 
			}
		}
		
		return max; 
	}
	
	
	static boolean isSide(int x, int y) {
		return y==0||y==n-1; 
	}
	
	public static void main(String[] args)throws IOException{
		int t = Integer.valueOf(buffer.readLine()); 
		
		StringBuilder sb = new StringBuilder();
		
		for(int test=1; test<=t; test++) {
			init();
			for(int x=0; x<n; x++) {
				for(int y=0; y<n; y++) {
					if(isSide(x, y))continue; 
					result = Math.max(result, getMaxKind(x,y));
				}
			}
			if(result == 0) {
				result = -1; 
			}
			
			sb.append("#").append(test).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb); 
	}
}