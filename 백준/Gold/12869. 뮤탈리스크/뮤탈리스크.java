import java.io.*; 
import java.util.*; 
class Main
{	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static ArrayList<Integer[]> attackPatterns; 
	static boolean[] isUsed;
	static final int[] attacks = {9,3,1}; 
	static int[] scvs; 
	static int n; 
	static int[][][] dp; 
	
	
	static void init() throws IOException{
		isUsed = new boolean[3]; 
		attackPatterns = new ArrayList<>(); 
		
		n = Integer.valueOf(buffer.readLine());
		scvs = new int[3]; 
		
		tokens = new StringTokenizer(buffer.readLine()); 
		for(int i=0; i<n; i++) {
			scvs[i] = Integer.valueOf(tokens.nextToken());
		}
		
		dp = new int[61][61][61]; 
		
	}
	static Integer[] selecteds= new Integer[3]; 
	
	static void makeAttackPattern(int cur) {
		if(cur==3) {
			Integer tmp[] = new Integer[3]; 
			
			for(int i=0; i<3; i++) {
				tmp[i] = selecteds[i]; 
			}
			
			attackPatterns.add(tmp); 
			
			return; 
		}
		
		for(int next=0; next<3; next++) {
			if(!isUsed[next]) {
				isUsed[next] = true; 
				selecteds[cur] = next; 
				makeAttackPattern(cur+1); 
				isUsed[next] = false; 
			}
		}
		
		
	}
	static int result = Integer.MAX_VALUE;
	static void dfs(int scv1, int scv2, int scv3, int attackCount) {
		if(scv1<0) scv1 = 0;
		if(scv2<0) scv2 = 0;
		if(scv3<0) scv3 = 0; 
		
		
		//전달받은 값보다 더 작은 값이 들어가 있는 경우
		if(dp[scv1][scv2][scv3]<=attackCount&&dp[scv1][scv2][scv3]!=0) {
			return;
		}
		else {
			dp[scv1][scv2][scv3] = attackCount; 
		}
		
		if(scv1<=0&&scv2<=0&&scv3<=0) {
			result = Math.min(result, attackCount);
			return; 
		}
		
		
		for(Integer[] attackPattern : attackPatterns) {
			dfs(scv1-attacks[attackPattern[0]], scv2-attacks[attackPattern[1]], scv3-attacks[attackPattern[2]], attackCount+1);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		//공격패턴 6 미리 정의하기 
		init(); 
		
		makeAttackPattern(0); 
		
		dfs(scvs[0], scvs[1], scvs[2], 0); 
		//dp[scv1][scv2][scv3] = 해당 체력이 될 때까지의 최소 공격횟수 
		
		
		
		System.out.println(result); 
		
	}
}