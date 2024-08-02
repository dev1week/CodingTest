
import java.io.*;
import java.util.*;




public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {
        //시간의 최대값을 구한다.

        tokens = new StringTokenizer(buffer.readLine());


        int numOfTunnel = Integer.parseInt(tokens.nextToken());
        long numOfPeople = Long.parseLong(tokens.nextToken());
        long[] tunnels = new long[numOfTunnel];

        for(int tunnel = 0;tunnel<numOfTunnel; tunnel++){
            tunnels[tunnel] = Long.parseLong(buffer.readLine());
        }

        long l = 1;
        long h = (long) 1e18;
        long answer = Long.MAX_VALUE;

        while(h>l){

            long mid = (h+l)/2;
            

            if(isValid(mid, tunnels, numOfPeople)){
                h = mid;
                answer = Math.min(answer, h);
            }else{
                l = mid+1;
            }



        }
        answer = Math.min(answer, h);
        System.out.println(answer);



    }

    private static boolean isValid(long mid, long[] tunnels, long numOfPeople){
        long possibleTotal = 0;

        for(long tunnel:tunnels){
            if(possibleTotal>=numOfPeople)return true;
            possibleTotal += (mid/tunnel);
        }

        return possibleTotal>=numOfPeople;

    }







}