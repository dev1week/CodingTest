import java.io.*; 

import java.util.*; 

class Point{
	int x, y;
	public Point(int x, int y) {
		this.x=x;
		this.y=y; 
	}
	
	public String toString() {
		return this.x+" : "+ this.y; 
	}
}

class Main
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static int n;
	static final int SIZE = 5;
	static int[][] map; 
	static boolean[][] isVisited; 
	
	static HashMap<Integer, Point> point; 
	
	static void init() throws IOException{
		map = new int[SIZE][SIZE]; 
		isVisited = new boolean[SIZE][SIZE]; 
		point = new HashMap<>(); 
		for(int x=0; x<SIZE; x++) {
			tokens = new StringTokenizer(buffer.readLine());
			for(int y=0; y<SIZE; y++) {
				map[x][y] = Integer.valueOf(tokens.nextToken());
				point.put(map[x][y], new Point(x,y));
			}
		}
	}
	
	static void judge()throws IOException {
		for(int r=0; r<SIZE; r++) {
			tokens = new StringTokenizer(buffer.readLine());
			for(int c=0; c<SIZE; c++) {
				stage++; 
				int data = Integer.valueOf(tokens.nextToken());
				Point p = point.get(data); 
				isVisited[p.x][p.y] = true;

				if(isBingo()) {
					return; 
				}
			}
			; 
		}
	}
	static int stage = 0; 
	public static void main(String[] args) throws IOException{
		init();
		judge(); 
		
		System.out.println(stage);

	}
	
	static void print(boolean[][] map) {
		System.out.println();
		for(boolean[] ma: map) {
			for(boolean m : ma) {
				if(m) {
					System.out.print(0+" ");
				}else {
					System.out.print(1+" ");
				}
			}System.out.println();
		}
		System.out.println(); 
	}
	
	static boolean isBingo() {
		int count = 0; 
		for(int y=0; y<SIZE; y++) {
			int result = 0; 
			for(int x=0; x<SIZE; x++) {
				if(isVisited[x][y]) {
					result++; 
				}
			}
			if(result==5) {count++;}
		}
		
		for(int x=0; x<SIZE; x++) {
			int result = 0; 
			for(int y=0; y<SIZE; y++) {
				if(isVisited[x][y]) {
					result++; 
				}
			}
			if(result==5) {count++;}
		}
		int result = 0; 
		for(int x=0; x<SIZE; x++) {
			if(isVisited[x][x]) {
				result++;
			}
		}
		if(result==5) {
			count++; 
		}
		result = 0; 
		for(int x=0; x<SIZE; x++) {
			if(isVisited[x][SIZE-1-x]) {
				result++; 
			}
		}
		if(result==5) {
			count++; 
		}

		return count>=3; 
	}
}