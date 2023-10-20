import java.io.*; 
import java.util.*; 




public class Main {
	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	//각 가문은 한 명의 시조를 root로 하는 트리 형태를 띈다. 
	
	static int n; 
	static int[] indegree;
	static String[] numToName; 
	static HashMap<String, Integer> nameToNum; 
	static boolean[] isVisited; 
	
	static ArrayList<Integer>[] edges;
	
	static ArrayList<Integer>[] graph; 
	
	static void init()throws IOException{
		n = Integer.valueOf(buffer.readLine());
		
		indegree = new int[n]; 
		numToName = new String[n]; 
		nameToNum = new HashMap<>();
		isVisited = new boolean[n]; 
		
		edges = new ArrayList[n]; 
		graph = new ArrayList[n]; 
		
		for(int node=0; node<n; node++) {
			edges[node] = new ArrayList<>();
			graph[node] = new ArrayList<>(); 
		}
		
		tokens = new StringTokenizer(buffer.readLine()); 
		for(int i=0; i<n; i++) {
			String name = tokens.nextToken(); 
			
			numToName[i] = name; 
		}
		
		
		Arrays.sort(numToName);
		
		for(int i=0; i<n; i++) {
			nameToNum.put(numToName[i], i); 
		}
		
	
		
		int m= Integer.valueOf(buffer.readLine()); 
		
		for(int i=0; i<m; i++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			String after = tokens.nextToken(); 
			String before = tokens.nextToken(); 
			
			int aNum = nameToNum.get(after); 
			int bNum = nameToNum.get(before); 
			
			edges[bNum].add(aNum); 
			indegree[aNum]++; 
		}
	}
	
	public static void main(String[] args)throws IOException{
		init(); 
		
		PriorityQueue<Integer> que = new PriorityQueue<>(); 
		ArrayList<String> starts = new ArrayList<>(); 
		
		for(int node=0; node<n; node++) {
			
			if(indegree[node]==0) {
				isVisited[node] = true;
				que.add(node); 
				starts.add(numToName[node]);
			}
			
		}
		
		while(!que.isEmpty()) {
			int cur = que.poll(); 
			
			for(int next : edges[cur]) {
				indegree[next]--; 
				if(isVisited[next])continue; 
				if(indegree[next]!=0)continue; 
				
				graph[cur].add(next); 
				que.add(next); 
			}
		}
		
		StringBuilder sb = new StringBuilder(); 
		sb.append(starts.size()).append("\n");
		
		for(String start: starts) {
			sb.append(start).append(" ");
		}sb.append("\n"); 
		
		for(int node=0; node<n; node++) {
			String start = numToName[node];
			sb.append(start).append(" ").append(graph[node].size()).append(" ");
			Collections.sort(graph[node]);
			for(int next : graph[node]) {
				String nextName = numToName[next]; 
				sb.append(nextName).append(" ");
			}
			sb.append("\n"); 
		}
		
		
		System.out.println(sb); 
		
		
	}
	
	
	
}
