import java.io.*; 

import java.util.*; 


class Result{
	int []result;
	
	public Result(int win, int tie, int lose) {
		result = new int[3];
		result[0]= win;
		result[1] = tie; 
		result[2] = lose;
		
	}
	
	public String toString() {
		return Arrays.toString(result);
	}
	
	
}

class Match{
	int t1, t2; 
	
	public Match(int t1, int t2) {
		this.t1 = t1;
		this.t2 = t2; 
	}
	
	public String toString() {
		return this.t1+" vs "+this.t2; 
	}
}

class Main
{
	static final int WIN =0;
	static final int TIE =1; 
	static final int LOSE = 2;
	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	static Result[][] map = new Result[4][6]; 
	static int result[] = new int[4]; 
	static ArrayList<Match> matchCombination; 
	static boolean[] isUsed; 
	
	static void init() throws IOException{
		for(int match=0; match<4; match++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int nation=0; nation<6; nation++) {
				int win = Integer.valueOf(tokens.nextToken());
				int tie = Integer.valueOf(tokens.nextToken());
				int lose = Integer.valueOf(tokens.nextToken());
				map[match][nation] = new Result(win,tie, lose); 
			}
		}
		matchCombination = new ArrayList<>(); 
		isUsed = new boolean[6];
		
		
		
	}
	
	static void print() {
		StringBuilder sb = new StringBuilder(); 
		for(int i=0; i<4; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.println(sb); 
	}
	
	static int[][] selecteds; 
	static boolean flag; 
	static void bt(int cur, int match) {
		
		if(cur==matchCombination.size()) {
			for(int team=0; team<6; team++) {
				for(int i=0; i<3; i++) {
					if(map[match][team].result[i]!=selecteds[team][i]) {
						return; 
					}
				}
			}
			flag = true; 
			return; 
		}
		
		Match curMatch = matchCombination.get(cur); 
		
		//t1이 이겼을 경우 
		selecteds[curMatch.t1][WIN]++;
		selecteds[curMatch.t2][LOSE]++;
		
		bt(cur+1, match);

		selecteds[curMatch.t1][WIN]--;
		selecteds[curMatch.t2][LOSE]--;
		//t1이 졌을 경우 
		selecteds[curMatch.t2][WIN]++;
		selecteds[curMatch.t1][LOSE]++;
		
		bt(cur+1, match);

		selecteds[curMatch.t2][WIN]--;
		selecteds[curMatch.t1][LOSE]--;
		
		//비겼을 경우 
		selecteds[curMatch.t2][TIE]++;
		selecteds[curMatch.t1][TIE]++;
		
		bt(cur+1, match);
		
		selecteds[curMatch.t2][TIE]--;
		selecteds[curMatch.t1][TIE]--;
		
		
	}
	
	static void makeMatchCombination(int cur, int last, int[] match) {
		if(cur==2) {
			matchCombination.add(new Match(match[0], match[1]));
			return; 
		}
		
		for(int team=last+1; team<6; team++) {
			if(!isUsed[team]) {
				isUsed[team] = true; 
				match[cur] = team; 
				makeMatchCombination(cur+1, team, match);
				isUsed[team] = false; 
			}
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		init(); 
		makeMatchCombination(0, -1, new int[2]); 

		for(int match=0; match<4; match++) {
			selecteds = new int[6][3]; 
			flag = false; 
			bt(0, match);
			if(flag) {
				result[match] = 1;
			}else {
				result[match] = 0; 
			}
		}
		
		
		
		print();
	}
	
}