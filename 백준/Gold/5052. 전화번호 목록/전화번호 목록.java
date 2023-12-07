import java.util.*;
import java.io.*;

class TrieNode{
	TrieNode[] children = new TrieNode[10]; 
	
	int count;
	boolean isEnd;
	
	TrieNode() {
		count = 0;
		isEnd = false;
		for(int i=0; i<10; i++) {
			children[i] = null;
		}
	}
}
public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    
	static String[] numbers; 
	static int n; 
	static TrieNode root; 
	
	
	static void init() throws IOException{
		root = new TrieNode(); 
		
		n = Integer.valueOf(buffer.readLine());
		numbers = new String[n];
		
		for(int i=0; i<n; i++) {
			numbers[i] = buffer.readLine();
		}
	}
	public static void main(String[] args)throws IOException{
		
		int t = Integer.valueOf(buffer.readLine());
		StringBuilder result = new StringBuilder();
		for(int test=1; test<=t; test++) {
			init();
			for(String number : numbers) {
				insert(number);
			}
			
			
			if(isValid()) {
				result.append("YES").append("\n");
			}else {
				result.append("NO").append("\n");
			}
		}
		
		System.out.println(result);		
	}
	
	static void insert(String word) {
		TrieNode current = root; 
		
		for(int i=0; i<word.length(); i++) {
			int idx = word.charAt(i) -'0'; 
			
			if(current.children[idx]==null) {
				current.children[idx] = new TrieNode(); 
				current.count++; 
			}
			
			current = current.children[idx];
			
		}
		
		current.isEnd = true; 
	}
	
	static boolean isValid() {
		for(String number: numbers) {
			//number맨 끝에 자식 수가 1이상인지 체크 
			
			//1이상이면 -> 해당 번호로 시작하는 다른 번호가 있음 
			if(isStartWith(number)) {
				return false;
			}
			
		}
		return true; 
	}
	
	static boolean isStartWith(String word) {
		TrieNode current = root; 
		
		for(int i=0; i<word.length(); i++) {
			int idx = word.charAt(i)-'0'; 
			
			current = current.children[idx];
		}
		
		
		return current.count>0; 
	}
	
	
}