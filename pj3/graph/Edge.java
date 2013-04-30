/* Edge.java */

package graph;

import list.*;

public class Edge {

	VertexPair pair;
	Edge partner;
	int weight;
	DListNode position;

	public Edge(Object u, Object v, int weight) {
		pair = new VertexPair(u, v);
		self.weight = weight;
	}


}