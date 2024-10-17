import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    private static final String AAAA = "AAAA";
    private static final String BB = "BB";


    public static void main(String[] args) throws IOException {

        String data = buffer.readLine();

        data = data.replace("XXXX", AAAA);
        data = data.replace("XX", BB);
        

        System.out.println(data.contains("X")?"-1":data);
    }
}
