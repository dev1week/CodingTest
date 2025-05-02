import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {

  
            int n = Integer.parseInt(buffer.readLine());
            int[] numbers = Arrays.stream(buffer.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[] tmp = new int[n];
        System.out.println(mergeSort(0,n-1,numbers,tmp));

    }


    private static long mergeSort(int left, int right, int[] numbers, int[] tmp){
        long cnt = 0;

        if(left<right){
            int mid = (left+right)/2;
            cnt+=mergeSort(left, mid, numbers, tmp);
            cnt+=mergeSort(mid+1, right, numbers, tmp);
            cnt+=merge(left, mid, right, numbers, tmp);
        }
        return cnt;
    }
    private static long merge(int left, int mid, int right, int[] numbers, int[] tmp){
        int i = left;
        int j = mid+1;
        int k = left;
        long cnt = 0;

        while(i<=mid&&j<=right){
            if(numbers[i]<=numbers[j]){
                tmp[k++]=numbers[i++];
            }else{
                cnt+=(long)(mid-i+1);
                tmp[k++] = numbers[j++];
            }
        }
        ;
        if(i>mid){
            for(int l=j; l<=right; l++, k++){
                tmp[k] = numbers[l];
            }
        }else{
            for(int l=i; l<=mid; l++, k++){
                tmp[k] = numbers[l];
            }
        }

        for(int l=left; l<=right; l++){
            numbers[l] = tmp[l];
        }
        return cnt;
    }
}