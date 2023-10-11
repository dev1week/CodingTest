

import java.io.*;
import java.util.*;


class Group implements Comparable<Group>{
	int x, y, size, dir; 
	
	static final char[] dirChars = {'상', '하', '좌', '우'}; 
	static final int[] dx = {-1,1,0,0};
	static final int[] dy = {0,0,-1,1}; 
	
	public Group(int x, int y, int size, int dir) {
		this.x = x;
		this.y = y;
		this.size = size; 
		this.dir = dir; 
	}
	
	public String toString() {
		return this.x+":"+this.y+","+this.size+" "+dirChars[dir]; 
	}
	
	public int compareTo(Group o) {
		return o.size - this.size; 
	}
	
	
	public void move() {
		this.x += dx[dir];
		this.y += dy[dir]; 
	}
	
	public boolean isSide(int n) {
		return x==0||y==0||x==n-1||y==n-1; 
	}
	
	public void turn180(){
		if(this.dir==0) {
			this.dir = 1; 
		}else if(this.dir==1) {
			this.dir = 0; 
		}else if(this.dir==2) {
			this.dir = 3; 
		}else if(this.dir==3) {
			this.dir = 2; 
		}
	}
	
}


public class Solution {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    
    static int k; //미생물 군집 개수 
    static int n; //
    static int m; //m 시간 후 남아있는 미생물수 총합 
    static int result; 
    
    static ArrayList<Group>[][] map; 
    static ArrayList<Group> groups; 
    
    static void init() throws IOException{
    	tokens = new StringTokenizer(buffer.readLine());
    	n = Integer.valueOf(tokens.nextToken());
    	m = Integer.valueOf(tokens.nextToken());
    	k = Integer.valueOf(tokens.nextToken()); 
    	
    	groups = new ArrayList<Group>(); 
    	
    	map = new ArrayList[n][n]; 
    	
    	for(int x=0; x<n; x++) {
    		for(int y=0; y<n; y++) {
    			map[x][y] = new ArrayList<Group>(); 
    		}
    	}
    	
    	for(int i=0; i<k; i++) {
    		tokens = new StringTokenizer(buffer.readLine()); 
    		
    		int x= Integer.valueOf(tokens.nextToken());
    		int y = Integer.valueOf(tokens.nextToken()); 
    		int size = Integer.valueOf(tokens.nextToken()); 
    		int dir = Integer.valueOf(tokens.nextToken())-1;
    		
    		groups.add(new Group(x,y,size,dir));
    	}
    }
    
    static void moveGroups() {
    	for(Group g: groups) {
    		g.move(); 
    		if(g.isSide(n)) {
    			g.size /=2; 
    			g.turn180(); 
    		}
			map[g.x][g.y].add(g);
    	}
    }
    
    static ArrayList<Group> mergeGroups() {
    	
    	ArrayList<Group> nextGroups = new ArrayList<>(); 
    	
    	
    	for(int x=0; x<n; x++) {
    		for(int y=0; y<n; y++) {
    			if(map[x][y].size()>1) {
    				int maxSize = -1;
    				int maxDir = -1;
    				int totalSize = 0; 
    				for(Group g: map[x][y]) {
    					if(g.size>maxSize) {
    						maxSize = g.size; 
    						maxDir = g.dir; 
    					}
    					
    					totalSize += g.size; 
    				}
    				nextGroups.add(new Group(x,y,totalSize, maxDir));
    				
    			}else if(map[x][y].size()==1) {
    				nextGroups.add(map[x][y].get(0));
    			}
    		}
    	}
    	
    	return nextGroups; 
    	
    }
    
    static void clear() {
    	for(int x=0; x<n; x++) {
    		for(int y=0; y<n; y++) {
    			map[x][y].clear(); 
    		}
    	}
    }
    
    public static void main(String[] args) throws IOException{
    	int T = Integer.valueOf(buffer.readLine()); 
    	
    	StringBuilder sb = new StringBuilder(); 
    	
    	for(int test=1; test<=T; test++) {
    		init(); 
    		for(int time =0; time<m; time++) {
    			
        		//10^3 
        		//미생물 리스트 
        			//미생물 이동 
    				//사이드일 경우 미생물 사이즈 변경 
        			//사이즈가 1 이상일 경우에만 맵에 넣어주기 
        		moveGroups(); 
        	
        		groups = mergeGroups(); 
        		//10^4 * 10^3 
        		//nextMap = 한바퀴 순회하면서 병합하기 
        			//맵 전체 순회 	
        				//2이상이면 병합하여 새로운 노드 만들기 
        					//새로운 그룹을 미생물 리스트에 넣어주기 
        				//1이면 
        					//해당 그룹을 미생물 리스트에 넣어주기 
        		 clear(); 
    		}
    		sb.append("#").append(test).append(" ").append(getTotal()).append("\n");
    	}
    	System.out.println(sb); 
    }
    
    static int getTotal() {
    	int result = 0; 
    	
    	for(Group g : groups) {
    		result += g.size; 
    	}
    	return result; 
    }
    
    //약품 
    	//가장 자리에 위치 
    
    
    //미생물 군집
    	//위치, 군집 내 미생물의 수, 이동방향
    
    //이동 
    	//1시간마다 이동함 
    
    //약품이 칠해진 셀에 도착 -> 군집 내 미생물의 절반이 죽고 이동방향이 반대로 바뀜 
    	//살아남은 미생물이 0마리인 경우 군집이 사라짐 
    
    //두 개 이상의 군집이 한 셀에 모이는 경우 군집들이 합쳐짐 
    	//미생물 수 : 각각의 미생물 수 총합 
    	//미생물 수가 가장많은 군집의 이동방향 
    
    
     
    
    
}
