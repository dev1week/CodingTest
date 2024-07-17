import java.io.*;
import java.util.*;



public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int result = 0;
    

    private static int getLimit(String number){
        String reverseData = new StringBuilder(number).reverse().toString();
        return Math.min(Integer.parseInt(reverseData) , Integer.parseInt(number) );
    }

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(buffer.readLine());
        for(int test=0; test<t; test++){
            result = 0;
            int n = Integer.parseInt(buffer.readLine());

            String bobData=buffer.readLine();

            int limit = getLimit(bobData);


            int[] aliceNumbers = getAliceNumber(buffer.readLine());
            Arrays.stream(aliceNumbers).sorted();



            permutation(aliceNumbers, 0, 0, new boolean [aliceNumbers.length+1], n, limit);
            sb.append(result).append("\n");
        }
        System.out.println(sb);
        //역순 데이터를 비교하여 한계를 찾는다.

        //한계를 바탕으로 백트래킹을 진행한다.
            //가능한 경우의 수가 나올 때마다 result 최대값을 갱신한다.
    }

    private static int[] getAliceNumber(String number){
        int[] aliceNumbers = new int[number.length()];
        int idx = 0;
        for(char n : number.toCharArray()){
            aliceNumbers[idx++]=(int)n-'0';
        }
        return aliceNumbers;
    }

    private static void permutation(int[] numbers, int tot, int current, boolean[] isUsed, int n, int limit){
        if(tot<limit ) {
            result = Math.max(tot, result);


        }

        if(current==n) return;


        for(int i = 0; i<numbers.length; i++){
            if(isUsed[i])continue;
            isUsed[i] = true;
            permutation(numbers, tot*10+numbers[i], current+1, isUsed, n, limit);
            isUsed[i] = false;
        }


    }

}
