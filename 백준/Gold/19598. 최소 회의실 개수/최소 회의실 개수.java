import java.io.*;
import java.util.*;

class Lecture implements Comparable<Lecture>{
    int start;
    int end;

    public Lecture(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Lecture o) {
        if(this.start==o.start){
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

        PriorityQueue<Integer> room  = new PriorityQueue<>();
        for(int i=0; i<n; i++){
            if(room.isEmpty()){
                room.add(lectures[i].end);
            }else {
                //방을 더 생성해야하는 경우
                if (room.peek() > lectures[i].start) {

                } else {
                    room.poll();
                }

                room.add(lectures[i].end);
                //방을 더 생성할 필요가 없는 경우
            }
        }
        System.out.println(room.size());

        }
    }




