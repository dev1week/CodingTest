

import java.io.*;
import java.util.*;



public class Main {

    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));


    //각 집합의 원소의 갯수2*10^5


    //해시셋으로 저장됨

    public static void main(String[] args)throws IOException {

        StringTokenizer tokens = new StringTokenizer(buffer.readLine());

        int numOfA = Integer.valueOf(tokens.nextToken());
        int numOfB = Integer.valueOf(tokens.nextToken());


        Set<Integer> a = new HashSet<>();
        int[] b = new int[numOfB];

        tokens = new StringTokenizer(buffer.readLine());
        for(int i=0; i<numOfA; i++){
            a.add(Integer.valueOf(tokens.nextToken()));
        }

        tokens = new StringTokenizer(buffer.readLine());
        for(int i=0; i<numOfB; i++){
            b[i] = Integer.valueOf(tokens.nextToken());
        }

        int numOfInterSet = 0;

        //a와 b의 교집합을 구한다.
        for(int elementB : b){
            if(a.contains(elementB)){
                numOfInterSet++;
            }
        }


        //합집합 개수를 구한다.
        //a + b - interSet

        //합집합에서 교집합을 뺀다.
        System.out.println(numOfA+numOfB-2*numOfInterSet);






    }
}
