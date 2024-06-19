import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static boolean flag = false;

    public static void main(String[] args) throws IOException {

        tokens = new StringTokenizer(buffer.readLine());

        int numOfStudent = Integer.valueOf(tokens.nextToken()) ; //학생의 수 ~5*10^5
        long limitOfCandy = Long.valueOf(tokens.nextToken()); //  가능한 사탕 갯수 ~10^12
        long[] candies = Arrays.stream(buffer.readLine().split("\\s+")).mapToLong(Long::parseLong).toArray();
        long max = Arrays.stream(candies).max().getAsLong();

        System.out.println(getMinLimitScore(candies, limitOfCandy, max));

        //최종 점수가 x를 넘어야 그 차이만큼 사탕 개수 지급

        //최대한 낮은 기준 점수를 이진탐색으로 탐색한다.
    }

    private static long getMinLimitScore(long[] candies, long limitOfCandy, long max){
        long l = 0;
        long h = max;

        while(h>l){
            long mid = (l+h)/2;

            if(isValid(candies, limitOfCandy, mid)){
                h = mid;
            }else{
                l = mid+1;
            }

        }

        return l;
    }

    private static boolean isValid(long[] candies, long limitOfCandy, long mid){
        long count = 0;


        for(long candy : candies){
            if(candy>=mid){
                count += (candy-mid);
            }
        }

        return count<=limitOfCandy;



    }





}