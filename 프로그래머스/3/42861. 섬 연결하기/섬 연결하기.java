import java.util.*;

class Solution {
    class Edge implements Comparable<Edge>{
        int start;
        int end;
        int cost;
        
        public Edge(int start,int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost; 
        }
        
        
        public int compareTo(Edge o){
            return this.cost - o.cost; 
        }
        
        public String toString(){
            return start+"-"+cost+"->"+end;
        }
    }
    
    static int[] parents;
    static final int NODE_MAX_NUM= 100; 
    
    private int find(int x){
        if(parents[x]==x) return x;
        
        
        return parents[x] = find(parents[x]);
    }
    
    private boolean union(int x, int y){
        if(find(x)==find(y)) return false; 
        
        parents[find(x)] = find(y);
        
        return true; 
        
    }
    
    
    
    public int solution(int n, int[][] costs) {
        int edgeCount = 0; 
        int result = 0;
        
        parents = new int[NODE_MAX_NUM+1];
        for(int node=0; node<=NODE_MAX_NUM; node++){
            parents[node] = node;
        }
        ArrayList<Edge> edges = new ArrayList<>(); 
        
        //edge에 입력 받는다. 
        for(int[] data: costs){
            int start = data[0];
            int end = data[1];
            int cost = data[2];
            
            Edge edge = new Edge(start, end, cost);
            edges.add(edge);
        }
        
        Collections.sort(edges);
        
        //edge를 정렬한다. 
        
        
        //edge를 순회하며 n-1개 뽑는다. 
            //사이클이 생기는 경우 뽑지 않는다. 
        for(Edge edge: edges){
            if(union(edge.start, edge.end)){
                result+=edge.cost; 
                edgeCount++;
                if(edgeCount==n-1)break; 
                
            }
            
            
        }
        
        
        return result;
    }
}