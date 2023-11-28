
import java.io.*;
import java.util.*;

class Point{
	int x;
	int y;
	
	@Override
	public String toString() {
		return this.x+":"+this.y;
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

 class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    
    static int n; //행 
    static int m; //열 
    
    static char HOLE = 'C';
    static char BLANK = '.';
    static char P1 = '/';
    static char P2 = '\\'; 
    
    static char[][] map; 
    static int[][] visitCounts; 
    static Point start;
    static char[] printDir= {'U','R','D', 'L'};
    
    static int result;
    static void init() throws IOException{
    	tokens = new StringTokenizer(buffer.readLine()); 
    	
    	n = Integer.valueOf(tokens.nextToken()); 
    	m = Integer.valueOf(tokens.nextToken());
    	result = 0;
    	map = new char[n][m]; 
    
    	for(int x=0; x<n; x++) {
    		String line = buffer.readLine();
    		for(int y=0; y<m; y++) {
    			map[x][y] = line.charAt(y);
    		}
    	}
    	
    	tokens = new StringTokenizer(buffer.readLine()); 
    	int x = Integer.valueOf(tokens.nextToken())-1; 
    	int y = Integer.valueOf(tokens.nextToken())-1;
    	
    	start = new Point(x, y);
    }
    		
    
    
    
    
    //행성
    	// 방향에는 두 종류 /\    
    //블랙홀 
    	//만나면 없어짐 
    //탐사선
    	//인접한 네 칸 중 하나를 골라 시그널을 보낸다. 
    //시그널 
    	//블랙홀이 있는 칸을 만나거나 항성계를 벗어날때까지 전파 
    
    
    //시그널을 보내는 방향 출력 
    //최대시간 출력
    //무한하다면 Voyger 출력 
    
   public static void main(String[] args)throws IOException{
	   init(); 	   
	   //4개 방향 순회 
	   	//
	   
	   boolean isVoyage = false;
	   int resultDir = 0;
	   for(int dir=0; dir<4; dir++) {
		   
		   visitCounts = new int[n][m];
		   visitCounts[start.x][start.y] = 1;
		   int maxTime = propagation(start.x,start.y, dir, 1);
		   
		   if(maxTime==Integer.MAX_VALUE) {
			  isVoyage = true;
			  System.out.println(printDir[dir]);
			  System.out.println("Voyager");
			  break;
		   }
		   
		   if(result<maxTime) {
			   result = maxTime;
			   resultDir = dir;
		   }
	   }
	   
	   if(!isVoyage) {
		   System.out.println(printDir[resultDir]);
		   System.out.println(result);
	   }
	   
	   
   }

   
   static int propagation(int cX, int cY,int dir, int time) {
	   int nx = cX + dx[dir];
	   int ny = cY + dy[dir];
	   
	   
	   //항성계를 벗어났는가 || 블랙홀인?
	   	//time 반환
	   if(!inRange(nx, ny)||map[nx][ny]==HOLE) {
		  
		   return time;
	   }
	   
	   //이미 갔던 곳을 갔는가?
	   	//Integer.MAX_VALUE 반환
	   if(visitCounts[nx][ny]>3) {
		   return Integer.MAX_VALUE;
	   }
	   
	   visitCounts[nx][ny]++;
	   if(map[nx][ny]==P1) {
		   int nextDir = changeDir(dir, P1);
		   return propagation(nx, ny, nextDir, time+1);
	   }else if(map[nx][ny]==P2) {
		   int nextDir = changeDir(dir, P2);
		   return propagation(nx, ny, nextDir, time+1);
	   }else  {
		   return propagation(nx, ny, dir, time+1);
	   }
	   // \ 인가?
	   	//propagation(next, nextDir, time+1);
	
	   
	   // / 인가? 
	   	//propagation(next, nextDir, time+1);
	   
	   // 빈칸인가? 
	   	//propagation(next, dir, time+1);
	   
	   
   }
   

   //0  1 2 3
   //상 우 하 좌  
   static final int[] dx = {-1,0,1,0};
   static final int[] dy = {0,1,0,-1};
   static int changeDir(int dir, char kind) {
	   // P1 = '/';
	   if(kind == P1) {
		   if(dir==0) {
			   //윗 방향으 들어왔을 -> 오른쪽 
			   return 1;
		   }else if(dir==1) {
			   //오 방향으 들어왔을 -> 윗 쪽 
			   return 0;
		   }else if(dir==2) {
			   //아랫 방향으 들어왔을 -> 왼쪽  
			   return 3;
		   }else {
			   //왼쪽 방향으 들어왔을 -> 아랫 쪽 
			   return 2; 
		   }
	   // P2 = '\\'; 
	   }else {
		   if(dir==0) {
			   //윗 방향으 들어왔을 -> 왼 쪽 
			   return 3; 
		   }else if(dir==1) {
			   //오른 방향으 들어왔을 -> 아래 쪽 
			   return 2;
		   }else if(dir==2) {
			   //아랫 방향으 들어왔을 -> 오른쪽  
			   return 1;
		   }else {
			   //왼쪽 방향으 들어왔을 ->위 쪽 
			   return 0;
		   }
	   }
	   
	   
   }
   
   static boolean inRange(int x, int y) {
	   return x>=0&&x<n&&y>=0&&y<m;
   }
    
    static void print(int[][] map) {
    	for(int[] ma: map) {
    		for(int m : ma) {
    			System.out.print(m+" ");
    		}System.out.println();
    	}
    }
    static void print(char[][] map) {
    	for(char[] ma: map) {
    		for(char m : ma) {
    			System.out.print(m+" ");
    		}System.out.println();
    	}
    }
    
    
    	
}