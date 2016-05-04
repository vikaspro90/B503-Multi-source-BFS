import java.util.LinkedList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Random;

public class Main {
	public static void main(String[] args) 
	{
		// specify the number of nodes in the graph
		int numNodes = 20000;
		// specify the number of source nodes for multi source BFS
		int numSrcs = 20;
		Node nodeList[] = new Node[numNodes];
		LinkedList<Node> srcList = new LinkedList<Node>();
		
		Random rng = new Random(); // Ideally just create one instance globally
		// Note: use LinkedHashSet to maintain insertion order 
		Set<Integer> generated = new LinkedHashSet<Integer>(); 
		while (generated.size() < numSrcs) { 
			Integer next = rng.nextInt(numNodes);
			// As we're adding to a set, this will automatically do a containment check 
			generated.add(next); 
		}
		//Create the graph, add nodes, create edges between nodes
		Graph g=new Graph();
		// Create a number of nodes
		for(int i=0;i<numNodes;i++) {
//			char label = Integer.toString(i+1); 
			nodeList[i] = new Node(i+1);
			g.addNode(nodeList[i]);
		}
		g.setRootNode(nodeList[0]);
		
		Set<Node> connectedNodes = new LinkedHashSet<Node>();
		int edgeCount = 0;
		System.out.println("generating random edges");
		while (connectedNodes.size()< (numNodes)){
			Integer first = rng.nextInt(numNodes);
			Integer second = rng.nextInt(numNodes);
			if(first != second){
				g.connectNode(nodeList[first], nodeList[second]);
				connectedNodes.add(nodeList[first]);
				connectedNodes.add(nodeList[second]);
				edgeCount++;
			}
		}
		System.out.println("Generated random edges " + edgeCount);
		
		for (Iterator<Integer> it = generated.iterator(); it.hasNext(); ) {
	        Integer i = it.next();
	        srcList.add(nodeList[i]);
		}
		
		System.out.println("\nBFS Traversal of the graph is ------------->");
		long startTime = System.currentTimeMillis();
		g.bfs();
		long stopTime = System.currentTimeMillis();
		System.out.println("\nTime taken for BFS is:" + (stopTime-startTime));
		
		System.out.println("\nMulti Source BFS Traversal of the graph is ------------->");
		long startTime2 = System.currentTimeMillis();
		g.multibfs(srcList);
		long stopTime2 = System.currentTimeMillis();
		System.out.println("\nTime taken for MultiBFS is:" + (stopTime2-startTime2));
		
		System.out.println("\nDFS Traversal of the graph is ------------->");
		long startTime3 = System.currentTimeMillis();
		g.multibfs(srcList);
		long stopTime3 = System.currentTimeMillis();
		System.out.println("\nTime taken for DFS is:" + (stopTime3-startTime3));
	}
}