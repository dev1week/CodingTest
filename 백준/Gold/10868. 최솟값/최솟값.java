import java.io.*;
import java.util.*;

public class Main {
        static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer tokens;

        static int n; //10^5 정수의 갯수

        static int m; //10^5 질의의 갯수

        static int[] numbers;
        static int[] minTree;


        public static void main(String[] args)throws IOException{
                tokens = new StringTokenizer(buffer.readLine());
                n = Integer.valueOf(tokens.nextToken());
                m =  Integer.valueOf(tokens.nextToken());

                numbers = new int[n+1];
                minTree = new int[4*n];

                for(int idx=1; idx<=n; idx++){
                        numbers[idx] = Integer.valueOf(buffer.readLine());
                }

                init(1,1,n);

                StringBuilder result = new StringBuilder();
                for(int query=0; query<m; query++){
                        tokens = new StringTokenizer(buffer.readLine());
                        int queryLeft = Integer.valueOf(tokens.nextToken());
                        int queryRight = Integer.valueOf(tokens.nextToken());
                        result.append(getMin(1,1,n,queryLeft, queryRight)).append("\n");
                }

                System.out.println(result);

        }

        private static void init(int current,int left, int right){

                if(left==right){
                        minTree[current] = numbers[left];
                        return;
                }

                int mid = (left+right)/2;

                init(current*2, left, mid);
                init(current*2+1, mid+1, right);

                minTree[current] = Math.min(minTree[current*2], minTree[current*2+1]);
        }

        private static int getMin(int current, int left, int right, int queryLeft, int queryRight){
                if(left>queryRight||right<queryLeft){
                        return Integer.MAX_VALUE;
                }

                if(left>=queryLeft&&right<=queryRight){
                        return minTree[current];
                }

                int mid = (left+right)/2;

                int leftMin = getMin(current*2, left, mid, queryLeft, queryRight);
                int rightMin = getMin(current*2+1, mid+1, right, queryLeft, queryRight);


                return Math.min(leftMin, rightMin);

        }


}