package graphAs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import Jama.Matrix;
import Jama.EigenvalueDecomposition;


public class Graph {
	Map<Node, LinkedList<Edge>> adjList = new HashMap<>();
	Map<Node, LinkedList<Node>> eigenList = new HashMap<>();
	public Graph() {
		
	}
	public int getDegree(Node which) {
		return adjList.get(which).size();
	}
	public double closenessCentrality(Node which) {
		int total=0;
		int nodeCount=0;
		for (Map.Entry<Node,LinkedList<Edge>> entry : adjList.entrySet()) {
			total+=getBFDistance(which, entry.getKey());
			if(which!=entry.getKey()) {
				nodeCount++;
			}
			
		}
		double result=(double)1/((double)total/(double)nodeCount);
		return result;
	}
	public void calculateEigen() {
		Matrix neo =new Matrix(makeMatrix());
		EigenvalueDecomposition trinity=neo.eig();
		for (int i = 0; i < trinity.getV().getRowDimension(); i++) {
			for (int j = 0; j < trinity.getV().getColumnDimension(); j++) {
				System.out.print(trinity.getV().get(i, j)+" ");
			}
			System.out.println();
		}
		
		
	}
	public void printList() {

		for (Map.Entry<Node,LinkedList<Node>> entry : eigenList.entrySet()) {
			System.out.print(entry.getKey().getName().toString()+"->");
			for (Node entry2 : entry.getValue()) {
				
				System.out.print(entry2.getName().toString());
			}
			System.out.println();
		}
	}
	public double[][] makeMatrix() {
		//make a zero-one matrix
		double[][] myArray=new double[eigenList.entrySet().size()][eigenList.entrySet().size()];
		int i=0;
		int j=0;
		
		for (Map.Entry<Node,LinkedList<Node>> entry : eigenList.entrySet()) {
			
			for (Map.Entry<Node,LinkedList<Node>> entry2 : eigenList.entrySet()) {
				if(entry.getValue().contains(entry2.getKey())) {
					myArray[i][j]=1;
					System.out.print("1");
				}else {
					myArray[i][j]=0;
					System.out.print("0");
				}
			}
			System.out.println();
			j=0;
			i++;
		}
		return myArray;
		
	}
	public double eccentricityCentrality(Node which) {
		int DF=0;
		int max=-1;
		for (Map.Entry<Node,LinkedList<Edge>> entry : adjList.entrySet()) {
			DF=getBFDistance(which, entry.getKey());
			if(max<DF) {
				max=DF;
			}
			
		}
		double result=(double)1/(double)max;
		return result;
	}
	//src->dest and dest->src
	public void addEdge( Node src, Node dest, String label) {
			addEigen(src, dest);
	        LinkedList<Edge> val;
	        if(adjList.containsKey(src)){
	            val = adjList.get(src);
	        }
	        else {
	            val = new LinkedList<>();
	        }
	        val.add(new Edge(dest, label));
	        adjList.put(src, val);

	        LinkedList<Edge> val2;
	        if(adjList.containsKey(dest)){
	            val2 = adjList.get(dest);
	        }
	        else {
	            val2 = new LinkedList<>();
	        }
	        val2.add(new Edge(src, label));
	        adjList.put(dest, val2);

	    }
	public void addEigen( Node src, Node dest) {

        LinkedList<Node> val;
        if(eigenList.containsKey(src)){
            val = eigenList.get(src);
        }
        else {
            val = new LinkedList<>();
        }
        val.add(dest);
        eigenList.put(src, val);

        LinkedList<Node> val2;
        if(eigenList.containsKey(dest)){
            val2 = eigenList.get(dest);
        }
        else {
            val2 = new LinkedList<>();
        }
        val2.add(src);
        eigenList.put(dest, val2);

    }
	public int getBFDistance( Node source, Node dest) {
		if(source==dest)
			return 0;
        Queue<Node> queue= new LinkedList<>();
        Map<Node, Boolean> tempVisited = new HashMap<>();
        Map<Node, Integer> tempDist = new HashMap<>();
        tempVisited.put(source, true);
        tempDist.put(source, 0);

        boolean routeFound = false;
        queue.add(source);
        while(!queue.isEmpty()){
            Node node = queue.poll();

            for(Edge e: adjList.get(node)){
            	if(tempVisited.get(e.endNode)==null)
            	{
            		tempDist.put(e.endNode, tempDist.get(node)+1);
            		
                    tempVisited.put(e.endNode, true);
                    queue.add(e.endNode);

                    if(e.endNode == dest){
                        routeFound=true;
                        break;
                    }

                }
            }
        }

        if(routeFound) {
            return tempDist.get(dest);
        }else {
			return -1;
		}
    }
}
