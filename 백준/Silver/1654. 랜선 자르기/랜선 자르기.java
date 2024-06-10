
import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;



    public static void main(String[] args)throws IOException{
        tokens = new StringTokenizer(buffer.readLine());


        int k = Integer.valueOf(tokens.nextToken()); // 필요한 랜선의 갯수 10^4
        int n = Integer.valueOf(tokens.nextToken()); //이미 가지고 있는 랜선의 갯수 10^6

        int[] lenOfLANs = new int[k];

        int max = 0;
        for(int i=0; i<k; i++){
            lenOfLANs[i] = Integer.valueOf(buffer.readLine());
            max = Integer.max(max, lenOfLANs[i]);
        }


        System.out.println(parametricSearch(max, lenOfLANs, n));


    }

    private static long parametricSearch(int max, int[] arr, int n){
        //길이를 매개변수화
        long l = 1;
        long h = Integer.MAX_VALUE;

        while(h>l){
            long mid = (l+h+1)/2;

            if(isValid(mid, arr, n)){
                l=mid;
            }else{
                h=mid-1;
            }
        }
        return l;
    }

    private static boolean isValid(long test, int[] arr, int limit){
        //test 길이로 토막냈을 때 전체 막대기 수
        long totNum = 0;
        //토막내서 만들 수 있는 막대기 수를 구한다.
        for(int length: arr){
            totNum += length/test;
        }

        //n보다 크거나 같은지 비교한다.
        return  totNum>=limit;
    }





}
