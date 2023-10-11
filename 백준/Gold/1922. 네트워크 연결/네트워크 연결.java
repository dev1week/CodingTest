

import java.io.*;
import java.util.*;


class Edge implements Comparable<Edge>{
	int start, end ,w; 
	
	
	public Edge(int start, int end, int w) {
		this.start = start; 
		this.end = end; 
		this.w= w; 
	}
	
	@Override
	public String toString() {
		return this.start+"-"+this.w+">"+this.end;
	}
	
	@Override
	public int compareTo(Edge o) {
		return this.w - o.w; 
	}
}

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    
    static int[] parents; 
    static PriorityQueue<Edge> pq; 
    static int n; 
    static int m; 
    static int result; 
    
    static int find(int a) {
    	if(parents[a]==a) {
    		return a; 
    	}
    	
    	return parents[a] = find(parents[a]);
    }
    
    
    static void union(int a, int b) {
    	int rootA = find(a); 
    	int rootB = find(b); 
    	
    	parents[rootA] = rootB;
    }
    
    
    
    static void init() throws IOException{
    	n = Integer.valueOf(buffer.readLine());
    	m = Integer.valueOf(buffer.readLine()); 
    	pq = new PriorityQueue<>(); 
    	parents = new int[n+1]; 
    	for(int node=1; node<=n; node++) {
    		parents[node] = node; 
    	}
    	

    	
    	for(int i=0; i<m; i++) {
    		tokens = new StringTokenizer(buffer.readLine());
    		int start = Integer.valueOf(tokens.nextToken()); 
    		int end = Integer.valueOf(tokens.nextToken()); 
    		int w = Integer.valueOf(tokens.nextToken()); 
    		
    		
    		pq.add(new Edge(start, end, w)); 
    		
    	}
    }
    
    public static void main(String[] args) throws IOException{
    	init(); 
    	
    	int edgeNum = 0; 
    	while(!pq.isEmpty()) {
    		Edge cur = pq.poll(); 
    	
    		if(find(cur.start)!=find(cur.end)) {
    			
    			union(cur.start, cur.end); 
    			edgeNum ++; 
    			result += cur.w; 
    		}
    		if(edgeNum==n-1) {
    			break; 
    		}
    		
    	}
    	System.out.println(result); 
    	
    }
   
    
}
