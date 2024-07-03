import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static final int CHANGE = 1;
    static final int EVEN = 2;
    static final int ODD = 3;


    static int n; // 수열의 크기 ~10^5
    static int[] numbers;
    static int m; // 쿼리의 개수 10^5


    static int[] evenTree;
    static int[] oddTree;


    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(buffer.readLine());

        tokens = new StringTokenizer(buffer.readLine());

        numbers = new int[n+1];
        evenTree = new int[4*n];
        oddTree = new int[4*n];


        for(int idx=1; idx<=n; idx++){
            numbers[idx] = Integer.parseInt(tokens.nextToken());
        }

        m = Integer.parseInt(buffer.readLine());

        StringBuilder result = new StringBuilder();

        init(1,1,n);

        for(int query=0; query<m; query++){
            tokens = new StringTokenizer(buffer.readLine());

            int command = Integer.parseInt(tokens.nextToken());

            if(command == CHANGE){
                int targetIdx = Integer.parseInt(tokens.nextToken());
                int targetValue = Integer.parseInt(tokens.nextToken());


                update(1,1,n,targetIdx, targetValue);
            }else if(command == EVEN){
                int targetIdx = Integer.parseInt(tokens.nextToken());
                int targetValue = Integer.parseInt(tokens.nextToken());

                result.append(getEven(1,1,n,targetIdx, targetValue)).append("\n");;
            }else if(command == ODD){
                int queryLeft = Integer.parseInt(tokens.nextToken());
                int queryRight = Integer.parseInt(tokens.nextToken());
                result.append(getOdd(1,1,n,queryLeft, queryRight)).append("\n");
            }
        }
        System.out.println(result);



    }

    private static int getEven(int current, int left, int right, int queryLeft, int queryRight){

        if(queryLeft>right||queryRight<left){
            return 0;
        }

        if(queryLeft<=left&&queryRight>=right){
            return evenTree[current];
        }

        int mid = (left+right)/2;
        int leftNode = current*2;
        int rightNode = current*2+1;

        int leftCount = getEven(leftNode,left,mid,queryLeft,queryRight);
        int rightCount = getEven(rightNode,mid+1, right,queryLeft,queryRight);


        return leftCount + rightCount;

    }

    private static int getOdd(int current, int left, int right, int queryLeft, int queryRight){

        if(queryLeft>right||queryRight<left){
            return 0;
        }

        if(queryLeft<=left&&queryRight>=right){
            return oddTree[current];
        }

        int mid = (left+right)/2;
        int leftNode = current*2;
        int rightNode = current*2+1;

        int leftCount = getOdd(leftNode,left,mid,queryLeft,queryRight);
        int rightCount = getOdd(rightNode,mid+1, right,queryLeft,queryRight);


        return leftCount + rightCount;

    }

    private static void update(int current, int left, int right, int targetIdx, int targetValue) {

        if(targetIdx<left||right<targetIdx){
            return;
        }

        if(left==right){
            //바꾸려는 값이 홀수, 원래 값이 홀수
            if(isOdd(numbers[targetIdx])&&isOdd(targetValue)){

            }
            //바꾸려는 값이 짝수, 원래 값이 짝수
            else if(!isOdd(numbers[targetIdx])&&!isOdd(targetValue)){

            }
            //바꾸려는 값이 홀수, 원래 값이 짝수
            else if(!isOdd(numbers[targetIdx])&&isOdd(targetValue)){
                if(evenTree[current]<=1){
                    evenTree[current] =0;
                }else{
                    evenTree[current]--;
                }

                oddTree[current]++;
            }
            //바꾸려는 값이 짝수, 원래 값이 홀수
            else if(isOdd(numbers[targetIdx])&&!isOdd(targetValue)){
                if(oddTree[current]<=1){
                    oddTree[current] =0;
                }else{
                    oddTree[current]--;
                }

                evenTree[current]++;
            }

            numbers[targetIdx] = targetValue;
            return;
        }

        int mid = left + (right - left)/2;
        int leftNode = current*2;
        int rightNode = current*2+1;

        update(leftNode, left, mid, targetIdx, targetValue);
        update(rightNode, mid+1, right, targetIdx, targetValue);

        oddTree[current] = oddTree[leftNode] + oddTree[rightNode];
        evenTree[current] = evenTree[leftNode] + evenTree[rightNode];
    }

    private static boolean isOdd(long num){
        return num%2==1;
    }

    private static void init(int current,int left, int right){
        if(left==right){
            if(isOdd(numbers[left])){
                oddTree[current] = 1;
            }else{
                evenTree[current] = 1;
            }

            return;
        }

        int mid = (left + right)/2;
        int leftNode = current*2;
        int rightNode = current*2+1;

        init(leftNode, left, mid);
        init(rightNode, mid+1, right);

        evenTree[current] = evenTree[leftNode] + evenTree[rightNode];
        oddTree[current] = oddTree[leftNode] + oddTree[rightNode];

    }

}
