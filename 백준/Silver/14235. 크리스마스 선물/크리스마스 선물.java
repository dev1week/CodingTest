

import java.io.*;
import java.util.*;


public class Main {


    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static final int NO_VALUE = -1;

    public static void main(String[] args)throws IOException {

        int n = Integer.valueOf(buffer.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->(b-a));

        StringBuilder result = new StringBuilder();

        for(int i=0; i<n; i++){
            StringTokenizer tokens = new StringTokenizer(buffer.readLine());
            int num = Integer.valueOf(tokens.nextToken());

            if(num==0){
                if(pq.isEmpty()){
                    result.append(NO_VALUE).append("\n");
                }else{
                    result.append(pq.poll()).append("\n");
                }
            }else{

                for(int valueIdx = 0; valueIdx<num; valueIdx++){
                    pq.add(Integer.valueOf(tokens.nextToken()));
                }
            }
        }
        System.out.println(result);
    }
}
