

import java.io.*;
import java.util.*;



class Number implements Comparable<Number>{

    private int data;


    public Number(int data){
        this.data = data;
    }


    @Override
    public int compareTo(Number o){
        return o.data - this.data;
    }

    public int getData(){
        return this.data;
    }

    @Override
    public String toString(){
        return String.valueOf(this.data);
    }

}
public class Main {


    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));


    //각 집합의 원소의 갯수2*10^5


    //해시셋으로 저장됨

    public static void main(String[] args)throws IOException {


        PriorityQueue<Number> pq = new PriorityQueue<>();
        int n = Integer.valueOf(buffer.readLine());


        StringBuilder result = new StringBuilder();
        for(int i=0; i<n; i++){
            Number num =new Number(Integer.valueOf(buffer.readLine()));
            if(num.getData()==0){
                if(pq.isEmpty()){
                    result.append(0).append("\n");
                }else{

                    result.append(pq.poll()).append("\n");

                }


            }else{
                pq.add(num);
            }
        }

        System.out.println(result);


    }
}
