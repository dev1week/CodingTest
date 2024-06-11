
import javax.swing.text.Position;
import java.io.*;
import java.util.*;
import java.util.stream.IntStream;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    public static void main(String[] args)throws IOException {
        int n = Integer.valueOf(buffer.readLine());


        int[] numbers= Arrays
                        .stream(buffer.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();


        int length = 0;
        int[] lis = new int[n];
        for(int number: numbers){

            int idx = Arrays.binarySearch(lis, 0, length, number);

            if(idx<0){
                idx*=(-1);
                idx--;
            }

            lis[idx] = number;

            if(idx==length){
                length++;
            }
        }
        System.out.println(length);



    }
}
