import java.util.*;
import java.util.stream.Collectors;
import java.io.*;


class TrieNode{
	TreeMap<String, TrieNode> children; 
	
	TrieNode(){
		children = new TreeMap<>(); 
	}
}

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    
	
    static List<String>[] paths; 
    static int n; 
    
    static TrieNode root;
    
    static void init() throws IOException{
    	root = new TrieNode(); 
    	
    	n = Integer.valueOf(buffer.readLine()); 
    	paths = new List[n];
    	
    	for(int i=0; i<n; i++) {
    		tokens = new StringTokenizer(buffer.readLine(), "\\");
    		List<String> path =  new ArrayList<>(); 
    		while(tokens.hasMoreTokens()) {
    			path.add(tokens.nextToken());
    		}
    		paths[i] = path;
    	}
    	
    	
    }
    
    
    static StringBuilder result; 
    public static void main(String[] args)throws IOException{
    	init(); 
    	
    	
    	for(int i=0; i<n; i++) {
    		insert(paths[i]);
    	}
    	
    	result = new StringBuilder(); 
    	printDirectories(root, 0);
    	
    	System.out.println(result);
    }
    
    static void insert(List<String> path) {
    	TrieNode current = root; 
    	for(String p: path) {
    		if(!current.children.containsKey(p)) {
    			current.children.put(p, new TrieNode());
    		}
    		current = current.children.get(p);
    	}
    }
    
    static void printDirectories(TrieNode current, int depth) {
    	
    	Set<String> keys = current.children.keySet();
    	
    	
    	for(String key: keys) {
    		for(int i=0; i<depth; i++) {
    			result.append(" ");
    		}
    		result.append(key).append("\n");
    		
    		printDirectories(current.children.get(key), depth+1);
    	}
    	
    }

	
}