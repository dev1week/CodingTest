import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

class Lecture implements Comparable<Lecture>{
    int start, end;


    public Lecture(int start, int end){
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

            lectures[i] = new Lecture(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
        }

        Arrays.sort(lectures);

        PriorityQueue<Integer> room = new PriorityQueue<>();

        for(int i=0; i<n; i++){


            if(room.isEmpty()){
                room.add(lectures[i].end);
                continue;
            }
            //방을 배정할 수 있는 경우
            else if(lectures[i].start>=room.peek()) {
                room.poll();
            }
            room.add(lectures[i].end);
        }


        System.out.println(room.size());





    }






}