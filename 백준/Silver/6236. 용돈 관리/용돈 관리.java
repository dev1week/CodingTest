import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    public static void main(String[] args)throws IOException{
        tokens = new StringTokenizer(buffer.readLine());
        int n= Integer.valueOf(tokens.nextToken());// 일수 ~10^5
        int m= Integer.valueOf(tokens.nextToken()); //인출 횟수 ~n
        int k;//인출 금액

        int[] costs = new int[n];


        for(int day=0; day<n; day++){
            costs[day] = Integer.valueOf(buffer.readLine());
        }

        System.out.println(getAnswer(costs, m));

        //하루를 보낼 수 있다> -> 그대로 사용
        //모자라다 -> 남은 금액은 통장에 집어넣고 k원 인출

        //남은 금액>그날 사용한 금액 -> 남은 금액 통장에 집어넣고 다시 k원 인출 가능

//
//        //k를 최소화하는 방법은
//        int[] test = {500,501};
//        System.out.println(isValid(500,test,2));

    }

    //인출금액 최소를 이진탐색을 통해 찾는다.

    private static long getAnswer(int[] costs, int limit){
        long l = 0;
        long h = Arrays.stream(costs).sum();

        while(h>l){
            long mid = (h+l)/2;

            if(isValid(mid, costs, limit)){
                h=mid;

            }else{
                l=mid+1;
            }
        }


        return l;
    }



    private static boolean isValid(long limitOfWithdraw,int[] costs,int limitOfWithdrawCount ){

        long currentMoney = limitOfWithdraw;
        int currentWithdrawCount = 1;

        for(int cost: costs){
            if(cost>limitOfWithdraw){
                return false;
            }
            if(currentMoney<cost){
                currentMoney = limitOfWithdraw-cost;
                currentWithdrawCount++;
            }else{
                currentMoney-=cost;
            }
        }

        return currentWithdrawCount<=limitOfWithdrawCount;

    }

    //결정 조건
        //현재 남아있는 금액 = 500
        //현재 인출 카운트 = 1
        //사용할 금액 순회(마지막 제외)
            //현재 남아있는 금액 < 사용해야하는 금액
                //현재 남아있는 금액 500-사용해야하는 금액
                //현재 인출 카운트 ++
            //그외 경우
                //현재 남아있는 금액 -= 현재 사용해야하는 금액

        // 인출 카운트<=m;






}
