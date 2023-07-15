
import java.io.*;
import java.util.*; 

class Point{
	int x, y;
	
	public Point(int x, int y ) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return x+":"+y; 
	}
}


public class Main {
	static public BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	
	static int n; 
	static char[][] map;
	static boolean[] isUsed; 
	
	static final char TEACHER = 'T';
	static final char STUDENT = 'S';
	static final char BLOCK = 'O'; 
	static final char BLANK = 'X'; 
	
	
	static final int[] dx = {-1,1,0,0};
	static final int[] dy = {0,0,-1,1}; 
	
	//장애물이 위치한 경우 장애물 뒤편에 숨어있는 학생들은 볼 수 없다.
	
	static ArrayList<Point> teachers = new ArrayList<>();
	static ArrayList<Point> students = new ArrayList<>();
	static ArrayList<Point> blanks= new ArrayList<>();
	static ArrayList<Point> blocks = new ArrayList<>();
	static boolean result;
	
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine()); 
		
		map = new char[n][n];
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int y=0; y<n; y++) {
				map[x][y] =  tokens.nextToken().charAt(0); 
				if(map[x][y]==TEACHER) {
					teachers.add(new Point(x,y));
				}else if(map[x][y]==STUDENT) {
					students.add(new Point(x,y));
				}else if(map[x][y]==BLANK) {
					blanks.add(new Point(x,y));
				}
			}
		}
		isUsed = new boolean[blanks.size()]; 
	}
	
	static void printMap(char[][] map1) {
		for(char[] ma: map1) {
			for(char m : ma) {
				System.out.print(m+" ");
			}System.out.println(); 
		}
	}
	
	
	static void bt(int cur, int last) {
		if(cur==3+1) {
			
			if(isPossible()) {
				result = true; 
			}
			return; 
		}
		
		for(int next=last+1; next<blanks.size(); next++) {
			if(!isUsed[next]) {
				isUsed[next] = true; 
				blocks.add(blanks.get(next));
				bt(cur+1, next); 
				isUsed[next] = false; 
				blocks.remove(blocks.size()-1); 
			}
		}
	}

	
	static boolean inRange(int x, int y) {
		return x>=0&&x<n&&y>=0&&y<n; 
	}
	
	static void getVisitedMap(Point t, char[][] tmpMap){
		for(int dir=0; dir<4; dir++) {
			for(int length=0; length<n; length++) {
				int nX = t.x+dx[dir]*length;
				int nY = t.y+dy[dir]*length;
				if(inRange(nX,nY)) {
					if(tmpMap[nX][nY]==BLOCK) {
						break; 
					}else {
						isVisited[nX][nY] = true; 
					}
				}
				
			}
		}
		
	}
	static boolean[][] isVisited; 
	static boolean isPossible() {
		 isVisited = new boolean[n][n]; 
		char[][] tmpMap = new char[n][n]  ;
		
		for(int x=0; x<n; x++) {
			for(int y=0; y<n; y++) {
				tmpMap[x][y] = map[x][y]; 
			}
		}
		
		for(Point block : blocks) {
			tmpMap[block.x][block.y] = BLOCK; 
		}
		
		for(Point teacher : teachers) {
			getVisitedMap(teacher, tmpMap); 
		}
		
		for(Point student : students) {
			if(isVisited[student.x][student.y]) {
				
				return false; 
			}
		}

		return true;
		
	}
	
	static void printV() {
		for(int x=0; x<n; x++) {
			for(int y=0; y<n; y++) {
				if(isVisited[x][y]) {
					System.out.print(1);
				}else {
					System.out.print(0);
				}
			}System.out.println(); 
		}
		
	}
	public static void main(String[] args) throws IOException{
		init(); 
		
		bt(1,-1);
		
		if(result) {System.out.println("YES");}
		else { System.out.println("NO");}
		//빈 공간 중 3개의 장애물 선택하기 36C3 =  7140 * 5*30 
			//선생님 순회 
				//선생님 자리를 기준으로 감시가능한 범위 isVisited; 배열 만들기 
				//학생 순회 	
					//학생 위치 기반으로 isVisited 확인 


	}
	
}
