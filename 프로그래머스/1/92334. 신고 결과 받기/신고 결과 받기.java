import java.util.*; 

class Solution {
    
    //한번에 한명의 유저만 신고 가능 
    //신고 횟수는 제한 없읍 -> 서로 다른 유저를 계속해서 신고 가능 
    //한 유저를 여러번 신고해도 1회로 처리 
    
    //k번 이상 신고된 유저는 게시판 이용 정지 
        //해당 유저를 신고한 모든 유저에게 정지 사실을 통지 
    
    
    public int[] solution(String[] idList, String[] report, int k) {
        int[] answer = new int[idList.length];
        
        
        
        Map<String, Integer> idToIdx = new HashMap<>(); 
        Map<String, Set<String>> criminalToReporter = new HashMap<>(); 
        
        for(int idx=0; idx<idList.length; idx++){
            criminalToReporter.put(idList[idx], new HashSet<>());
            idToIdx.put(idList[idx], idx);
        }
        
        for(String data : report){
            StringTokenizer tokens = new StringTokenizer(data); 
            String reporter = tokens.nextToken();
            String criminal = tokens.nextToken(); 
            
            criminalToReporter.get(criminal).add(reporter);
            
        }
        
        //정지된 사람 리스트 추출 
        List<String> stopList = new ArrayList<>(); 
        for(String id: idList){
            
            int reportCount = criminalToReporter.get(id).size(); 
            
            if(reportCount>=k){
                //정지 당한 케이스 
                stopList.add(id);
            }
            
        }
        
        
        //정지된 사람 리스트 바탕으로 메일 받을 유저 카운트 
        Map<String, Integer> mailCount = new HashMap<>(); 
        for(String stopUser: stopList){
            Set<String> reporters = criminalToReporter.get(stopUser);
            
            for(String reporter : reporters){
                answer[idToIdx.get(reporter)]++;
            }
            
        }
        
        
        
        return answer;
    }
}