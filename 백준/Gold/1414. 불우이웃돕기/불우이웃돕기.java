

import java.io.*;
import java.util.*;


class Edge implements Comparable<Edge>{
	int start;
	int end;  
	int w; 
	
	
	public Edge(int start, int end, int w) {
		this.start= start;
		this.end = end; 
		this.w = w; 
	}
	
	@Override 
	public int compareTo(Edge o)
	{
		return this.w-o.w; 
	}
	
	@Override 
	public String toString() {
		return this.start+"-"+this.w+">"+this.end; 
	}
	
}

public class Main {
	
	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	
	static int n; 
	
	static ArrayList<Edge> edges; 
	static int totalLength; 
	static int[] parents; 
	
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine());
		edges = new ArrayList<>(); 
		totalLength = 0; 
		parents = new int[n]; 
		
		for(int i=0; i<n; i++) {
			parents[i] = i; 
		}
		for(int start=0; start<n; start++) {
			String line = buffer.readLine(); 
			for(int end=0; end<n; end++) {
				char data = line.charAt(end); 

				int w = Integer.valueOf(Integer.valueOf(data-'a')+1);
				if(data=='0') {
					w=0; 
				}else if(w<0) {
					w+=58; 
				}else {
					
				}
				totalLength += w; 
				if(w==0) continue; 
				if(start==end) continue; 
				edges.add(new Edge(start, end, w));
			}
		}
	}
	
	
	
	public static void main(String[] args)throws IOException{
		
		
		init(); 
		
		Collections.sort(edges); 
		
		int minTot = 0; 
		int count = 0; 
		for(Edge edge : edges) {
			
			if(find(edge.start)==find(edge.end))continue; 
			union(edge.start, edge.end);
			count++;
			minTot += edge.w; 
			
			if(count==n-1) break; 

		}
	
		if(isAllConnected()) {
			System.out.println(totalLength-minTot);
		}else {
			System.out.println(-1);
		}

		
		
	}
	
	static boolean isAllConnected() {
		for(int node=0; node<n-1; node++) {
			if(find(node)!=find(node+1)) {
				return false;
			}
		}
		return true; 
	}
	
	static int find(int a) {
		if(a==parents[a]) {
			return a; 
		}
		
		return parents[a] = find(parents[a]); 
	}
	
	
	static void union (int a, int b) {
		int pA = find(a);
		int pB = find(b); 
		
		parents[pA] = pB; 
	}
	
}
