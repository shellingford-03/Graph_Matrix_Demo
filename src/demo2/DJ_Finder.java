/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.LinkedList;
import java.io.PrintWriter;
/**
 *
 * @author TanHin
 */
public class DJ_Finder {
    public static final int INF = Integer.MAX_VALUE;
    Graph_Matrix g;
    int startV=-1;
    boolean[] flags;
    int[] costs;
    int[] predecessors;
    int n;
    boolean finished = false;
    
    
    
    public DJ_Finder(Graph_Matrix g){
        this.g = g;
        
        n= g.nVertices;
        flags = new boolean[n];
        costs = new int[n];
        predecessors = new int[n];
    }
    
    public void prepare(int startV){
        for (int i = 0; i < n; i++) {
            flags[i] = false;
            costs[i] = INF;
            predecessors[i] = -1;
        }
        
        flags[startV] = true;
        costs[startV] = 0;
        finished=false;
    }
    
    private int getMinCostVertex(){
        int minVertex = -1;
        for (int i = 0; i < n; i++) {
            if(flags[i]==false){
                if(minVertex==-1) minVertex = i;
                else if (costs[i]<costs[minVertex]) minVertex=i;
            }
        }
        return minVertex;
    }
    
    public void DJ (int startV){
        this.startV = startV;
        finished = false;
        prepare(startV);
        int v = startV;
        int newCostToU, weightVU;
        while (v!=-1){
            flags[v] = true;
            for (int i = 0; i < n; i++) {
                weightVU = g.adjMatrix[v][i];
                if (weightVU<INF && flags[i]==false){
                    newCostToU = costs[v]+weightVU;
                    if(newCostToU<costs[i]){
                        costs[i] = newCostToU;
                        predecessors[i] = v;
                    }
                }
            }
            v = getMinCostVertex();
        }
        finished = true;
    }
    
    
    private String getEdgeStr(int v, int u){
        return "["+g.vSet[v]+","+g.vSet[u]+","+g.adjMatrix[v][u]+"}";
    }
    
    private String getShorttestPath(int u){
        LinkedList<String> path = new LinkedList();
        int dest = u;
        int src = predecessors[dest];
        while (src!=-1){
            path.addFirst(getEdgeStr(src, dest));
            dest = src;
            src = predecessors[dest];
        }
        String result = "** SP "+g.vSet[startV]+" -> "+g.vSet[u]+
                ", len="+ costs[u]+" : "+path;
        return result;
    }
    
    public String spsToString(){
        String result = null;
        if (finished){
            result = "";
            for (int i = 0; i < n; i++) {
                result += getShorttestPath(i)+"\r\n";
            }
        }
        return result;
    }
    
    public boolean printSPsToFile(String filename) throws Exception{
        if (!finished) return false;
        PrintWriter pw =new PrintWriter(filename);
        pw.print(this.spsToString());
        pw.close();
        return true;
    }
    
    
    
    
    
    
    
}
