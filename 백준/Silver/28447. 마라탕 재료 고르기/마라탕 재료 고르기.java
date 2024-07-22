import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    static int[][] scores;
    static int n;
    static int k;
    static int result = Integer.MIN_VALUE;

    private static int getTotScore(int[] selecteds){
        int totScore = 0;
        for(int x=0; x<selecteds.length; x++){
            for(int y=x+1; y<selecteds.length; y++){
                totScore += scores[selecteds[x]][selecteds[y]];
            }
        }


        return totScore;
    }

    static void dfs(int current, int[] selecteds, boolean[] isUsed, int last){
        if(current==k){

            //뽑은 조합으로 크기가 2인 모든 세트 생성하기
            result = Math.max(result, getTotScore(selecteds));

            return;
        }

        for(int next=last+1; next<n; next++){
            if(isUsed[next]) continue;

            isUsed[next] = true;
            selecteds[current] = next;
            dfs(current+1, selecteds, isUsed, next);
            isUsed[next] = false;
        }

    }

    public static void main(String[] args) throws IOException{
        tokens = new StringTokenizer(buffer.readLine());
        n = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());

        scores = new int[n][n];

        for(int y=0; y<n; y++){
            tokens = new StringTokenizer(buffer.readLine());
            for(int x=0; x<n; x++){
                scores[y][x] = Integer.parseInt(tokens.nextToken());
            }
        }



        dfs(0,new int[k], new boolean[n], -1);

        System.out.println(result);


    }







}