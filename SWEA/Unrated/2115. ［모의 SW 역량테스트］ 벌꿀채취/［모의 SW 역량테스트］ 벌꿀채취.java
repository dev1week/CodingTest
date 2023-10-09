
import java.io.*;
import java.util.*;


public class Solution {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    
    static int n; //맵의 사이즈 ~10 
    //2명의 일꾼 
    	//서로 채취하는 공간이 겹치면 안된다. 
    static int m; // 가로로 연속되도록 채취할 수 있는 크 
    static int[][] map;
    
    static int c;
    //합이c 이상일 경우 하나의 벌통만 선택하여 채취해야한다. 
    
    
    static boolean[] isUsed; 
    
    //서로 다른행에서 가져오는 경우 
    // 10 * 9 
    	//뽑고 난 후 => 0~n-m까지 순회하면서 최대값 골라담기 
    
    static int result; 
    
    static int tmpRow;
    static void powerSet(int cur, int[] candidates, int total, int[] selecteds) {
    	if(cur==m) {
//			System.out.println(Arrays.toString(selecteds));
//			System.out.println(total);
    		if(total<=c) {
//    			System.out.println(Arrays.toString(selecteds));
    			int tmp = 0;
    			for(int selected : selecteds) {
    				tmp += selected*selected; 
    			}
    			tmpRow = Math.max(tmpRow, tmp);
    		}
    		return; 
    	}
    	
    	selecteds[cur] = candidates[cur];
    	powerSet(cur+1, candidates,total+candidates[cur], selecteds);
    	selecteds[cur] = 0; 
    	powerSet(cur+1, candidates,total, selecteds);
    	
    }
    
    static void findAtOtherRow(int cur, int last, int[] selecteds) {
    	if(cur==2) {
    		int total =0;
    		for(int row : selecteds) {
    			tmpRow = 0; 
    			for(int col=0; col<n-m+1; col++) {
    				
    				int[] tmp = new int[m]; 
    				for(int offset=0; offset<m; offset++) {
    					tmp[offset] = map[row][col+offset];
    				}
    				
    				powerSet(0, tmp, 0, new int[m]); 
    			}
    			
    			total += tmpRow;
    		}
    		
    		result = Math.max(total, result);
    		
    		
    		return;
    	}
    	
    	for(int next=last; next<n; next++) {
    		if(!isUsed[next]) {
    			isUsed[next] = true; 
    			selecteds[cur] = next; 
    			findAtOtherRow(cur+1, next+1, selecteds);
    			isUsed[next] = false; 
    		}
    		
    	}
    }
    
    static void init() throws IOException{
    	tokens = new StringTokenizer(buffer.readLine()); 
    	n = Integer.valueOf(tokens.nextToken());
    	m = Integer.valueOf(tokens.nextToken());
    	c = Integer.valueOf(tokens.nextToken()); 
    	
    	isUsed = new boolean[n]; 
    	
    	map = new int[n][n]; 
    	
    	tmpRow = 0;
    	result = 0;
    	
    	for(int x=0; x<n; x++) {
    		tokens = new StringTokenizer(buffer.readLine());
    		for(int y=0; y<n; y++) {
    			map[x][y] = Integer.valueOf(tokens.nextToken());
    		}
    	}
    }
    
    
    static void findAtRow(int cur, int[]selecteds, int last, int row) {
    	if(cur==2) {
    		int[] a = new int[m];
    		int[] b = new int[m]; 
    		
    		for(int i=0; i<m; i++) {
    			a[i] = map[row][selecteds[0] + i]; 
    			b[i] = map[row][selecteds[1] +i]; 
    		}
    		
    		
    		if(row==2) {
//    			System.out.println(row);
//    			System.out.println("A: "+Arrays.toString(a));
//    			System.out.println("B: "+Arrays.toString(b));
    			powerSet(0, a, 0, new int[m]);
        		int aSum = tmpRow; 
        		powerSet(0, b, 0, new int[m]);
        		int bSum = tmpRow; 
        		
//    			System.out.println(aSum+bSum);
        		result = Math.max(aSum+bSum, result);

    		}
    		
    		return; 
    	}
    	
    	for(int col=last; col<n-m+1; col++) {
    		selecteds[cur] = col; 
    		findAtRow(cur+1, selecteds, col+m, row);
    	}
    	
    }
    
    public static void main(String[] args)throws IOException{
    	int T = Integer.valueOf(buffer.readLine());
    	StringBuilder sb = new StringBuilder(); 
    	for(int t=1; t<=T; t++) {
    		init(); 
    		
    		if(m!=1) {
    			//서로 다른 행에서 가져오는 경우 
        		findAtOtherRow(0, 0,new int[2]);

        		//서로 같은 행에서 가져오는 경우 
//        		if(n>m) {
//        			for(int row=0; row<n; row++) {
//        				findAtRow(0, new int[2], 0, row);
//            		}
//        		}
    		}else {
    			int[] tmpArray = new int[n*n];
    			int N = 0; 
    			for(int x=0; x<n; x++) {
    				for(int y=0; y<n; y++) {
    					tmpArray[N] = map[x][y];
    					N++; 
    				}
    			}
    			Arrays.sort(tmpArray);
    			
    			result = Math.max(result, tmpArray[n*n-1]*tmpArray[n*n-1]+tmpArray[n*n-2]*tmpArray[n*n-2]);
    		}
    		
    		
    		
    		
    		sb.append("#").append(t).append(" ").append(result).append("\n");
    	}
    	System.out.println(sb);
    }
    //서로 같은 행에서 가져오는 경우 
    	//
}