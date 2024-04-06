
//(인 경우 스택에 넣는다. 
//)인 경우 스택에서 뺀다.

//뺄 때 스택에 아무것도 없다면 false
//다 끝나고나서 스택에 값이 남아있다면 true 
import java.util.*;
class Solution {
    
    boolean judge(String s){
        Stack<Character> stack = new Stack<>(); 
        
        for(int i=0; i<s.length(); i++){
            char data = s.charAt(i);
            
            if(data=='('){
                stack.add('(');        
            }else{
                if(stack.isEmpty()){
                    return false;
                }else{
                    stack.pop();
                }
            }
        }
        System.out.println(stack);
        if(!stack.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    boolean solution(String s) {
        
        

        return judge(s);
    }
}