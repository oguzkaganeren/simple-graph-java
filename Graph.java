package graphAs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Graph {
	Map<Node, LinkedList<Edge>> g = new HashMap<>();
	public Graph() {
		
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
	
	public void printShortestDistance( Node source, Node dest) {

//        int[] dist = new int[size];
//        boolean[] visited = new boolean[size];
//        int[] previous = new int[size];
        
        Queue<Node> queue= new LinkedList<>();
//        Arrays.fill(previous, -1);
//        Arrays.fill(dist, -1);
//        Arrays.fill(visited, false);
        Map<Node, Boolean> tempVisited = new HashMap<>();
        Map<Node, Integer> tempDist = new HashMap<>();
        Map<Node, Integer> tempPrevious = new HashMap<>();
        tempVisited.put(source, true);
        tempDist.put(source, 0);
        //visited[startV] = true;
        //dist[startV]= 0;

        boolean routeFound = false;
        queue.add(source);
        while(!queue.isEmpty()){
            Node node = queue.poll();

            for(Edge e: g.get(node)){
                //if(!visited[e.endNode]){
            	if(tempVisited.get(e.endNode)==null)
            	{
            		tempDist.put(e.endNode, tempDist.get(node)+1);
            		
                    //dist[e.endVertex] = dist[node]+1;
                    //previous[e.endVertex] = node;
                    tempPrevious.put(e.endNode, tempDist.get(node));
                    tempVisited.put(e.endNode, true);
                    //visited[e.endVertex] = true;
                    queue.add(e.endNode);

                    if(e.endNode == dest){
                        routeFound=true;
                        break;
                    }

                }
            }
        }

        if(routeFound) {
           // System.out.print("Path is: ");
            //int i = dest;
            //previous[i] will be 0 for source node
//            while (previous[i] != -1) {
//                System.out.print(i + " ");
//                i = previous[i];
//            }
//            System.out.print(i + " ");

            System.out.println();
            System.out.println("Path length is:" + tempDist.get(dest));
        }
    }
}
