import java.io.*;
import java.util.*;



public class Main {

    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args)throws IOException{

        int n = Integer.parseInt(buffer.readLine());


        List<Integer>[] graph = initGraph(n);


        int[] costs = new int[n+1];
        int[] indegree = new int[n+1];
        makeGraph(n, graph, costs, indegree);

        int[] totMinCosts = getTotMinCosts(n, graph, costs, indegree);


        System.out.println(print(totMinCosts, n));
    }


    private static String print(int[] array, int n){
        StringBuilder result = new StringBuilder();

        for(int idx=1; idx<=n; idx++){
            result.append(array[idx]).append("\n");
        }

        return result.toString();
    }

    private static int[] getTotMinCosts(int n, List<Integer>[] graph, int[] costs, int[] indegree) {
        int[] totMinCosts = new int[n +1];
        Queue<Integer> que = getRootNodes(n, costs, indegree, totMinCosts);

        return fillTotMinCosts(graph, costs, indegree, totMinCosts, que);
    }

    private static int[] fillTotMinCosts(List<Integer>[] graph, int[] costs, int[] indegree, int[] totMinCosts, Queue<Integer> que) {

        while(!que.isEmpty()){
            int parent = que.poll();


            for(int child: graph[parent]){

                indegree[child]--;
                if(indegree[child]==0){
                    que.add(child);
                }

                totMinCosts[child] = Math.max(totMinCosts[child], totMinCosts[parent]+ costs[child]);
            }
        }
        return totMinCosts;
    }

    private static Queue<Integer> getRootNodes(int n, int[] costs, int[] indegree, int[] totMinCosts) {
        Queue<Integer> que = new LinkedList<>();
        for(int building = 1; building<= n; building++){
            if(indegree[building]==0){
                que.add(building);
                totMinCosts[building] = costs[building];
            }
        }
        return que;
    }

    private static void makeGraph(int n, List<Integer>[] graph, int[] costs, int[] indegree) throws IOException {
        for(int building = 1; building<= n; building++){
            tokens = new StringTokenizer(buffer.readLine());
            int cost = Integer.parseInt(tokens.nextToken());

            costs[building] = cost;

            int preBuilding = Integer.parseInt(tokens.nextToken());
            while(preBuilding!=-1){
                graph[preBuilding].add(building);
                indegree[building]++;
                preBuilding = Integer.parseInt(tokens.nextToken());
            }
        }
    }

    private static List<Integer>[] initGraph(int n) {
        List<Integer>[] graph = new List[n +1];

        for(int node = 0; node<= n; node++){
            graph[node] = new ArrayList<>();
        }
        return graph;
    }
}