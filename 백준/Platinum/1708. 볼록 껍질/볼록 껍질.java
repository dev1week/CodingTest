import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    public static void main(String[] args)throws IOException{
        int n = Integer.parseInt(buffer.readLine());

        List<Point> points= new ArrayList<>();

        for(int i=0; i<n; i++){
            tokens= new StringTokenizer(buffer.readLine());
            int x = Integer.parseInt(tokens.nextToken());
            int y = Integer.parseInt(tokens.nextToken());
            points.add(new Point(x,y));
        }


        Point.pivot = Collections.min(points);

        points.sort(Point.ccwComparator());


        Stack<Point> hull = new Stack<>();


        for(Point p: points){

            while(hull.size()>=2){
                Point second = hull.get(hull.size() - 2);
                Point first = hull.get(hull.size() - 1);

                if (Point.ccw(second, first, p) > 0) break;
                hull.pop();
            }
            hull.push(p);
        }
        System.out.println(hull.size());


    }

}

class Point implements Comparable<Point>{
    int x, y;

    static Point pivot;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public int compareTo(Point o) {
        if(this.y==o.y){
            return this.x-o.x;
        }
        return this.y-o.y;
    }

    public static long ccw(Point a, Point b, Point c){
        return (long)(b.x-a.x)*(c.y-a.y)-(long)(b.y-a.y)*(c.x-a.x);

    }

    public static Comparator<Point> ccwComparator(){
        return (a,b)->{
            long ccw = ccw(pivot,a,b);
            if(ccw>0){
                return -1;
            }else if(ccw<0){
                return 1;
            }
            return Long.compare(dist(pivot, a), dist(pivot, b));
        };
    }

    public static long dist(Point a, Point b){
        long dx = a.x-b.x;
        long dy = a.y-b.y;
        return dx*dx+dy*dy;
    }


}

