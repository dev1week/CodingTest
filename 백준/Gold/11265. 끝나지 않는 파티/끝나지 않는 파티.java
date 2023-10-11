import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    
    static int n; 
    static int m; 
    static int[][] dist; 
    
    static void init() throws IOException{
    	
    	tokens = new StringTokenizer(buffer.readLine()); 
    	
    	n = Integer.valueOf(tokens.nextToken());
    	m = Integer.valueOf(tokens.nextToken()); 
    	
    	
    	dist = new int[n+1][n+1];
    	
    	for(int x=1; x<=n; x++) {
    		tokens = new StringTokenizer(buffer.readLine()); 
    		for(int y=1; y<=n; y++) {
    			dist[x][y] = Integer.valueOf(tokens.nextToken()); 
    		}
    	}
    	
    	
    	for(int k=1; k<=n; k++) {
    		for(int start=1; start<=n; start++) {
    			for(int end=1; end<=n; end++) {
    				dist[start][end] = Math.min(dist[start][end], dist[start][k]+dist[k][end]);
    			}
    		}
    	}
    }
    
    public static void main(String[] args)throws IOException{
    	init(); 
    	
    	StringBuilder sb = new StringBuilder(); 
    	for(int q=0; q<m; q++) {
    		tokens = new StringTokenizer(buffer.readLine()); 
    		
    		int start = Integer.valueOf(tokens.nextToken()); 
    		int end = Integer.valueOf(tokens.nextToken()); 
    		int limit = Integer.valueOf(tokens.nextToken());
    		int cost = dist[start][end];
    		
    		if(limit>=cost) {
    			sb.append("Enjoy other party \n");
    		}else {
    			sb.append("Stay here \n");
    		}
    	
    	}
		System.out.println(sb);

    }
}
