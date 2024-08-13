

import java.io.*;
import java.util.*;


class Node{
    String data;

    Node prev, next;

    public Node(String data){
        this.data = data;
    }

    public String toString(){
        return this.data;
    }

    public String connection(){
        StringBuilder result = new StringBuilder();


        return result.append(this.prev!=null?this.prev.data:"-1").append(" ").append(this.data).append(" ").append(this.next!=null?this.next.data:"-1").toString();
    }
}

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(buffer.readLine());

        Map<Integer, Integer> idToTail  = new HashMap<Integer, Integer>();
        Node[] nodes = new Node[n+1];

        for(int i=1; i<=n; i++){
            String schoolName = buffer.readLine();

            nodes[i] = new Node(schoolName);
            idToTail.put(i, i);
        }



        int head = 0;


        for(int query=0; query<n-1; query++){
            tokens = new StringTokenizer(buffer.readLine());

            int i = Integer.parseInt(tokens.nextToken());
            int j = Integer.parseInt(tokens.nextToken());

            //i의 뒤쪽에 j를 이어 붙인다.
            int iTail = idToTail.get(i);
            //i의 tail을 가져온다.

            //i의 tail과 j를 이어붙인다.
            connect(nodes[iTail], nodes[j]);
            //i의 tail을 j의 tail로 설정한다.
            idToTail.put(i, idToTail.get(j));
//            System.out.println(i+" "+j);
//            System.out.println(nodes[i].connection());
//            System.out.println(nodes[j].connection());
            if(query==n-2){
                head = i;
            }
        }



        Node current = nodes[head];
        StringBuilder result  = new StringBuilder();
        while(current.next!=null){
            result.append(current.data);
            current = current.next;
        }result.append(current.data);

        System.out.println(result);


    }

    private static void connect(Node s, Node e){
        if(s!=null)s.next = e;
        if(e!=null)e.prev = s;
    }

    private static void insertNext(Node target, Node insert){
        connect(insert, target.next);
        connect(target, insert);
    }
}





