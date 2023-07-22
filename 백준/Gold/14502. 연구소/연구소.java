

import java.io.*;
import java.util.*; 

class Point{
	int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y; 
	}
	
	public String toString() {
		return this.x+" "+this.y; 
	}
}

public class Main {
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	

	static final int BLANK = 0;
	static final int BLOCK = 1;
	static final int VIRUS = 2; 
	static final int FINAL =3;
	
	static final int[] dx = {-1,1,0,0};
	static final int[] dy = {0,0,-1,1};
	
	static int n; 
	static int m; 
	
	static int[][] map;
	static boolean[][] isVisited;
	
	static boolean[] isUsed; 
	
	static ArrayList<Point> candiates;
	static ArrayList<Point> origins; 
	
	static int[][] next; 
	static ArrayList<Point> selecteds; 
	static int result = Integer.MIN_VALUE; 
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine());
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken()); 
		
		map = new int[n][m];
		candiates = new ArrayList<>(); 
		selecteds = new ArrayList<>(); 
		origins = new ArrayList<>(); 
		
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int y=0;y<m; y++) {
				map[x][y] = Integer.valueOf(tokens.nextToken());
				if(map[x][y] == BLANK) {
					candiates.add(new Point(x,y)); 
				}
				
				if(map[x][y]== VIRUS) {
					origins.add(new Point(x,y)); 
				}
			}
		}
		isUsed = new boolean[candiates.size()];
	}
	
	
	static void print(int[][] arr) {
		for(int[] ar : arr) {
			for(int a : ar) {
				System.out.print(a+" ");
			}System.out.println(); 
		}
	}
	
	static int getSize(int x, int y) {
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(x,y));
		isVisited[x][y] = true; 
		int size = 1; 
		
		while(!que.isEmpty()) {
			Point cur = que.poll();
			for(int dir=0; dir<4; dir++) {
				int nX = cur.x+ dx[dir];
				int nY = cur.y + dy[dir]; 
				
				if(canGoSafe(nX, nY)) {
					que.add(new Point(nX, nY));
					isVisited[nX][nY] =true; 
					size++; 
				}
			}
		}
		return size; 
		
	}
	
	static boolean canGoSafe(int x, int y) {
		return inRange(x,y)&&next[x][y]==BLANK&&!isVisited[x][y]; 
	}
	
	static int getSafeZoneCount() {
		isVisited = new boolean[n][m];
		int size = 0; 
		for(int x=0; x<n; x++) {
			for(int y=0; y<m; y++) {
				if(next[x][y]==BLANK&&!isVisited[x][y]) {
					size += getSize(x, y); 
				}
			}
		}
		
		return size; 
	}
	
	static void bt(int cur, int last) {
		if(cur==FINAL +1) {
			next = new int[n][m];
			for(int x=0; x<n; x++) {
				next[x] = Arrays.copyOf(map[x], m);
			}
			
			for(Point s: selecteds) {
				next[s.x][s.y] = BLOCK; 
			}
			
			
			setVirus();
			
			result = Math.max(result, getSafeZoneCount()); 
			
			
			
			return; 
		}
		
		for(int next= last+1; next<candiates.size(); next++) {
			if(!isUsed[next]) {
				isUsed[next] = true; 
				selecteds.add(candiates.get(next));
				bt(cur+1, next); 
				selecteds.remove(selecteds.size()-1); 
				isUsed[next] = false; 
			}
		}
		
		
	}
	
	
	static boolean inRange(int x, int y) {
		return x>=0&&x<n&&y>=0&&y<m;
	}
	
	static boolean virusCanGo(int x, int y) {
		return inRange(x,y)&&next[x][y]!=BLOCK&!isVisited[x][y];
	}
	
	static void setVirus() {
		Queue<Point> que = new LinkedList<>(); 
		isVisited = new boolean[n][m];
		for(Point v : origins) {
			que.add(v);
			isVisited[v.x][v.y] = true; 
		}
		
		
		while(!que.isEmpty()) {
			Point cur = que.poll(); 
			
			for(int dir=0; dir<4; dir++) {
				int nX = cur.x+ dx[dir];
				int nY = cur.y + dy[dir]; 
				
				if(virusCanGo(nX, nY)) {
					que.add(new Point(nX, nY));
					next[nX][nY] = VIRUS; 
					isVisited[nX][nY] = true; 
				}
			}
			
			
		}
	}
	
	
	public static void main(String[] args)throws IOException {
		
		init();
		bt(1,-1); 
		System.out.println(result); 
		//벽을 세울 3곳을 고르기 
			//다골랐다면 
				//바이러스를 퍼뜨리기 
				//전체 맵 순회, 0일 때마다 
					//bfs돌려서 넓이 구하기 
					//최대넓이 갱신하기 
	}
}
