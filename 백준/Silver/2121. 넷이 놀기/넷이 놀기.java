
import java.io.*;
import java.util.*;


class Position implements Comparable<Position>{
    long x, y;


    public Position(long x, long y){
        this.x = x;
        this.y = y;
    }


    @Override
    public int hashCode(){
        return Objects.hash(x,y);
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj==null||getClass()!=obj.getClass()) return false;

        Position o = (Position) obj;

        return x==o.x && y==o.y;
    }

    @Override
    public int compareTo(Position o){
        if(this.x==o.x){
            return Long.compare(this.y, o.y);
        }
        return Long.compare(this.x, o.x);
    }
}

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;



    public static void main(String[] args)throws IOException {


        int n = Integer.valueOf(buffer.readLine());

        tokens = new StringTokenizer(buffer.readLine());

        int a = Integer.valueOf(tokens.nextToken());
        int b = Integer.valueOf(tokens.nextToken());

        Position[] positions = new Position[n];

        for (int i = 0; i < n; i++) {
            tokens = new StringTokenizer(buffer.readLine());

            positions[i] = new Position(Long.valueOf(tokens.nextToken()), Long.valueOf(tokens.nextToken()));

        }

        Arrays.sort(positions);


        int result = 0;
        for (Position position : positions) {

            if (isValid(positions, position, a,b)) {
                result++;
            }

        }

        System.out.println(result);

    }

    private static boolean isValid(Position[] positions, Position position, int a, int b){

        return binarySearch(positions, position.x+a, position.y+b)
                &&binarySearch(positions, position.x, position.y+b)
                &&binarySearch(positions, position.x+a, position.y);

    }

    private static boolean binarySearch(Position[] positions, long targetX, long targetY) {

        int l = 0;
        int h = positions.length;


        while(h>l){
            int mid = (l+h)/2;

            Position midPosition = positions[mid];

            if(midPosition.x==targetX&&midPosition.y==targetY){
                return true;
            }else if(midPosition.compareTo(new Position(targetX, targetY))<0){
                l=mid+1;
            }else{
                h=mid;
            }
        }

        return false;

    }


}
