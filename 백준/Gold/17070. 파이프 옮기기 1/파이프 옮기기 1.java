import java.io.*; 
import java.util.*; 
class Main
{	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	//가로 세로 대각선 
	static final int[] dx = {0,1,1};
	static final int[] dy = {1,1,0};
	
	static int[][][] dp; 
	static int[][] map; 
	static int n; 
	
	
	static final int BLOCK =1; 
	
	
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine());
		map = new int[n][n]; 
		
		dp = new int[3][n][n];
		
		
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int y=0; y<n; y++) {
				map[x][y] = Integer.valueOf(tokens.nextToken()); 
			}
		}
		
		//0,1에 가로로 놓는 경우의 수
		for(int y=1; y<n; y++) {
			if(map[0][y]==BLOCK)break; 
			dp[0][0][y] = 1;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		init();
		
		for(int x=1; x<n; x++) {
			for(int y=2; y<n; y++) {
				if(map[x][y]==BLOCK)continue; 
				for(int dir=0; dir<3; dir++) {
					int pX = x-dx[dir];
					int pY = y-dy[dir];
					if(canGo(pX, pY)) {
						if(dir==0) {
							dp[dir][x][y] = dp[0][pX][pY]+dp[1][pX][pY]; 
						}else if(dir==1) {
							if(canGo(pX+1,pY)&&canGo(pX,pY+1)) {
								dp[dir][x][y] = dp[0][pX][pY] + dp[1][pX][pY] + dp[2][pX][pY];
							}
						}else if(dir==2) {
							
								dp[dir][x][y] = dp[1][pX][pY]+dp[2][pX][pY]; 	
									
						}
					}
				}
			}
		}
		
		System.out.println(dp[0][n-1][n-1]+dp[1][n-1][n-1]+dp[2][n-1][n-1]); 
		
	}
	static int result = 0; 
	
	static final char[] dirs = {'-','|','/'}; 
	
	static boolean canGo(int x,int y) {
		return inRange(x,y)&&map[x][y]!=BLOCK; 
	}
	
	static boolean inRange(int x, int y) {
		return x>=0&&y>=0&&x<n&&y<n;
	}
}