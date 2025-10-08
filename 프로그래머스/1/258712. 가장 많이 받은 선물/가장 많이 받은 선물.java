import java.util.*; 
import java.io.*;
class Solution {
    private static StringTokenizer tokens;
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        Map<String, Integer> presentScore = new HashMap<>(); 
        Map<String, Integer> presentCntGroupBy = new HashMap<>(); 
        
        Map<String, Integer> nextMonthPresentCnt = new HashMap<>(); 
        
        
        for(String fromTo: gifts){
            tokens = new StringTokenizer(fromTo, " ");
            String from = tokens.nextToken();
            String to = tokens.nextToken(); 
            
            presentCntGroupBy.put(fromTo, presentCntGroupBy.getOrDefault(fromTo,0)+1); 
            presentScore.put(from, presentScore.getOrDefault(from, 0)+1);
            presentScore.put(to, presentScore.getOrDefault(to, 0)-1);
        }
        
        
      // System.out.println(presentScore);
      //   System.out.println(presentCntGroupBy);
        for(int i=0; i<friends.length; i++){
            for(int j=i+1; j<friends.length; j++){
                
                
                String key = friends[i]+" "+friends[j]; 
                String reverseKey = friends[j]+" "+friends[i]; 
                
                
                if(presentCntGroupBy.containsKey(key)||presentCntGroupBy.containsKey(reverseKey))
                {   
                    int iCnt = presentCntGroupBy.getOrDefault(key, 0);
                    int jCnt = presentCntGroupBy.getOrDefault(reverseKey,0);
                    if(iCnt>jCnt){
                        // System.out.println(friends[i]+"이 더 많이 줘서 선물 받음");
                        increasePresentCnt(friends[i], nextMonthPresentCnt);
                    }
                    else if(jCnt>iCnt){
                        // System.out.println(friends[j]+"이 더 많이 줘서 선물 받음");
                        increasePresentCnt(friends[j], nextMonthPresentCnt);
                    }else if(iCnt == jCnt){
                        // System.out.println("주고 받은 기록만 있음");
                        increaseByPresentScore(friends[i], friends[j], nextMonthPresentCnt,  presentScore);
                    }
                }else{
                    increaseByPresentScore(friends[i], friends[j], nextMonthPresentCnt,  presentScore);
                }
            }
        }
                   
                  
        
        //친구 이중 for 문 완탐 50*49 
            //해당 친구에게서 받을 수 있는지 확인한다. 
                //주고 받은 선물 기록이 있는 경우 
                    //더 많이 준 사람 +1 
                //주고 받은 선물 기록이 없는 경우 
                    //선물지수를 확인한다. 
                        //큰 사람 +1 
                        //같으면 그냥 넘어간다. 
        int result = 0; 
        for(String friend: friends){
            result = Math.max(result, nextMonthPresentCnt.getOrDefault(friend, 0));
        }
        return result;
    }
    
    private static void increasePresentCnt(String name, Map<String, Integer> nextMonthPresentCnt){
            nextMonthPresentCnt.put(name,nextMonthPresentCnt.getOrDefault(name,0)+1);
    }
    
    
    private static void increaseByPresentScore(String f1, String f2, Map<String, Integer>nextMonthPresentCnt, Map<String, Integer> presentScore){
        
        if(presentScore.getOrDefault(f1,0)>presentScore.getOrDefault(f2,0)){
                // System.out.println(f1+ "의 선물지수가 더 커서 받음");
                nextMonthPresentCnt.put(f1, nextMonthPresentCnt.getOrDefault(f1,0)+1);
        }else if(presentScore.getOrDefault(f2,0)>presentScore.getOrDefault(f1,0)){
                // System.out.println(f2+ "의 선물지수가 더 커서 받음");
                nextMonthPresentCnt.put(f2, nextMonthPresentCnt.getOrDefault(f2,0)+1);               
        }
    }
    //이름이 같은 사람이 없다. 
    
    
    //이번달 까지 선물을 주고 받은 기록 -> 다음달에 누가 선물을 많이 받을지 
    
    //두 사람이 서로 선물을 주고 받은 기록이 있음 -> 더 많은 선물을 준 사람이 다음달에 받는다. 
    
    //하나도 없거나 주고 받은 수가 같다면 선물지수가 더 큰 사람이 받는다. 
        //선물지수마저 같은 경우 주고 받지 않는다. 
    
    //선물 지수 
        //이번달까지 자신이 친구들에게 준 선물의 수 - 받은 선물의 수 
}