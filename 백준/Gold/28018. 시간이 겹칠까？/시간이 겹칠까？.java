import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(buffer.readLine());

        int[] diff = new int[2000001];
        int[] occupiedSeat = new int[2000001];
        for(int student=0; student<n; student++){
            tokens = new StringTokenizer(buffer.readLine());

            int start = Integer.parseInt(tokens.nextToken());
            int end = Integer.parseInt(tokens.nextToken());

            diff[start] +=1;
            diff[end+1]-=1;
        }

        for(int time=1; time<=1000000; time++){
            occupiedSeat[time] = occupiedSeat[time-1]+diff[time];
        }

        int q = Integer.parseInt(buffer.readLine());

        StringBuilder result = new StringBuilder();
        tokens = new StringTokenizer(buffer.readLine());
        for(int query=0; query<q; query++){
            int time = Integer.parseInt(tokens.nextToken());
            result.append(occupiedSeat[time]).append("\n");
        }

        System.out.println(result);



    }

}