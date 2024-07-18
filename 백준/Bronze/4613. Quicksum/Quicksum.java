import java.io.*;
import java.util.*;



public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    public static void main(String[] args) throws IOException {
        String data = buffer.readLine();
        StringBuilder resultData = new StringBuilder();

        while(!data.equals("#")){
            int result = 0;
            for(int idx=1; idx<=data.length(); idx++){
                if(data.charAt(idx-1)==' '){
                    result += 0;
                }else{
                    result += (idx*(data.charAt(idx-1)-'A'+1));
                }


            }

            resultData.append(result).append("\n");
            data = buffer.readLine();

        }

        System.out.println(resultData.toString());

    }


}
