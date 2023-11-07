
import java.io.*;
import java.util.*; 
class Robot{
	int position; 
	
	public Robot(int position) {
		this.position = position; 
	}
	
	@Override
	public String toString() {
		return "이 로봇의 위치는"+position+"입니다.";
	}	
	
	
	public void rotateWithBelt(int n) {
		int nextPos = position+1;
		
		if(nextPos==2*n) {
			position = 0; 
		}else {
			position = nextPos; 
		}
		
	}
	
}



public class Main {
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	

	//1번 칸이 있는 위치 = 올리는 위치 
	//n번 칸이 있는 위치 = 내리는 위치 
		//언제든지 로봇이 도달하면 즉시내린다. 
	
	// 로봇을 올리는 위치에 올리거나 이동하면 내구도가 즉시 1만큼 감소한다. 
	
	
	
	
	
	
	static int n; 
	static int k; 
	
	static int[] health; 
	static int[] isRobotOn; 
	static List<Robot> robots; 
	static int stage; 
	
	
	
	static void init() throws IOException{
		
		stage = 1; 
		tokens = new StringTokenizer(buffer.readLine()); 
		n = Integer.valueOf(tokens.nextToken());
		k = Integer.valueOf(tokens.nextToken()); 
		health = new int[2*n];
		isRobotOn = new int[2*n]; 
		robots = new ArrayList<>();
		
		tokens = new StringTokenizer(buffer.readLine()); 
		
		for(int i=0; i<2*n; i++) {
			health[i] = Integer.valueOf(tokens.nextToken()); 
		}
		
	}
	
	
	// 각 벨트가 각 칸위에 있는 로봇과 함께 한칸 회전한다. 
	static void rotate() {
		int lastHealth = health[2*n-1]; 
		int lastRobotOn = isRobotOn[2*n-1]; 
		
		
		for(int i=2*n-1; i>=1; i--) {
			health[i] = health[i-1]; 
			isRobotOn[i] = isRobotOn[i-1]; 
		}
		
		health[0] = lastHealth; 
		isRobotOn[0] = lastRobotOn; 
		
		for(int i=robots.size()-1; i>=0; i--) {
			robots.get(i).rotateWithBelt(n); 
			if(robots.get(i).position==n-1) {
				isRobotOn[robots.get(i).position]--; 
				robots.remove(i); 
			}

		}
		
	}

	//가장 먼저 벨트에 올라간 로봇부터 
		//벨트가 회전하는 방향으로 한칸 이동할 수 있는가? => 이동하는 칸에 로봇이 없으며 내구도가 1이상이어야한다. 
			//이동한다. -> 해당 벨트의 내구도를 1 감소시킨다. 
			//이동할 수 없다면 가만히 있는다. 
		//로봇이 내리는 칸인가? 
			//내린다 
	
	static void moveRobots() {
		
		
		List<Robot> nextRobots = new ArrayList<>(); 
		for(int i=0; i<robots.size();i++) {
			int nextPosition = robots.get(i).position+1; 
			
			if(nextPosition==2*n) {
				nextPosition = 0; 
			}
			
			if(isRobotOn[nextPosition]==0&&health[nextPosition]>=1) {
				isRobotOn[robots.get(i).position]--; 
				isRobotOn[nextPosition]++; 
				health[nextPosition]--;
				robots.get(i).position = nextPosition; 
			}
			
			if(robots.get(i).position == n-1) {
				isRobotOn[robots.get(i).position]--; 
				continue; 
			}; 
			
			nextRobots.add(robots.get(i)); 
		}
		
		robots = nextRobots; 
		
		
	}
	
	//올리는 칸의 내구도가 0인가?
	//로봇을 올린다. 
	public static void setUpRobot() {
		if(health[0]!=0) {
			robots.add(new Robot(0)); 
			health[0] --;
			isRobotOn[0]++; 
		}
	}
	
	static boolean isEnd() {
		int count =0 ; 
		for(int h : health) {
			if(h==0){
				count ++; 
			}
		}
		if(count>=k) {
			return true; 
		}else {
			return false; 
		}
	}
	
	
	static void print(String info) {
		System.out.println(info); 
		System.out.println("내구도");
		System.out.println(Arrays.toString(health)); 
		System.out.println("벨트 위 로봇 개수");
		System.out.println(Arrays.toString(isRobotOn)); 
		System.out.println("로봇들"); 
		System.out.println(robots); 
	}
	
	public static void main(String[] args) throws IOException{
		init(); 
		
		
		while(true) {
			
//			System.out.println(stage); 
			rotate(); 
//			print("회전후");
			
			moveRobots(); 
//			print("로봇들이 각자 움직인 후"); 
			
			
			setUpRobot(); 
//			print("로봇을 올린 후"); 
			
			if(isEnd()) {
				break;
			}
			
			stage++; 
			
		}
		
		System.out.println(stage); 
		
		
		
		
		//stage ++ 
		
		

		
		
		
		//내구도가 0인 칸의 개수가 k개 이상인가?
			//과정을 종료한다. 
	}
}
