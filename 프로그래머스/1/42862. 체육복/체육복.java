import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] clothes = new int[n+1]; 
        
        Arrays.fill(clothes, 1);
        
        for(int l : lost){
            clothes[l]--; 
        }
        
        for(int r: reserve){
            clothes[r]++; 
        }
        
        
        for(int i=1; i<n; i++){
            if(clothes[i]==2&&clothes[i+1]==0){
                clothes = borrow(i,i+1, clothes);
            }else if(clothes[i]==0&&clothes[i+1]==2){
                clothes = borrow(i,i+1, clothes);
            }
        }
        
        //System.out.println(Arrays.toString(clothes)); 
        
        int answer = 0;
        for(int i=1;i<=n; i++){
            if(clothes[i]>=1)answer++;
        }
            
        return answer;
    }
    
    private static int[] borrow(int current, int next, int[] clothes){
        clothes[current]=1; 
        clothes[next]=1; 
        
        return clothes; 
    }
}