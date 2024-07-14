import java.util.*; 
class Solution {
    
    private static final int RECEIVE = 0;
    private static final int SEND = 1; 
    
    private static Map<String, Integer> getNameToIdx(String[] freinds){
        Map<String, Integer> nameToIdx = new HashMap<>(); 
    
        for(int i=0; i<freinds.length; i++){
            nameToIdx.put( freinds[i], i); 
        }
    
        return nameToIdx;
    }
    private static int[][] getGiftLog(String[] gifts, Map<String, Integer> nameToIdx){
        int[][] giftCount = new int[nameToIdx.size()][nameToIdx.size()];
        for(String gift : gifts){
            StringTokenizer tokens = new StringTokenizer(gift, " "); 
            String sender = tokens.nextToken(); 
            String receiver = tokens.nextToken(); 
            
            giftCount[nameToIdx.get(sender)][nameToIdx.get(receiver)]++;             
        }
        
        return giftCount; 
        
    }
    
    private static int[][] getGiftCount(int[][] giftLog){
        int[][] giftCount = new int[giftLog.length][2]; 
        for(int sender=0; sender<giftLog.length; sender++){
            for(int receiver=0; receiver<giftLog.length; receiver++){
                if(giftLog[sender][receiver]>0){
                    giftCount[sender][SEND] += giftLog[sender][receiver];
                    giftCount[receiver][RECEIVE]+= giftLog[sender][receiver];
                }
            }
        }
        
        return giftCount; 
    }
    
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
            
        Map<String, Integer> nameToIdx = getNameToIdx(friends); 
        //giftCount[a][b] a가 b에게 요번달에 준 선물의 횟수
        int[][] giftLog = getGiftLog(gifts, nameToIdx);
        int[][] giftCount = getGiftCount(giftLog); 
        
        for(int[] gift: giftLog){
            System.out.println(Arrays.toString(gift));
        }
        
        System.out.println();
        
        
        for(int[] count : giftCount){
            System.out.println(Arrays.toString(count)); 
        }
        
        int[] nextGiftCount = getNextGiftCount(giftLog,giftCount);
        
        System.out.println(Arrays.toString(nextGiftCount)); 
        
        Arrays.sort(nextGiftCount);
        
        return nextGiftCount[nextGiftCount.length-1]; 
    }
    
    
    private static int[] getNextGiftCount(int[][] giftLog, int[][] giftCount){
        int[] nextGiftCount = new int[giftLog.length];
        for(int user1 =0; user1<giftLog.length; user1++){
            for(int user2=user1; user2<giftLog.length; user2++){
                if(user1 == user2) continue; 
                int giftScore = giftLog[user1][user2] - giftLog[user2][user1]; 
                
                if(giftScore == 0 ){
                    
                    //선물 지수를 구한다. 
                    int user1Score = giftCount[user1][SEND]-giftCount[user1][RECEIVE];
                    int user2Score = giftCount[user2][SEND]-giftCount[user2][RECEIVE];
                    if(user1Score==user2Score)continue; 
                    int result = user1Score > user2Score ? user1: user2; 
                    
                    nextGiftCount[result]++; 
                }else{
                    int result = giftLog[user1][user2] > giftLog[user2][user1] ? user1: user2; 
                    nextGiftCount[result]++; 
                    
                }
            }
        }
        
        return nextGiftCount;
        
        
    }
}