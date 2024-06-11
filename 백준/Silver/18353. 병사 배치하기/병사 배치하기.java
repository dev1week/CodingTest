
import javax.swing.text.Position;
import java.io.*;
import java.util.*;
import java.util.stream.IntStream;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    public static void main(String[] args)throws IOException {
        int n = Integer.valueOf(buffer.readLine());

        Long[] people = Arrays
                .stream(buffer.readLine().split("\\s+"))
                .map(Long::valueOf)
                .toArray(Long[]::new);


        Long[] lds = new Long[n+1];

        int length = 0;

        for(Long person : people){

            int idx = Arrays.binarySearch(lds, 0, length, person,Collections.reverseOrder());

            if(idx<0){
                idx*=(-1);
                idx--;
            }

            lds[idx] = person;

            if(idx==length){
                length++;
            }

        }

        System.out.println(n-length);



    }
}
