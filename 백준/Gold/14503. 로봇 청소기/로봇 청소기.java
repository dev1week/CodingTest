import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
class Robot{
	int x, y, dir;
	
	public Robot(int x, int y, int dir) {
		if(dir==1) {
			dir =3; 
		}else if(dir==3) {
			dir = 1;
		}
		this.x = x;
		this.y = y;
		this.dir = dir; 
	}
	
	static final char[]dirs = {'북','서','남','동'};
    //방향 : 북, 동, 남 ,서 
    static final int[] dx = {-1,0,1,0};
    static final int[] dy = {0,-1,0,1}; 
	
	public String toString() {
		return x+":"+y+" "+dirs[dir];
	}
	
	public char getDir() {
		return dirs[this.dir];
	}
	
	//반시계 방향 90도 회전 
	public void turn() {
		int nextDir = this.dir +1; 
		
		if(nextDir>3) {
			nextDir = 0; 
		}
		
		this.dir = nextDir; 
		
	}
	
	public void move(int x, int y) {
		this.x = x;
		this.y = y; 
	}
	
	private void moveTo(int dir) {
		this.x+= dx[dir];
		this.y+=dy[dir]; 
	}
	
	public void back() {
		int reverseDir = this.getReverseDir(); 
		moveTo(reverseDir);
	}
	
	public int getReverseDir() {
		
		
		if(this.dir==0) {
			return  2; 
		}else if(this.dir==1) {
			return 3; 
		}else if(this.dir==2) {
			return 0; 
		}else if(this.dir==3) {
			return 1; 
		}else {
			return -1; 
		}
		
	}
}




public class Main {
	
	
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    
    static int n;
    static int m; 
    
    static int[][] map; 
    static boolean[][] isCleaned; 
    static int result;
    
    static final int BLANK = 0; 
    
    //방향 : 북, 서, 남 ,동  
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1}; 
    
    
    static Robot init() throws IOException{
    	tokens = new StringTokenizer(buffer.readLine());
    	n = Integer.valueOf(tokens.nextToken());
    	m = Integer.valueOf(tokens.nextToken());
    	map = new int[n][m]; 
    	isCleaned = new boolean[n][m]; 
    	result = 0; 
    	
    	tokens = new StringTokenizer(buffer.readLine());
    	int cx = Integer.valueOf(tokens.nextToken());
    	int cy = Integer.valueOf(tokens.nextToken());
    	int dir = Integer.valueOf(tokens.nextToken());
    	Robot robot = new Robot(cx,cy,dir);
    	
    	for(int x=0; x<n; x++) {
    		tokens = new StringTokenizer(buffer.readLine());
    		for(int y=0; y<m; y++) {
    			map[x][y] = Integer.valueOf(tokens.nextToken());
    			if(map[x][y]==1) {
    				isCleaned[x][y] = true; 
    			}
    		}
    	}
    	return robot;
    }
    
    public static void main(String[] args) throws IOException{
    	Robot robot = init(); 
    	
    	action(robot);
    	
//    	System.out.println(result); 
    	
    	
    }
    
    
    static void print(Robot robot) {
    	for(int x=0; x<n; x++) {
    		System.out.println(Arrays.toString(map[x])); 
    	}
    	
    	
    	System.out.println(robot);
    }
    
    
    static void action(Robot currentRobot) {
//    	System.out.println(currentRobot);
    	
    	int cX = currentRobot.x;
    	int cY = currentRobot.y; 
    	int currentDir = currentRobot.dir; 
    	if(!isCleaned[cX][cY]) {
    		isCleaned[cX][cY]= true;
    		result++;
//    		System.out.println(result+"칸");
    	}
    
//    	printMap(currentRobot); 
    	
    	
    	if(isNeedToClean(cX, cY)) {
    		for(int turn=0; turn<4; turn++) {
//    			System.out.println("도는 중 ");
    			currentRobot.turn();
    			int nX = cX+ dx[currentRobot.dir];
    			int nY = cY+ dy[currentRobot.dir]; 
//    			System.out.println(currentRobot);
    			
    			if(inRange(nX, nY)&&!isCleaned[nX][nY]&&map[nX][nY]==BLANK) {
    				currentRobot.move(nX, nY);
//    				System.out.println("돌기 끝"+ currentRobot);
    				action(currentRobot);
    			}
    		}
    		
    		
    	}else {
//    		System.out.println("청소할칸 없음 ");
    		if(isPossibleToBack(currentRobot)) {
    			
    			currentRobot.back();
    			action(currentRobot);
//    			System.out.println("뒤로갔음 "+currentRobot);
    		}else {
    			System.out.println(result);
    			System.exit(0); 
    		}
    	}
    	
    	
    }
    
    static boolean isNeedToClean(int x, int y) {
    	for(int dir=0; dir<4; dir++) {
    		int nX = x + dx[dir];
    		int nY = y + dy[dir]; 
    		
    		if(inRange(nX, nY)&&!isCleaned[nX][nY]&&map[nX][nY]==BLANK) {
    			return true; 
    		}
    	}
    	return false;
    }
    
    static boolean inRange(int x, int y) {
    	return x>=0&&x<n&&y>=0&&y<m;
    }
    
    
    static boolean isPossibleToBack(Robot robot) {
    	int reverseDir = robot.getReverseDir(); 
    	
    	int nX = robot.x+ dx[reverseDir];
    	int nY = robot.y + dy[reverseDir]; 
    	
    	if(inRange(nX, nY)&&map[nX][nY]==BLANK) {
    		return true;
    	}else {
    		return false; 
    	}
    	
    }
    
    static void printMap(Robot robot) {
    	for(int x=0; x<n; x++) {
    		for(int y=0; y<m; y++) {
    			if(x==robot.x&&y==robot.y) {
    				System.out.print(robot.getDir()+" ");
    			}
    			else if(isCleaned[x][y]) {
    				System.out.print(2+" ");
    			}else {
    				System.out.print(map[x][y]+" ");
    			}
    		}System.out.println();
    	}
    	System.out.println();
    }
    //로봇	
    	//현재 칸이 청소되지 않음 
    		//청소함 
    	//현재 칸의 주변 4칸 중 청소되지 않은 빈칸이 있는지 검사 
    		//없음 
    			//후진할 수 있는가 
    				//없다 -> 작동 정지 
    				//있다 -> 보는 방향 유지 한칸 뒤로 
    		//있음 
    			//반시계 방향 90도 회전 
    			//바라보는 방향 기준 앞칸이 청소되어있지 않은 빈칸인지 검사 
    				//검사 
    
    
    
}
    
   