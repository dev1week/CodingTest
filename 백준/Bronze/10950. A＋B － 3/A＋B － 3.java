import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;




    public static void main(String[] args) throws IOException{
        int t = Integer.parseInt(buffer.readLine());
        
        StringBuilder result = new StringBuilder();
        for(int test=0; test<t; test++){
            tokens = new StringTokenizer(buffer.readLine());
            
            
            int num1 = Integer.parseInt(tokens.nextToken());
            int num2 = Integer.parseInt(tokens.nextToken());
            
            result.append(num1+num2).append("\n");
        }
        
        System.out.println(result); 
        

    }







}