

import java.io.*;
import java.util.*; 

public class Main {
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	
	static int queryNum = 0; 
	static int set =0; 
	static void init() throws IOException{
		queryNum = Integer.valueOf(buffer.readLine());
		
	}
	
	public static void main(String[] args)throws IOException{
		init(); 
		
		StringBuilder sb = new StringBuilder(); 
		for(int q=0; q<queryNum; q++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			
			String command = tokens.nextToken(); 
			
			if("all".equals(command)) {
				set = (1<<21)-1; 
			}else if("empty".equals(command)) {
				set = 0; 
			}
			else {
				int n = Integer.valueOf(tokens.nextToken()); 
				if("add".equals(command)) {
					add(n); 
				}else if("check".equals(command)) {
					
					sb.append(check(n)).append("\n");
				}else if("remove".equals(command)) {
					remove(n); 
				}else if("toggle".equals(command)) {
					toggle(n); 
				}
				
			}
			
			
		}
		System.out.println(sb); 
		
	}
	
	
	
	static void remove(int n) {
		if(((set>>n)&1)==1){
			set^= (1<<n); 
		}
	}
	
	static void toggle(int n) {
		if(((set>>n)&1)==0) {
			set ^= (1<<n); 
		}else {
			set^= (1<<n); 
		}
	}
	
	static void add(int n) {
		if(((set>>n)&1)==0) {
			set ^= (1<<n); 
		}
	}
	
	static int check(int n) {
		return (set>>n)&1; 
	}
}
