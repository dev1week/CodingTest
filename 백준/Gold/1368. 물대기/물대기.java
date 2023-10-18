
import java.io.*; 
import java.util.*;


class Node implements Comparable<Node>{
	int vertex, w; 
	
	public Node(int vertex, int w) {
		this.vertex = vertex;
		this.w = w; 
	}
	
	@Override
	public String toString() {
		return "="+w+">"+vertex;
	}
	
	@Override 
	public int compareTo(Node o) {
		return this.w - o.w; 
	}
	
}

public class Main {
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	
	static int n; 
	static ArrayList<Node>graph[];
	
	
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine());
		
		graph = new ArrayList[n+2];
		
	
		
		for(int node=1; node<=n+1; node++) {
			graph[node] = new ArrayList<>();
		}
		
		for(int node=1; node<=n; node++) {
			int w = Integer.valueOf(buffer.readLine());
			graph[n+1].add(new Node(node,w));
		}
			
		
		
		
		for(int start=1; start<=n; start++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int end=1; end<=n; end++) {
				int w = Integer.valueOf(tokens.nextToken()); 
				if(start==end)continue;
				graph[start].add(new Node(end, w)); 		
			}
		}
	}
	
	public static void main(String[] args)throws IOException{
		
		init(); 
		
		
		int totalCost = 0;  
		PriorityQueue<Node> pq = new PriorityQueue(); 
		int[] dist = new int[n+2];
		boolean[] isVisited = new boolean[n+2]; 
		Arrays.fill(dist, (int)1e9);
		
		
		pq.add(new Node(n+1, 0)); 
		dist[n+1] = 0; 
		
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll(); 
			
			if(isVisited[cur.vertex])continue; 

			isVisited[cur.vertex] = true; 
			totalCost += cur.w; 
			
			
			for(Node target : graph[cur.vertex]) {
				int newDist = target.w; 
				
				if(dist[target.vertex]>newDist) {
					dist[target.vertex] = newDist;
					pq.add(new Node(target.vertex, newDist)); 
				}
			}
		}
		
		
		
		for(int node=1; node<=n; node++) {
			if(!isVisited[node]) {
				totalCost += graph[node].get(0).w; 
			}
		}
		
		System.out.println(totalCost); 
		
		
	}
	
	
	
}
