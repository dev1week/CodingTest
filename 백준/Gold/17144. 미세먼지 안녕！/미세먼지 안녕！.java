

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
	
	

	static int n;
	static int m;
	static int t;
	
	static final int CLEANER = -1;
	
	static Point upCleaner;
	static Point downCleaner;
	
	//미세먼제 확산
		//확산 되는 양 정하기  
		//네 방향 확산
			//각 칸에 확산되는 양 뿌리기
			//먼지양 빼기
	
	
	//공기청정기
		//위쪽 공기청정기 바람은 반시계 방향
		//아래쪽 공기청정기 바람은 시계 방향
		//컨테이너 처럼 구현
		//공기 청정기 칸을 차지한 칸 모두 비우기  

	
	
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine());
		
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken());
		
		t = Integer.valueOf(tokens.nextToken());
		
		map = new int[n][m];
		
		boolean isUpCleanerHere = false;
		
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine());
			for(int y=0; y<m; y++) {
				map[x][y] = Integer.valueOf(tokens.nextToken());
				if(map[x][y] == CLEANER) {
					if(isUpCleanerHere) {
						downCleaner = new Point(x,y);
					}else {
						upCleaner = new Point(x,y);
						isUpCleanerHere = true;
					}
				}
			}
		}
		
		
	}
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static boolean canGo(int x, int y) {
		return inRange(x, y) && map[x][y]!=CLEANER;
	}
	
	static boolean inRange(int x, int y) {
		return x>=0&&y>=0&&x<n&&y<m;
	}

	static int[][] diffusion(){
		int next[][] = new int[n][m];
		
		for(int x=0; x<n; x++) {
			next[x] = Arrays.copyOf(map[x], m);
		}
		

		for(int x=0; x<n; x++) {
			for(int y=0; y<m; y++) {
				if(map[x][y]>1) {
					int tmp = map[x][y]/5;
					if(tmp ==0)continue;
					int count = 0;
					for(int dir=0; dir<4; dir++) {
						int nX = x+ dx[dir];
						int nY = y+ dy[dir];
						
						if(canGo(nX, nY)) {
							next[nX][nY] += tmp;
							count++;
						}
						
					}

					next[x][y] -= tmp*count;
							
				}
			}
		}
		
		return next;
		
		//전체맵 순회
			//1 보다 클 경우
				//해당 위치의 분산양 구하기
					//방향을 돌면서 갈 수 있는 곳 방문
						//갈 수 있으면
							//자기자신 - 분산양
							//해당칸 += 분산양
	}
	
	
	static int[][] activateCleaner(int[][] map){
		int[][] result = new int[n][m];
		
		for(int x=0; x<n; x++) {
			result[x] = Arrays.copyOf(map[x], m);
		}

		//첫번째 열 
		for(int x=upCleaner.x-1; x>=1; x--) {
			result[x][0] = map[x-1][0];
		}
		
		//첫번째 행
		for(int y=1; y<m; y++) {
			result[0][y-1] = map[0][y];
		}
		//마지막 열
		for(int x=0; x<upCleaner.x; x++) {
			result[x][m-1] = map[x+1][m-1];
		}
				
		//마지막행
		for(int y=m-1; y>upCleaner.y+1; y--) {
			result[upCleaner.x][y] = map[upCleaner.x][y-1];  
		}
		
		result[upCleaner.x][upCleaner.y+1] =0; 
	
		//첫번째 열
		for(int x=downCleaner.x+1; x<n-1; x++ ) {
			result[x][downCleaner.y] = map[x+1][downCleaner.y];
		}
		//마지막 행
		for(int y=0; y<m-1; y++) {
			result[n-1][y] = map[n-1][y+1];
		}
		//마지막 열
		for(int x=n-1; x>=downCleaner.x+1; x--) {
			result[x][m-1] = map[x-1][m-1];
		}
		//첫번째 열
		for(int x=downCleaner.x+1; x<n-1; x++) {
			result[x][0] = map[x+1][0];
		}
		

		//첫번째 행
		for(int y=m-1; y>=2; y--) {
			result[downCleaner.x][y] = map[downCleaner.x][y-1];
		}
		
		result[downCleaner.x][1] = 0;
		//첫번째 열
		//마지막 행
		//마지막 열
		
		
		//아랫 범위 공기청정기 아랫부분 x,0 ~ n,m
		
		
		return result;
	}
	
	
	public static void main(String[] args) throws IOException{
		
		init();

		for(int time = 0; time<t; time ++) {
			int[][] next =diffusion();

			next = activateCleaner(next);

			//미세먼지 확산
			//공기 청정기 가동
			
			for(int x=0; x<n; x++) {
				map[x] = Arrays.copyOf(next[x], m);
			}

			
			//최종 다시 map으로 옮기기
		}
		//t회 반복
			//미세먼지 확산
			//공기청정기 가동


		System.out.println(getTotal());
	}
	static int getTotal() {
		int result = 0;
		for(int[] ma: map) {
			for(int m :ma) {
				if(m==CLEANER)continue; 
				result+= m;
			}
		}
		return result; 
	}
		
	static void print(int[][] arr) {
		System.out.println();
		for(int[] ar: arr) {
			for(int a : ar) {
				System.out.printf("%3d", a);
			}System.out.println();
		}
		System.out.println();
	}
    
}