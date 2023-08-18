import java.io.*; 

import java.util.*; 

class Point {
	int x, y;
	
	public Point(int x, int y) {
		this.x =x;
		this.y =y;
	}
	
}

class Main
{	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	
	static int n; 
	static int m; 
	
	static char[][] map; 
	static boolean[][] isVisited; 
	static boolean[] isUsed; 
	static ArrayList<Character> select; 
	
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken()); 
		
		map = new char[n][m]; 
		
		for(int x=0; x<n; x++) {
			String line = buffer.readLine(); 
			for(int y=0; y<m; y++) {
				map[x][y] = line.charAt(y); 
			}
		}

	}
	
	static int result =0; 
	static HashSet<Character> routes; 
	static final int[] dx = {-1,1,0,0};
	static final int[] dy = {0,0,-1,1};

	static void dfs(int x, int y, int count) {
		
		result = Math.max(result, count);

		
		
		for(int dir=0; dir<4; dir++) {
			int nX = x+dx[dir];
			int nY = y+dy[dir]; 
			
			if(canGo(nX, nY)) {
				isVisited[nX][nY] = true;
				//isUsed[map[nX][nY]]= true;
				routes.add(map[nX][nY]);
				select.add(map[nX][nY]);
//				System.out.println(nX+" "+nY+" "+map[nX][nY]);
//				System.out.println("hash "+ routes);
//				System.out.println("list "+ select); 
				dfs(nX, nY, count+1);
				isVisited[nX][nY]= false; 
				isUsed[map[nX][nY]]= false;
				select.remove(select.size()-1); 
				routes.remove(map[nX][nY]);
			}

		}
	}
	
	static boolean inRange(int x, int y) {
		return x>=0&&x<n&&y>=0&&y<m; 
	}
	static boolean canGo(int x, int y) {
		return inRange(x,y)&&!routes.contains(map[x][y])&&!isVisited[x][y];
	}
	public static void main(String[] args)throws IOException{
		init(); 
		
		
				isVisited = new boolean[n][m];
				routes = new HashSet<>(); 
				select = new ArrayList<>();  
				isUsed = new boolean[1000]; 
				routes.add(map[0][0]); 
				select.add(map[0][0]);
				isVisited[0][0] = true;
				dfs(0,0, 1); 
			
		System.out.println(result); 
		
	}
	
}