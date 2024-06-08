import java.util.*;
import java.io.*;

class TrieNode{
	TreeMap<String, TrieNode> children;
	
	public TrieNode() {
		children = new TreeMap<>(); 
	}
	
}


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    
    static List<String>[] words; 
    static TrieNode root; 
    static int n; 
    static StringBuilder result; 
    
    static void init() throws IOException{
    	root = new TrieNode(); 
    	n = Integer.valueOf(buffer.readLine());
    	words = new List[n];
    	result = new StringBuilder(); 
    	for(int i=0; i<n; i++) {
    		tokens = new StringTokenizer(buffer.readLine());
    		List<String> word = new ArrayList<>(); 
    		int k = Integer.valueOf(tokens.nextToken());
    		for(int j=0; j<k; j++) {
    			word.add(tokens.nextToken());
    		}
    		words[i] = word; 
    	}
    	
    	
    	
    }
    
    public static void main(String[] args) throws IOException{
    	init(); 
    	
    	for(List<String> word: words) {
    		insert(word);
    	}
    	
    	
    	printTree(root, 0);
    	System.out.println(result);
    	
    }
    
    static void insert(List<String>word) {
    	TrieNode current = root; 
    	
    	for(String w: word) {
    		if(!current.children.containsKey(w)) {
    			current.children.put(w, new TrieNode());
    		}
    		current = current.children.get(w);
    	}
    	
    	
    }
    
    static void printTree(TrieNode current, int depth) {
    	
    	Set<String> childrens = current.children.keySet();
    	
    	
    	for(String child: childrens) {
    		
    		for(int i=0; i<depth*2; i++) {
    			result.append("-");
    		}
    		result.append(child).append("\n");
    		printTree(current.children.get(child), depth+1);
    	}
    	
    }
    
    
	
    

	
}