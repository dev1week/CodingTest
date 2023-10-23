import java.io.*; 
import java.util.*; 



public class Main {
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int n; 
	static int m; 
	
	static String[] set; 
	
	static HashSet<String>[] prefixes; 
	
	//문자열의 길이가 n일 때 prefix 집합 
	
	public static void init() throws IOException{
		
		tokens = new StringTokenizer(buffer.readLine());
		
		
		n = Integer.valueOf(tokens.nextToken()); 
		m = Integer.valueOf(tokens.nextToken()); 
		
		set = new String[n]; 
		prefixes = new HashSet[501]; 
		
		for(int i=0; i<=500; i++) {
			prefixes[i] = new HashSet<>(); 
		}
		
		for(int i=0; i<n; i++) {
			String data = buffer.readLine();
			
			for(int pos=0; pos<data.length(); pos++) {
				prefixes[pos+1].add(data.substring(0, pos+1));
			}
		}

	}
	
	public static void main(String[] args) throws IOException{
		init(); 
		int count = 0;
		for(int q=0; q<m; q++) {
			String query = buffer.readLine(); 
			if(prefixes[query.length()].contains(query)){
				count++;
			}
		}
		System.out.println(count); 
	}
	
	
	
	
}
