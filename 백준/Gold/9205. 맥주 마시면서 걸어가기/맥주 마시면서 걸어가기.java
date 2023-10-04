import java.io.*;
import java.util.*;



class Point{
	int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return this.x +":"+this.y; 
	}
	
	public int getDistnace(Point other) {
		return Math.abs(this.x-other.x) + Math.abs(this.y-other.y); 
	}
}

 class Main {
	 
	
	 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static Point[] nodes; 
	static int n; 
	static ArrayList<Integer>[] graph; 
	
	//거리가 1000이하인 모든 노드끼리 그래프형태로 만들어 놓기 
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine()); 
		graph = new ArrayList[n+2]; 
		nodes = new Point[n+2]; 
		for(int i=0; i<n+2; i++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			int x = Integer.valueOf(tokens.nextToken());
			int y = Integer.valueOf(tokens.nextToken());
			
			nodes[i] = new Point(x,y); 
		}
		
		for(int i=0; i<n+2; i++) {
			graph[i] = new ArrayList<Integer>(); 
		}
				
		for(int start=0; start<n+2; start++) {
			for(int end=0;end<n+2; end++) {

				if(start==end)continue;
				if(nodes[start].getDistnace(nodes[end]) <=1000) {
					graph[start].add(end);
				}
			}
		}
		isVisited = new boolean[n+2];
		result = false; 
		
	}
	
	static boolean[] isVisited; 
	static boolean result; 
	
	static boolean bfs() {
		ArrayDeque<Integer> que = new ArrayDeque<>(); 
		
		isVisited[0] = true; 
		que.add(0);
		
		while(!que.isEmpty()){
			int cur = que.poll(); 
			if(nodes[cur].x==nodes[n+1].x && nodes[cur].y == nodes[n+1].y) {
				return true; 
			}
			
			for(int neighbor : graph[cur]) {
				if(!isVisited[neighbor]) {
					isVisited[neighbor] = true; 
					que.add(neighbor);
				}
			}
			
		}
		return false; 
	}
	
	
	
	
	public static void main(String[] args) throws IOException{
	
		int t= Integer.valueOf(buffer.readLine()); 
		
		StringBuilder sb = new StringBuilder(); 
		//50
		for(int test=0; test<t; test++) {
			init(); 
			
			
			//0번 노드에서부터 시작 
			 
			if(bfs()) {
				sb.append("happy\n");
			}else {
				sb.append("sad\n");
			}
			
	
		}
		System.out.println(sb);
		
	}
	
	
	
}
