import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args)throws IOException{

        int t = Integer.valueOf(buffer.readLine());
        StringBuilder result = new StringBuilder();

        for(int test=1; test<=t; test++){
            tokens = new StringTokenizer(buffer.readLine());

            int numOfCandy = Integer.parseInt(tokens.nextToken());
            int numOfBox = Integer.parseInt(tokens.nextToken());


            PriorityQueue<Integer> boxes = getBoxes(numOfBox);
            int minBoxSize = getMinBoxSize(boxes, numOfCandy);
            result.append(minBoxSize).append("\n");
        }

        System.out.println(result);
    }

    private static int getMinBoxSize(PriorityQueue<Integer> boxes, int numOfCandy) {

        int count = 0;
        while(numOfCandy>0&&!boxes.isEmpty()){
            
            numOfCandy -= boxes.poll();

            count++;
        }

        return count;
    }

    private static PriorityQueue<Integer> getBoxes(int numOfBox) throws IOException{
        PriorityQueue<Integer> boxes = new PriorityQueue<>(Comparator.reverseOrder());

        for(int i=0; i<numOfBox; i++) {
            tokens = new StringTokenizer(buffer.readLine());
            int r = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());
            boxes.add(r * c);
        }
        return boxes;
    }
}
