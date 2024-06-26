import java.io.*;
import java.util.*;

public class Main {
        static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer tokens;
        static int num;//문제의 갯수
        static int sumMin; //가능한 총합의 최저
        static int sumMax; //가능한 총합의 최고
        static int diffMin; //가능한 차이의 최저
        static int[] difficulties;
        static int count;

        static boolean isValid(List<Integer> selecteds){
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int sum = 0;
            for(int selected: selecteds){
                max = Math.max(max, selected);
                min = Math.min(min, selected);
                sum += selected;
            }


            return max-min>=diffMin && sum<=sumMax && sum>=sumMin;
        }
        static void dfs(int current, List<Integer> selecteds, int totDifficulty  ){
            if(current==num){

                if(selecteds.size()>=2){
                    if(isValid(selecteds)){
                        count++;
                    }
                }

                return;
            }

            dfs(current+1, selecteds,totDifficulty);
            selecteds.add(difficulties[current]);
            dfs(current+1, selecteds,totDifficulty+difficulties[current]);
            selecteds.remove(selecteds.size()-1);

        }

        public static void main(String[] args)throws IOException{
            tokens = new StringTokenizer(buffer.readLine());
            num = Integer.valueOf(tokens.nextToken());
            sumMin = Integer.valueOf(tokens.nextToken());
            sumMax = Integer.valueOf(tokens.nextToken());
            diffMin= Integer.valueOf(tokens.nextToken());


            difficulties = Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            dfs(0,  new ArrayList<>(),0);

            System.out.println(count);
        }


}