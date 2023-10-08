
import java.io.*;
import java.util.*;

public class Solution{
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    
    static int[][] map;
    static int d; //세로
    static int w; //가로 
    
    static int k; 
    //동일한 특성의 셀들이 k개 이상 연속적으로 있는 경우에만 통과 
    
    
    //약품 
    	//행의 모든 특성이 a or b로 변함 
    
    
    
    //약품의 투입횟수 0으로 
    
    
    static int[] selecteds; 
    static int result;
    
    //w개의 power set 구하기 3^13 
    
    static int[][] inject(){
    	int[][] testMap = new int[d][w]; 
    	
    	for(int x=0; x<d; x++) {
    		for(int y=0; y<w; y++) {
    			testMap[x][y] = map[x][y]; 
    		}
    	}
    	
    	for(int row=0; row<d; row++) {
    		if(selecteds[row]!=-1) {
    			Arrays.fill(testMap[row], selecteds[row]);
    		}
    	}
    	return testMap; 
    }
    
    static boolean isPossible(int[][] testMap) {
    	int rowCount = 0; 
    	for(int y=0; y<w; y++) {
    		for(int sx=0; sx<d-k+1; sx++) {
    			int sameCount =1; 
    			for(int offset=1; offset<k; offset++) {
    				if(testMap[sx][y] == testMap[sx+offset][y]) {
    					sameCount++;
    				}else {
    					break; 
    				}
    			}
    			if(sameCount>=k) {
    				rowCount ++;
    				break; 
    			}
    			
    		}
    		
    	}
    	
    	return  rowCount == w; 
    }
    
    static void bt(int cur, int count) {
		if(count>=result) return; 

    	if(cur==d) {
    		//약 적용하기 
    		int[][] testMap = inject(); 
    		
    		//한 행씩 돌아가면서 합격기준 확인하기 d*w
    		if(isPossible(testMap)) {
    			
    			result = Math.min(result, count);
    		}
    		return;
    	}
    	
    	//약 a로 넣을 경우 
    	selecteds[cur] = 0; 
    	bt(cur+1, count+1);
    	
    	//약 b로 넣을 경우 
    	selecteds[cur] = 1;
    	bt(cur+1, count+1);
    	
    	//약 안 넣을 경우 
    	selecteds[cur] = -1;
    	bt(cur+1, count);
    }
    
    
    public static void main(String[] args)throws IOException{
    	int T = Integer.valueOf(buffer.readLine()); 
    	StringBuilder sb = new StringBuilder(); 
    	for(int t=1; t<=T; t++) {
			init(); 
			bt(0,0);
			sb.append("#").append(t).append(" ").append(result).append("\n");
	    }
    	System.out.println(sb);
    	
    }
    
    static void print(int[][] arr) {
    	for(int[] ar: arr) {
    		for(int a: ar) {
    			System.out.print(a+" ");
    		}System.out.println();
    	}
    }
    
    static void init() throws IOException{
    	
		tokens = new StringTokenizer(buffer.readLine()); 
		d = Integer.valueOf(tokens.nextToken());
		w = Integer.valueOf(tokens.nextToken());
		k = Integer.valueOf(tokens.nextToken()); 
		
		map = new int[d][w];
		
		for(int x=0;x<d; x++) {
			tokens = new StringTokenizer(buffer.readLine());
			for(int y=0; y<w; y++) {
				map[x][y] = Integer.valueOf(tokens.nextToken()); 
			}
		}
		
		result = Integer.MAX_VALUE; 
		selecteds = new int[d];
    }
}