
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

    private static int[] getArr() throws IOException{
        return Arrays.stream(buffer.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void main(String[] args)throws IOException{
        tokens = new StringTokenizer(buffer.readLine());
        int aNum = Integer.valueOf(tokens.nextToken());
        int bNum = Integer.valueOf(tokens.nextToken());

        int[] aSet = getArr();
        Arrays.sort(aSet); 
        int[] bSet = getArr();
        Arrays.sort(bSet);

        int count = 0;
        ArrayList<Integer> result = new ArrayList<>();
        for(int a: aSet){
            if(binarySearch(bSet, a)==-1){
                count++;
                result.add(a);
            }
        }

        StringBuilder resultPrint = new StringBuilder();

        resultPrint.append(count).append("\n");
        for(int data: result){
            resultPrint.append(data).append(" ");
        }

        System.out.println(resultPrint);

    }

    private static int binarySearch(int[] arr, int target){
        int l = 0;
        int h = arr.length;


        while(h>l){
            int mid = (h+l)/2;

            if(arr[mid]==target) return mid;

            if(arr[mid]>target) h=mid;
            else l=mid+1;
        }

        return -1;
    }



}
