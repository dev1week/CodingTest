import java.io.*;
import java.util.*;

class Process implements Comparable<Process>{



    int priority, id, runtime, originalPriority;


    public Process(int id, int runtime, int priority){
        this.id = id;
        this.runtime = runtime;
        this.priority = priority;
        this.originalPriority = priority;
    }

    @Override
    public int compareTo(Process o) {
        if(this.priority == o.priority){

            return this.id - o.id;
        }
        return o.priority - this.priority;
    }

    public void decrease(){

    }


    public String toString(){
        return this.id+"번째 프로세스의 우선순위는 "+this.priority+"이고, 종료까지 남은 시간은 "+ this.runtime;
    }
}

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(buffer.readLine());
        int t = Integer.parseInt(tokens.nextToken());
        int n = Integer.parseInt(tokens.nextToken());
        PriorityQueue<Process> que = new PriorityQueue<>();
        int[] logs = new int[t+1];


        for(int i=0; i<n; i++ ){

             tokens = new StringTokenizer(buffer.readLine());

             int id = Integer.parseInt(tokens.nextToken());
             int runtime = Integer.parseInt(tokens.nextToken());
             int priority = Integer.parseInt(tokens.nextToken());


             que.add(new Process(id, runtime, priority));
        }
        StringBuilder result = new StringBuilder();

        for(int time=1; time<=t; time++){

            Process currentProcess = que.poll();
            logs[time] = currentProcess.id;;
            result.append(currentProcess.id).append("\n");
            //큐에서 프로세스를 가져옴
            //[현재 시간] = 사용한 프로세스의 id를 기록합니다.

            //현재 프로세스 id를 제외한 나머지 프로세스의 우선순위가 1 증가합니다.
            //= 현재 프로세스의 우선순위가 1 감소합니다.

            currentProcess.priority--;

            currentProcess.runtime--;
            //현재 프로세스의 필요 시간이 1 감소합니다.


            if(currentProcess.runtime>0){
                que.add(currentProcess);
            }
        }
        System.out.println(result);
    }




}