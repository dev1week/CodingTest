import java.io.*;
import java.util.*;

public class Main {
        static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer tokens;

        static int n; //정수의 갯수 10^5
        static int q; //턴의 개수 10^5
        static int[] numbers;
        static long[] sumTree;


        private static void init(int current, int left, int right){
            if(left==right){
                sumTree[current] = numbers[left];
                return;
            }

            int mid = (left+right)/2;


            init(current*2, left, mid);
            init(current*2+1, mid+1, right);

            sumTree[current] = sumTree[current*2]+sumTree[current*2+1];


        }

        private static long getSum(int current, int left, int right, int queryLeft, int queryRight){
            if(left>queryRight||right<queryLeft){

                return 0;
            }

            if(left>=queryLeft&&right<=queryRight){
                return sumTree[current];
            }

            int mid = (left+right)/2;
            long leftSum = getSum(current*2, left, mid, queryLeft, queryRight);
            long rightSum = getSum(current*2+1, mid+1, right, queryLeft, queryRight);

            return leftSum+rightSum;
        }

        private static void update(int current, int left, int right, int targetIdx, int targetValue){
            if(targetIdx<left||targetIdx>right){
                return;
            }

            if(left==right){
                sumTree[current] = targetValue;
                return;
            }

            int mid = (left+right)/2;

            update(current*2, left,mid, targetIdx, targetValue);
            update(current*2+1, mid+1, right, targetIdx, targetValue);

            sumTree[current] = sumTree[current*2] + sumTree[current*2+1];
        }

       public static void main(String[] args) throws IOException{
            tokens = new StringTokenizer(buffer.readLine());

            n = Integer.valueOf(tokens.nextToken());
            q = Integer.valueOf(tokens.nextToken());

            numbers = new int[n+1];
            sumTree = new long[4*n];


            tokens = new StringTokenizer(buffer.readLine());
            for(int idx=1; idx<=n; idx++){
                numbers[idx] = Integer.valueOf(tokens.nextToken());
            }

            init(1,1,n);

            StringBuilder result = new StringBuilder();

            for(int query=1; query<=q; query++){
                tokens = new StringTokenizer(buffer.readLine());

                int num1 = Integer.valueOf(tokens.nextToken());
                int num2 = Integer.valueOf(tokens.nextToken());

                int targetIdx = Integer.valueOf(tokens.nextToken());
                int targetValue = Integer.valueOf(tokens.nextToken());
                result.append(getSum(1,1,n,Math.min(num1, num2), Math.max(num1, num2))).append("\n");

                update(1,1,n, targetIdx, targetValue);




            }

            System.out.println(result);

       }


}