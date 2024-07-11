import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    //전구 상태가 바뀌는 횟수가 최소

    static int result = Integer.MAX_VALUE;
    static int totCount = 0;

    private static void dfs(int current, String[] selecteds, int count, int lastPart, int n, String[] parts, boolean[] isUsed){
        if(count>result){
            //return;
        }

        if(current==n){
            result = Math.min(result, count);
            totCount++;
            return;
        }

        for(int next=0; next<n; next++){
            if(isUsed[next]) continue;

            isUsed[next] = true;

            selecteds[current] = parts[next];


            int currentPartLastNum = parts[next].charAt(parts[next].length()-1)-'0';
            int currentPartFirstNum = parts[next].charAt(0)-'0';

            if(lastPart!=-1&&lastPart!=currentPartFirstNum){
                dfs(current+1, selecteds, count+1, currentPartLastNum , n, parts, isUsed);
            }else{
                dfs(current+1, selecteds, count,currentPartLastNum, n, parts, isUsed);
            }



            isUsed[next] = false;
        }
    }
    public static void main(String[] args )throws IOException{
        int n = Integer.valueOf(buffer.readLine());

        String[] parts = new String[n];

        for(int i=0; i<n; i++){
            parts[i] = buffer.readLine();
        }

        int staticCount = 0;
        for(String part: parts){
            for(int i=0; i<part.length()-1; i++){
                if(part.charAt(i)!=part.charAt(i+1)){
                    staticCount++;
                }
            }
        }
        
        //10!
        //순열 뽑기
        dfs(0, new String[n], staticCount, -1, n, parts, new boolean[n]);

        System.out.println(result);
//        System.out.println(totCount);




    }





}