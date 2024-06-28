import java.util.*;
class Solution {
    
    private int addScovilleScores(int score1, int score2){
        return score1+(score2*2);
    }
    
    public int solution(int[] scovilleScores, int K) {
        // 모든 음식의 스코빌 지수를 k 이상으로 
        
        // 스코빌 지수가 가장 낮은 두개의 음식 합치기 
            //가장 맵지 않은 + 2번째로 가장 맵지 않은 x2 
        PriorityQueue<Integer> scovlilleScoreQue = new PriorityQueue<>(); 
        
        for(int score : scovilleScores){
            scovlilleScoreQue.add(score);
        }
        
        return getCount(scovlilleScoreQue, K);
    }
    
    private int getCount(PriorityQueue<Integer> scovilleScores, int limit){
        int count = 0;
        if(scovilleScores.peek()>=limit){
            return 0; 
        }else{
            while(scovilleScores.size()>1){
                int score1 = scovilleScores.poll();
                int score2 = scovilleScores.poll();
                
                if(score1>=limit){
                    return count;
                }
                
                scovilleScores.add(addScovilleScores(score1, score2));
                count++;
            }
        }
        
        if(scovilleScores.peek()>=limit){
            return count;
        }else{
            return -1;
        }
    }
    
     //첫 음식이 k 이상일 경우 
            //return 0; 
        //첫 음식이 k 미만일 경우 
            //while(트리의 크기가 1일때까지)
                //두 개의 음식 가져오기 
                //첫번째 음식이 k보다 클 경우 
                    //현재 카운트 반환 
                // 공식대로 합치기 
                // count++
            
        //남아있는 하나의 음식 k보디 큰지 판단 
            //아니면 -1
            //맞으면 count 반환 
        
}