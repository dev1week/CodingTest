import java.util.*; 
class Solution {
    private static boolean[][] map; 
    private static int[][] memo; 
    
    private static int width; 
    private static int height; 
    
    public int solution(int m, int n, int[][] puddles) {
        map = new boolean[n+1][m+1]; 
        memo = new int[n+1][m+1]; 
        
        for(int[] me : memo){
            Arrays.fill(me, -1); 
        }
        
        width = m; 
        height = n; 
        
        for(int[] puddle:puddles){
            map[puddle[1]][puddle[0]] = true; 
        }
        return dfs(1,1);
    }
    static boolean canGo(int x, int y){
        return isValid(x, y)&&!map[x][y];
    }
    
    static boolean isValid(int x, int y){
        return x>=1&&y>=1&&x<=height&&y<=width; 
    }
    
    static int dfs(int x, int y){
        
        if(x==height&&y==width){
            return 1; 
        }
        
        if(!isValid(x,y))return 0; 
        
        if(map[x][y]) return 0; 
        
        
        if(memo[x][y]!=-1){
            return memo[x][y]%1000000007; 
        }
        
        return memo[x][y] = (dfs(x+1,y) + dfs(x, y+1))%1000000007; 
    }
}