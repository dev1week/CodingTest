import java.io.*; 

import java.util.*; 

class Point {
	int x, y;
	
	public Point(int x, int y) {
		this.x =x;
		this.y =y;
	}
	
	public Point(Point p) {
		this.x = p.x;
		this.y = p.y; 
	}
	
	public int getDistance(Point o) {
		return Math.abs(o.x-this.x) + Math.abs(o.y-this.y); 
	}
	
	@Override
	public String toString() {
		return this.x+":"+this.y; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return this.x==other.x&&this.y==other.y;
	}

	public void move(int n) {
		this.x++; 
		if(this.x>n) {
			this.x=n; 
		}
	}
}

class Target extends Point implements Comparable<Target>{
	int idx; 
	int d; 
	public Target(Point p, int idx, int d) {
		super(p); 
		this.idx = idx;
		this.d = d;
	}

	
	@Override
	public int compareTo(Target o) {
		if(this.d==o.d) {
			return this.y-o.y; 
		}
		return this.d-o.d; 
	}
	@Override
	public String toString() {
		return super.toString()+" "+d+" 거리에 위치합니다.";
	}

	
}

class Main
{	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	
	static int n; 
	static int m; 
	static int limit; 
	static ArrayList<Point> originalEnemies; 
	static final int ENEMY = 1; 
	static boolean[] isUsed; 
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken()); 
		limit = Integer.valueOf(tokens.nextToken()); 
		isUsed = new boolean[m]; 
		originalEnemies = new ArrayList<>(); 
		
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int y=0; y<m; y++) {
				int data = Integer.valueOf(tokens.nextToken()); 
				if(data==ENEMY) {
					originalEnemies.add(new Point(x,y)); 
				}
			}
		}
		
		selecteds = new Point[3]; 
	}
	
	

	//궁수 위치로 공격
		//궁수 타겟 배열생성 
		//궁수 리스트 순회 
			//적 리스트 순회 
				//거리를 구해 d이하인 적을 타겟 후보에 넣어준다. 
			//해당 궁수의 최소 거리 타겟을 구한다. 
			//궁수타겟[궁수인덱스] = 최소 거리 타겟 
		//궁수 타겟 순회 
			// 타겟의 포인트 == 적 리스트.get(타겟.idx)의 포인트 일 경우에만 삭제 
			//데드 카운트 ++ 
	static void attack() {
		Target[] finalTargets = new Target[3];
		HashSet<Point> filter = new HashSet<>(); 
		
		for(Point e: simulatingEnemies) {
			filter.add(e);
		}
		for(int a=0; a<3; a++) {
			Point archer = selecteds[a]; 
			ArrayList<Target> targets = new ArrayList<>();
			for(int e=0; e<simulatingEnemies.size(); e++) {
				Point enemy = simulatingEnemies.get(e);
				int d = enemy.getDistance(archer);
				if(limit>=d) {
					targets.add(new Target(enemy, e, d));
				}
			}
			Collections.sort(targets);
//			System.out.println(archer+"의 타겟"+targets);
			if(targets.size()>0) {
				finalTargets[a] = targets.get(0);
			}
		}
		
		for(Point target: finalTargets) {
			if(target==null)continue; 
			filter.remove(new Point(target.x, target.y));
		}
		killCount+=(simulatingEnemies.size()-filter.size()); 
//		System.out.println(killCount); 
		simulatingEnemies = new ArrayList<>();
		for(Point e : filter) {
			simulatingEnemies.add(e); 
		}
		
		
	}
	
	
	static void moveEnemies() {
		for(int i=simulatingEnemies.size()-1; i>=0; i--) {
			Point enemy = simulatingEnemies.get(i); 
			enemy.move(n);
			if(enemy.x==n) {
				simulatingEnemies.remove(i);
			}
		}
	}
	
	//적 이동 
		//적리스트 순회 
			// 적을 한번씩 움직인다. 
			//해당 적이 성에 도달하면 삭제한다. 
	
	//시뮬레이션 
		//데드 카운트 = 0 초기화 
		//적 리스트 빌 때까지 
			//궁수 위치로 공격 
			//적 이동 
	static int killCount;
	static int result; 
	static ArrayList<Point> simulatingEnemies;
	
	static void simluation() {
		killCount = 0;
		simulatingEnemies = new ArrayList<>();
		
		for(Point e: originalEnemies) {
			simulatingEnemies.add(new Point(e.x, e.y)); 
		}
		
		
		while(simulatingEnemies.size()!=0) {
//			System.out.println("공격전");
//			System.out.println(simulatingEnemies);
			attack(); 
//			System.out.println("공격후");
//			System.out.println(simulatingEnemies);
			moveEnemies(); 
//			System.out.println("이동후");
//			System.out.println(simulatingEnemies);
		}
//		System.out.println(killCount); 
		result = Math.max(result, killCount);
	}
	
	
	static Point[] selecteds; 
	static void selectPosition(int cur, int last) {
		if(cur==3) {
//			System.out.println("궁수 위치");
//			System.out.println(Arrays.toString(selecteds)); 
			simluation(); 
			return; 
		}
		
		for(int next=last+1; next<m; next++) {
			if(!isUsed[next]) {
				isUsed[next] = true;
				selecteds[cur] = new Point(n,next); 
				selectPosition(cur+1, next); 
				isUsed[next] = false; 
			}
		}
	}
	
	//m개 중 3개 뽑는다면 
		// 시뮬레이션 진행 
	
	public static void main(String[] args) throws IOException{
		init(); 
		//궁수 위치 배치 (백트래킹) m개 중 3개 뽑기 
		selectPosition(0,-1); 
		System.out.println(result); 
		
	}
	
	
}