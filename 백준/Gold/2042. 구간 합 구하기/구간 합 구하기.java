import java.io.*;
import java.util.*;

public class Main {
        static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer tokens;

        static int n; //수의 갯수

        static int m; //수의 변경이 일어나는 횟수
        static int k; //구간의 합을 구하는 횟수

        static long[] numbers;
        static long[] sumTree;

        static final int CHANGE = 1;
        static final int GET_PART_SUM = 2;


       public static void main(String[] args) throws IOException{

           tokens = new StringTokenizer(buffer.readLine());


           n = Integer.valueOf(tokens.nextToken());
           m = Integer.valueOf(tokens.nextToken());
           k = Integer.valueOf(tokens.nextToken());


           numbers = new long[n+1];
           sumTree = new long[4*n];
           for(int idx=1; idx<=n; idx++){
                numbers[idx] = Long.valueOf(buffer.readLine());
           }



            StringBuilder result = new StringBuilder();

           init(1,1,n);

           for(int query=0; query<k+m; query++) {
               tokens = new StringTokenizer(buffer.readLine());

               int command = Integer.valueOf(tokens.nextToken());

               if (command == CHANGE) {
                   int targetIdx = Integer.valueOf(tokens.nextToken());
                   long targetValue = Long.valueOf(tokens.nextToken());
                   update(1, 1, n, targetIdx, targetValue);
               } else if (command == GET_PART_SUM) {
                   int queryIdxLeft = Integer.valueOf(tokens.nextToken());
                   int queryIdxRight = Integer.valueOf(tokens.nextToken());
                   result.append(getPartSum(1, 1, n, queryIdxLeft, queryIdxRight)).append("\n");

               }


           }
           System.out.println(result);
       }

       private static void init(int current, int left, int right) {
           if (left == right) {
               sumTree[current] = numbers[left];
               return;
           }


           int mid = (left + right) / 2;
           int leftNode = current * 2;
           int rightNode = current * 2 + 1;

           init(leftNode, left, mid);
           init(rightNode, mid + 1, right);


           sumTree[current] = sumTree[leftNode] + sumTree[rightNode];
       }

        private static void update(int current, int left, int right, int targetIdx, long targetValue){
           if(targetIdx<left||targetIdx>right){
               return;
           }

           if(left==right){
                sumTree[current] = targetValue;
                return;
            }

           int mid = (left+right)/2;
           int leftNode = current*2;
           int rightNode = current*2+1;



           update(leftNode, left, mid, targetIdx, targetValue);
           update(rightNode, mid+1, right, targetIdx, targetValue);


           sumTree[current] = sumTree[leftNode]+sumTree[rightNode];
        }


        private static long getPartSum(int current, int left, int right, int queryLeft, int queryRight){
           //어느 범위에도 속하지 않을 경우
            if(left>queryRight||right<queryLeft){
                return 0;
            }

            //해당 파트를 써먹을 수 있을 경우
            if(left>=queryLeft&&right<=queryRight){
                return sumTree[current];
            }


            //그외 파트를 찾아서 계속 탐색해야하는 경우
           int mid = (left+right)/2;
            int leftNode = current*2;
            int rightNode = current*2+1;


            long leftSum = getPartSum(leftNode, left, mid, queryLeft, queryRight);
            long rightSum = getPartSum(rightNode, mid+1, right, queryLeft, queryRight);

            return leftSum + rightSum;
        }



}