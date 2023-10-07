
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
    static int m; 
    static int[] indegree; 
    static ArrayList<Integer>[] graph; 
    
    static void init() throws IOException{
    	tokens = new StringTokenizer(buffer.readLine()); 
    	n = Integer.valueOf(tokens.nextToken());
    	m = Integer.valueOf(tokens.nextToken()); 
    	
    	indegree = new int[n+1];
    	graph = new ArrayList[n+1]; 
    	
    	for(int node=0; node<=n; node++) {
    		graph[node] = new ArrayList<>(); 
    	}
    	
    	for(int edge=0; edge<m; edge++) {
    		tokens = new StringTokenizer(buffer.readLine());
    		int start = Integer.valueOf(tokens.nextToken()); 
    		int end = Integer.valueOf(tokens.nextToken()); 
    		
    		graph[start].add(end);
    		indegree[end]++; 
    	}
    	
    	
    }
    
    public static void main(String[]args) throws IOException{
    	init(); 
    	
    	PriorityQueue<Integer> pq = new PriorityQueue<>(); 
    	
    	
    	for(int node=1; node<=n; node++) {
    		if(indegree[node]==0) {
    			pq.add(node); 
    		}
    	}
    	StringBuilder result = new StringBuilder();
    	while(!pq.isEmpty()) {
    		int cur = pq.poll();
    		result.append(cur+" ");
    		for(int next: graph[cur]) {
    			indegree[next] --;
    			
    			if(indegree[next]==0) {
    				pq.add(next);
    			}
    		}
    	}
    	System.out.println(result);
    	
    }
    
  
}