import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;




    public static void main(String[] args) throws IOException{
        tokens = new StringTokenizer(buffer.readLine());
        int numOfWork = Integer.parseInt(tokens.nextToken());
        int workPerDay = Integer.parseInt(tokens.nextToken());
        int limit = Integer.parseInt(tokens.nextToken());


        PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());

        for(int work=0; work<numOfWork; work++){
            int w = Integer.parseInt(buffer.readLine());
            que.add(w);
        }

        StringBuilder result = new StringBuilder();
        int tot = 0;
        int totDay = 0;
        while(!que.isEmpty()){
            totDay++;
            int todayP = que.poll();

            tot = tot/2 + todayP;

            int remaining = todayP - workPerDay;

            if(remaining>limit){
                que.add(remaining);
            }
            result.append(tot).append("\n");
        }
        System.out.println(totDay);
        System.out.println(result);

    }







}