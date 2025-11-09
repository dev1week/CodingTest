import java.io.*; 
import java.util.*; 
class Edge implements Comparable<Edge>{
    int to; 
    int weight; 

    Edge(int to, int weight){
        this.to = to; 
        this.weight = weight; 
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}

class Main{
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    private static final int MAX = (int) 5e8+1; 

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(buffer.readLine()); 
        int n = Integer.parseInt(tokens.nextToken());
        int m = Integer.parseInt(tokens.nextToken());


        List<Edge>[] graph = new ArrayList[n+1];;

        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>(); 
        }

        for(int edge=0; edge<m; edge++){
            tokens = new StringTokenizer(buffer.readLine()); 
            int from = Integer.parseInt(tokens.nextToken());
            int to = Integer.parseInt(tokens.nextToken());
            int weight = Integer.parseInt(tokens.nextToken());

            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }
        tokens = new StringTokenizer(buffer.readLine());
        int start = Integer.parseInt(tokens.nextToken());
        int end = Integer.parseInt(tokens.nextToken()); 

        
        //e -> s 기준 다익스트라 만들기 

        int[] dist = new int[n+1];
        Arrays.fill(dist, MAX);
        dist[end] = 0; 

        PriorityQueue<Edge> que = new PriorityQueue<>();
        que.add(new Edge(end, 0)); 

        while(!que.isEmpty()){
            Edge current= que.poll(); 

            for(Edge next: graph[current.to]){
                int tmpDist = dist[current.to] + next.weight; 
                if(dist[next.to]>tmpDist){
                    dist[next.to] = tmpDist; 
                    que.add(new Edge(next.to, tmpDist)); 
                }
            }
        }

        for(int node=1; node<=n; node++){
            Collections.sort(graph[node], new Comparator<Edge>(){
                @Override
                public int compare(Edge e1, Edge e2) {
                    return Integer.compare(e1.to, e2.to);
                }
            }); 
        }

        int current = start;
        List<Integer> routes = new ArrayList<>(); 
        boolean[] isUsed = new boolean[n+1]; 
        routes.add(start); 
        while(current!=end){
            for(Edge next: graph[current]){
                //current->end == current -> next + next->end
                if(dist[current]==dist[next.to]+next.weight){
                    routes.add(next.to);
                    current = next.to; 
                    isUsed[current] = true; 
                    break; 
                }
            }
        }
        isUsed[end] =false; 

        int[] reverseDist = new int[n+1];
        Arrays.fill(reverseDist, MAX); 
        reverseDist[end] = 0; 
        
        que.add(new Edge(end, 0)); 

        while(!que.isEmpty()){
            Edge currentEdge = que.poll(); 
            if(isUsed[currentEdge.to])continue; 
            for(Edge next: graph[currentEdge.to]){
                if(isUsed[next.to])continue; 
                int tmpDist = next.weight + reverseDist[currentEdge.to];
                if(reverseDist[next.to]>tmpDist){
                    que.add(new Edge(next.to, tmpDist)); 
                    reverseDist[next.to] = tmpDist;
                }
            }
        }
        

        
        System.out.println(dist[start]+reverseDist[start]); 
        
        
        //s->e로 갈 때 루트 정하기 

        //해당 루트에 방문 표시하기 


        //e->s 다익스트라 돌리기 
            //저번 루트에서 사용한 곳은 빼고 진행하기 
        
    }
}


