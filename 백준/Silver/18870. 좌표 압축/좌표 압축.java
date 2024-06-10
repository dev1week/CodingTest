
import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    private static List<Integer> getUniquePositions(int[] positions){
        List<Integer> uniquePositions = new ArrayList<>();

        uniquePositions.add(positions[0]);

        for(int i=1; i<positions.length; i++){
            if(positions[i-1]!=positions[i]){
                uniquePositions.add(positions[i]);
            }
        }

        return uniquePositions;
    }

    public static void main(String[] args)throws IOException{
        int n = Integer.valueOf(buffer.readLine());
        int[] positions = Arrays.stream(buffer.readLine().split("\\s+")).
                mapToInt(Integer::parseInt)
                .toArray();

        int[] sortedPositions = Arrays.stream(positions).sorted().toArray();

        //중복을 제거한다. n
        List<Integer> uniquePositions = getUniquePositions(sortedPositions);


        int[] compressedPositions = new int[n];
        for(int i=0; i<n; i++){
            compressedPositions[i] = Collections.binarySearch(uniquePositions, positions[i]);
        }

        StringBuilder result = new StringBuilder();

        for(int compressedPosition: compressedPositions ){
            result.append(compressedPosition).append(" ");
        }


        System.out.println(result);
        //입력받은 쿼리들을 순회한다.
            //중복을 제거한 배열로 이진탐색을 진행한다.




    }







}
