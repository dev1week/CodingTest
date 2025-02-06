import java.io.*;
import java.util.*;



public class Main {

    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args)throws IOException{

        tokens = new StringTokenizer(buffer.readLine());

        int n = Integer.parseInt(tokens.nextToken());
        int m = Integer.parseInt(tokens.nextToken());

        List<Integer>[]graph = initGraph(n);
        int[] indegree = addEdgesAndGetIndegree(n, m, graph);

        StringBuilder result = topologicalSort(n, graph, indegree);

        System.out.println(result);


    }

    private static StringBuilder topologicalSort(int n, List<Integer>[] graph, int[] indegree) {
        StringBuilder result = new StringBuilder();
        PriorityQueue<Integer> que = new PriorityQueue<>();
        addRootNodes(n, indegree, que);

        while(!que.isEmpty()){
            int current = que.poll();
            printNode(result, current);
            for(int child : graph[current]){
                indegree[child]--;
                findFirstNodeAndAddQue(indegree, que, child);
            }
        }
        return result;
    }

    private static void addRootNodes(int n, int[] indegree, PriorityQueue<Integer> que) {
        for(int node = 1; node<= n; node++){
            findFirstNodeAndAddQue(indegree, que, node);
        }
    }

    private static int[] addEdgesAndGetIndegree(int n, int m, List<Integer>[] graph) throws IOException {
        int[] indegree = new int[n +1];
        for(int edge = 0; edge< m; edge++){
            tokens = new StringTokenizer(buffer.readLine());
            int parent = Integer.parseInt(tokens.nextToken());
            int child = Integer.parseInt(tokens.nextToken());
            graph[parent].add(child);
            indegree[child]++;
        }
        return indegree;
    }

    private static void findFirstNodeAndAddQue(int[] indegree, PriorityQueue<Integer> que, int child) {
        if(isFirstNode(indegree[child])){
            que.add(child);
        }
    }

    private static boolean isFirstNode(int indegree) {
        return indegree == 0;
    }

    private static void printNode(StringBuilder result, int child) {
        result.append(child).append(" ");
    }

    private static void sortGraph(int n, List<Integer>[] graph) {
        for(int node = 0; node<= n; node++){
            Collections.sort(graph[node]);
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