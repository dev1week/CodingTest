class Solution {
    private final int ATTACK = 1; 
    public int solution(int[][] board, int[][] skills) {
        int answer = 0;
        
        int[][] changes = new int[board.length+1][board[0].length+1];
        
        
        
        for(int[] skill: skills){
            int type = skill[0];
            
            int startX = skill[1];
            int startY = skill[2];
            int endX = skill[3];
            int endY = skill[4];
            int value = skill[5]; 
            
            int operator = type==ATTACK?-1:1;
            int damage = operator*value; 
            
            changes[startX][startY] += damage; 
            
            changes[endX+1][startY] -= damage; 
            changes[startX][endY+1] -= damage; 
            changes[endX+1][endY+1] += damage;  
        }
        
        
        for(int x=1; x<board.length; x++){
            for(int y=0; y<board[0].length; y++){
                changes[x][y] += changes[x-1][y];
            }
        }
        
        for(int y=1; y<board[0].length; y++){
            for(int x=0; x<board.length; x++){
                changes[x][y] += changes[x][y-1];
            }
        }
        
        int cnt = 0; 
        for(int x=0; x<board.length; x++){
            for(int y=0; y<board[0].length; y++){
                if(board[x][y]+changes[x][y]>0){
                    cnt++; 
                }
            }
        }
        
        
        return cnt;
    }
}