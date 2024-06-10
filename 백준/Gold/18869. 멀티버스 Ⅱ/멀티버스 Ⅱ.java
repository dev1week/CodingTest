
import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static String print(int[][] arr){
        StringBuilder result = new StringBuilder();

        for(int[] ar:arr){
            for(int a: ar){
                result.append(a).append(" ");
            }
            result.append("\n");
        }

        return result.toString();
    }

    public static void main(String[] args)throws IOException{
        tokens = new StringTokenizer(buffer.readLine());
        int m = Integer.valueOf(tokens.nextToken()); //우주의 개수 10^2
        int n = Integer.valueOf(tokens.nextToken()); //행성의 개수  10^4

        int[][] sizes = new int[m][n];


        for(int space=0; space<m; space++){
            tokens = new StringTokenizer(buffer.readLine());
            for(int planet=0; planet<n; planet++){
                sizes[space][planet] = Integer.valueOf(tokens.nextToken());
            }
        }

        //압축된 size를 만든다.

        int[][] compressedSizes = compress(sizes);
        
        int sameSpaceCount = 0;

        for(int space1=0; space1<m; space1++){
            for(int space2=space1+1; space2<m; space2++){

                if(isSame(compressedSizes[space1], compressedSizes[space2])) {
                    sameSpaceCount++;
                }

            }
        }


        System.out.println(sameSpaceCount);
        //압축된 사이즈로 각 우주 끼리 비교한다. j k
            //n만큼 반복하며 arr[j][i] == arr[k][i]인지 확인한다.
            //전부 일치할 경우에만 count를 증가시킨다.
    }


    private static boolean  isSame(int[] arr1, int[] arr2){
        if(arr1.length!=arr2.length) return false;
        for(int i=0; i<arr1.length; i++){
            if(arr1[i]!=arr2[i])return false;
        }
        return true;
    }

    static int[][] compress(int[][] arr){
        int[][] compressedArr = new int[arr.length][arr[0].length];

        for(int space=0; space<arr.length; space++){
            int[] sortedPlanets = Arrays.stream(arr[space]).distinct().sorted().toArray();
            for(int planet=0; planet<arr[0].length; planet++){
                compressedArr[space][planet] = Arrays.binarySearch(sortedPlanets, arr[space][planet]);
            }
            //중복을 제거하고 정렬된 배열 만들기
            //해당 배열로 이진탐색을 진행하여 arr값 업데이트하기
        }

        return compressedArr;
    }
}
