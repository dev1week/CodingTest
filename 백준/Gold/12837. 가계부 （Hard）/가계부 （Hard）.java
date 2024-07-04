import java.io.*;
import java.util.*;

public class Main {
        static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        static StringTokenizer tokens;

        //생후 p일에 수입/지출 내용을 추가
            //수입은 양수
            //지출은 음수

        //p~q일까지의 잔고 변화값 출력
    static long[] sumTree;
    static int n; //살아온 날
    static int q; //쿼리 갯수

    static final int CHANGE = 1;
    static final int GET = 2;


    static long getSum(int current, int left, int right, int queryLeft, int queryRight){
        if(queryLeft>right||queryRight<left){
            return 0;
        }


        if(queryLeft<=left&&queryRight>=right){
 //출력시 문제가 있다면 다음과 같은 디버깅을 수행해보는 것도 좋다.
//            System.out.println(new StringBuilder().append(left).append("~").append(right).append(" : ").append(sumTree[current]).append(":").append(current).toString());
            return sumTree[current];
        }

        int mid = (left+right)/2;

        int leftNode = current*2;
        int rightNode = current*2+1;

        long leftSum = getSum(leftNode, left, mid, queryLeft, queryRight);
        long rightSum = getSum(rightNode, mid+1, right, queryLeft, queryRight);



        return leftSum + rightSum;
    }

    static void change(int current, int left, int right, int targetIdx, long targetValue){
        if(targetIdx>right||targetIdx<left){
            return;
        }
        if(left==right){
            sumTree[current] += targetValue;
            return;
        }




        int mid = (left+right)/2;

        int leftNode = current*2;
        int rightNode = current*2+1;

        change(leftNode, left, mid, targetIdx, targetValue);
        change(rightNode, mid+1, right, targetIdx, targetValue);

        sumTree[current]=sumTree[leftNode]+sumTree[rightNode];
    }



    public static void main(String[] args) throws IOException{
        tokens = new StringTokenizer(buffer.readLine());

        n = Integer.valueOf(tokens.nextToken());
        q = Integer.valueOf(tokens.nextToken());


        sumTree = new long[4*n];

        StringBuilder result = new StringBuilder();

        for(int query=0; query<q; query++){
            tokens = new StringTokenizer(buffer.readLine());

            int command = Integer.parseInt(tokens.nextToken());

            if(command == GET){
                int queryLeft = Integer.valueOf(tokens.nextToken());
                int queryRight = Integer.valueOf(tokens.nextToken());

                result.append(getSum(1,1,n,queryLeft, queryRight)).append("\n");
            }else if(command == CHANGE){
                int targetIdx = Integer.parseInt(tokens.nextToken());
                long targetValue = Integer.parseInt(tokens.nextToken());

                change(1,1,n,targetIdx, targetValue);
            }

        }

        System.out.println(result);
   }






}