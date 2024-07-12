import java.io.*;
import java.util.*;



public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static final int[] numbers = {0,1,2};
    static boolean[] isUsed;

    static int result = 0;
    static int n;

    private static boolean isValid(int num){
        return num%3 == 0;
    }

    private static void dfs(int current, int currentNumber){
        if(current==n-1){
//            System.out.println(currentNumber);

            if(isValid(currentNumber)){

                result++;
            }


            return;
        }

        for(int number: numbers){
//            if(isUsed[number])continue;
            isUsed[number] = true;
            dfs(current+1, currentNumber*10+number);
            isUsed[number] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        n = Integer.valueOf(buffer.readLine());

        isUsed = new boolean[4];

        for(int i=1; i<numbers.length; i++){
            isUsed[numbers[i]] = true;
            dfs(0, numbers[i]);
            isUsed[numbers[i]] = false;
        }

        System.out.println(result);





    }



}
