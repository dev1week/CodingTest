
import java.io.*;
import java.util.*;

class BC{
	int x, y, c, p; 
	
	public BC(int x, int y, int c, int p) {
		this.x =x;
		this.y = y; 
		this.c = c; 
		this.p = p; 
	}
	
	public String toString() {
		return x+":"+y+"에서 범위는 "+c+" 충전량 " +p; 
	}
}

class Player {
	int x, y;
	static final int[] dx = {0, -1, 0, 1, 0};
	static final int[] dy = {0,  0, 1, 0, -1}; 
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y; 
	}
	
	public String toString() {
		return this.x+":"+this.y; 
	}
	
	
	public void move(int dir) {
		this.x =this.x + dx[dir];
		this.y= this.y + dy[dir];
	}
	
	
	public boolean isAvailable(BC bc) {
		return bc.c>= (Math.abs(bc.x-this.x)+Math.abs(bc.y-this.y));
	}
	
}

public class Solution {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    

    
    //사용자의 초기 위치부터 충전 가능 
    
    //bc
    	//위치 
    	//충전 범위 (거리가 이하까지 가능 )
    	//성능 
    	//겹쳐 있으면 둘 중 하나만 접속 가능 
    	//2명이 동에 접속한 경우 충전양 균등 분배 
    	//같은 위치에 2개 이상 설치된 경우는 없음 
    
    
    //a는 1,1 지점에서 출발 
    //b는 10, 10 지점에서 출발 
    
    //두 지점 사이의 거리 = 맨해튼 거리
    
    static final int SIZE = 10; 
    static int m; //~100 총 이동 시간 
    static int A; // bc의 개수 
    static Player a;
    static Player b; 
    static int[] commandsA;
    static int[] commandsB; 

    static BC[] bcs; 
    
    static int result; //모든 사용자의 충전량 합의 최댓값 
    
    static void init() throws IOException{
    	tokens = new StringTokenizer(buffer.readLine()); 
    	m = Integer.valueOf(tokens.nextToken()); 
    	A = Integer.valueOf(tokens.nextToken()); 
    	
    	commandsA = new int[m]; 
    	commandsB = new int[m]; 

    	
    	bcs = new BC[A]; 
    	tokens = new StringTokenizer(buffer.readLine());
    	for(int time=0; time<m; time++) {
    		commandsA[time] = Integer.valueOf(tokens.nextToken());
    	}
    	tokens = new StringTokenizer(buffer.readLine());
    	for(int time=0; time<m; time++) {
    		commandsB[time] = Integer.valueOf(tokens.nextToken());
    	}
    	
    	
    	for(int i=0; i<A; i++) {
    		tokens = new StringTokenizer(buffer.readLine()); 
    		int y= Integer.valueOf(tokens.nextToken());
    		int x= Integer.valueOf(tokens.nextToken());
    		int c= Integer.valueOf(tokens.nextToken());
    		int p= Integer.valueOf(tokens.nextToken());
    		bcs[i] = new BC(x,y,c,p); 
    	}
    	
    	
    	
    	a = new Player(1,1);;
    	b = new Player(10,10);
    }
    
    static ArrayList<BC> getBCList(Player p){
    	
    	ArrayList<BC> possibles = new ArrayList<>(); 
    	
    	for(BC bc : bcs) {
    		if(p.isAvailable(bc)) {
    			possibles.add(bc); 
    		}
    	}
    	
    	return possibles; 
    	
    	
    }
    
    public static void main(String[] args)throws IOException{
    	int T = Integer.valueOf(buffer.readLine());
    	
    	StringBuilder sb = new StringBuilder(); 
    	
    	for(int t=1; t<=T; t++) {
    		init(); 
    		int result = 0;
    		for(int time=0; time<=m; time++) {
    			//a의 현재 위치에서 접속 가능한 bc 뽑기 
    			ArrayList<BC> possibleToA = getBCList(a);
    			//b의 현재 위치에서 접속 가능한 bc 뽑기 
    			ArrayList<BC> possibleToB = getBCList(b);
    			
    			result += getPower(possibleToA, possibleToB); 
    			
    			//a와 b 리스트로 가능한 조합 뽑기 
    				//현재 시간에서의 최대 충전량 구하기 
    				//a==0일 때 
    				//b==0일 때 
    				//둘다 0일 때 
    				//둘다 0이 아닐 때 
    			
    			//배열에 넣어주기 
    			
    			//a 이동 시키기 
    			if(time!=m) {
    				a.move(commandsA[time]);
        			//b 이동 시키기 
        			b.move(commandsB[time]);
    			}
    			
    		}
    		
    		//로그 배열에서 충전량 총합 구하기 
    		
    		sb.append("#").append(t).append(" ").append(result).append("\n");
    	}
    	System.out.println(sb);
    }
    
    
    static int getPower(ArrayList<BC> aList, ArrayList<BC> bList) {
    	int totalPower = 0; 
    	if(aList.size()==0&&bList.size()==0) {
    		return 0; 
    	}
    	//둘다 없을 경우 
    	else if(bList.size()==0) {
    		for(BC aSelected : aList) {
    			totalPower = Math.max(aSelected.p, totalPower);
    		}
    		return totalPower; 
    	}
    	//aList가 0일 경우 
    	else if(aList.size()==0) {
    		for(BC bSelected : bList) {
    			totalPower = Math.max(bSelected.p, totalPower);
    		}
    		return totalPower; 
    	}else {
    		for(BC aSelected : aList) {
    			for(BC bSelected : bList) {
    				if(aSelected == bSelected) {
    					totalPower = Math.max(totalPower, aSelected.p);
    				}else {
    					totalPower = Math.max(totalPower, aSelected.p + bSelected.p);
    				}
    			}
    		}
    		return totalPower; 
    	}
    	//bList가 0일 경우 
    	
    	//둘다 0이 아닐 경우 
    }
   
    
    	
}