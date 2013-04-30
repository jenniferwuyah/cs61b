/* Vertex.java */

package graph;

import list.*;

public class Vertex {

	Object vert;
	int degree;
	DList edges;
	DListNode position;

	public Vertex(Object obj) {
		vert = obj;
		degree = 0;
		edges = new DList();
	}

	public void addEdge(Edge e){
		edges.insertBack(e);
		degree++;
	}


}