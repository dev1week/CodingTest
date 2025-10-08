import java.io.*; 
import java.util.*; 

class Solution {
    
    private static List<Result> results = new ArrayList<>(); 
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        
        discountComb(0, new int[emoticons.length], emoticons.length, users, emoticons);
        Collections.sort(results); 
        
        answer[0] = results.get(0).totSubs; 
        answer[1] = results.get(0).totSales; 
        return answer;
    }
    
    private int getAfterDiscount(int price, int ratio){
        return price*(100-ratio)/100; 
    }
    
    private void discountComb(int current, int[] selectedDiscount, int m, int[][] users, int[] emoticons){
        if(current==m){
            int sub = 0; 
            int totSales =0; 
            for(int[] user: users){
                int userSales = 0;
                int discountLimit = user[0];
                int priceLimit = user[1]; 
                
                for(int i=0; i<emoticons.length; i++){
                    if(selectedDiscount[i]>=discountLimit){
                        userSales += getAfterDiscount(emoticons[i], selectedDiscount[i]);
                    }
                }
                if(userSales>=priceLimit){
                    userSales = 0; 
                    sub++; 
                    //구매를 취소한다. 
                    //구독한다. 
                }
                
                totSales += userSales; 
                results.add(new Result(totSales, sub)); 
            }
            //n번 반복 -> 사람 순회 
                //m번 반복 -> 이모티콘 순회 -> 사람 당 구매하는 이모티콘 총 금액 or 구독여부 파악하기 
            
            
            
            return; 
        }
        
        for(int discount=10; discount<=40; discount+=10){
            selectedDiscount[current] = discount; 
            discountComb(current+1, selectedDiscount, m, users, emoticons);
        }
        
        
    }
    //n명에게 m개의 이모티콘 할인 
    //할인율은 10, 20, 30, 40 중 하나로 설정 
    
    //2^14*100*7
    
    //4^m 
    
    
    //목표 
        //구독 서비스 가입자 늘리기 
        //판매액 최대 
    
    //구매비용의 합이 일정 가격을 넘어감 -> 구독 
    //일정 비율 이상 할인할 경우 모두 구매함 
    
    
}   

class Result implements Comparable<Result>{
    
    int totSales, totSubs;
    
    public Result(int totSales, int totSubs){
        this.totSales = totSales;
        this.totSubs = totSubs; 
    }
    
    @Override
    public int compareTo(Result o){
        if(o.totSubs==this.totSubs){
            return o.totSales-this.totSales; 
        }
        return o.totSubs-this.totSubs;
    }
    
    
    @Override 
    public String toString(){
        return this.totSales+"원, 구독자 수 : "+this.totSubs; 
    }
    
    
}