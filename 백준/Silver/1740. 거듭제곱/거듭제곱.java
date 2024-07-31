import java.io.*;
import java.util.*;



public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    private static long power(long base, long exponent) {

        long result = 1;

        for(long i=0; i<exponent; i++) {
            result *= base;
        }

        return result;

    }


    public static void main(String[] args) throws IOException {
        long n = Long.parseLong(buffer.readLine());

        long exponent = 0;
        long result = 0;

        while(n>0){
            if((n &1)==1){
                result += power(3,exponent);
            }
            exponent++;
            n = n>>1;
        }
        System.out.println(result);
    }

}





