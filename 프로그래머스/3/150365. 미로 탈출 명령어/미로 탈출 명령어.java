import java.util.*; 
class Solution {
    
    private static int[][] dirs = {
        //d
        {1,0},
        //l
        {0,-1},
        //r
        {0,1},
        //u
        {-1,0}
    }; 
    
    private static char[] dirCommand = {'d','l','r', 'u'}; 
    
    
    private static int limitDist;
    private static int height;
    private static int width; 
    private static int startX;
    private static int startY;
    private static int endX;
    private static int endY; 
    private static boolean isEnd = false; 
    private String result = "impossible";
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        
        height = n; 
        width = m; 
        startX = x; 
        startY = y; 
        endX = r;
        endY = c; 
        limitDist = k; 
        
        dfs(new char[limitDist], 0, startX, startY); 
        
        return result; 
        
        
    }
    
    
    private String charToString(char[] datas){
        String result= "";
        for(char data : datas){
            result +=data;
        }
        
        return result;
    }
    private void dfs(char[] command, int current,  int currentX, int currentY){
        if(isEnd) return; 
        if(current==limitDist){
            if(currentX==endX&&currentY==endY){
                result = charToString(command); 
            }
            isEnd = true;     
            return; 
        }
        

        
        for(int i=0; i<4; i++){
            int nx = currentX + dirs[i][0];
            int ny = currentY + dirs[i][1]; 
            
            if(!inRange(nx, ny))continue; 
            if((limitDist-current)<getDist(nx, ny,endX, endY))continue;
            
            command[current] = dirCommand[i]; 
            dfs(command, current+1, nx, ny); 
        }
    }
    
    private boolean inRange(int x, int y){
        return x>=1&&x<=height&&y>=1&&y<=width; 
    }
    
    private int getDist(int x1, int y1, int x2, int y2){
        return Math.abs(x1-x2)+Math.abs(y1-y2); 
    }
}