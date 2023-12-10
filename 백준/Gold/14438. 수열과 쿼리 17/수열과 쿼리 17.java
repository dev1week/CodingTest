import java.util.*;
import java.util.stream.Collectors;
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
    
    static int n; 
    static int q;
    static int[] arr;
    static int[] minTree;
    
   static void init() throws IOException{
	   n = Integer.valueOf(buffer.readLine());
	   
	   arr = new int[n];
	   
	   tokens = new StringTokenizer(buffer.readLine());
	   for(int i=0; i<n; i++) {
		   arr[i] = Integer.valueOf(tokens.nextToken()); 
	   }
	   
	   
	   q = Integer.valueOf(buffer.readLine());
	   
	   minTree = new int[4*n];
	   
   }
   
   public static void main(String[] args)throws IOException{
	   init();
	   
	   
	   initSegTree(1,0,n-1); 
	   
	   StringBuilder result = new StringBuilder(); 
//	   System.out.println(Arrays.toString(minTree));
	   for(int query=0; query<q; query++) {
		   tokens = new StringTokenizer(buffer.readLine()); 
		   
		   int opt = Integer.valueOf(tokens.nextToken());
		   int num1 = Integer.valueOf(tokens.nextToken());
		   int num2 = Integer.valueOf(tokens.nextToken());
		   
		   if(opt==1) {
			   update(1,0,n-1,num1-1, num2);
		   }else {
			   int min = queryMin(1,0,n-1,num1-1,num2-1);
			   
			   result.append(min).append("\n");
			   
		   }
//		   System.out.println(opt+" "+num1+" "+num2);
//		   System.out.println(Arrays.toString(minTree));
	   }
	   System.out.println(result);
   }
   
   static void initSegTree(int current, int leftNode, int rightNode) {
	   if(leftNode==rightNode) {
		   minTree[current] = arr[leftNode];
		   return; 
	   }
	   
	   int mid = (leftNode+rightNode)/2; 
	   
	   initSegTree(current*2,leftNode,mid);
	   initSegTree(current*2+1, mid+1, rightNode);
	   
	   minTree[current] = Math.min(minTree[current*2], minTree[current*2+1]);
   }
   
   static void update(int current, int leftNode, int rightNode, int target, int value) {
	   
	   if(target<leftNode||rightNode<target) {
		   return; 
	   }
	   
	   
	   if(leftNode==rightNode) {
		   minTree[current] = value;
		   return;
	   }
	   
	   int mid = (leftNode+rightNode)/2;
	   
	   update(current*2,leftNode,mid,target,value);
	   update(current*2+1, mid+1, rightNode,target,value);
	   
	   minTree[current] = Math.min(minTree[current*2], minTree[current*2+1]);
	   
   }
    
   static int queryMin(int current, int leftNode, int rightNode, int queryLeft, int queryRight) {
	   if(queryRight<leftNode||queryLeft>rightNode) {
		   return Integer.MAX_VALUE; 
	   }
	   
	   if(queryLeft<=leftNode&&rightNode<=queryRight) {
		   return minTree[current];
	   }
	   
	   int mid = (leftNode+rightNode)/2;
	   
	   
	   int minLeft = queryMin(current*2, leftNode, mid,queryLeft, queryRight);
	   int minRight = queryMin(current*2+1, mid+1, rightNode, queryLeft, queryRight);
	   
	   
	   return Math.min(minLeft, minRight);
	   
   }
   
    

	
}