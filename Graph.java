package graphAs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import Jama.Matrix;
import Jama.EigenvalueDecomposition;


public class Graph {
	Map<Node, LinkedList<Edge>> g = new HashMap<>();
	
	public Graph() {
		
	}
	public int getDegree(Node which) {
		return g.get(which).size();
	}
	public double closenessCentrality(Node which) {
		int total=0;
		int nodeCount=0;
		for (Map.Entry<Node,LinkedList<Edge>> entry : g.entrySet()) {
			total+=getBFDistance(which, entry.getKey());
			if(which!=entry.getKey()) {
				nodeCount++;
			}
			
		}
		double result=(double)1/((double)total/(double)nodeCount);
		return result;
	}
	public void makeMatrix() {
		int[][] myArray=new int[g.entrySet().size()-1][g.entrySet().size()-1];
		
	}
	public double eccentricityCentrality(Node which) {
		int DF=0;
		int max=-1;
		for (Map.Entry<Node,LinkedList<Edge>> entry : g.entrySet()) {
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

	        LinkedList<Edge> val;
	        if(g.containsKey(src)){
	            val = g.get(src);
	        }
	        else {
	            val = new LinkedList<>();
	        }
	        val.add(new Edge(dest, label));
	        g.put(src, val);

	        LinkedList<Edge> val2;
	        if(g.containsKey(dest)){
	            val2 = g.get(dest);
	        }
	        else {
	            val2 = new LinkedList<>();
	        }
	        val2.add(new Edge(src, label));
	        g.put(dest, val2);

	    }
	
	public int getBFDistance( Node source, Node dest) {
		if(source==dest)
			return 0;
        //previ sil
        Queue<Node> queue= new LinkedList<>();
        Map<Node, Boolean> tempVisited = new HashMap<>();
        Map<Node, Integer> tempDist = new HashMap<>();
        Map<Node, Integer> tempPrevious = new HashMap<>();
        tempVisited.put(source, true);
        tempDist.put(source, 0);

        boolean routeFound = false;
        queue.add(source);
        while(!queue.isEmpty()){
            Node node = queue.poll();

            for(Edge e: g.get(node)){
            	if(tempVisited.get(e.endNode)==null)
            	{
            		tempDist.put(e.endNode, tempDist.get(node)+1);
            		
                    tempPrevious.put(e.endNode, tempDist.get(node));
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
