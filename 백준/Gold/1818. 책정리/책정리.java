
import javax.swing.text.Position;
import java.io.*;
import java.util.*;
import java.util.stream.IntStream;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    public static void main(String[] args)throws IOException {
        int n = Integer.valueOf(buffer.readLine());

        int[] books = Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();


        int[] lis = new int[n];
        int length = 0;

        //이분탐색으로 lis의 길이를 구한다.
        for(int book : books){
            int idx = Arrays.binarySearch(lis, 0, length, book);

            if(idx<0){
                idx*=(-1);
                idx--;
            }

            lis[idx] = book;

            if(idx==length){
                length++;
            }
        }
        System.out.println(n-length);

        //전체 길이 - lis의 길이를 출력한다.



    }
}
