import java.io.*;
import java.util.*;



public class Main {

    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    public static void main(String[] args) throws IOException{
        int t = Integer.parseInt(buffer.readLine());

        for(int test=0; test<t; test++){
            int n = Integer.parseInt(buffer.readLine());

            Set<Integer> note1 = getNote1(n);

            int m = Integer.parseInt(buffer.readLine());

            System.out.println( getResult(note1, m));
        }


    }

    private static StringBuilder getResult(Set<Integer> note1, int m) throws IOException {
        StringBuilder result = new StringBuilder();
        tokens = new StringTokenizer(buffer.readLine());

        for(int query = 0; query< m; query++){
            int queryNum = Integer.parseInt(tokens.nextToken());
            result.append(isAppeared(note1, queryNum));
            if(query!=m-1){
                result.append("\n");
            }
        }

        return result;
    }

    private static int isAppeared(Set<Integer> note1, int queryNum) {
        return note1.contains(queryNum) ? 1 : 0;
    }

    private static Set<Integer> getNote1(int n) throws IOException {
        Set<Integer> note1 = new HashSet<>();

        tokens = new StringTokenizer(buffer.readLine());
        for(int i = 0; i< n; i++){
            note1.add(Integer.parseInt(tokens.nextToken()));
        }
        return note1;
    }
}