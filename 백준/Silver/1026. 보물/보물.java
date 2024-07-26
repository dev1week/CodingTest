import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    //a의 순열을 구한다.
    static int[] a;
    static int[] b;
    static int result = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(buffer.readLine());
        a = Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        b = Arrays.stream(buffer.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(a);
        Arrays.sort(b);

        int result = 0;
        for(int i=0; i<n; i++){
            result += (a[i]*b[n-i-1]);
        }
        System.out.println(result);




    }





}