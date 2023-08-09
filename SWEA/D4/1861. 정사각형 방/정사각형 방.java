import java.io.*; 

import java.util.*; 
class Point {
	int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y; 
	}
	
	public String toString() {
		return (x+1)+" "+(y+1); 
	}
}

class Solution
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static int n;
	static int[][] map; 
	static boolean[][] isVisited; 
	static int maxRoomCount ;
	static int maxRoomNum;
	
	static final int[] dx = {-1,1,0,0};
	static final int[] dy = {0,0,-1,1}; 
	
	static void init() throws IOException{
		maxRoomCount = -1; 
		 maxRoomNum = Integer.MAX_VALUE; 
		n = Integer.valueOf(buffer.readLine());
		map = new int[n][n];
		
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int y=0; y<n; y++) {
				map[x][y] = Integer.valueOf(tokens.nextToken());
			}
		}
		
	}
	static boolean inRange(int x, int y) {
		return x>=0&&x<n&&y>=0&&y<n; 
	}
	
	static boolean canGo(int x, int y, int num) {
		return inRange(x,y)&&num+1==map[x][y]&&!isVisited[x][y];
	}
	
	static int getRoomNum(Point start) {
		int count = 1;
		Queue<Point> que = new ArrayDeque<>(); 
		que.add(start);
		isVisited[start.x][start.y] = true; 
		
		while(!que.isEmpty()) {
			
			Point cur = que.poll(); 
			for(int dir=0; dir<4; dir++) {
				int nX = cur.x + dx[dir];
				int nY = cur.y + dy[dir]; 
				
				if(canGo(nX, nY, map[cur.x][cur.y])) {
					que.add(new Point(nX, nY)); 
					isVisited[nX][nY] = true; 
					count ++; 
				}
			}
		}
		return count; 
		
		
	}
	public static void main(String[] args) throws IOException{
		int T = Integer.valueOf(buffer.readLine());
		
		StringBuilder sb = new StringBuilder(); 
		for(int testCase =1; testCase<=T; testCase++) {
			init(); 
			
			
			for(int x=0; x<n; x++) {
				for(int y=0; y<n; y++) {
					isVisited = new boolean[n][n]; 
					int roomCount = getRoomNum(new Point(x,y)); 
					if(maxRoomCount<roomCount) {
						maxRoomCount = roomCount; 
						maxRoomNum = map[x][y]; 
					}else if(maxRoomCount == roomCount &&maxRoomNum>map[x][y]) {
						maxRoomCount = roomCount; 
						maxRoomNum = map[x][y]; 
					}
				}
			}
			//map 전체 순회 
				//시작점 지정 
				//방의 개수 반환 얼마나 갈 수 있는지 확인
				//최대값 < 방의 개수 
					//결과 좌표 반환 
			
			sb.append("#").append(testCase).append(" ").append(maxRoomNum).append(" ").append(maxRoomCount).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
}