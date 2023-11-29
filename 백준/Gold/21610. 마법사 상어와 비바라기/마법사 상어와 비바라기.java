import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    
    static int n; // 격자 크기 
    static int m; 
    static int[][] map; // (x,y)위치에서 바구니에 저장되어 있는 물의 양 
    	//1번행과 n번행을 연결함 
    	//1번열과 N번열을 연결
    
    //좌,좌상,상,우상,우,우하,하,좌하 
   static int[] dx= { 0,-1,-1,-1,0,1,1, 1};
   static int[] dy= {-1,-1, 0, 1,1,1,0,-1};
   
   static boolean[][] isCloud; 
   
   static void init() throws IOException{
	   tokens = new StringTokenizer(buffer.readLine()); 
	   
	   n = Integer.valueOf(tokens.nextToken());
	   m = Integer.valueOf(tokens.nextToken()); 
	   
	   map = new int[n][n];
	   isCloud = new boolean[n][n]; 
	   
	   
	   for(int x=0; x<n; x++) {
		   tokens = new StringTokenizer(buffer.readLine());
		   for(int y=0; y<n; y++) {
			   map[x][y]= Integer.valueOf(tokens.nextToken());
		   }
	   }
   }
   
   public static void main(String[] args) throws IOException{
	   init();
	   
	   initCloud();
	   
	   
	   for(int i=0; i<m; i++) {
		  
		   tokens = new StringTokenizer(buffer.readLine());
		   
		   int d = Integer.valueOf(tokens.nextToken())-1;
		   int s = Integer.valueOf(tokens.nextToken());
		   
		   
		   
		   process(d,s); 
		   
	   }
	   
	   System.out.println(getSumWater());
	   
	   
   }
   
   static int getSumWater() {
	   int result = 0; 
	   for(int x=0; x<n; x++) {
		   for(int y=0; y<n; y++) {
			   result += map[x][y];
		   }
	   }
	   
	   return result; 
   }
   
	//구름 생성 
		//n,1 / n,2 / n-1,1 / n-1,2에 생긴다.  
   		//isCloud true로 만든다. 
   
   static void initCloud() {
	   for(int x= n-2; x<n; x++) {
		   for(int y=0; y<2; y++) {
			   isCloud[x][y] = true; 
		   }
	   }
   }
   
   static boolean[][] move(int d, int s){
	   boolean[][] movedCloud = new boolean[n][n];
	   for(int x=0; x<n; x++) {
		   for(int y=0; y<n; y++) {
			   if(isCloud[x][y]) {
				   int cs = s%n; 
				   int nx = x+ dx[d]*cs;
				   int ny = y+ dy[d]*cs; 
				   
				   if(nx<0) {
					   nx+=n; 
				   }else if(nx>=n) {
					   nx-=n;
				   }
				   if(ny<0) {
					   ny+=n;
				   }else if(ny>=n) {
					   ny-=n;
				   }
				   
				   movedCloud[nx][ny] = true;
				   
				   
			   }
		   }
	   }
	   
	   return movedCloud;
   }
   
   static void rainDrop(boolean[][] nextIsCloud) {
	   for(int x=0; x<n; x++) {
		   for(int y=0; y<n; y++) {
			   if(nextIsCloud[x][y]) {
				   map[x][y]++; 
			   }
		   }
	   }
   }
   
   static int magic(boolean[][] nextIsCloud, int x, int y) {
	   int count = 0; 
	   for(int dir=0; dir<8; dir++) {
		   //대각선에 위치한 경우만 확인 
		   if(dir%2==1) {
			   int nX = x + dx[dir];
			   int nY = y + dy[dir]; 
			   if(inRange(nX, nY)&&map[nX][nY]>=1) {
				   count ++; 
			   }
		   }
	   }
	   return count;
   }
   
   static boolean inRange(int x, int y) {
	   return x>=0&&x<n&&y>=0&&y<n; 
   }
   
   static void process(int d, int s) {
	   
	   boolean[][] movedCloud = move(d,s);
	   	//모든 구름이 d 방향으로 s칸 이동
				//isCloud 모든 칸 순회 
					//각 칸에서 구름이 있는 곳만 이동 로직 	
	   
	   rainDrop(movedCloud);

	   	//각 구름에서 비내림 -> 구름이 있는 칸 물증 ++
			//isCloud 모든 칸 순회 
					//각 칸에서 구름이 있는 곳만 이동 로직

	   //물 복사 버그 마법 
	   	//물이 증가했던 곳에서만 
	   	//대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 증가 
	   	//경계를 넘어가는 칸은 포함하지 않는다.
	   int[][] nextMap = new int[n][n];
	   for(int x=0; x<n; x++) {
		   for(int y=0; y<n; y++)
		   {
			   nextMap[x][y] = map[x][y];
			   if(movedCloud[x][y]) {
				   int count = magic(movedCloud, x, y);
				   
				   nextMap[x][y] += count;
 
			   }
		   }
	   }
	   map = nextMap; 
	   
	   
	   
	   isCloud = createCloud(movedCloud);
		 //구름 생성
	  		//전체 맵 순회 
	  			//isCloud==false && map>=2
	  				//map -=2; 
	  			//isCloud==true
	  				//구름 지우기 isCloud = false;
	  
		   	//물의 양이 2이상인 칸에 구름 생김,물의 양이 2 줄어들음 
		   	//이때 [이동]에서 구름이 사라진 칸이면 안됨 
   }
   
   
   static boolean[][] createCloud(boolean[][] movedCloud) {
	   boolean[][] afterCreatedCloud = new boolean[n][n]; 
	   for(int x=0; x<n; x++) {
		   for(int y=0; y<n; y++) {
			   if(!movedCloud[x][y]&&map[x][y]>=2) {
				   afterCreatedCloud[x][y]  =true;
				   map[x][y] -=2; 
			   }
		   }
	   }
	   
	   return afterCreatedCloud;
   }
    
	
	
   
   static void printAll(boolean[][] map1, int[][]map2) {
	   System.out.println("구름의 위치 ");
	   print(map1);
	   System.out.println("물의 양");
	   print(map2);
   }
   static void print(boolean[][] map) {
	   for(boolean[] ma: map){
		   for(boolean m : ma){
			   if(m) {
				   System.out.print(1+" ");
			   }else{
				   System.out.print(0+" ");
			   }
		   }System.out.println();
	   }
   }
   
   static void print(int[][]map) {
	   for(int[] ma: map) {
		   for(int m : ma) {
			   System.out.print(m+" ");
		   }System.out.println();
	   }
   }
 
   
	   		//물이 증가한 칸에서 물복사 버그 마법
   				//isCloud 모든 칸 순회 
   					//isCloud == true일 경우 물복사 버그
   
   			//구름 생성 
			//구름이 모두 사라짐   
	   
	  
	   
	   
   	
   //m회 반복이 끝난 후 바구니에 들어있는 물의 양 합 구하기  
   
    
    
}