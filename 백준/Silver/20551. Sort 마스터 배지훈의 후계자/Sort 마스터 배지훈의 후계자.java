//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.*;
import java.util.*;



class Main{
    static BufferedReader buffer = new BufferedReader((new InputStreamReader(System.in)));
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException{
        tokens = new StringTokenizer(buffer.readLine());

        int n = Integer.parseInt(tokens.nextToken());
        int m = Integer.parseInt(tokens.nextToken());

        List<Integer> numbers = new ArrayList<>();
        for(int i=0; i<n; i++){
            numbers.add(Integer.parseInt(buffer.readLine()));
        }
        Collections.sort(numbers);

        Map<Integer, Integer> numberToIdx = new HashMap<>();
        for(int i=0; i<n; i++){
            if(numberToIdx.containsKey(numbers.get(i)))continue;
            numberToIdx.put(numbers.get(i), i);
        }


        StringBuilder result = new StringBuilder();
        for(int query=0; query<m; query++){
            int q = Integer.parseInt(buffer.readLine());
            result.append(numberToIdx.getOrDefault(q, -1)).append("\n");
        }

        System.out.println(result);
    }
}