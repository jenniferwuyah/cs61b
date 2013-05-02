/* Edge.java */

package graph;

import list.*;

/**
 * The Edge class represents an edge object for WUGraph.
 * An Edge object includes a VertexPair field holding the connecting vertices.
 * It also holds references to a partner Edge, its weight,
 * and position in DList of the Vertex that holds it.
 */

class Edge {

	VertexPair pair;
	Edge partner;
	int weight;
	DListNode position;

	/**
	 *	Edge() constructor.
	 *  assigns the VertexPair (u, v) and the weight of this edge.
	 *  partner field is null in default.
	 *
	 *	Running time: O(1).
	 */
	protected Edge(Object u, Object v, int weight) {
		pair = new VertexPair(u, v);
		this.weight = weight;
	}

	/**
	 *	destination() gives the vertex on other end of the edge
	 *	@param u: vertex on one of the ends of the edge.
	 *
	 *	Running time: O(1);
	 */
	protected Object destination(Object u) {
		if (pair.object1.equals(u)) {
			return pair.object2;
		} else {
			return pair.object1;
		}
	}

	/**
	 *  remove() removes the edge (and partner) from the DList that it's in.
	 *
	 *	Running time: O(1).
	 */
	protected void remove() {
		if (this.partner != null) {
	      this.partner.position.remove();
	    }
	    this.position.remove();
	}
}