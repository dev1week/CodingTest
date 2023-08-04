import java.io.*; 

import java.util.*; 


class Point {
	int x, y;
	
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Main
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static int n; 
	static int m; 
	static int[][] map;
	static int result; 
	static ArrayList<Integer> logs; 
	static final int CHEEZE = 1;
	static final int BLANK = 0; 
	static final int AIR = 2; 
	
	static int cheezeCount;
	static int stage = 0; 
	
	static void init() throws IOException{
		//치즈 개수 세기 
		logs = new ArrayList<>(); 
		logs.add(-1); 
		
		tokens = new StringTokenizer(buffer.readLine()); 
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken()); 
		
		map = new int[n][m]; 
		
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine());
			for(int y=0; y<m; y++) {
				map[x][y] = Integer.valueOf(tokens.nextToken()); 
				if(map[x][y] == CHEEZE) {
					cheezeCount++; 
				}
			}
		}
		
	}
	
	static final int[] dx = {-1,1,0,0};
	static final int[] dy = {0,0,-1,1}; 
	
	static boolean inRange(int x, int y) {
		return x>=0&&y>=0&&x<n&&y<m; 
	}
	
	static boolean isMelted(int x, int y) {
		int count  =0; 
		for(int dir=0; dir<4; dir++) {
			int nX = x + dx[dir]; 
			int nY = y + dy[dir]; 
			
			if(inRange(nX, nY)) {
				if(map[nX][nY]==AIR) {
					count ++; 
				}
			}
		}
		return count>0; 
	}
	
	//새 지도 만들기 
		//모든 좌표 순회
			//치즈 인가?
				//접촉한 칸이 한칸이라도 있는지 확인 
					//해당 칸 삭제 
				//안녹는 칸이면 치즈 카운트 
	static int simulation() {
		int[][] tmp = new int[n][m]; 
		int count = 0; 
		for(int x=0; x<n; x++) {
			for(int y=0; y<m; y++) {
				if(map[x][y]==CHEEZE) {
					if(isMelted(x,y)) {
						tmp[x][y] = BLANK; 
					}else {
						tmp[x][y] = CHEEZE;
						count++; 
					}
				}else {
					tmp[x][y] = map[x][y]; 
				}
			}
		}
		
		map = tmp; 
		
		return count; 
	}
		
	
	
	//while 치즈 개수>0
		//스테이지 카운트 ++ 
		// 새 지도 만들면서 치즈 카운트  
		
	static boolean canGo(int x, int y, boolean[][] isVisited) {
		return inRange(x,y)&&!isVisited[x][y]&&map[x][y]!=CHEEZE; 
	}
	static void updateAir() {
		boolean[][] isVisited = new boolean[n][m]; 
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(0,0));
		isVisited[0][0] = true; 
		map[0][0] = AIR; 
		
		while(!que.isEmpty()) {
			Point cur = que.poll(); 
			for(int dir=0; dir<4; dir++) {
				int nX = cur.x + dx[dir];
				int nY = cur.y + dy[dir];
				if(canGo(nX, nY, isVisited)) {
					que.add(new Point(nX, nY));
					map[nX][nY] = AIR;
					isVisited[nX][nY] = true; 
				}
			}
		}
		
		
		
	}
	
	public static void main(String[] args)throws IOException{
		init();
		
		updateAir();
		logs.add(cheezeCount); 
		while(cheezeCount>0) {
			stage++; 
			updateAir(); 
			cheezeCount = simulation(); 
			logs.add(cheezeCount); 
		}
		System.out.println(stage);
		System.out.println(logs.get(logs.size()-2));
	}
	
	
	static void print(int[][] map) {
		for(int[] ma : map) {
			for(int m : ma) {
				System.out.print(m+" ");
			}System.out.println(); 
		}
	}
}