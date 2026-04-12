
#include <string>
#include <vector>
#include <unordered_set> 

using namespace std;

class UnionFind{
    private:
        vector<int> parents;
        vector<int> rank;
    
        void init(int n){
            parents.resize(n);
            rank.assign(n,0); 
            
            for(int node=0; node<n; node++){
                parents[node] = node; 
            }
        }
    
    public:
        UnionFind(int n){
            init(n); 
        }    
    
        int find(int node){
            if(parents[node]==node){
                return node; 
            }
            
            return parents[node] = find(parents[node]); 
        }
    
        void unite(int a, int b){
            int parentA = find(a);
            int parentB = find(b); 
            
            if(parentA==parentB){
                return; 
            }
            
            if(rank[parentA]<rank[parentB]){
                swap(parentA, parentB); 
            }
            
            parents[parentB] = parentA; 
            
            if(rank[parentA]==rank[parentB]){
                rank[parentA]++; 
            }
        }
};


int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    
    UnionFind network = UnionFind(n); 
    
    for(int com1=0; com1<n; com1++){
        for(int com2=0; com2<n; com2++){
            if(com1==com2)continue; 
            
            if(computers[com1][com2]==1){
                network.unite(com1, com2); 
            }
        }
    }
    
    unordered_set<int> networkTypes; 
    for(int com=0; com<n; com++){
        networkTypes.insert(network.find(com));
    }
    
    return networkTypes.size();
}