
import java.io.*;
import java.util.*;


class Container{
    int priority, weight;


    public Container(int priority, int weight){
        this.priority = priority;
        this.weight = weight;
    }


    @Override
    public String toString(){

        return new StringBuilder().append("우선순위 : ").append(this.priority).append(", 무게 : ").append(this.weight).toString();
    }



}



public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    private static int[] fillArray(int size)throws IOException{
        int[] arr = new int[size];



        tokens = new StringTokenizer(buffer.readLine());
        for(int i=0; i<size; i++){
            arr[i] = Integer.valueOf(tokens.nextToken());
        }


        return arr;
    }


    public static void main(String[] args)throws IOException{
        //10^5
        int n = Integer.valueOf(buffer.readLine());

        //10^5


        int[] numbers = fillArray(n);
        Arrays.sort(numbers);

        int m = Integer.valueOf(buffer.readLine());
        int[] queries = fillArray(m);

        StringBuilder result = new StringBuilder();

        for(int query: queries){

            result.append(binarySearch(numbers, query)).append("\n");

        }

        System.out.println(result);
    }

    private static int binarySearch(int[] arr, int target){

        int l = 0;
        int h = arr.length;

        while(h>l){
            int mid = (l+h)/2;
            if(arr[mid]==target){
                return 1;
            }else if(arr[mid]>target){
                h = mid;
            }else{
                l = mid+1;
            }
        }

        return 0;


    }

}
