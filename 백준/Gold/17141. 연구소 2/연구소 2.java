import java.io.*;
import java.util.*; 

class Point{
	int x, y;
	
	
	public Point(int x, int y) {
		this.x =x;
		this.y = y;
	}
	
	public String toString() {
		return this.x +  " : " +this.y; 
	}
}

public class Main {
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens;
	
	
	static final int BLANK = 0;
	static final int BLOCK  =1;
	static final int AVAILABLE =2; 
	static final int VIRUS = 3; 
	
	static final int NOT_VISITED = -1; 
	
	static int[][] map;
	static int[][] testMap; 
	static int[][] distance; 
	
	static int n;
	static int m; 
	
	static ArrayList<Point> candiates;
	static boolean[] isUsed; 
	static ArrayList<Point> origins; 
	
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken()); 
		
		map = new int[n][n]; 
		candiates = new ArrayList<>();
		origins = new ArrayList<>(); 
		selecteds= new ArrayList<>(); 
		
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int y=0; y<n; y++) {
				map[x][y] = Integer.valueOf(tokens.nextToken());
				if(map[x][y] == AVAILABLE) {
					candiates.add(new Point(x,y)); 
				}
			}
		}
		
		isUsed = new boolean[candiates.size()];
		
	}
	
	static final int[] dx = {-1,1,0,0};
	static final int[] dy = {0,0,-1,1}; 
	
	static boolean inRange(int x, int y) {
		return x>=0&&x<n&&y>=0&&y<n; 
	}
	
	static boolean canInsfect(int x,  int y) {
		return inRange(x, y)&&map[x][y]!=BLOCK&&distance[x][y]==NOT_VISITED;
	}
	
    static void bfs(){
    	distance = new int[n][n];
    	Queue<Point> que = new LinkedList<>(); 
    	for(int[] d :distance) {
    		Arrays.fill(d, NOT_VISITED);
    	}
    	
    	for(Point virus: selecteds) {
    		distance[virus.x][virus.y]= 0; 
    		que.add(virus);
    	}
    	
    	while(!que.isEmpty()) {
    		Point cur = que.poll(); 
    		
    		for(int dir=0; dir<4; dir++) {
    			int nX = cur.x + dx[dir];
    			int nY = cur.y + dy[dir];
    			if(canInsfect(nX, nY)) {
    				que.add(new Point(nX, nY));
    				distance[nX][nY] = distance[cur.x][cur.y]+1; 
    			}
    		}
    		
    	}
    	 
    }
    
    static boolean isAllInsfected() {
    	for(int x=0; x<n; x++) {
    		for(int y=0; y<n; y++) {
    			if(testMap[x][y]!=BLOCK&&distance[x][y]==NOT_VISITED) {
    				return false;
    			}
    		}
    	}
    	return true; 
    }
    
    static int getTime() {
    	int time = Integer.MIN_VALUE;
    	
    	for(int x=0; x<n; x++) {
    		for(int y=0; y<n; y++) {
    			time = Math.max(time, distance[x][y]);
    		}
    	}
    	
    	return time; 
    }
	
	static ArrayList<Point> selecteds; 
	static int result = Integer.MAX_VALUE;
	static void bt(int cur, int last) {
		if(cur==m+1) {
			testMap = new int[n][n];
			
			for(int x=0; x<n; x++) {
				testMap[x] = Arrays.copyOf(map[x], n);
			}
			
			for(Point virus : selecteds) {
				testMap[virus.x][virus.y] = VIRUS;
			}
			
			
			bfs();
			if(isAllInsfected()) {
				result = Math.min(result, getTime());
				
			}
			
			return; 
		}
		
		for(int nextIdx =last+1; nextIdx<candiates.size(); nextIdx++) {
			if(!isUsed[nextIdx]) {
				isUsed[nextIdx] = true;
				selecteds.add(candiates.get(nextIdx)); 
				bt(cur+1, nextIdx);
				selecteds.remove(selecteds.size()-1); 
				isUsed[nextIdx] =false;
			}
		}
	}
	
	
	//m개의 빈공간 뽑기 
		//bfs 돌려서 distance 만들기 
		//distance 중 최대값 찾기 
		//전체 결과중 최소값 갱신 
	
	
	public static void main(String[] args) throws IOException{
		init();
		bt(1,-1); 
		
		if(result==Integer.MAX_VALUE) {
			result = -1;
		}
		
		System.out.println(result);
	}
	
	static void print(int[][] arr) {
		for(int[] ar: arr) {
			for(int a: ar) {
				System.out.print(a+" ");
			}System.out.println(); 
		}
	}

}


