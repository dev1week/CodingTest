import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	
	static int[][] image;
	static int n; 
	static StringBuilder result;
	
	static final int BLACK = 1;
	static final int WHITE = 0; 
	
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine());
		image = new int[n][n];
		result = new StringBuilder(); 
		for(int x=0; x<n;x++) {
			String line = buffer.readLine();
			for(int y=0; y<n; y++) {
				image[x][y] = Integer.valueOf(line.charAt(y)-'0'); 
			}
		}
	}
	
	static void quad(int sX, int sY, int size ) {
		int bCount = 0;
		
		for(int x=sX; x<sX+size; x++) {
			for(int y=sY; y<sY+size; y++) {
				if(image[x][y]==BLACK) {
					bCount ++;
				}
			}
		}
		
		if(bCount==size*size) {

			result.append(BLACK);
		}else if(bCount==0) {

			result.append(WHITE);
		}else{
			int nextSize = size/2;
			result.append("(");
			quad(sX, sY, nextSize);
			
			quad(sX, sY+nextSize, nextSize);
			
			quad(sX+nextSize, sY, nextSize);
			
			quad(sX+nextSize, sY+nextSize, nextSize);
			result.append(")");
		}
	}

	public static void main(String[] args) throws IOException{
		init(); 
		quad(0,0,n);
		
		System.out.println(result); 

		
	}
	
	static void print() {
		for(int[] ma: image) {
			for(int m : ma) {
				System.out.print(m+" ");
			}System.out.println(); 
		}
	}
}
