import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static final long MAX = 1000000001;

    public static void main(String[] args) throws IOException{
        tokens = new StringTokenizer(buffer.readLine());
        long tot = Integer.parseInt(tokens.nextToken());
        long winCount = Integer.parseInt(tokens.nextToken());
        long rate = winCount*100/tot;


        long l  = 0;
        long h = MAX;

        while(h>l){
            long mid = (h+l)/2;

            if(isValid(mid, tot, winCount, rate)){
                h = mid;
            }else{
                l = mid+1;
            }
        }
        if(l == MAX){
            System.out.println(-1); 
        }else{
            System.out.println(l);
        }



    }


    static boolean isValid(long target, long tot, long winCount, long rate){

        long winRate = (winCount+target)*100/(tot+target);


        return winRate!=rate;
    }






}