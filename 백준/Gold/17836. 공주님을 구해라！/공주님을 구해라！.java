import java.io.*; 
import java.util.*; 

class Point{
	int x, y;
	
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y; 
	}
	
	public String toString() {
		return x+" : "+y; 
	}
}

class Main
{
	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	
	static StringTokenizer tokens;
	
	
	static int[][] map;
	static int[][] distance;
	
	static final int NOT_VISITED = Integer.MAX_VALUE; 
	static final int BLOCK = 1; 
	static final int SWORD = 2; 
	
	static int n; 
	static int m; 
	static int T; 
	
	static Point sword; 
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine());
		
		n = Integer.valueOf(tokens.nextToken()); 
		m = Integer.valueOf(tokens.nextToken());
		T  = Integer.valueOf(tokens.nextToken());
		
		map = new int[n][m]; 
		distance = new int[n][m]; 
		
		for(int[] dis : distance) {
			Arrays.fill(dis, NOT_VISITED);
		}
		
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int y=0; y<m; y++) {
				map[x][y]  = Integer.valueOf(tokens.nextToken());
				if(map[x][y]==SWORD) {
					sword = new Point(x,y); 
				}
			}
		}
		
	 
		
		
	}
	
	static final int[] dx = {-1,1,0,0};
	static final int[] dy = {0,0,-1,1};
	
	static boolean inRange(int x, int y) {
		return x>=0&&y>=0&&x<n&&y<m; 
	}
	
	static boolean canGo(int x, int y) {
		return inRange(x, y)&&map[x][y] != BLOCK && distance[x][y] == NOT_VISITED; 
	}
	
	static void bfs() {
		Queue<Point> que = new LinkedList<>(); 
		que.add(new Point(0,0));
		distance[0][0] = 0; 
		
		while(!que.isEmpty()) {
			Point cur = que.poll(); 
			for(int dir=0; dir<4; dir++) {
				int nX = cur.x + dx[dir];
				int nY = cur.y + dy[dir]; 
				
				if(canGo(nX, nY)) {
					que.add(new Point(nX, nY));
					distance[nX][nY] = 0; 
					distance[nX][nY] = distance[cur.x][cur.y] +1; 
				}
			}
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		init();
		
		bfs(); 
		
		int totalTime = distance[n-1][m-1]; 

		if(distance[sword.x][sword.y]!=NOT_VISITED) {
			totalTime = Math.min(totalTime,  distance[sword.x][sword.y] + Math.abs(sword.x-(n-1))+ Math.abs(sword.y-(m-1)));
		}
		//BFS로 디스턴스 배열 채우기 
		
		
		if(totalTime<=T) {
			System.out.println(totalTime);
		}else {
			System.out.println("Fail");
		}
		//그람이 있는 공간에 디스턴스가 != NOT_VISITED인지 확인하기 
			//방문했다면 좌표 바로 빼서 최소거리 업데이트 

	}
	
	static void print(int[][] arr) {
		for(int[] ma: arr) {
			for(int m : ma) {
				System.out.print(m+" ");
			}System.out.println(); 
		}
	}
}