import java.io.*;
import java.util.*; 

class Point{
	int x, y; 
	public Point(int x, int y) {
		this.x = x;
		this.y = y; 
	}
}

public class Main {
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	//죽음의 구역 
		//안에서 움직일 때마다 생명 1씩 소모 
	
	static final int[] dx = {-1,1,0,0};
	static final int[] dy = {0,0,-1,1}; 
	
	//잃는 생명의 최솟값 구하기 
	static final int BLOCK = 1;
	static final int BLANK = 0;
	static final int NOT_VISITED = -1; 
	
	//제일 왼쪽 위의 한점을 기준으로 
	static int[][] map; 
	static int[][] distance; 
	
	static int n; 
	static int m;
	static int unitW;
	static int unitH; 
	
	static Point start;
	static Point end; 
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken());
		unitH = Integer.valueOf(tokens.nextToken());
		unitW = Integer.valueOf(tokens.nextToken());
		
		int k = Integer.valueOf(tokens.nextToken());
		map = new int[n][m]; 
		distance = new int[n][m]; 
		
		
		for(int[] dis : distance) {
			Arrays.fill(dis, NOT_VISITED); 
		}
		
		for(int i=0; i<k; i++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			int blockX = Integer.valueOf(tokens.nextToken())-1;
			int blockY = Integer.valueOf(tokens.nextToken())-1;
			map[blockX][blockY] = BLOCK; 
		}
		
		tokens = new StringTokenizer(buffer.readLine());
		start = new Point(Integer.valueOf(tokens.nextToken())-1, Integer.valueOf(tokens.nextToken())-1);
		
		tokens = new StringTokenizer(buffer.readLine());
		end = new Point(Integer.valueOf(tokens.nextToken())-1, Integer.valueOf(tokens.nextToken())-1);
		
	}
	
	static boolean inRange(int x, int y) {
		if(x<0||x>=n||y<0||y>=m) return false; 
		for(int offsetX=0; offsetX<unitH; offsetX++ ) {
			for(int offsetY=0; offsetY<unitW; offsetY++) {
				if(x+offsetX<0||y+offsetY<0||x+offsetX>=n||y+offsetY>=m) {
					return false; 
				}
			}
		}
		
		return true; 
	}
	
	static boolean isBlank(int x, int y) {
		for(int offsetX=0; offsetX<unitH; offsetX++) {
			for(int offsetY=0; offsetY<unitW; offsetY++) {
				if(map[x+offsetX][y+offsetY]==BLOCK) return false;  
			}
		}
		return true; 
	}
	
	
	static boolean canGo(int x, int y) {
		return inRange(x, y)&&isBlank(x,y)&&distance[x][y] == NOT_VISITED; 
	}
	
	static void bfs(Point start) {
		Queue<Point> que = new LinkedList<>(); 
		
		distance[start.x][start.y] = 0;
		que.add(start);
		
		while(!que.isEmpty()) {
			Point cur = que.poll();
			
			for(int dir=0; dir<4; dir++) {
				int nX = cur.x + dx[dir];
				int nY = cur.y + dy[dir]; 
				
				if(canGo(nX, nY)) {
					que.add(new Point(nX, nY));
					distance[nX][nY] = distance[cur.x][cur.y] +1; 
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		init(); 
		
		bfs(start); 
		
		System.out.println(distance[end.x][end.y]); 
	}
}
