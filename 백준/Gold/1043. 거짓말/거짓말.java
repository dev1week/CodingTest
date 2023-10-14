

import java.io.*;
import java.util.*;



class Main
{
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static int n;
	static int m; 
	static int[] parents;  
	static HashSet<Integer> truthParties; 
	static int[] truePeople;
	static int[] parties; 
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken()); 
		parties = new int[m];
		parents = new int[n+1]; 
		
		for(int i=0; i<=n; i++) {
			parents[i] = i; 
		}
		
		tokens = new StringTokenizer(buffer.readLine());
		int truePeopleNum = Integer.valueOf(tokens.nextToken()); 
		
		truePeople = new int[truePeopleNum];
		
		for(int i=0; i<truePeopleNum; i++) {
			truePeople[i] = Integer.valueOf(tokens.nextToken());
		}
		
		for(int i=0; i<m; i++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			int num = Integer.valueOf(tokens.nextToken());
			int[] people = new int[num]; 
			for(int j=0; j<num; j++) {
				people[j] = Integer.valueOf(tokens.nextToken());
				if(j==0) {
					parties[i] =people[0]; 
				}
				 
				if(j>0) {
					union(people[j], people[j-1]);
				}
			}
		}
		
	
	}
	static void union(int a, int b) {
		int pA = find(a); 
		int pB = find(b); 
		
		parents[pA] = pB; 
	}
	
	static int find(int a) {
		if(a==parents[a]) {
			return a; 
		}
		
		return parents[a] = find(parents[a]);
	}
	
	public static void main(String[] args)throws IOException{
		
		init(); 
		
		truthParties = new HashSet<>(); 
		
		for(int p : truePeople) {	
			truthParties.add(find(p));
		}
		
		int count = 0; 
		for(int p : parties) {
			if(!truthParties.contains(find(p))) {
				count++;
			}
		}
		System.out.println(count);

	}

    
}