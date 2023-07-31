import java.io.*; 

import java.util.*; 


class Point {
	int x, y,z ;
	
	
	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z; 
	}
	
	public String toString() {
		return this.x+" : "+this.y+" : "+this.z; 
	}
}

class Main
{
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 

	static char[][][] map;
	static int[][][] distance;
	static final int[] dx = {-1,1,0,0,0,0};
	static final int[] dy = {0,0,-1,1,0,0}; 
	static final int[] dz = {0,0,0,0,-1,1};	
	
	
	static int L;
	static int R; 
	static int C; 
	
	static final char START= 'S';
	static final char BLANK = '.';
	static final char FIRE = '#'; 
	static final char DESTINATION ='E'; 
	
	static final int NOT_VISITED = -1; 
	
	static Point start; 
	static Point end; 
	static boolean inRange(int x, int y, int z) {
		return x>=0&&y>=0&&z>=0&&x<R&&y<C&&z<L; 
	}
	static boolean canGo(int x, int y, int z) {
		return inRange(x,y,z)&&distance[z][x][y]==NOT_VISITED&&map[z][x][y]!=FIRE; 
	}
	
	static void bfs() {
		Queue<Point> que = new LinkedList<>(); 
		que.add(start); 
		while(!que.isEmpty()) {
			Point cur = que.poll(); 
			for(int dir=0; dir<6; dir++) {
				int nX = cur.x + dx[dir];
				int nY = cur.y + dy[dir];
				int nZ = cur.z+ dz[dir];
				if(canGo(nX, nY, nZ)) {

					que.add(new Point(nX, nY, nZ));
					distance[nZ][nX][nY] = distance[cur.z][cur.x][cur.y] +1; 
				}
			}
		}
	}
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		
		L = Integer.valueOf(tokens.nextToken()); 
		R = Integer.valueOf(tokens.nextToken());
		C = Integer.valueOf(tokens.nextToken()); 
		
		while(L!=0) {
			map =  new char[L][R][C];
			distance = new int[L][R][C];
			for(int z=0; z<L; z++) {
				for(int x=0; x<R; x++) {
					String line ="";
					while(line.equals("")) {
						line = buffer.readLine(); 
					}
					for(int y=0; y<C; y++) {
						map[z][x][y] = line.charAt(y);
						
						if(map[z][x][y]==START) {
							start = new Point(x,y,z);
						}else if(map[z][x][y]==DESTINATION) {
							end = new Point(x,y,z); 
						}
					}
					
				}
				
			}
			
			for(int[][] dist : distance) {
				for(int[] di : dist) {
					Arrays.fill(di, NOT_VISITED); 
				}
			}
			distance[start.z][start.x][start.y] = 0; 
			
			bfs(); 
			if(distance[end.z][end.x][end.y]!=NOT_VISITED) {
				System.out.printf("Escaped in %d minute(s).\n", distance[end.z][end.x][end.y]);
			}else {
				System.out.println("Trapped!");
			}
			
			String line ="";
			while(line.equals("")) {
				line = buffer.readLine(); 
			}
			tokens = new StringTokenizer(line);

			L = Integer.valueOf(tokens.nextToken()); 
			R = Integer.valueOf(tokens.nextToken());
			C = Integer.valueOf(tokens.nextToken()); 
			if(L==0) {
				break; 
			}
		}
		
	}
	
	static void print(char[][][] arr) {
		for(char[][] ar: arr) {
			for(char[] a: ar) {
				for(char num : a) {
					System.out.print(num+" ");
				}System.out.println(); 
			}System.out.println(); 
		}
	}
	static void print(int[][][] arr) {
		for(int[][] ar: arr) {
			for(int[] a: ar) {
				for(int num : a) {
					System.out.print(num+" ");
				}System.out.println(); 
			}System.out.println(); 
		}
	}
	public static void main(String[] args) throws IOException{
		init(); 
		
		
	}
	
}