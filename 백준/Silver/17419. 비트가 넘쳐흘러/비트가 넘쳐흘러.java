import java.io.*;
import java.util.*;



public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(buffer.readLine());

        String binary = buffer.readLine();
        long count = 0;
        for(int i=0; i<n; i++){
            if(binary.charAt(i)=='1'){
                count++;
            }
        }
        System.out.println(count);
    }

}





