
import java.io.*;
import java.util.*;


class Container{
    int priority, weight;


    public Container(int priority, int weight){
        this.priority = priority;
        this.weight = weight;
    }


    @Override
    public String toString(){

        return new StringBuilder().append("우선순위 : ").append(this.priority).append(", 무게 : ").append(this.weight).toString();
    }



}



public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    //n개의 컨테이너

    //컨테이너 옮기기
        //무게 = 비용

    //우선 순위
        //1~m
        //낮은 컨테이너를 먼저 적재
        //적어도 하나 이상은 존재한다.
        //번호가 안채워진채로 높은 우선순위가 먼저 온다면 레일의 처음으로 보냄
            //무게만큼 비용 발생
        //번호에 맞는 컨테이너가 올경우 무조건 적재한다.
            //우선 순위가 같을 경우
                //가벼운 컨테이너가 빠질 때까지 전부 빼냄
                //현재 컨테이너 삽입
                //빼낸 컨테이너 다시 전부 넣기





    public static void main(String[] args)throws IOException{
        tokens = new StringTokenizer(buffer.readLine());
        int n = Integer.valueOf(tokens.nextToken());
        int m = Integer.valueOf(tokens.nextToken());

        int currentPriority =m;
        int[] remainContainers = new int[m+1]; //우선순위를 입력하면 현재 적재가 안된 컨테이너 갯수 반환
        List<Container> belt = new LinkedList<>();
        Stack<Container> resultArea = new Stack<>();
        Stack<Container> remainArea = new Stack<>();


        for(int i=0; i<n; i++){
            tokens = new StringTokenizer(buffer.readLine());

            int priority = Integer.valueOf(tokens.nextToken());
            int weight = Integer.valueOf(tokens.nextToken());

            belt.add(new Container(priority, weight));
            remainContainers[priority]++;
        }

        int totCost = 0;

        while(belt.size()!=0){
            Container currentContainer = belt.get(0);
            belt.remove(0);
            if(currentContainer.priority==currentPriority){

                remainContainers[currentContainer.priority]--;
                if(remainContainers[currentContainer.priority]==0) currentPriority--;


                while(!resultArea.isEmpty()&&resultArea.peek().priority==currentContainer.priority&&resultArea.peek().weight<currentContainer.weight){
                    totCost += resultArea.peek().weight;
                    remainArea.add(resultArea.pop());
                }

                resultArea.add(currentContainer);
                totCost+=currentContainer.weight;

                while(!remainArea.isEmpty()){
                    totCost+= remainArea.peek().weight;
                    resultArea.add(remainArea.pop());
                }

            }else{
                belt.add(currentContainer);
                totCost+=currentContainer.weight;
            }



            //가장 마지막에 있는 컨테이너를 가져온다.
                //해당 컨테이너의 우선 순위와 현재 우선 순위를 비교한다.
                    //유효할 경우
                        // 벨트에서 빼낸다.
                        // remainContainers 배열을 처리한다.
                            //해당 값이 0이 되었을 경우 currentPriority ++한다.

                        //무게를 고려하여 처리한다.
                            //while(resultArea.peek()>currentWeight)
                                //빼내서 remainArea에 넣기
                            //resultArea에 현재 컨테이너 넣어주기
                            //while(remainArea.isEmpty())
                                //빼내서 resultArea에 넣기
                    //유효하지 않을 경우
                        //벨트에서 빼낸다.
                        //앞으로 옮긴다.
                        //해당 무게만큼 cost를 올린다.




        }


        System.out.println(totCost);




    }

}
