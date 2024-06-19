import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(buffer.readLine());
        int n= Integer.valueOf(tokens.nextToken()); //학생의 수 10^6
        int target = Integer.valueOf(tokens.nextToken()); // 가능한 지친 사람의 수
        int[] heights= Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int max = Arrays.stream(heights).max().getAsInt();


        if(heights.length==1){
            System.out.println(0);
        }else{
            System.out.println(getMin(heights, max, target));
        }
        
        //점수차 최소값 찾기
    }

    static long getMin(int[] heights, int max, int target){
        long l = 0;
        long h = max;

        while(h>l){
            long mid = (h+l)/2;

            if(isValid(heights, target, mid)){
                h = mid;
            }else{
                l =mid+1;
            }
        }

        return l;
    }

    private static boolean isValid(int[] heights, int target, long mid){
        int count = 0;

        if(Math.abs(heights[0]-heights[1])>mid){
            count++;
        }


        for(int i=1;i<heights.length-1; i++){
            int h1 = heights[i-1];
            int h2 = heights[i];
            int h3 = heights[i+1];

            if(Math.abs(h1-h2)>mid||Math.abs(h2-h3)>mid){
                count++;
            }
        }

        if(Math.abs(heights[heights.length-2]- heights[heights.length-1])>mid){
            count++;
        }

        return count<=target;
    }


    //결정 조건
        //해당 점수차 = 허용가능한 키차이
        //키 차이 이ㅣ하를 가진 사람 섹


}