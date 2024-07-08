import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int n;
    static int m;

    //초밥
        //1번부터 N번 손님 순서대로 초밥을 받는다.
        //아무도 초밥을 먹지 않으면 초밥은 버려진다.


    //n명의 손님은 먹고 싶은 초밥의 목록이 있음
        //순서에 상관 없이 목록에 적힌 초밥이 앞에 오면 반드시 먹는다.
        //목록에 적히지 않았을 경우 반드시 먹지 않는다.


    //m개의 초밥이 주어질 경우 각 손님이 먹게 되는 초밥의 개수를 구하기

    //각 종류의 초밥은 최대 한번 먹는다.




    public static void main(String[] args) throws IOException {
        // [초밥 종류] = 대기중인 사람
        tokens= new StringTokenizer(buffer.readLine());

        int n = Integer.valueOf(tokens.nextToken());
        int m = Integer.valueOf(tokens.nextToken());



        Map<Integer, PriorityQueue<Integer>> sushiToPerson = new HashMap<>();
        int[] count = new int[n+1];




        for(int person=1; person<=n; person++){
            tokens = new StringTokenizer(buffer.readLine());

            int k = Integer.valueOf(tokens.nextToken());

            for(int sushiIdx=0; sushiIdx<k ; sushiIdx++){
                int sushi = Integer.valueOf(tokens.nextToken());

                if(!sushiToPerson.containsKey(sushi)){
                    sushiToPerson.put(sushi, new PriorityQueue<>());
                }

                sushiToPerson.get(sushi).add(person);

            }

        }


        int[] sushiList = Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();


        for (int sushi:sushiList){
            if(sushiToPerson.containsKey(sushi)){
                if(sushiToPerson.get(sushi).size()!=0){
                    count[sushiToPerson.get(sushi).poll()]++;
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for(int person = 1; person<=n; person++){
            result.append(count[person]).append(" ");
        }
        System.out.println(result);





        //초밥 리스트 순회 m
            //int targetPerson sushiToPerson[현재초밥종류].poll();
            //count[사람]++
        //count 배열 출력
    }




}