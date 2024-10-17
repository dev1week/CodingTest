import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(buffer.readLine());

        int target = Integer.parseInt(buffer.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0; i<n-1; i++){
            pq.add(Integer.parseInt(buffer.readLine()));
        }

        int cnt = 0;

        while(!pq.isEmpty() &&pq.peek()>=target){
            pq.add(pq.poll()-1);
            target++;
            cnt++;
        }

        System.out.println(cnt);

    }
}
