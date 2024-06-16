import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    public static void main(String[] args)throws IOException{

        int n= Integer.valueOf(buffer.readLine()); //전봇대의 갯수 10^5


        int[] datas = Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();


        int[] lis = new int[n];
        int length = 0;


        for(int data: datas){

            int idx = Arrays.binarySearch(lis, 0,length, data);

            if(idx<0){
                idx*=(-1);
                idx--;
            }

            lis[idx] = data;

            if(length==idx){
                length++;
            }

        }

        System.out.println(n-length);
    }




}
