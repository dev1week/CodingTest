import java.awt.*;
import java.math.BigInteger;
import java.util.*;
import java.io.*;
import java.util.List;

public class Main {

    private static final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static final int MAX_DIST = Integer.MAX_VALUE/2;
    private static final  BigInteger TWO = new BigInteger("2");
    private static final  BigInteger ONE = new BigInteger("1");


    public static void main(String[] args)throws IOException {

        int t= Integer.parseInt(buffer.readLine());
        StringBuilder result = new StringBuilder();


        for(int test=1;test<=t; test++){
            int n = Integer.parseInt(buffer.readLine());

            Program[] programs = new Program[n];
            for(int i=0;i<n; i++){
                tokens = new StringTokenizer(buffer.readLine());
                int start = Integer.parseInt(tokens.nextToken());
                int range = Integer.parseInt(tokens.nextToken());
                int score = Integer.parseInt(tokens.nextToken());

                programs[i] = new Program(start, start+range, score);
            }

            Arrays.sort(programs);


            //모든 프로그램 순회
            //해당 프로그램에서 가장 직전에 끝나는 프로그램을 이분탐색으로 구한다.
            //nonIntervalIdx 배열을 완성한다.
            int[] nonIntervalIdx = new int[n];
            long[] dp = new long[n];
            //dp[i] = i번째 프로그램까지 고려했을 경우 가족들의 선호도
            dp[0] = programs[0].score;
            for(int i=0; i<n; i++){

                int l = 0;
                int r = i-1;
                int idx = -1;
                while(l<=r){
                    int mid = (l+r)/2;
                    if(programs[mid].end<=programs[i].start){
                        idx = mid;
                        l = mid+1;
                    }else{
                        r=mid-1;
                    }
                }
                nonIntervalIdx[i] = idx;

                long tmpScoreTot = programs[i].score;


                if(nonIntervalIdx[i]!=-1){
                    tmpScoreTot += dp[nonIntervalIdx[i]];
                }
                if(i==0){
                    dp[i] = programs[i].score;
                }else{
                    dp[i] = Math.max(dp[i-1], tmpScoreTot);
                }

            }



            result.append(dp[n-1]).append("\n");

            //n 순회
                //dp[i] = Math.max(dp[i-1], nonIntervalIdx + programs[i].score);
        }

        System.out.println(result);

    }
}


class Program implements Comparable<Program>{

    int start, end, score;

    public Program(int start, int end, int score){
        this.start = start;
        this.end = end;
        this.score = score;
    }


    @Override
    public int compareTo(Program o) {
        return this.end-o.end;
    }
}
