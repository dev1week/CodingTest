class Solution {
    
    static void dfs(int[][] graph, int current, boolean[] isVisited, int n ){
        
        
        for(int nextNode=0; nextNode<n; nextNode++){
            if(!isVisited[nextNode]&&graph[current][nextNode]==1){
                isVisited[nextNode] = true; 
                dfs(graph, nextNode, isVisited, n); 
            }
        }
        
        
        
    }
    
    static final int NOT_VISITED = -1; 
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        boolean[] isVisited = new boolean[n];
        
        
        for(int node=0; node<n; node++){
            if(!isVisited[node]){
                isVisited[node] = true;
                
                dfs(computers, node, isVisited,n); 
                
                answer++; 
            }
            
            
            
            
        }
        
        //모든 노드 순회 
            //방문했는가?
                //방문할 때마다 개수를 세기 
                //시작점으로부터 dfs 돌기 
                    //dfs 내부에서 시작점으로 마킹해놓기 
            
        
        return answer;
    }
}