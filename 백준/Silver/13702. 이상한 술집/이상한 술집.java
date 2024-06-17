import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    public static void main(String[] args)throws IOException{
        tokens = new StringTokenizer(buffer.readLine());
        int n=Integer.valueOf(tokens.nextToken()); // 주문한 막걸리 주전자 갯수 ~10^5
        int numOfFriends=Integer.valueOf(tokens.nextToken()); // 친구들의 수 ~10^6
        int[] kettles = new int[n];
        int max = 0;
        for(int kettle=0; kettle<n; kettle++){
            kettles[kettle] = Integer.valueOf(buffer.readLine());
            max = Math.max(kettles[kettle], max);
        }

        System.out.println(getAnswer(kettles, numOfFriends, max));

        //주전자에 막걸리가 남으면 그냥 버린다.

        //한명에게 주는 막걸리 용량을 이진탐색으로 찾는다.

    }

    private static long getAnswer(int[] kettles, int numOfFriends, int max){
        long l= 0;
        long h = max;

        while(h>l){
            long mid = (l+h+1)/2;

            if(isValid(mid, kettles, numOfFriends)){
                l =mid;
            }else{
                h = mid-1;
            }

        }

        return l;
    }

    private static boolean isValid(long mid, int[] kettles, int numOfFriends){
        int count = 0;

        for(int kettle : kettles){
            count += (kettle/mid);
        }

        return count>=numOfFriends;
    }

    //결정 조건
        //int tot = 0;
        //전체 주전자 순회
            //tot += (주전자에 담긴 양//한명에게 주는 막걸리 용량)
        //return tot>=k;













}
