import java.util.*; 
class Solution {
    public int solution(int n, int k) {
        
        
        String kDigit = getDigit(n, k);
        
        List<Long> numbers = getPartNumbers(kDigit);
        
        int cnt = 0;
        
        for(long number: numbers){
            if(isDecimal(number)){
                cnt++; 
            }
        }
        return cnt;
    }
    
    private boolean isDecimal(long number){
        if(number==1)return false; 
        
        for(int i=2; i<=Math.sqrt(number); i++){
            if(number%i==0)return false; 
        }
        return true; 
    }
    
    private String getDigit(long n, int k){
        long current = n; 
        StringBuilder  kDigit = new StringBuilder(); 
        while(current>0){
            kDigit.append(current%k);
            current/=k;
        }
        
        return kDigit.reverse().toString(); 
    }
    
    private List<Long> getPartNumbers(String kDigit){
        List<Long> numbers = new ArrayList<>(); 
        StringTokenizer tokens = new StringTokenizer(kDigit, "0"); 
        while(tokens.hasMoreTokens()){
            numbers.add(Long.parseLong(tokens.nextToken()));
        }        
        return numbers; 
    }
}