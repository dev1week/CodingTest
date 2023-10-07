
import java.io.*;
import java.util.*;


class Point implements Comparable<Point>{
	int x, y, blanks, favorites;
	public Point(int x, int y, int blanks, int favorites) {
		this.x = x;
		this.y =y;
		this.blanks= blanks;
		this.favorites = favorites; 
	}
	@Override 
	public String toString() {
		return this.x+":"+this.y+":"+this.blanks+":"+this.favorites; 
	}
	
	@Override
	public int compareTo(Point o) {
		if(o.favorites==this.favorites) {
			if(o.blanks==this.blanks) {
				if(o.x==this.x) {
					return this.y - o.y;
				}
				return this.x- o.x; 
			}
			return o.blanks-this.blanks;
		}
		 return o.favorites-this.favorites; 
	}
}


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    
    static final int[] scores = {0,1,10,100,1000}; 
    
    static int n; //~20 
    static boolean[][] isFavorite; 
    static int[][] map; 
    static boolean[][] isVisited; 
    static Point[] numToPoint; 
    static ArrayList<Integer> students; 
    
    
    static void init() throws IOException{
    	n = Integer.valueOf(buffer.readLine());
    	
    	map = new int[n][n]; 
    	students = new ArrayList<>(); 
    	numToPoint = new Point[n*n+1];
    	isFavorite = new boolean[n*n+1][n*n+1];
    	isVisited = new boolean[n][n]; 
    	for(int i=0; i<n*n; i++) {
    		tokens = new StringTokenizer(buffer.readLine());
    		
    		int student = Integer.valueOf(tokens.nextToken());
    		students.add(student); 
    		for(int f=0; f<4; f++) {
    			int friend = Integer.valueOf(tokens.nextToken());
    			isFavorite[student][friend] = true; 
    		}
    	}
    	int count = 1; 
    	for(int x=0; x<n;x++) {
    		for(int y=0; y<n; y++) {
    			numToPoint[count] = new Point(x,y,0, 0);
    			count++; 
    		}
    	}
    	
    	
    }
    
    
    public static void main(String[] args)throws IOException{
    	init(); 
    	//학생 전체 순회 
    		//가능한 리스트 만들기 
    		//하나 뽑아와서 넣어주기 
    
    	//map이 다 만들어짐
    
    	//map에서 각 자리 돌면서 선호도 구하기 n^2*4 
    	for(int student : students) {
    		ArrayList<Point> selecteds = new ArrayList<>(); 
    		for(int x=0; x<n; x++) {
    			for(int y=0; y<n; y++) {
    				if(isVisited[x][y])continue; 
    				int fCounts = 0;
    				int bCounts = 0; 
    				for(int dir=0; dir<4; dir++){
    					int nX = x + dx[dir];
    					int nY = y+ dy[dir];
    					if(inRange(nX, nY)) {
    						if(map[nX][nY]==0) {
    							bCounts++; 
    						}else if(isFavorite[student][map[nX][nY]]) {
    							fCounts++;
    						}
    					}
    				}
    				selecteds.add(new Point(x,y,bCounts, fCounts));
    			}
    		}
    		Collections.sort(selecteds);
    		
    		isVisited[selecteds.get(0).x][selecteds.get(0).y] = true;
    		map[selecteds.get(0).x][selecteds.get(0).y] = student; 
    	}
    	int score = 0; 
    	for(int x=0; x<n; x++) {
    		for(int y=0; y<n; y++) {
    			int fCount = 0; 
    			for(int dir=0; dir<4; dir++){
					int nX = x + dx[dir];
					int nY = y+ dy[dir];
					if(inRange(nX, nY)) {
						if(isFavorite[map[x][y]][map[nX][nY]]) {
							fCount++;
						}
					}
				}
    			score += scores[fCount]; 
    			
    		}
    	}
    	System.out.println(score); 
    	
    	
    }
    
    static final int[] dx = {-1,1,0,0};
    static final int[] dy = {0,0,-1,1}; 
    
    static boolean inRange(int x, int y) {
    	return x>=0&&y>=0&&x<n&&y<n; 
    }
    
    static void print(int[][] arr) {
    	for(int[] ar: arr) {
    		for(int a: ar) {
    			System.out.print(a+" ");
    		}System.out.println(); 
    	}
    }
    
    

    
    //비어있는 칸중 좋아하는 학생이 인접한 칸에 가장 많은 칸을 자리로 한다. 
    //1을 만족하는 칸이 여러개인 경우 비어있는 칸이 가장 많은 칸으로 자릴 정한다. 
    //2를 만족하는 칸이 여러개인 경우 행의 번호가 가장 작은 칸
    //3을 만족하는 칸이 여러개인 경우 열의 번호가 가장 작은 칸 
    
  
}