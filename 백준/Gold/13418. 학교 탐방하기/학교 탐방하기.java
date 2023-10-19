import java.io.*; 
import java.util.*; 


class Edge implements Comparable<Edge>{
	int start, end, w; 
	
	public Edge(int start, int end, int w) {
		this.start = start;
		this.end = end; 
		this.w = w; 
	}
	
	
	@Override 
	public int compareTo(Edge o) {
		return this.w - o.w; 
	}
	
	@Override 
	public String toString() {
		return this.start+"="+this.w+">"+this.end; 
	}
	
}

public class Main {
	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	
	//피로도 
		//오르막길을 k번 오를 때 피로도는 k*k 
		//최초 조사된 길을 기준으로만 
			//내리막길로 내려갔다가 다시 올라올 때 오르막길이 되는 경우는 고려하지 않는다.
		
	static ArrayList<Edge> edges; 
	static Edge startEdge;
	static int[] parents; 
	static int n; 
	static int m; 
	
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		n = Integer.valueOf(tokens.nextToken()); 
		m = Integer.valueOf(tokens.nextToken()); 
		
		edges = new ArrayList<>(); 
		
		tokens = new StringTokenizer(buffer.readLine()); 
		int start = Integer.valueOf(tokens.nextToken());
		int end = Integer.valueOf(tokens.nextToken()); 
		int w = Integer.valueOf(tokens.nextToken());
		if(w==0) {
			w = 1; 
		}else if(w==1) {
			w=0; 
		}
		startEdge = new Edge(start, end, w); 
		
		
		for(int edge=0; edge<m; edge++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			
			start = Integer.valueOf(tokens.nextToken());
			end = Integer.valueOf(tokens.nextToken()); 
			w = Integer.valueOf(tokens.nextToken()); 
			if(w==0) {
				w = 1; 
			}else if(w==1) {
				w=0; 
			}
			
			edges.add(new Edge(start, end, w)); 
		}
		
		
	}
	
	
	
	public static void main(String[] args) throws IOException{
		init(); 
				
		int edgeCount = 0;
		
		parents = new int[n+1]; 
		for(int node=0; node<=n; node++) {
			parents[node] = node; 
		}
		
		int total = startEdge.w; 
		union(startEdge.start, startEdge.end); 
		 
		
		
		 
		//최소 스패닝 트리
		Collections.sort(edges);
		for(Edge e: edges) {
						
			if(find(e.start)==find(e.end))continue; 			 
			edgeCount ++;
			total+=e.w; 
			union(e.start, e.end);
			
			if(edgeCount==n) break; 
			
		}
		int minTotal = total; 
	
		
		
		edgeCount = 0;
		
		parents = new int[n+1]; 
		for(int node=0; node<=n; node++) {
			parents[node] = node; 
		}
		
		total = startEdge.w; 
		union(startEdge.start, startEdge.end); 

		 
		
		
		 
		//최대 스패닝 트리
		Collections.sort(edges, Collections.reverseOrder());
		for(Edge e: edges) {
						
			if(find(e.start)==find(e.end))continue; 
			edgeCount ++;
			total+=e.w; 
			union(e.start, e.end);
			
			if(edgeCount==n) break; 
			
		}
		
		System.out.println(total*total-minTotal*minTotal); 
	}
	
	static int getScore(int t) {
		int sum = 0; 
		for(int i=1; i<=t; i++) {
			sum += (i*i);
		}
		return sum; 
	}
	
	static void union(int a, int b) {
		int pA = find(a);
		int pB = find(b); 
		
		parents[pA]= pB; 
	}
	
	static int find(int a) {
		if(parents[a]==a) {
			return a; 
		}
		
		return parents[a] = find(parents[a]); 
	}
	
	
}
