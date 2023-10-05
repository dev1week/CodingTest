
import java.io.*;
import java.util.*;



class Point{
	int x, y, size;
	public Point(int x, int y, int size) {
		this.x = x;
		this.y = y; 
		this.size = size; 
	}
	
	public String toString() {
		return this.x+":"+this.y+" 범위는 "+this.size; 
	}
}
 class Solution {

	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	
	static int[][] map;
	static int[][] nextMap; 
	static int n;
	static int w;
	static int h; 
	
	static ArrayList<Integer> selecteds; 
	static int score; 
	static int total; 
	static int maxScore; 
	
	static void init() throws IOException{
		total = 0; 
		score = 0;
		maxScore = 0; 
		tokens = new StringTokenizer(buffer.readLine()); 
		n = Integer.valueOf(tokens.nextToken()); 
		w = Integer.valueOf(tokens.nextToken());
		h = Integer.valueOf(tokens.nextToken()); 
		
		map = new int[h][w]; 
		
		for(int x=0; x<h; x++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int y=0; y<w; y++) {
				map[x][y] = Integer.valueOf(tokens.nextToken());
				if(map[x][y]!=0) {
					total++; 
				}
			}
		}
		selecteds = new ArrayList<>(); 
		
	}
	
	static void bt(int cur) {
		if(cur==n) {
			simulation(); 
			return;
		}
		
		
		for(int i=0; i<w; i++) {
			selecteds.add(i);
			bt(cur+1);
			selecteds.remove(selecteds.size()-1);
		}
		
		
		
	}
	
	static void print(int[][] arr) {
		for(int[] ar: arr) {
			for(int a: ar) {
				System.out.print(a+" ");
			}System.out.println(); 
		}
	}
	
	
	public static void main(String[] args)throws IOException{
		int T = Integer.valueOf(buffer.readLine()); 
		StringBuilder sb = new StringBuilder(); 
		for(int t=1; t<=T; t++) {
			init(); 
			

			bt(0);
			
			
			sb.append("#").append(t).append(" ").append(total-maxScore).append("\n"); 
		}
		System.out.println(sb); 
	}
	
	static void simulation() {
		
		nextMap = new int[h][w];
		for(int x=0; x<h; x++) {
			for(int y=0; y<w; y++) {
				nextMap[x][y] = map[x][y]; 
			}
		}
		score = 0 ; 
		for(int startY : selecteds) {
			int startX = findStartRow(startY); 
			if(startX==-1) continue;
			nextMap = bomb(startX, startY);

			nextMap = gravity();
		
		}
		maxScore = Math.max(maxScore, score); 
	}
	
	static int[][] gravity() {
		int[][] tmpMap = new int[h][w]; 
		for(int y=0; y<w; y++){
			int grdRow = h-1;
			int skyRow = 0; 

			for(int x=grdRow; x>=skyRow; x--) {
				if(nextMap[x][y]!=0) {
					tmpMap[grdRow][y] = nextMap[x][y]; 
					grdRow--; 
				}
			}
		}
		
		return tmpMap; 
	}
	
	
	static int findStartRow(int y) {
		for(int x=0; x<h; x++) {
			if(nextMap[x][y]!=0) {
				return x; 
			}
		}
		return -1; 
	}
	
	static final int[] dx = {-1,1,0,0};
	static final int[] dy = {0,0,-1,1}; 
	
	static int[][] bomb(int cx, int cy) {
		ArrayDeque<Point> que = new ArrayDeque<>();
		
		que.add(new Point(cx,cy, nextMap[cx][cy])); 
		nextMap[cx][cy] = 0; 
		
		while(!que.isEmpty()) {
			Point cur = que.poll();
			score++;
			for(int dir=0; dir<4; dir++) {
				for(int range=1; range<=cur.size; range++) {
					int nX = cur.x + dx[dir]*(range-1); 
					int nY = cur.y + dy[dir]*(range-1); 
					
					if(canBomb(nX, nY)) {
						que.add(new Point(nX, nY, nextMap[nX][nY])); 
						nextMap[nX][nY] = 0; 
					}
				}
			}
		}		
		return nextMap; 
	}
	
	static boolean canBomb(int x, int y) {
		return x>=0&&y>=0&&x<h&&y<w&&nextMap[x][y]!=0; 
	}
	//w중에서 n개 뽑는 중복 순열 12*12*12*12 
		//가장 위에서부터 시작해서 처음으로 빈칸이 아닌 곳 찾기 *~15  
			//없다면 RETURN
		//상하좌우 제거 배열 만들어놓기 
		//
	
	//터뜨리기
		//벽돌은 상하좌우로 벽돌에 적힌 숫자 -1칸만큼 같이 제거  	
			//bfs로 연쇄작용 구현 다음 맵 구하기
	//중력 작용 
		//중력 작용하기 
}
