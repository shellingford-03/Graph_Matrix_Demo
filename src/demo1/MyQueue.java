/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package simpleGraphs.graph_matrix;
import java.util.LinkedList;
/**
 *
 * @author TanHin
 */
class MyQueue {
    LinkedList<Integer> t;
    MyQueue(){
        t= new LinkedList();
    }
    
    boolean isEmpty(){
        return(t.isEmpty());
    }
    
    void enqueue(int x){
        t.add(x);
    }
    
    int dequeue(){
        return(t.removeFirst());
    }
    
    
}
