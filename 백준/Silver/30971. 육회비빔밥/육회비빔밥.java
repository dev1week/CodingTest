import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int[] sweets;
    static int[] salts;
    static int[] stress;

    static boolean[] isUsed;

    static int result;

    public static void main(String[] args) throws IOException{

        tokens = new StringTokenizer(buffer.readLine());

        int n = Integer.parseInt(tokens.nextToken());
        int k = Integer.parseInt(tokens.nextToken());

        isUsed = new boolean[n];

        sweets = Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        salts = Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        stress = Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        result = -1;

        getPermutation(0, k, n, new int[n]);


        System.out.println(result);

    }
    static int getScore(int[] selecteds, int tot, int limit){
        int score = 0;
        int count = 1;
        for(int idx=1; idx<tot; idx++){

            int currentStress = stress[selecteds[idx-1]]*stress[selecteds[idx]];

            if(currentStress<=limit){
                score += (sweets[selecteds[idx-1]]*salts[selecteds[idx]]);
                count++;
            }

        }

        if(count != tot){
            return -1;
        }else{
            return score;
        }

    }

    static void getPermutation(int current, int limit, int tot, int[] selecteds){

        if(current==tot){
            result = Math.max(result, getScore(selecteds, tot, limit));
            return;
        }

        for(int next=0; next<tot; next++){
            if(isUsed[next])continue;
            selecteds[current] = next;
            isUsed[next] = true;
            getPermutation(current+1, limit, tot, selecteds);
            isUsed[next] = false;

        }


    }






}