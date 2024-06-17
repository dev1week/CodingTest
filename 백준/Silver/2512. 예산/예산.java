import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    public static void main(String[] args)throws IOException{
        int n= Integer.valueOf(buffer.readLine()); // 지방의 개수 3~10^4
        //예산 요청 ~10^5
        //총예산 합계 ~10^9

        int[] budgets = Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int limit = Integer.valueOf(buffer.readLine());

        int max = Arrays.stream(budgets).max().getAsInt();



        System.out.println(getAnswer(budgets, limit, max));


    }

    private static int getAnswer(int[] budgets, int limit, int max){
        int l = 0;
        int h = max;

        while(h>l){
            int mid = (l+h+1)/2;
            if(isValid(mid, budgets, limit)){
                l = mid;
            }else{
                h = mid-1;
            }

        }

        return l;

    }

    //상한금액을 이진탐색한다.

    private static boolean isValid(int mid, int[] budgets, int limit){
        int tmpTotSum = 0;

        for(int budget:budgets){
            if(budget>mid){
                tmpTotSum+=mid;
            }else{
                tmpTotSum+=budget;
            }
        }

        return tmpTotSum<=limit;
    }
    //결정문제
        //상한금액, 예산안, 예산총합
            // 예산안 전부 순회
                //해당 예산이 상한금액보다 클경우 -> 상한 금액으로 더한다.
                //예산안을 임시 예산 총합에 더한다.
            //예산 총합>=임시예산 총합



}
