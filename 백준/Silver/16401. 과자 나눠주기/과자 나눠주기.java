
import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;



    public static void main(String[] args)throws IOException{
        tokens = new StringTokenizer(buffer.readLine());


        int m = Integer.valueOf(tokens.nextToken()); // 조카의 수 ~10^6
        int n = Integer.valueOf(tokens.nextToken()); //과자의 수  ~10^6


        //각 과자의 길이 10^9
        long[] snacks = Arrays.stream(buffer.readLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();

        //조카 1명에게 줄 수 있는 막대 과자의 최대 길이
        System.out.println(parametricSearch(snacks, m));


    }

    private static boolean isValid(long test, long[] snacks, int personNum){
        int numOfSnack = 0;

        for(long snack: snacks){
            numOfSnack+=snack/test;
        }

        return numOfSnack>=personNum;

    }

    private static long parametricSearch(long[] snacks, int personNum){
        long l =0;
        long h = 1000000000;

        while(h>l){
            long mid = (l+h+1)/2;

            if(isValid(mid, snacks, personNum)){
                l=mid;
            }else{
                h=mid-1;
            }

        }
        return l;

    }






}
