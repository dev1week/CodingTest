import java.util.*; 
class Solution {
    
    private static int count = -1; 
    private static int result= 0; 
    private static final char[] mo= {'A', 'E', 'I', 'O', 'U'};
    
    private static void dfs(String query, int current, String currentData){
        count++; 
        if(query.equals(currentData)){
            result = count; 
        }
        
        //5개 전부 뽑았을 때 종료 
        if(current==5){
            return; 
        }
        
        for(char next : mo){
            currentData+= next;
            
            dfs(query, current+1, currentData);
            
            currentData = currentData.substring(0, currentData.length()-1);
            
        }
        
        
        
        
    }
    
    public int solution(String word) {
        
        dfs(word, 0, "");
        return result;
    }
}