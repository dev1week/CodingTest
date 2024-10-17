import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {
        String data = buffer.readLine();

        StringTokenizer tokens1 = new StringTokenizer(data, "1");
        StringTokenizer tokens2 = new StringTokenizer(data, "0");

        int cnt = Integer.MAX_VALUE;


        int cntToken = 0;
        while(tokens1.hasMoreTokens()){
            tokens1.nextToken();
            cntToken++;
        }

        cnt = Math.min(cntToken, cnt);

        cntToken = 0;
        while(tokens2.hasMoreTokens()){
            tokens2.nextToken();
            cntToken++;
        }
        cnt = Math.min(cntToken, cnt);


        System.out.println(cnt);

    }
}
