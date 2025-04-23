import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;


    public static void main(String[] args) throws IOException{
        tokens = new StringTokenizer(buffer.readLine());

        int a = Integer.parseInt(tokens.nextToken());
        int b = Integer.parseInt(tokens.nextToken());

        if(a>b){
            System.out.println(">");
        }else if(a<b){
            System.out.println("<");
        }else{
            System.out.println("==");
        }

    }
}
