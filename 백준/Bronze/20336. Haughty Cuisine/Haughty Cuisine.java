//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.*;
import java.util.*;
import java.util.stream.*;


class Main{
    private static BufferedReader buffer = new BufferedReader((new InputStreamReader(System.in)));
    private static StringTokenizer tokens;


    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(buffer.readLine());


        List<String> foods = new ArrayList<>();
        for(int i=0; i<n; i++){
            tokens = new StringTokenizer(buffer.readLine());
            int d = Integer.parseInt(tokens.nextToken());

            for(int dish=0; dish<d; dish++){
                String food = tokens.nextToken();
                foods.add(food);
            }
            break;
        }

        StringBuilder result = new StringBuilder();
        result.append(foods.size()).append("\n");
        for(int i=0; i<foods.size(); i++){
            result.append(foods.get(i)).append("\n");
        }

        System.out.println(result);
    }

}
