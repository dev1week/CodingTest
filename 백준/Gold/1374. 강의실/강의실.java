import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

class Lecture implements Comparable<Lecture>{
    int idx, start, end;


    public Lecture(int idx, int start, int end){
        this.idx = idx;
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Lecture o){
        if(this.start == o.start){
            return this.end - o.end;
        }
        return this.start-o.start;
    }
}


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {


        int n = Integer.parseInt(buffer.readLine());

        Lecture[] lectures = new Lecture[n];

        for(int i=0; i<n; i++){
            tokens = new StringTokenizer(buffer.readLine());

            int idx = Integer.parseInt(tokens.nextToken());
            int start = Integer.parseInt(tokens.nextToken());
            int end = Integer.parseInt(tokens.nextToken());


            lectures[i] = new Lecture(idx, start, end);
        }

        int count = 0;
        PriorityQueue<Integer> currentLectures = new PriorityQueue<>();
        Arrays.sort(lectures); 


        for(int i=0; i<n; i++){
            if(currentLectures.isEmpty()){
                currentLectures.add(lectures[i].end);
            }else{
                int fastestEndTime = currentLectures.peek();
                //강의실 증설 없이 시작할 수 있는 경우
                if(lectures[i].start>=fastestEndTime){
                    currentLectures.poll();
                }else{
                //강의실을 증설해야 시작할 수 있는 경우
                    count++;
                }
                currentLectures.add(lectures[i].end);
            }

        }
        System.out.println(currentLectures.size());


        //currentLecture를 pop한다.
            //현재 진행 중인 강의의 종료시간 < nextLecture의 시작 시간






    }






}