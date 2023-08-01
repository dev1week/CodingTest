import java.io.*; 

import java.util.*; 


class Point{
	int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return this.x+":"+this.y; 
	}
}


class Command extends Point{
	int dir;
	
	public Command(int x, int y, char dir) {
		super(x, y); 
		if(dir=='E') {
			this.dir = 0;
		}else if(dir=='W') {
			this.dir = 1;
		}else if(dir=='S') {
			this.dir = 2;
		}else if(dir=='N') {
			this.dir = 3; 
		}
		 
	}
	
	public Command(int x, int y, int dir) {
		super(x,y);
		this.dir = dir; 
	}
	
	public String toString() {
		return super.toString() + "를"+ this.dir+" 방향으로"; 
	}
}

class Main
{
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	
	static int n; 
	static int m;
	static int r; 
	
	
	static int[][] map; 
	static boolean[][] isCollapse; 
	
	static Point[] defenses;
	static Command[] offenses;
	
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		n = Integer.valueOf(tokens.nextToken()); 
		m = Integer.valueOf(tokens.nextToken()); 
		r = Integer.valueOf(tokens.nextToken()); 
		
		map = new int[n][m]; 
		isCollapse = new boolean[n][m]; 
		
		
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int y=0; y<m; y++) {
				map[x][y] = Integer.valueOf(tokens.nextToken());
			}
		}
		
		defenses = new Point[r]; 
		offenses = new Command[r]; 
		
		for(int round=0; round<r; round++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			offenses[round] = new Command(Integer.valueOf(tokens.nextToken())-1, Integer.valueOf(tokens.nextToken())-1, tokens.nextToken().charAt(0)); 
			tokens = new StringTokenizer(buffer.readLine()); 
			defenses[round] = new Point(Integer.valueOf(tokens.nextToken())-1, Integer.valueOf(tokens.nextToken())-1);
		}
	}
	
	//각 도미노는 1~5의 높이를 갖는다.. 
	
	//공격수
		//원하는 방향으로 넘어뜨리기 
		//길이가 k인 도미노가 특정 방향으로 넘어진다면 그방향으로 k-1개의 도미노들 중 넘어지지 않은 것들이 같은 방향으로 연달아 넘어짐 
		
	
	//공격수 점수
		//해당 라운드에 넘어뜨린 도미노의 개수 
	
	
	//수비수 
		//원하는 도미노를 세울 수 있음 
	
	static final int[] dx = {0,0,1,-1};
	static final int[] dy = {1,-1,0,0}; 
	
	static boolean inRange(int x, int y) {
		return x>=0&&y>=0&&x<n&&y<m; 
	}
	
	static boolean canGo(int x, int y) {
		return inRange(x, y)&&isCollapse[x][y] == false; 
	}
	
	
	static int score = 0; 
	static void offense(int round) {
		if(!isCollapse[offenses[round].x][offenses[round].y]) {
			Queue<Command> que = new LinkedList<>();
			que.add(offenses[round]); 
			isCollapse[offenses[round].x][offenses[round].y] = true;
			score ++; 
			while(!que.isEmpty()) {
				Command cur = que.poll();
				
				int size = map[cur.x][cur.y]; 
				for(int offset=0; offset<size; offset++) {
					int nX = cur.x + dx[cur.dir]*offset; 
					int nY = cur.y + dy[cur.dir]*offset; 
					
					if(canGo(nX, nY)) {
						que.add(new Command(nX, nY, cur.dir)); 
						isCollapse[nX][nY] = true; 
						score ++; 
					}
				}
				
			}
			
		}
	}
	
	static void defense(int round) {
		Point cur = defenses[round]; 
		isCollapse[cur.x][cur.y]= false; 
	}
	
	public static void main(String[] args)throws IOException{
		init();
		
	
		for(int round=0; round<r; round++) {
			offense(round); 
			//공격수 턴 3 1 E 
				//도미노가 넘어짐 
					//해당 칸에 있는것 중 넘어지지 않은 도미노만 큐에 넣어줌 
			
			
			defense(round); 
			//수비수 턴 
				//해당칸의 도미노를 세워준다. 
		}
		System.out.println(score); 
		System.out.println(print(isCollapse));
		
	}
	
	static void print(int[][] arr) {
		for(int[] ar: arr)
		{
			for(int a: ar) {
				System.out.print(a+" ");
			}System.out.println(); 
		}
	}
	
	static String print(boolean[][] arr) {
		StringBuilder result = new StringBuilder(); 
		for(boolean[] ar: arr) {
			for(boolean a: ar) {
				if(a) {
					result.append("F ");
				}else {
					result.append("S ");
				}
			}result.append("\n"); 
		}
		
		return result.toString();
	}
	
}