import java.io.*;
import java.util.*;

public class Main {
        static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer tokens;

        static int n; //10^5 정수의 갯수

        static int m; //10^5 질의의 갯수

        static int[] numbers;
        static int[] maxTree;
        static int[] minTree;

        static void init(int current, int left, int right){
                if(left==right){
                        maxTree[current] = numbers[left];
                        minTree[current] = numbers[left];
                        return;
                }

                int mid = (left+right)/2;

                init(current*2, left, mid);
                init(current*2+1, mid+1, right);

                maxTree[current] = Math.max(maxTree[current*2], maxTree[current*2+1]);
                minTree[current] = Math.min(minTree[current*2], minTree[current*2+1]);

        }

        public static void main(String[] args)throws IOException{
                tokens = new StringTokenizer(buffer.readLine());

                n = Integer.valueOf(tokens.nextToken());
                m = Integer.valueOf(tokens.nextToken());

                numbers = new int[n+1];
                maxTree = new int[n*4];
                minTree = new int[n*4];

                StringBuilder result = new StringBuilder();
                for(int idx=1; idx<=n; idx++){
                        numbers[idx] = Integer.valueOf(buffer.readLine());
                }

                init(1,1,n);
                for(int query=1; query<=m;query++){
                        tokens = new StringTokenizer(buffer.readLine());
                        int queryLeft = Integer.valueOf(tokens.nextToken());
                        int queryRight = Integer.valueOf(tokens.nextToken());

                        result.append(getMin(1,1,n, queryLeft, queryRight)).append(" ").append(getMax(1,1,n, queryLeft, queryRight)).append("\n");
                }

                System.out.println(result);

        }
        private static int getMin(int current, int left, int right, int queryLeft, int queryRight){
                if(queryLeft>right||queryRight<left){
                        return Integer.MAX_VALUE;
                }

                if(queryLeft<=left&&queryRight>=right){
                        return minTree[current];
                }

                int mid = (left+right)/2;

                int leftMin =  getMin(current*2, left, mid, queryLeft, queryRight);
                int rightMin = getMin(current*2+1, mid+1, right, queryLeft, queryRight);

                return Math.min(leftMin, rightMin);
        }

        private static int getMax(int current, int left, int right, int queryLeft,int queryRight){
                if(queryLeft>right||queryRight<left){
                        return 0;
                }

                if(queryLeft<=left&&queryRight>=right){
                        return maxTree[current];
                }

                int mid = (left+right)/2;

                int leftMax = getMax(current*2, left,mid, queryLeft, queryRight);
                int rightMax = getMax(current*2+1, mid+1, right, queryLeft, queryRight);

                return Math.max(leftMax, rightMax);


        }

}