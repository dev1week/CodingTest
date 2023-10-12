

import java.io.*;
import java.util.*;


class Node implements Comparable<Node>{
	int vertex, dist;
	
	public Node(int vertex, int dist) {
		this.vertex = vertex; 
		this.dist = dist; 
	}
	
	@Override
	public int compareTo(Node o) {
		return this.dist - o.dist; 
	}
	
	@Override 
	public String toString() {
		return "-"+this.dist+"->"+this.vertex; 
	}
}

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
   
    
    static ArrayList<Node>[] graph; 
    static int n; 
    static int m; 
    static long total;
    static void init() throws IOException{
    	tokens = new StringTokenizer(buffer.readLine());
    	
    	
    	n = Integer.valueOf(tokens.nextToken()); 
    	m = Integer.valueOf(tokens.nextToken()); 
    	
    	graph = new ArrayList[n+1]; 
    	
    	
    	for(int node=0; node<=n; node++) {
    		graph[node] = new ArrayList<>(); 
    	}
    	
    	total = 0; 
    	for(int i=0; i<m; i++) {
    		tokens = new StringTokenizer(buffer.readLine()); 
    		
    		int node1 = Integer.valueOf(tokens.nextToken());
    		int node2 = Integer.valueOf(tokens.nextToken()); 
    		int w = Integer.valueOf(tokens.nextToken()); 
    		
    		graph[node1].add(new Node(node2, w));
    		graph[node2].add(new Node(node1, w));
    		total +=w; 
    	}
   
    }
    
    public static void main(String[] args) throws IOException{
    	init(); 
    	
    	
    	long result = 0; 
    	boolean isVisited[] = new boolean[n+1]; 
    	long[] dist = new long[n+1];
    	Arrays.fill(dist, (int)1e9); 
    	PriorityQueue<Node> pq = new PriorityQueue<>(); 
    	
    	pq.add(new Node(1,0)); 
    	dist[1] = 0;
    	
    	while(!pq.isEmpty()) {
    	 
    		Node cur = pq.poll(); 
    		if(!isVisited[cur.vertex]) {

    			isVisited[cur.vertex] = true; 
        		result += cur.dist; 
        		
        		for(Node target: graph[cur.vertex]) {
        			if(dist[target.vertex]>target.dist) {
            			pq.add(new Node(target.vertex, target.dist)); 
            			dist[target.vertex] = target.dist;
        			}
        			
        			
        		}
    		}

    	}
    	
    	if(isPossible(isVisited)) {
        	System.out.println(total-result); 

    	}else {
    		System.out.println(-1); 
    	}
    	
    	
    	
    }
    
    static boolean isPossible(boolean[] isVisited) {
    	for(int node=1; node<=n; node++) {
    		if(!isVisited[node])return false; 
    	}
    	return true; 
    }
    
}

    

