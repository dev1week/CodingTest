import java.io.*; 
import java.util.*; 
class Main
{	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static int[][] map;
	static int[][] dp; 
	static int n; 
	static int m; 
	
	static final int[] dx = {-1,0,-1};
	static final int[] dy = {0,-1,-1}; 
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken()); 
		
		
		map = new int[n][m]; 
		dp = new int[n][m];
		
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int y=0; y<m; y++) {
				map[x][y] = Integer.valueOf(tokens.nextToken()); 
			}
		}
		
		dp[0][0] = map[0][0]; 
		
	}
	
	static boolean inRange(int x, int y) {
		return x>=0&&x<n&&y>=0&&y<m; 
	}
	
	public static void main(String[] args) throws IOException{
		init(); 
		
		for(int x=0; x<n; x++) {
			for(int y=0; y<m; y++) {
				
				int tmp = 0; 
				
				for(int dir=0; dir<3; dir++) {
					int nX = x+ dx[dir];
					int nY = y+ dy[dir]; 
					if(inRange(nX, nY)) {
						tmp = Math.max(tmp, dp[nX][nY]);
					}
				}
				dp[x][y] = tmp+map[x][y]; 
			}
		}
		
		//dp[x][y] = Math.max(dp[x][y-1], dp[x-1][y], dp[x-1][y-1])+map[x][y]; 
		
		System.out.println(dp[n-1][m-1]); 
		
	}
}