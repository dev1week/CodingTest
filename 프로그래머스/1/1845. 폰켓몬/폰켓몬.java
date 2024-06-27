import java.io.*;
import java.util.*;
class Solution {
   
    public int solution(int[] nums) {
        
        int answer = 0;
        Set<Integer> set = new HashSet<>();
        
        for(int num : nums){
            set.add(num);
        }
        
        
        answer = getAnswer(set.size(), nums.length/2);
        //해시 크기 > n/2
            // n/2만큼 반환 
        
        //해시 크기 <= n/2 
            // 해시크기만큼 반환 
        
        return answer;
    }
    
    static int getAnswer(int setSize, int monsterNum){
        if(setSize > monsterNum){
            return monsterNum;
        }else{
            return setSize;
        }
    }
}