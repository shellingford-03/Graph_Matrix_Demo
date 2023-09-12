/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Stack;
import java.io.File;
import java.io.RandomAccessFile;
/**
 *
 * @author TanHin
 */
public class Euler_Graph extends Graph_Matrix{
    ArrayList<Integer> E_cycle;
    int startV = 0;

    Euler_Graph() {
        super();
    }
    
    Euler_Graph(String vertexNames){
        super(vertexNames);
    }
    
    private int degree(int v){
        int result = 0;
        for (int i = 0; i < nVertices; i++) {
            result += adjMatrix[v][i];
        }
        return result;
    }
    
    private int countOddVertices(){
        int count = 0;
        for (int i = 0; i < nVertices; i++) {
            if(degree(i)%2>0) count++;
        }
        return count;
    }
    
    public boolean hasEulerCycle(){
        return countOddVertices()==0;
    }
    
    public boolean hasEulerPath(){
        return countOddVertices()==2;
    }
    
    private int firstAdjacent(int v){
        for (int i = 0; i < nVertices; i++) {
            if(adjMatrix[v][i]>0) return i;
        } return -1;
    }
    
    public Euler_Graph copy(){
        Euler_Graph result = new Euler_Graph();
        result.nVertices = this.nVertices;
        result.vSet = this.vSet;
        result.setAdjMatrix(this.adjMatrix);
        return result;
    }
    
    private void removeOneEdge(int u, int v){
        if(adjMatrix[u][v]>0){
            adjMatrix[u][v]--;
            adjMatrix[v][u]--;
        }
    }
    
    public ArrayList<Integer> findEulerCycle(int startV){
        if(!hasEulerCycle()) return E_cycle=null;
        this.startV = startV;
        Euler_Graph gr = this.copy();
        E_cycle = new ArrayList();
        Stack<Integer> stk = new Stack();
        stk.push(startV);
        int u;
        while(!stk.empty()){
            u = stk.peek();
            int v= gr.firstAdjacent(u);
            if(v>=0){
                stk.push(v);
                gr.removeOneEdge(u, v);
            }
            else E_cycle.add(stk.pop());
        }
        return E_cycle;
    }
    
    public String EulerCycleStr(){
        if(E_cycle==null) return null;
        String result = "Euler cycle from "+this.vSet[startV]+": ";
        for (Integer v: E_cycle) {
            result += this.vSet[v]+" ";
        }
        return result;
    }
    
    public boolean printEulerCycleToFile(String filename) throws Exception{
        if(E_cycle==null) return false;
        File f = new File(filename);
        if (f.exists()) f.delete();
        RandomAccessFile rf = new RandomAccessFile(f, "rw");
        rf.writeBytes(this.EulerCycleStr()+"\r\n");
        rf.close();
        return true;
    }
    
    
    
    
    
    
}
