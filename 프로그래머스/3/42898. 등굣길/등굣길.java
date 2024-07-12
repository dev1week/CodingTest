import java.util.*; 

class Point{
    int x, y;
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public String toString(){
        return this.x+":"+this.y; 
    }
}

class Solution {
    
    private static final int STATIC = 1000000007; 

    
    private static boolean[][] isPuddles; 
    private static int[][] memory; 
    private static List<Point> route = new ArrayList<>(); 
    
    private static int dfs(int currentX, int currentY, int height, int width){
                
       if(currentY == height && currentX == width){
            return 1; 
        }
        
        if(currentY>height||currentX>width){
            return 0; 
        }
        
        if(isPuddles[currentY][currentX]){
            return 0; 
        }
        
        if(memory[currentY][currentX]!=-1){
            return memory[currentY][currentX];
        }
        
        return memory[currentY][currentX] = (dfs(currentX+1, currentY, height, width) + dfs(currentX, currentY+1, height, width))%STATIC;         
        
    }
    
    public int solution(int width, int height, int[][] puddles) {
        
        
        isPuddles = new boolean[height+1][width+1];
        memory = new int[height+1][width+1];
        for(int[] puddle : puddles){
            isPuddles[puddle[1]][puddle[0]] = true; 
        }
        
        for(int[] mem : memory){
            Arrays.fill(mem, -1);
        }
        
        
        return dfs(1, 1, height, width);
    }
}