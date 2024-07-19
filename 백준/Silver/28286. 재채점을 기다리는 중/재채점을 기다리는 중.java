import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int[] answer;
    static int result = 0;

    private static int getCount(List<Integer>data){
        int count = 0;
        for(int idx=0; idx<data.size(); idx++){
            if(idx>=answer.length) return count;

            if(data.get(idx)==answer[idx]){
                count++;
            }
        }

        return count;
    }

    static void dfs(int current, List<Integer> data, int numOfOperation, int numOfProblem){
        result  = Math.max(getCount(data), result);
        if(current == numOfOperation ){



            return;
        }

        for(int idx=0; idx<data.size(); idx++){
            data.add(idx, 0);
            dfs(current+1, data, numOfOperation, numOfProblem);
            data.remove(idx);

            int memory = data.get(idx);
            data.remove(idx);
            dfs(current+1, data, numOfOperation, numOfProblem);
            data.add(idx, memory);
        }
    }

    public static void main(String[] args) throws IOException{
        tokens= new StringTokenizer(buffer.readLine());

        int numOfProblem = Integer.valueOf(tokens.nextToken());
        int numOfOperation = Integer.valueOf(tokens.nextToken());


        answer = Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        List<Integer> data = Arrays.stream(buffer.readLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        dfs(0, data, numOfOperation, numOfProblem);
        System.out.println(result);
    }







}