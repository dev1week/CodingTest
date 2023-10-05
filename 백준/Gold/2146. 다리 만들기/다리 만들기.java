

import java.io.*;
import java.util.*;


class Point{
	int x, y;
	
	public Point(int x, int y) {
		this.x =x;
		this.y = y; 
	}
	
	public String toString() {
		return this.x+":"+this.y; 
	}
}

 class Main {
	 
	
	 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static int[][] map; 
	static int n; 
	static int kind; 
	static boolean[][] isVisited; 
	
	static final int[] dx = {-1,1,0,0};
	static final int[] dy = {0,0,-1,1}; 
	static final int NOT_VISITED = -1; 
	
	static ArrayList<ArrayList<Point>> grounds; 
	
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine()); 
		map = new int[n][n]; 
		isVisited = new boolean[n][n]; 
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine());
			for(int y=0; y<n; y++) {
				map[x][y] = Integer.valueOf(tokens.nextToken()); 
			}
		}
		
		grounds = new ArrayList<ArrayList<Point>>();
		grounds.add(new ArrayList<Point>());
	}
	
	static void fill(Point start, int kind) {
		ArrayDeque<Point> que = new ArrayDeque<>();
		
		que.add(start);
		isVisited[start.x][start.y] = true; 
		
		
		
		while(!que.isEmpty()) {
			Point cur = que.poll(); 
			grounds.get(kind).add(cur); 
			map[cur.x][cur.y] = kind; 
			for(int dir=0; dir<4; dir++) {
				int nX = cur.x + dx[dir];
				int nY = cur.y + dy[dir]; 
				
				if(canFill(nX, nY)) {
					isVisited[nX][nY] = true; 
					que.add(new Point(nX, nY)); 
				}
			}
		}
	}
	
	static boolean canFill(int x, int y) {
		return inRange(x, y)&& map[x][y]==1 &&!isVisited[x][y];
	}
	
	static boolean inRange(int x, int y) {
		return x>=0&&y>=0&&x<n&&y<n;
	}
	
	//플로이드 필로 각 육지 표시 
	public static void main(String[] args )throws IOException{
		init();
		kind = 0; 
		
		for(int x=0; x<n; x++) {
			for(int y=0; y<n; y++) {
				if(map[x][y]==1 &&!isVisited[x][y]) {
					grounds.add(new ArrayList<Point>());
					kind++; 
					fill(new Point(x, y), kind); 
				}
			}
		}
		
		int result = Integer.MAX_VALUE; 
		for(int i=1; i<=kind; i++) {
			//각 대륙에서부터 시작했을 때 distance 배열 채우기 
			
			result = Math.min(result, findMinDistance(i));
		}
		
		System.out.println(result);
		
	}
	
	static int findMinDistance(int kind) {
		int tmpDistance = Integer.MAX_VALUE; 
		int[][] distance = new int[n][n];
		
		for(int x=0; x<n; x++) {
			Arrays.fill(distance[x], NOT_VISITED);
		}
		
		ArrayList<Point> starts = grounds.get(kind); 
		
		ArrayDeque<Point> que = new ArrayDeque<>();
		
		
		for(Point start: starts) {
			que.add(start);
			distance[start.x][start.y] = 0; 
		}
		
		while(!que.isEmpty()) {
			Point cur = que.poll(); 
			
			
			for(int dir=0; dir<4; dir++) {
				int nX = cur.x + dx[dir];
				int nY = cur.y + dy[dir]; 
				
				
				if(inRange(nX, nY)&&distance[nX][nY]==NOT_VISITED) {
					if(map[nX][nY]==0) {
						distance[nX][nY] = distance[cur.x][cur.y] +1; 
						que.add(new Point(nX, nY)); 
					}else if(map[nX][nY]!=kind) {
						distance[nX][nY] = distance[cur.x][cur.y] +1; 
						tmpDistance = Math.min(distance[cur.x][cur.y], tmpDistance);
						
					}
				}
				//지도 범위 내에 있는가 + 방문안했는가. 
					//다른 대륙에 도착했는가 
					//바다에 도착했는가
			}
		}
		return tmpDistance; 
	}
	
	static void print(int[][] arr) {
		for(int[] ar: arr) {
			for(int a: ar) {
				System.out.print(a+" ");
			}System.out.println();
		}
	}
	
	
}
