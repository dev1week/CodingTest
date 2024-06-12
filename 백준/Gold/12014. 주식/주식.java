
import javax.swing.text.Position;
import java.io.*;
import java.util.*;
import java.util.stream.IntStream;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

    static StringTokenizer tokens;

    public static void main(String[] args)throws IOException {

        int t = Integer.valueOf(buffer.readLine());

        StringBuilder result = new StringBuilder();
        for(int test=1; test<=t; test++){
            result.append("Case #").append(test).append("\n");
            tokens = new StringTokenizer(buffer.readLine());

            int n = Integer.valueOf(tokens.nextToken());
            int k = Integer.valueOf(tokens.nextToken());


            int[] numbers = Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();


            int[] lis = new int[n];
            int length = 0;
            for(int number: numbers){


                int idx = Arrays.binarySearch(lis, 0,length, number);


                if(idx<0){
                    idx*=(-1);
                    idx--;
                }


                lis[idx] = number;

                if(idx==length){
                    length++;
                }

            }


            if(length>=k){
                result.append(1).append("\n");
            }else{
                result.append(0).append("\n");
            }


        }

        System.out.println(result);



    }
}
