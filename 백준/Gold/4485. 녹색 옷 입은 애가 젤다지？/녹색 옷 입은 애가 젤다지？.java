
import java.io.*;
import java.util.*;


class Node2 implements Comparable<Node2>{
	int vertex, dist; 
	
	public Node2(int vertex, int dist) {
		this.vertex = vertex; 
		this.dist = dist; 
	}
	
	@Override
	public int compareTo(Node2 o) {
		return this.dist - o.dist; 
	}
	
	public String toString() {
		return this.vertex+":"+this.dist; 
	}
}


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    
    
    static int n; 
    static int[][] map;
    static int[][] nodeNum;
    
    static ArrayList<Node2>[] graph; 
    static int[] dist; 
    
    static final int MAX = (int)1e9; 
    
    
    
    static void init() throws IOException{
    	map = new int[n][n]; 
    	
    	for(int x=0; x<n; x++) {
    		tokens = new StringTokenizer(buffer.readLine());
    		for(int y=0; y<n; y++) {
    			map[x][y] = Integer.valueOf(tokens.nextToken());
    		}
    	}
    }
    
    static void mappingNodeNum() {
    	int count = 0;
    	nodeNum = new int[n][n]; 
    	
    	for(int x=0; x<n; x++) {
    		for(int y=0; y<n; y++) {
    			nodeNum[x][y] = count;
    			count++;
    		}
    	}
    	
    	dist= new int[count+1];
    	Arrays.fill(dist, MAX);
    }
    
    static final int[] dx = {-1,1,0,0};
    static final int[] dy = {0,0,-1,1};
    
    static void makeMapToGraph() {
    	graph = new ArrayList[nodeNum[n-1][n-1]+1];
    	for(int i=0; i<=nodeNum[n-1][n-1]; i++) {
    		graph[i] = new ArrayList<>();
    	}
    	
    	for(int x=0; x<n; x++) {
    		for(int y=0; y<n; y++) {
    			for(int dir=0; dir<4; dir++) {
    				int nX = x + dx[dir];
    				int nY = y + dy[dir]; 
    				
    				if(!inRange(nX, nY)) continue;
    				int start = nodeNum[x][y];
    				int end = nodeNum[nX][nY];
    				int w = map[nX][nY]; 
    				
    				graph[start].add(new Node2(end, w));
    			}
    		}
    	}
    }
    
    static boolean inRange(int x, int y) {
    	return x>=0&&y>=0&&x<n&&y<n;
    }
    
    static void doDijkstra() {
    	PriorityQueue<Node2> pq = new PriorityQueue<>();
    	dist[0] = map[0][0];
    	pq.add(new Node2(0, map[0][0])); 
    	
    	
    	while(!pq.isEmpty()) {
    		Node2 cur = pq.poll(); 
    		
    		if(dist[cur.vertex]!=cur.dist)continue; 
    		
    		for(Node2 target: graph[cur.vertex]) {
    			
    			int newDist = target.dist + dist[cur.vertex];
    			if(dist[target.vertex]>newDist) {
    				dist[target.vertex] = newDist; 
    				pq.add(new Node2(target.vertex, newDist));
    			}
    			
    		}
    		
    	}
    }
    
    public static void main(String[] args) throws IOException{
    	StringBuilder sb = new StringBuilder();
    	n = Integer.valueOf(buffer.readLine());
    	int t = 1; 
    	while(n!=0) {
    		init();
    		mappingNodeNum(); 
    		makeMapToGraph(); 
    		doDijkstra();
    		
    		sb.append("Problem ").append(t).append(": ").append(dist[nodeNum[n-1][n-1]]).append("\n");
    		t++;
    		n = Integer.valueOf(buffer.readLine());
    	}
    	System.out.println(sb);
    	
    	
    	//맵에서 그래프로 바꾸기 
    		//맵과 노드 번호 매칭 
    		//노드 번호 바탕으로 인접 리스트 만들기 
    	
    	//0을 시작점으로 잡는 다익스트라 적용
    		//nloge 
    		// e= n^2     	
    }
    
  
}