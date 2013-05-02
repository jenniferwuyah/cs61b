/* Vertex.java */

package graph;

import list.*;

/**
 * The Vertex class represents a vertex object for WUGraph.
 * A Vertex object stores the object, its degree, 
 * and a DList of edges going out of the vertex.
 * Also holds its position in DList vertexList in a WUGraph.
 */

class Vertex {

	Object vert;
	int degree;
	DList edges;
	DListNode position;

	/**
	 *	Vertex() constructor creates a vertex object with no edges.
	 *
	 *	Running time: O(1).
	 */
	protected Vertex(Object obj) {
		vert = obj;
		degree = 0;
		edges = new DList();
	}

	/**
	 *	addEdge() adds an edge to this vertex, also sets the position of Edge object
	 *
	 *	Running time: O(1).
	 */
	protected void addEdge(Edge e){
		edges.insertBack(e);
		e.position = this.edges.back();
		degree++;
	}

	/**
	 *	remove() remove this vertex from vertexList
	 *
	 *	Running time: O(1).
	 */
	protected void remove() {
	    this.position.remove();
	}
}