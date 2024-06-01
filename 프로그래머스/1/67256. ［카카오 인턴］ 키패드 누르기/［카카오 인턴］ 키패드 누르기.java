import java.util.HashMap;
import java.util.Map;



class Point{
    int x, y;
    
    
    public Point(int x, int y){
        this.x = x;
        this.y = y; 
        
    }
    
    
    @Override
    public String toString(){
        //to-do StringBuilder로 바꾸기 
        return this.x+":"+this.y;
    }
    
    public int getDistanceFrom(Point otherPoint){
        
        return Math.abs(this.x-otherPoint.x) + Math.abs(this.y-otherPoint.y); 
        
    }
    
}

class Solution {
    //엄지손가락 규칙 
        //상하좌우 4가지 방향 
        // 1,4,7 -> 왼쪽
        // 3,6,9 -> 오른쪽 
        // 2,5,8,0 -> 키패드 위치에서 더 가까운 엄지손가락 
            //거리가 같을 경우 오른손잡이/왼손잡이 여부에 따라 달라짐 
    
    static final String L = "left";
    static final String R = "right"; 
                            
    
    static Map<Integer, Point> positions; 
    
    static boolean isLeft(Integer data ){
        return data.equals(1)||data.equals(4)||data.equals(7);
    }
    
    static boolean isRight(Integer data ){
        return data.equals(3)||data.equals(6)||data.equals(9); 
    }
    
    static void init(){
        positions = new HashMap<>(); 
        
        int number = 1;
        for(int x=0; x<3; x++){
            for(int y=0; y<3; y++){
                Point current = new Point(x,y);
                
                positions.put(number, current); 
                number++; 
                
            }
        }
        
        positions.put(0, new Point(3,1)); 
        
    }
    
    
    static Point currentLeftFinger = new Point(3,0);
    static Point currentRightFinger = new Point(3,2);
    static StringBuilder result = new StringBuilder(); 
    
    static void moveLeftHand(Point target){
        currentLeftFinger = target;
        result.append("L");
    }
    
    static void moveRightHand(Point target){
        currentRightFinger = target; 
        result.append("R"); 
        
    }
    
    
    
    
    public String solution(int[] numbers, String hand) {
                
        //해쉬맵에 각 번호 + 좌표 저장 
        init(); 
        
        for(int targetNumber: numbers){
            Point targetPoint = positions.get(targetNumber);
            
            if(isRight(targetNumber)){
                moveRightHand(targetPoint);
            }else if(isLeft(targetNumber)){
                moveLeftHand(targetPoint); 
            }else{
                
                if(currentLeftFinger.getDistanceFrom(targetPoint) == currentRightFinger.getDistanceFrom(targetPoint)){
                    
                    //왼손잡이인 경우 
                    if(hand.equals(L)){
                        moveLeftHand(targetPoint);
                    }
                    //오른손잡이인 경우 
                    else{
                        moveRightHand(targetPoint);
                    }
                }//오른쪽이 더 가까운 경우 
                else if(currentLeftFinger.getDistanceFrom(targetPoint)>currentRightFinger.getDistanceFrom(targetPoint)){
                    moveRightHand(targetPoint);
                }//왼쪽이 더 가까운 경우 
                else{
                    moveLeftHand(targetPoint);
                }
                
                
            }
            

            
            
            
            //눌러야할 숫자가 왼손인지 오른손인지 파악 
                //둘중에 하나일 경우 해당 손의 좌표 옮기기 
            
            //아닐 경우 
                //해당 좌표 구하기 //해시맵 -> o(1)
                //거리 중 어느게 가까운지 
                    //가까운쪽의 좌표를 옮긴다. 
            
                //거리가 같다면?
                    //주사용손으로 판단하여 좌표를 옮긴다. 
            
            
        }
        
        //O(n) -> 1000 
        
        return result.toString();
    }
}