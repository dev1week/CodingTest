import java.util.*;
class Solution {
    
    public static boolean isPossible(String bannedId, String userId){
        if(bannedId.length()!=userId.length()) return false; 
        
        for(int i=0; i<bannedId.length(); i++){
            if(bannedId.charAt(i)=='*') continue;
            if(bannedId.charAt(i)!=userId.charAt(i))return false; 
            
        }
        return true; 
    }
    
    public List<Integer>[] getPossibleBanned(String[] userIds, String[] bannedId){
        List<Integer>[] possibleBanned = new List[bannedId.length];
        
        
        
        for(int idx=0; idx<bannedId.length; idx++){
            possibleBanned[idx] = new ArrayList<Integer>(); 
            for(int userIdx=0; userIdx<userIds.length; userIdx++){
                if(isPossible(bannedId[idx], userIds[userIdx])){
                    possibleBanned[idx].add(userIdx);
                }
            }
            
        }
        return possibleBanned; 
    }
  
    public int solution(String[] userId, String[] bannedId) {
        
        
        //userId와 bannedId로 [][] 배열을 만든다. 
        
        List<Integer>[] possibleBannedId = getPossibleBanned(userId, bannedId);
        

        
        //[][] 배열을 순회하며 가능한 인덱스 조합을 찾는다. 
            //해당 인덱스 조합을 정렬하고 해시에 저장한다. 
        Set<String> set = new HashSet<>(); 
        dfs(0,possibleBannedId, new int[bannedId.length], new boolean[userId.length], set); 
        //해시의 크기를 반환한다. 
        return set.size(); 
    }
    
    
    private static void dfs(int current, List<Integer>[] possibleBannedId, int[] selecteds, boolean[] isUsed, Set<String> set){
        
        if(current==possibleBannedId.length){
            
            
            int[] sorted = Arrays.copyOf(selecteds, selecteds.length);
            Arrays.sort(sorted);
            
            set.add(Arrays.toString(sorted));
            return;
        }
        
        for(int nextIdx : possibleBannedId[current]){
            if(isUsed[nextIdx])continue;
            isUsed[nextIdx] = true; 
            selecteds[current] = nextIdx; 
            dfs(current+1, possibleBannedId, selecteds, isUsed, set);
            isUsed[nextIdx] = false; 
        }
        
        
    }
}