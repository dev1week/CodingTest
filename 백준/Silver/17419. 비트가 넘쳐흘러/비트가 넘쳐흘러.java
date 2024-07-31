import java.io.*;
import java.util.*;



public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    private static int operate(int data){
        return data-(data&((~data)+1));
    }


    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(buffer.readLine());

        String binary = buffer.readLine();

        int data = Integer.parseInt(binary,2);

        int count  = 0;
        while(data>0){
            data = operate(data);
            count ++;
        }

        System.out.println(count);



    }

}





