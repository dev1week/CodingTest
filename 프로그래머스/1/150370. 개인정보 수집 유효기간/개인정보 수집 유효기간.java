import java.util.*; 
class Solution {
    
    private int getTotDays(int year, int month, int day){
        return year*12*28+month*28+day; 
    }
    
    private int getTotDays(String date){
        
        String[] data= date.split("\\.");
        
        return getTotDays(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2])); 
    }
    
    public List<Integer> solution(String today, String[] terms, String[] privaciess) {
        
        List<Integer> result = new ArrayList<>(); 
        
        
        Map<String, Integer> termsToValidPeriod = new HashMap<>(); 
        for(String term: terms){
            String[] termData = term.split(" ");
            
            termsToValidPeriod.put(termData[0], Integer.valueOf(termData[1])); 
        }
        //terms에서 약관 -> 유효기간 월을 구한다. -> 월을 표준으로 바꾼다. 20 
        
        
        Privacy[] privacies = new Privacy[privaciess.length];
        //privacies에서 -> 약관 동의 시점 , 종류를 갖는 클래스를 만든다. 100
        
        for(int i=0; i<privaciess.length; i++){
            String[] data = privaciess[i].split(" "); 
            System.out.println(Arrays.toString(data));
            privacies[i] = new Privacy(getTotDays(data[0]),data[1]);
        }
        
        
        for(int i=0; i<privacies.length; i++){
            if(isNotValid(privacies[i], termsToValidPeriod, getTotDays(today))){
                result.add(i+1);
            }
        }
                    
        //오늘의 표준을 구한다. 

        //클래스 순회한다. 
            //유효기간을 넘어간 프라이버시의 인덱스만 result에 넣는다. 
        
        
        return result;
    }
    
    private boolean isNotValid(Privacy privacy, Map<String, Integer> termsToValidPeriod, int today){
        int periodMonth = termsToValidPeriod.get(privacy.term); 
        
        System.out.println(getTotDays(0,periodMonth,0)+"+"+privacy.signDate+" "+today);
        
        return getTotDays(0,periodMonth,0)+privacy.signDate<=today;
    }
    //개인정보 N개 
    //모든달은 28일까지 있다고 가정함
    //약관마다 유효기간이 다르다. 
    
    //오늘 날짜가 주어졌을 때 
        //파기해야할 개인 정보 반환하기
    
}

class Privacy{
    int signDate;
    String term; 
    public Privacy(int signDate, String term){
        this.signDate = signDate; 
        this.term = term; 
    }
    
    public String toString(){
        return signDate+" "+term; 
    }
}