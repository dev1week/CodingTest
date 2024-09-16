import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;





    public static void main(String[] args)throws IOException{
        int n = Integer.parseInt(buffer.readLine());
        Map<String, Integer> cards = new HashMap<>();
        for(int i=0; i<n; i++){
            tokens = new StringTokenizer(buffer.readLine());

            String key = tokens.nextToken();
            int data = Integer.parseInt(tokens.nextToken());


            int newData = cards.getOrDefault(key, 0)+data;


            cards.put(key, newData);
        }


        boolean result = false;
        for(String key : cards.keySet()){
            if(cards.get(key)==5) {
                result = true;
            }
        }

        if(result){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
        
    }
}
