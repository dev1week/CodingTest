import java.io.*;
import java.util.*;


class Edge implements  Comparable<Edge> {
    int start, end, w;

    public Edge(int start, int end, int w){
        this.start = start;
        this.end = end;
        this.w = w;
    }


    @Override
    public int compareTo(Edge other){
        return this.w - other.w;
    }


    @Override
    public String toString(){
        return this.start+"-"+this.w+">"+this.end;
    }
}

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    
    static int n; 
    static int m; 
    
    static int[] parents; 
    static Edge[] edges; 
    static ArrayList<Edge> selecteds = new ArrayList<>();
    
    
    static void init() throws IOException{
    	tokens = new StringTokenizer(buffer.readLine());
    	n = Integer.valueOf(tokens.nextToken());
    	m = Integer.valueOf(tokens.nextToken()); 
    	
    	parents = new int[n+1];
    	edges = new Edge[m]; 
    	
    	for(int i=1; i<=n; i++) {
    		parents[i] = i; 
    	}
    	
    	for(int i=0; i<m; i++) {
    		tokens = new StringTokenizer(buffer.readLine());
    		int start = Integer.valueOf(tokens.nextToken()); 
    		int end = Integer.valueOf(tokens.nextToken());
    		int w = Integer.valueOf(tokens.nextToken()); 
    		
    		edges[i]  = new Edge(start, end, w);	
    	}

    	Arrays.sort(edges);

    }
    
    public static void main(String[] args) throws IOException{
    	init();;
    	
    	int lastW = 0;
    	int count = 0;
    	int totalW = 0;

    	for(Edge e : edges) {
    		if(find(e.start)!=find(e.end)) {
    			totalW += e.w; 
    			lastW = e.w; 
    			count++;
    			union(e.end, e.start);
    			if(count==n-1)break; 
    			
    		}
    	}
    	System.out.println(totalW-lastW); 
    }

    static void union(int a, int b) {
    	int rootA = find(a);
    	int rootB = find(b);
    	
    	parents[rootA] = rootB; 
    }
    
    static int find(int a) {
    	if(a==parents[a]) {
    		return a; 
    	}
    	
    	return parents[a] = find(parents[a]);
    }    
}