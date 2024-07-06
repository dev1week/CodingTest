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
    static boolean[] isUsed;
    static int n;
    static int m;


    private static String convert(List<Integer>selecteds){

        StringBuilder part = new StringBuilder();

        part.append(selecteds.get(0));

        for(int idx=1; idx<selecteds.size(); idx++){
            part.append(" ");

            if(selecteds.get(idx-1)>selecteds.get(idx)) return null;


            part.append(selecteds.get(idx));
        }


        return part.toString();


    }
    private static void bt(int current, List<Integer> selecteds, int lastNum){

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


        for(int nextIdx=lastNum+1;nextIdx<n; nextIdx++){
            if(isUsed[nextIdx])return;
            isUsed[nextIdx] = true;
            selecteds.add(numbers[nextIdx]);
            bt(current+1, selecteds, nextIdx);
            selecteds.remove(selecteds.size()-1);
            isUsed[nextIdx] = false;
        }

    }


    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(buffer.readLine());

        n = Integer.valueOf(tokens.nextToken());
        m = Integer.valueOf(tokens.nextToken());

        isUsed = new boolean[n];
        set= new HashSet<>();
        resultNumbers = new StringBuilder();


        numbers = Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).sorted().toArray();

        bt(0, new ArrayList<>(), -1);

        System.out.println(resultNumbers);


    }




}