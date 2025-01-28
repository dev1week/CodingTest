import java.io.*;
import java.util.*;



public class Main {

    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args)throws IOException{
        int t = Integer.parseInt(buffer.readLine());

        StringBuilder result = new StringBuilder();

        for(int test=0; test<t; test++){
            tokens = new StringTokenizer(buffer.readLine());
            int n = Integer.parseInt(tokens.nextToken());
            int k = Integer.parseInt(tokens.nextToken());

            //cost를 저장한다.
            int[] costs = getCosts(n);

            //간선 정보를 저장하면서 indegree 배열을 만든다.
            int[] indegree = new int[n+1];
            List<Integer>[] graph = getGraph(n, k, indegree);


            int[] minCosts = getMinCosts(n, costs, indegree, graph);

            //위상정렬을 수행한다. -> dp를 반환한다.
                //indegree가 0인 노드를 큐에 넣는다.

                //큐를 순회한다.
                    //현재 노드의 자식 노드를 순회한다.
                        //해당 자식의 indegree 배열 값을 1 줄인다.
                        //해당 자식의 indegree 값이 0일 경우  -> 큐에 넣는다.
                        //dp를 업데이트한다.
                            //dp[child] = Math.max(dp[child], dp[parent] + costs[child]);


            int w = Integer.parseInt(buffer.readLine());
            result.append(minCosts[w]).append("\n");
            
        }

        System.out.println(result);
    }

    private static int[] getMinCosts(int n, int[] costs, int[] indegree, List<Integer>[] graph) {
        int[] minCosts = new int[n +1];
        Queue<Integer> que = new LinkedList<>();
        for(int struct = 1; struct<= n; struct++){
            if (indegree[struct]==0) {
                que.add(struct);
                minCosts[struct] = costs[struct];
            }
        }

        while(!que.isEmpty()){
            int parent = que.poll();
            //System.out.println(parent);
            for(int child: graph[parent]){
                indegree[child]--;

                if(indegree[child]==0){
                    que.add(child);
                }
                minCosts[child] = Math.max(minCosts[child], minCosts[parent]+ costs[child]);
            }
        }
        return minCosts;
    }

    private static List<Integer>[] getGraph(int n, int k, int[] indegree) throws IOException {
        List<Integer>[] graph = initGraph(n);
        makeGraph(k, indegree, graph);
        return graph;
    }

    private static void makeGraph(int k, int[] indegree, List<Integer>[] graph) throws IOException {
        for(int edge = 1; edge<= k; edge++){
            tokens = new StringTokenizer(buffer.readLine());
            int parent = Integer.parseInt(tokens.nextToken());
            int child = Integer.parseInt(tokens.nextToken());

            graph[parent].add(child);
            indegree[child]++;
        }
    }

    private static List<Integer>[] initGraph(int n) {
        List<Integer>[] graph = new List[n +1];

        for(int node = 0; node<= n; node++) {
            graph[node]= new ArrayList<>();
        }
        return graph;
    }

    private static int[] getCosts(int n) throws IOException {
        int[] costs = new int[n +1];

        tokens = new StringTokenizer(buffer.readLine());
        for(int struct = 1; struct<= n; struct++){
            costs[struct] = Integer.parseInt(tokens.nextToken());
        }
        return costs;
    }
}