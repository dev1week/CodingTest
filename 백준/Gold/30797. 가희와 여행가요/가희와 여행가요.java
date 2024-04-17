import java.io.*;
import java.util.*;


class Edge implements Comparable<Edge>{
	int start;
	int end;
	int cost;
	int time;
	
	
	public Edge(int start, int end, int cost, int time) {
		this.time = time;
		this.end = end;
		this.cost = cost;
		this.start = start; 
	}
	
	@Override
	public int compareTo(Edge o) {
		if(o.cost==this.cost) {
			return this.time - o.time; 
		}
		return this.cost - o.cost; 
	}
	
}
class Main{
	
	
	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	
	static int q; //구매할 수 있는 edge 수 
	static int n; //도시의 수 1번부터 n번까지 존재한다. 
	
	
	static int[] parents; 
	
	private static int find(int a) {
		if(parents[a]==a)return parents[a];
		
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		if(find(a)==find(b))return false; 
		
		parents[find(b)] = find(a);
		
		return true;
		
	}
	
	public static void main(String[] args)throws IOException{
		
		
		tokens = new StringTokenizer(buffer.readLine());
		n = Integer.valueOf(tokens.nextToken());
		q = Integer.valueOf(tokens.nextToken()); 
		
		
		
		Edge[] edges = new Edge[q];
		parents = new int[n+1];
		for(int i=0; i<=n; i++) {
			parents[i] = i; 
		}
		
		//edge list에 만들기 
		
		
		for(int i=0; i<q; i++) {
			tokens = new StringTokenizer(buffer.readLine());
			int start = Integer.valueOf(tokens.nextToken());
			int end = Integer.valueOf(tokens.nextToken());
			int cost = Integer.valueOf(tokens.nextToken());
			int time = Integer.valueOf(tokens.nextToken());
			
			edges[i] = new Edge(start, end, cost, time);
			
		}
		
		
		Arrays.sort(edges);
		
		
		int selectedEdgeCount = 0;
		int completeTime = 0;
		long totalCost = 0; 
		//edge 정렬하기 
		for(int i=0; i<q; i++) {
			if(union(edges[i].start, edges[i].end)) {
				selectedEdgeCount++; 
				totalCost += edges[i].cost;
				completeTime = Math.max(completeTime, edges[i].time);
				
				if(selectedEdgeCount==n-1) {
					break;
				}
			}
		}
		//n-1개 뽑기
			//합치는 것이 가능할 때만
				//합치기	
				//count,
				//최대 시각 갱신 
		
		if(selectedEdgeCount!=n-1) {
			System.out.println(-1); 
		}else {
			System.out.println(completeTime+" "+totalCost);
		}
		
		
		
	}

}