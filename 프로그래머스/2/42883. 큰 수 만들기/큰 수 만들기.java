
import java.util.*;
import java.util.stream.Collectors; 
class Solution {
    public String solution(String number, int k) {
        Stack<Integer> maxNum = new Stack<>();
        
        for(int i=0; i<number.length(); i++){
            int data = number.charAt(i)-'0';
            
            while(k>0&&!maxNum.isEmpty()&&data>maxNum.peek()){
                maxNum.pop(); 
                k--; 
            }
            
            maxNum.push(data);
        }
        
        while(k-->0){
            maxNum.pop(); 
        }
        
        return maxNum.stream().map(String::valueOf).collect(Collectors.joining());
        
    }
}