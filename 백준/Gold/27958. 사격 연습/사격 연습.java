import java.io.*; 

import java.util.*; 



class Target{
	int point , health;
	
	public Target(int point, int health) {
		this.point = point;
		this.health = health; 
	}
	
	public String toString() {
		return this.health+""; 
	}
}

class Main
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static final int[] dx = {-1,1,0,0}; 
	static final int[] dy = {0,0,-1,1}; 
	
	static int n; 
	static int k; 
	
	static Target[][] map; 
	static int[] bullets;
	static int[] starts; 

	
	
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine());
		k = Integer.valueOf(buffer.readLine());
		
		map = new Target[n][n];
		bullets = new int[k]; 
		starts = new int[k]; 

		
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int y=0; y<n; y++) {
				int num = Integer.valueOf(tokens.nextToken());
				map[x][y] = new Target(num, num); 
			}
		}
		
		tokens = new StringTokenizer(buffer.readLine());
		for(int i=0; i<k; i++) {
			bullets[i] = Integer.valueOf(tokens.nextToken());
		}
		
	}
	
	
	//총알발사(발사 순서, 발사 위치)  
		//숫자 마주치기 전까지 전진 
			//다음칸이 숫자라면?
				//점수가 10 미만일 경우 
					// 다음체력 계산 
						//다음 체력 == 0 
							//map에서 지우기 
							//분신 만들기 
							//점수 업데이트 
						//다음 체력이 남아있을 경우
							//현재 체력에 업데이트 
				//점수가 10 이상일 경우
					//바로 없애기 
	//시작위치 뽑기 
		// 시작위치 순회 	
			// 총알발사 
	
	public static void main(String[] args) throws IOException{
		init();
		
		//시작위치 뽑기 
		bt(0,-1);
		System.out.println(result); 
	}
	
	static boolean inRange(int x,int y) {
		return x>=0&&x<n&&y>=0&&y<n; 
	}
	
	static boolean canGenerate(int x, int y) {
		return inRange(x, y) && map[x][y].point == 0; 
	}
	
	static void generateDummy(int x, int y, int health) {

		if(health <4) {
			return; 
		}
		for(int dir=0; dir<4; dir++) {
			int nX = x+dx[dir];
			int nY = y+dy[dir]; 
			if(canGenerate(nX, nY)) {
				map[nX][nY] = new Target(health/4, health/4); 
			}
		}
	}
	
	static int shoot(int order, int startRow) {
		int score  = 0; 
		for(int y=0; y<n; y++) {
			if(map[startRow][y].health == 0) {
				continue; 
			}
			else if(map[startRow][y].health<10) {
				int nextHealth = map[startRow][y].health - bullets[order];
				if(nextHealth<=0) {
					generateDummy(startRow, y, map[startRow][y].point);
					score += map[startRow][y].point;
					map[startRow][y] = new Target(0,0); 
					
				}else {
					map[startRow][y] = new Target(map[startRow][y].point, nextHealth);
				}
				break;
			}else if(map[startRow][y].health>=10) {
				score += map[startRow][y].point;
				map[startRow][y] = new Target(0,0); 
				break; 
			}
		}

		return score; 
	}
	
	static int result = Integer.MIN_VALUE; 
	//n개 중 k 개 뽑기  0~n
	static void bt(int cur, int last) {
		
		if(cur==k) {
			Target[][] tmpOrigin = new Target[n][n]; 
			for(int x=0; x<n; x++) {
				tmpOrigin[x] = Arrays.copyOf(map[x], n);
			}
			int score = 0; 

			for(int i=0; i<k; i++) {
				score += shoot(i, starts[i]);

			}
			
			result = Math.max(result, score); 
			for(int x=0; x<n; x++) {
				map[x] = Arrays.copyOf(tmpOrigin[x], n);
			}
			return; 
		}
		
		for(int next=0; next<n; next++) {
			
				starts[cur]= next; 
				bt(cur+1, next);
			
		}
		
		
	}
	//총알 
		//공격력 정보 포함 1~100 
	
	//사격 
		//1~N행 중 하나의 행을 선택 
		//가장 왼쪽 열에서 시작해 왼
	//표적 
		//1이상의 체력 
		//총알이 표적에 닿으면 현재 체력이 공격력만큼 감소 
		//총알은 표적을 관통하지 못하고, 표적에 닿은 즉시 사라짐 
		//0 이하가 되면 표적은 사라짐 
		//초기 체력만큼 점수를 얻음 
		//상하 좌우 위치중 빈칸에 대해 초기 체력의 1/4값을 초기 체력으로 갖는 표적 생성 
			//0이하인 경우 생성하지 않음 
	
	//초기체력이 10이상인 표적은 보너스 표적 
		//보너스 표적을 맞히는 순간 총알의 공격력과 상관없이 보너스 표적이 사라짐 
		//점수만큼 바로 점수 
		//보너스 표적은 새로운 표적을 생성하지 않음 
	
	static void printMap() {
		for(Target[] ma : map) {
			for(Target m : ma) {
				System.out.print(m+" ");
			}System.out.println(); 
		}
	}
	
	static void printOriginMap() {
		for(Target[] ma : map) {
			for(Target m : ma) {
				System.out.print(m.point+" ");
			}System.out.println(); 
		}
	}
	
}