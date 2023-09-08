import java.io.*; 
import java.util.*;

class Edge implements Comparable<Edge>{
	
	int start;
	int end;
	int w; 
	
	
	public Edge(int start, int end, int w) {
		this.start = start;
		this.end = end; 
		this.w = w;
	}
	
	@Override
	public int compareTo(Edge o) {
		return this.w - o.w; 
	}
	
	public String toString() {
		return this.start+"-"+this.w+">"+this.end; 
	}
}

class Main
{	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static Edge[] edges; 
	static int[] parents; 
	static int V;
	static int E; 
	
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
			
		V = Integer.valueOf(tokens.nextToken());
		E = Integer.valueOf(tokens.nextToken()); 
		
		
		edges = new Edge[E]; 
		parents = new int[V+1]; 
		
		for(int i=0; i<E; i++) {
			tokens = new StringTokenizer(buffer.readLine());
			int start = Integer.valueOf(tokens.nextToken());
			int end = Integer.valueOf(tokens.nextToken());
			int w = Integer.valueOf(tokens.nextToken());
			
			edges[i] = new Edge(start, end, w); 
		}
		

		
		
	}
	
	static void makeSet() {
		for(int i=1; i<=V; i++) {
			parents[i] = i; 
		}
	}
	
	static void union(int x, int y) {
		int parentX = find(x);
		int parentY = find(y); 
		
		parents[parentX] = parentY;
	}
	
	
	static int find(int a) {
		if(parents[a]==a) return a; 
		return parents[a] = find(parents[a]); 
	}
	
	public static void main(String[] args) throws IOException{
		init(); 
		Arrays.sort(edges);

		makeSet(); 
		
		int count =0; 
		int result = 0; 
		for(Edge edge: edges) {
			if(find(edge.start)!=find(edge.end)) {
				result += edge.w; 
				union(edge.start, edge.end);
				if(++count==V-1)break; 
			}
		}
		
		System.out.println(result); 
	}
}