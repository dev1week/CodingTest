import java.io.*;
import java.util.*;

public class Main {
        static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        static StringTokenizer tokens;

        static final int CONST = 1000000007;

        static int n; //수의 개수 10^6

        static int m; //10^4 수의 변경이 일어나는 횟수
        static int k; //10^4 구간의 곱을 구하는 횟수

        static long[] resultTree;
        static long[] numbers;

        static final int CHANGE =1;
        static final int PRINT = 2;

    public static void main(String[] args) throws IOException{
        tokens = new StringTokenizer(buffer.readLine());

        n = Integer.valueOf(tokens.nextToken());
        m = Integer.valueOf(tokens.nextToken());
        k = Integer.valueOf(tokens.nextToken());

        resultTree = new long[4*n];
        numbers = new long[n+1];

        for(int idx=1; idx<=n; idx++){
            numbers[idx] = Long.valueOf(buffer.readLine());
        }

        init(1,1,n);

        StringBuilder result = new StringBuilder();

        for(int query=0; query<m+k; query++){
            tokens = new StringTokenizer(buffer.readLine());

            int command = Integer.valueOf(tokens.nextToken());

            if(command == CHANGE){
                int targetIdx = Integer.valueOf(tokens.nextToken());
                int targetValue = Integer.valueOf(tokens.nextToken());

                change(1,1,n,targetIdx, targetValue);

            }else if(command == PRINT){
                int num1 = Integer.valueOf(tokens.nextToken());
                int num2 = Integer.valueOf(tokens.nextToken());

                result.append(getResult(1,1,n,Math.min(num1, num2), Math.max(num1, num2))).append("\n");
            }
        }

        System.out.println(result);
   }

   private static void init(int current, int left, int right){
        if(left==right){
            resultTree[current] = numbers[left]%CONST;
            return;
        }

        int mid = (left+right)/2;
        int leftNode = current*2;
        int rightNode = current*2+1;

        init(leftNode, left, mid);
        init(rightNode, mid+1, right);

        resultTree[current] = ((resultTree[leftNode]%CONST) * (resultTree[rightNode]%CONST))%CONST;
   }

   private static void change(int current, int left, int right, int targetIdx, int targetValue){
        if(targetIdx<left||targetIdx>right){
            return;
        }

        if(left==right){
            resultTree[current] = targetValue;
            return;
        }

        int mid = (left+right)/2;
        int leftNode = current*2;
        int rightNode = current*2+1;

        change(leftNode, left, mid, targetIdx, targetValue);
        change(rightNode, mid+1, right, targetIdx, targetValue);

       resultTree[current] = ((resultTree[leftNode]) * (resultTree[rightNode]))%CONST;
   }

   private static long getResult(int current, int left, int right, int queryLeft, int queryRight){
        if(queryRight<left||queryLeft>right){
            return 1;
        }

        if(queryRight>=right&&queryLeft<=left){
            return resultTree[current]%CONST;
        }

        int mid = (left+right)/2;
        int leftNode = current*2;
        int rightNode = current*2+1;

        long leftResult = getResult(leftNode, left, mid, queryLeft, queryRight);
        long rightResult = getResult(rightNode, mid+1, right, queryLeft, queryRight);


        return (leftResult*rightResult)%CONST;
   }
}