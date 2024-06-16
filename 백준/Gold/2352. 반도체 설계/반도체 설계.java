import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    public static void main(String[] args)throws IOException{
        int n = Integer.valueOf(buffer.readLine());
        int[] endPorts = Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int[] lis = new int[n];
        int lengthOfLis = 0;


        for(int endPort: endPorts){

            int idx = Arrays.binarySearch(lis, 0, lengthOfLis, endPort);



            if(idx<0){
                idx*=(-1);
                idx--;
            }
            lis[idx] = endPort;
            if(idx==lengthOfLis){
                lengthOfLis++;
            }

        }

        System.out.println(lengthOfLis);



    }




}
