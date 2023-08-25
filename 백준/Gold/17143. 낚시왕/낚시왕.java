import java.io.*;
import java.math.BigInteger;
import java.util.*; 

class Shark implements Comparable<Shark>{
	int x, y, dir, v, size;
	
	//상 하 좌 우 왼 
	static final int[] dx = {0,-1,1,0,0};
	static final int[] dy = {0,0,0,1,-1}; 
	static final char[] direction = {' ','상','하', '우', '좌'}; 
	
	public Shark(int x, int y, int v, int dir, int size) {
		this.x = x;
		this.y = y;
		this.v = v; 
		this.dir = dir;
		this.size = size; 
	}

	
	@Override
	public int compareTo(Shark o) {
		//좌표가 같을 경우 사이즈가 큰순으로 정렬 
		if(this.x==o.x&&this.y==o.y) {
			return o.size-this.size; 
		}
		//기본적으로 땅 (x=0에 가까울 수록 정렬) 
		return this.x-o.x;
	}
	
	
	
	@Override
	public String toString() {
		return "Shark [x=" + x + ", y=" + y + ", dir=" + direction[dir] + ", v=" + v + ", size=" + size + "]";
	}


	public int turn(int dir) {
		if(dir==1) {
			return 2;
		}else if(dir==2) {
			return 1;
			
		}else if(dir==3) {
			return 4;
		}else if(dir==4) {
			return 3; 
		}
		
		return dir; 
	}
	public void move(int r, int c) {

		int nX =this.x;
		int nY =this.y ; 
		for(int i=0; i<v; i++) {
			nX += dx[this.dir];
			nY+=dy[this.dir];
			if(!inRange(nX, nY,r,c)) {
				nX -= dx[this.dir];
				nY -= dy[this.dir]; 
				this.dir = turn(this.dir);
				nX += dx[this.dir];
				nY += dy[this.dir]; 
			}
		}
		this.x = nX;
		this.y = nY; 
	}
	
	boolean inRange(int x, int y, int r, int c) {
		return x>=1&&x<=r&&y>=1&&y<=c; 
	}
	
	
}
class Main
{	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	
	static int r; //~100 
	static int c; //~100
	
	static int m; // 상어의 수 ~10000
	
	
	
	
	static PriorityQueue<Shark>[] col;
	

	
	static void move() {
		PriorityQueue<Shark>[] nextCol = new PriorityQueue[c+1]; 
		PriorityQueue<Shark>[][] map = new PriorityQueue[r+1][c+1];
		for(int y=0; y<=c; y++) {
			nextCol[y] = new PriorityQueue<>(); 
			for(int x=0; x<=r; x++) {
				map[x][y] = new PriorityQueue<>(); 
			}
		}
		
		for(int y=0; y<=c; y++) {
			while(!col[y].isEmpty()) {
				Shark s = col[y].poll();
//				System.out.print("변화전 ");
//				System.out.println(s); 
				s.move(r,c);
//				System.out.print("변화후 ");
//				System.out.println(s); 
				
				map[s.x][s.y].add(s); 
				
			}
		}
		
		for(int x=0; x<=r; x++) {
			for(int y=0; y<=c; y++) {
				if(map[x][y].size()==0)continue; 
				Shark s = map[x][y].poll(); 
				nextCol[y].add(s); 
			}
		}
		
		col = nextCol; 
	}
	//상어 이동 
		//nextRow 만들어 놓기 
		//pq 이차원 배열 생성 
		//col 순회 
			// col에 남아있는 값이 없을 때까지 col.poll()
				// 주어진 속도로 이동시킨다. 
					//이동하려고 하는 판이 격자판의 경계를 넘는 경우 방향을 바꿔 이동
				//map의 pq에 넣어준다. 
		
		//col 순회
			//row 순회하여 맞는 colIdx에 col[] = 이차원배열.poll();
		//모든 상어가 이동을 마친 후에 칸에 두마리 이상 남아있다면 크기가 가장 큰 상어만 남는다.
		
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		r = Integer.valueOf(tokens.nextToken());
		c = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken());
		
		col = new PriorityQueue[c+1];
		
		for(int y=0; y<=c; y++) {
			col[y] = new PriorityQueue<>();
		}
		
		for(int i=0; i<m; i++) {
			tokens = new StringTokenizer(buffer.readLine());
			
			int x = Integer.valueOf(tokens.nextToken());
			int y = Integer.valueOf(tokens.nextToken());
			int v = Integer.valueOf(tokens.nextToken());
			int dir = Integer.valueOf(tokens.nextToken());
			int size = Integer.valueOf(tokens.nextToken());
			col[y].add(new Shark(x,y,v,dir,size)); 
		}
		
	}
	
	static void print(PriorityQueue<Shark>[] arr) {
		for(int y=1; y<=c; y++) {
			System.out.println(arr[y]);
		}
	}
	
	public static void main(String[] args)throws IOException{
		
		init();

		
		int totalSize = 0; 
		
		for(int y=1; y<=c; y++) {
			if(col[y].size()!=0) { 
				Shark s = col[y].poll();
				totalSize+=s.size;
//				System.out.println("잡은 생선 : "+s);
			}; 
			move(); 
			
//			print(col); 
		}
		System.out.println(totalSize);
		//낚시왕 오른쪽 한칸 이동 
			//열에 있는 상어 중 제일 가까운 상어 잡기 
			//합 갱신 
			//상어 이동 
	}
}