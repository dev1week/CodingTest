import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    //정렬한다.

    //n개를 뽑는다.

    //중복 조합 이다.

    //해당 숫자가 쓰였는지 판단한다.

    // 1 7 9 9


    static StringBuilder resultNumbers;

    static Set<String> set;
    static int[] numbers;

    static int n;
    static int m;


    private static String convert(List<Integer>selecteds){

        StringBuilder part = new StringBuilder();

        part.append(selecteds.get(0));

        for(int idx=1; idx<selecteds.size(); idx++){
            part.append(" ");


            part.append(selecteds.get(idx));
        }


        return part.toString();


    }
    private static void bt(int current, List<Integer> selecteds){

        if(current==m){
            String data = convert(selecteds);
            //변환하기
            //증가하지 않는 경우 null 반환
            if(data == null) return;

            if(set.contains(data)) return;
            set.add(data);
            resultNumbers.append(data).append("\n");


            return;
        }


        for(int nextIdx=0;nextIdx<n; nextIdx++){

            selecteds.add(numbers[nextIdx]);
            bt(current+1, selecteds);
            selecteds.remove(selecteds.size()-1);

        }

    }


    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(buffer.readLine());

        n = Integer.valueOf(tokens.nextToken());
        m = Integer.valueOf(tokens.nextToken());


        set= new HashSet<>();
        resultNumbers = new StringBuilder();


        numbers = Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).sorted().toArray();

        bt(0, new ArrayList<>());

        System.out.println(resultNumbers);


    }




}