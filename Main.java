package graphAs;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph ourGraph=new Graph();
		Node A=new Node("A");
		Node B=new Node("B");
		Node C=new Node("C");
		Node D=new Node("D");
		Node E=new Node("E");
		ourGraph.addEdge(A,B, "connect");
		ourGraph.addEdge(B,C,"connect");
		ourGraph.addEdge(B,E,"connect");
		ourGraph.addEdge(B,D,"connect");
		ourGraph.addEdge(E,C,"connect");
		ourGraph.addEdge(E,D,"connect");
		System.out.println(ourGraph.closenessCentrality(C));
		System.out.println(ourGraph.eccentricityCentrality(B));
		System.out.println("B dg:"+ourGraph.getDegree(E));
		
		
	}

}
