import java.io.*; 

import java.util.*; 


class Point {
	int x, y;
	
	static final int[] dx = {0,-1,0,1,0};
	static final int[] dy = {0,0,1,0,-1}; 
	
	public Point(int x, int y) {
		this.x = x-1;
		this.y = y-1;
	}
	
	public int getDistance(Point o) {
		return Math.abs(this.x-o.x) + Math.abs(this.y-o.y); 
	}
	
	public String toString() {
		return this.x+":"+this.y; 
	}
	
	public void move(int dir) {
		int nX = this.x+ dx[dir];
		int nY = this.y + dy[dir]; 
		if(inRange(nX, nY)) {
			this.x = nX;
			this.y = nY; 
		}
	}
	
	static boolean inRange(int x, int y) {
		return x>=0&&y>=0&&x<10&&y<10; 
	}
}

class BC{
	int[][] coverage;
	int p;
	
	static final int[] dx = {-1,1,1,-1};
	static final int[] dy = {-1,-1,1,1}; 
	
	public BC(int y, int x, int c, int p) {
		this.coverage = new int[10][10]; 
		this.p = p; 
		//중심점부터 시작하여 대각선 굴리기 
		this.coverage[x-1][y-1] = p; 
		for(int length=1; length<=c; length++) {
			int nX = x-1;
			int nY = y+ length-1; 
			for(int dir=0; dir<4; dir++) {
				for(int l=0; l<length; l++ ) {
					nX += dx[dir];
					nY += dy[dir]; 
					if(inRange(nX, nY)) {
						this.coverage[nX][nY] = p; 
					}
				}
			}
		}
	}
	
	static boolean inRange(int x, int y) {
		return x>=0&&y>=0&&x<10&&y<10; 
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		
		for(int x=0;x<10; x++) {
			for(int y=0; y<10; y++) {
				sb.append(" ").append(this.coverage[x][y]).append(" ");
			}sb.append("\n");
		}
		
		return sb.toString(); 
	}
	
	
}

class Solution
{	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	
	//충전 가능 범위 
		
	
	//거리 구하기 
	
	static int m; //총 이동 시간 
	static int a; //bc의 개수 
	static int sum; 
	
	static int[][] commands;
	static int[] log; 
	static boolean[][] isBCUsed; 
	static Point[] people; 
	static BC[] bcs; 
	
	
	static final int UP =1;
	static final int RIGHT = 2;
	static final int DOWN = 3;
	static final int LEFT =4; 
	
	static final int[] dx = {0,-1,0,1,0};
	static final int[] dy = {0,0,1,0,-1}; 
	
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine());
		m = Integer.valueOf(tokens.nextToken());
		a = Integer.valueOf(tokens.nextToken()); 
		
		people = new Point[2];
		log = new int[m+1]; 
		commands = new int[2][m+1];
		bcs = new BC[a]; 
		isBCUsed = new boolean[2][a];
		for(int p=0; p<2; p++) {
			tokens= new StringTokenizer(buffer.readLine()); 
			for(int t=1; t<=m; t++) {
				commands[p][t] = Integer.valueOf(tokens.nextToken()); 
			}
		}
		
		people[0] = new Point(1,1);
		people[1] = new Point(10,10);
		
		for(int i=0; i<a; i++) {
			tokens = new StringTokenizer(buffer.readLine());
			bcs[i] = new BC(Integer.valueOf(tokens.nextToken()),
					Integer.valueOf(tokens.nextToken()),
					Integer.valueOf(tokens.nextToken()),
					Integer.valueOf(tokens.nextToken()));

		}
		
		

	}
	
	static int[] selecteds;
	static int[] userCounts; 
	static void bt(int people) {
		
		if(people==2) {
			int tmp = 0;

			for(int i=0; i<2; i++) {
				if(userCounts[selecteds[i]]==0) {return;}
				tmp += bcs[selecteds[i]].p/userCounts[selecteds[i]];
			}
			sum = Math.max(tmp,sum);
			return; 
		}
		
		for(int bcIdx=0; bcIdx<a; bcIdx++) {
			if(isBCUsed[people][bcIdx]) {
				selecteds[people] = bcIdx;
				userCounts[bcIdx]++;
				bt(people+1);
				userCounts[bcIdx]--;
			}
		}
	}
	static int result; 
	public static void main(String[] args)throws IOException{
		int T = Integer.valueOf(buffer.readLine());
		StringBuilder format = new StringBuilder(); 
		for(int test=1; test<=T; test++) {
			init();
			
			
			for(int time=0; time<=m; time++) {
				isBCUsed = new boolean[2][a]; 
				
				//이동 후 사용중인 BC 표시하기 
				for(int person=0; person<2; person++) {
					
					//커맨드 따라 움직임
					int command = commands[person][time]; 
					people[person].move(command);
					Point p = people[person]; 

					for(int bcIdx = 0; bcIdx<a; bcIdx++) {
	
						if(bcs[bcIdx].coverage[p.x][p.y]>0) {
							isBCUsed[person][bcIdx] = true; 
						}
					}
				}
				//최적의 BC 분배하기 
				
				boolean flag = false; 
				for(int bcIdx=0; bcIdx<a; bcIdx++) {
					int r = 0; 
					for(int p=0; p<2; p++) {
						if(isBCUsed[p][bcIdx]) {
							r++;
						}
					}
					if(r==2) {
						flag = true;
						break; 
					}
				}
				
				sum  =0; 
				
				if(flag) {
					//같은거 2개 동시에 쓰는 경우에만 해당 
					//백트래킹으로 2개 경우의 수 뽑기
					selecteds = new int[2]; 
					userCounts = new int[a]; 
					bt(0);
				}else {
					for(int p=0; p<2; p++) {
						int ind = 0; 
						for(int bcIdx=0; bcIdx<a; bcIdx++) {
							if(isBCUsed[p][bcIdx]) {
								ind = Math.max(ind, bcs[bcIdx].p);
							}
						}
						sum += ind; 
					}
				}
//				System.out.println(Arrays.toString(people)); 
//				System.out.println(flag);
//				System.out.println(Arrays.toString(isBCUsed[0]));
//				System.out.println(Arrays.toString(isBCUsed[1]));
//				System.out.println(sum);
				
				//갱신 
				log[time]= sum; 
				result = 0;
				for(int l : log) {
					result += l; 
				}
			}

			format.append("#").append(test).append(" ").append(result).append("\n"); 
		}
		
		System.out.println(format); 
		
		
	}
	
	
}