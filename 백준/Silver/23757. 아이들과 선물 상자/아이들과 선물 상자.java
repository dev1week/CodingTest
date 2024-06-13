
import javax.swing.text.Position;
import java.io.*;
import java.util.*;
import java.util.stream.IntStream;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

    static StringTokenizer tokens;

    public static void main(String[] args)throws IOException {
        tokens = new StringTokenizer(buffer.readLine());

        int n= Integer.valueOf(tokens.nextToken()); // 선물상자 개수 ~ 10^5
        int m= Integer.valueOf(tokens.nextToken()); // 선물 받을 아이들의 수 1~m까지 번호로 표기 10^5
        //한명씩 현재 선물이 가장 많이 담겨 있는 사장에서 원하는 만큼 선물을 가져간다.
        //상자에 원하는 것보다 적은 개수의 선물이 들어있다면 선물을 가져가지 못해 실망한다.


        PriorityQueue<Integer> que = new PriorityQueue<>(Comparator.reverseOrder());
        tokens = new StringTokenizer(buffer.readLine());
        for(int box=0; box<n; box++){
            que.add(Integer.valueOf(tokens.nextToken()));
        }

        int[] wantedSizes = Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        System.out.println(simluation(wantedSizes, que));







    }

    private static int simluation(int[] wantedSizes, PriorityQueue<Integer>que){
        for(int size : wantedSizes){

            int currentSize = que.poll();

            if(currentSize>=size){
                currentSize-=size;
                que.add(currentSize);
            }else{
                return 0;
            }

        }
        return 1;
    }
}
