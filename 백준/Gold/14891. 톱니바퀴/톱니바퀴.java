
import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    
    //톱니 바퀴 4개 
    
    //각 톱니바퀴 당 날은 8개 
    
    static int k; //톱니 바퀴 회전 수 
    
    //다음 톱니 회전 
    	//맞물린 톱니의 극이 다를 경우에만 
    		//이전 톱니의 반대 방향으로 회전 
    
    
    static int result; // 톱니 바퀴 점수 총합 
    
    
    static int[][] gears; 
    
    
    static int[][] commands;
    
    static void init() throws IOException{
    	gears = new int[4][8];
    	
    	for(int x=0; x<4; x++) {
    		String line = buffer.readLine();
    		for(int y=0; y<8; y++) {
    			gears[x][y] = Integer.valueOf(line.charAt(y)-'0');
    		}
    	}
    	
    	k = Integer.valueOf(buffer.readLine());
    	
    	commands = new int[k][2];
    	
    	for(int i=0; i<k; i++) {
    		tokens = new StringTokenizer(buffer.readLine());
    		commands[i][0] = Integer.valueOf(tokens.nextToken())-1; 
    		commands[i][1] = Integer.valueOf(tokens.nextToken());
    	}
    	
    }
    
    static void print(int[][] map) {
    	for(int[] ma: map) {
    		for(int m : ma) 
    		{
    			System.out.print(m);
    		}System.out.println(); 
		}
    	
    }
    
    static int[] getDirs(int targetRow, int option) {
    	int[] gearDirs = new int[4];
    	int prevOpt = option; 
    	int nextOpt = option; 
    	
    	gearDirs[targetRow] = option; 
    	
    	//targetRow 전 기준으로 봐보기 
    	for(int row= targetRow; row>=1; row--) {
    		if(gears[row][6]!=gears[row-1][2]) {
    			if(prevOpt==-1) {
    				prevOpt = 1;
    			}else if(prevOpt==1) {
    				prevOpt = -1; 
    			}
    			gearDirs[row-1] = prevOpt;
    		}else {
    			break; 
    		}
    	}
    	
    	
    	//targetRow 이후 기준으로 봐보기 
    	for(int row=targetRow; row<3; row++) {
    		if(gears[row][2]!=gears[row+1][6]) {
    			if(nextOpt==-1) {
    				nextOpt = 1;
    			}else if(nextOpt==1) {
    				nextOpt = -1; 
    			}
    			gearDirs[row+1] = nextOpt;
    		}else {
    			break; 
    		}
    	}
    	
    	return gearDirs; 
    }
    
    public static void main(String[] args)throws IOException{
    	init(); 
    	
    	for(int i=0; i<k; i++) {
        	int[] gearDirs = getDirs(commands[i][0], commands[i][1]); //각 톱니바퀴 회전방향 구하기 0,-1,1 =>  
        	gears = next(gearDirs); 
        	//gearDirs 바탕으로 gears 배열 변경 
        	
    	}
    	System.out.println(getScore()); 
    }
    
    static int[][] next(int[] getDirs){
    	int[][] nextGears = new int[4][8];
    	
    	for(int row=0; row<4; row++) {
    		if(getDirs[row]==1) {
    			nextGears[row] = rotate(row);
    		}else if(getDirs[row]==-1) {
    			nextGears[row] = reverseRotate(row);
    		}else {
    			nextGears[row] = gears[row]; 
    		}
    	}
    	return nextGears; 
    }
    
    
    static int[] rotate(int row) {
    	int[] nextGear = new int[8]; 
    	int tmp = gears[row][7]; 
    	
    	for(int i=7; i>=1; i--) {
    		nextGear[i] = gears[row][i-1];
    	}
    	nextGear[0] = tmp; 
    	return nextGear; 
    }
    
    static int[] reverseRotate(int row) {
    	int[] nextGear = new int[8]; 
    	int tmp = gears[row][0]; 
    	
    	for(int i=0; i<7; i++) {
    		nextGear[i] = gears[row][i+1];
    	}
    	nextGear[7] = tmp; 
    	return nextGear; 
    }
    
    
    static int getScore() {
    	int score = 0;
    	int partScore = 1; 
    	
    	for(int gear=0; gear<4; gear++) {
    		score += gears[gear][0] * partScore; 
    		partScore*=2;
    	}
    	return score; 
    }
    
   
    
}
