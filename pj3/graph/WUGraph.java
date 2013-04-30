/* WUGraph.java */

package graph;

import dict.*;
import list.*;

/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {

  public HashTableChained vertexTable;
  public HashTableChained edgeTable;
  public DList vertexList;
  public int vertexCount = 0;
  public int edgeCount = 0; 

  /**
   * WUGraph() constructs a graph having no vertices or edges.
   *
   * Running time:  O(1).
   */
  public WUGraph() {
    vertexTable = new HashTableChained(10);
    edgeTable = new HashTableChained(10);
    vertexList = new DList();
  }

  /**
   * vertexCount() returns the number of vertices in the graph.
   *
   * Running time:  O(1).
   */
  public int vertexCount() {
    return vertexCount;
  }

  /**
   * edgeCount() returns the number of edges in the graph.
   *
   * Running time:  O(1).
   */
  public int edgeCount() {
    return edgeCount;
  }

  /**
   * getVertices() returns an array containing all the objects that serve
   * as vertices of the graph.  The array's length is exactly equal to the
   * number of vertices.  If the graph has no vertices, the array has length
   * zero.
   *
   * (NOTE:  Do not return any internal data structure you use to represent
   * vertices!  Return only the same objects that were provided by the
   * calling application in calls to addVertex().)
   *
   * Running time:  O(|V|).
   */
  public Object[] getVertices() {
    Object[] vertexArray = new Object[vertexCount];
    DListNode v = vertexList.front();
    int i = 0;
    while (v.isValidNode()) {
      vertexArray[i] = ((Vertex)v.item()).vert;
      i++;
      v = v.next();
    }
    return vertexArray;
  }

  /**
   * addVertex() adds a vertex (with no incident edges) to the graph.  The
   * vertex's "name" is the object provided as the parameter "vertex".
   * If this object is already a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(1).
   */
  public void addVertex(Object vertex) {
    if (vertexTable.find(vertex) != null) {
      return;
    }
    Vertex v = new Vertex(vertex);
    vertexList.insertBack(v);
    vertexTable.insert(vertex, v);
    vertexCount++;
  }

  /**
   * removeVertex() removes a vertex from the graph.  All edges incident on the
   * deleted vertex are removed as well.  If the parameter "vertex" does not
   * represent a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public void removeVertex(Object vertex){
    if (!isVertex(vertex)) {
      return;
    }
    Vertex v = vertexTable.find(vertex).value();
    DList allEdges = v.edges;
    DListNode edge = allEdges.front();
    while (edge.isValidNode()) {
      
    }

  }

  /**
   * isVertex() returns true if the parameter "vertex" represents a vertex of
   * the graph.
   *
   * Running time:  O(1).
   */
  public boolean isVertex(Object vertex) {
    if (vertexTable.find(vertex) == null) {
      return false;
    }
    return true;
  }

  /**
   * degree() returns the degree of a vertex.  Self-edges add only one to the
   * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
   * of the graph, zero is returned.
   *
   * Running time:  O(1).
   */
  public int degree(Object vertex) {
    if (!isVertex(vertex)) {
      return 0;
    }
    Vertex v = (Vertex) vertexTable.find(vertex).value();
    return v.degree;
  }

  /**
   * getNeighbors() returns a new Neighbors object referencing two arrays.  The
   * Neighbors.neighborList array contains each object that is connected to the
   * input object by an edge.  The Neighbors.weightList array contains the
   * weights of the corresponding edges.  The length of both arrays is equal to
   * the number of edges incident on the input vertex.  If the vertex has
   * degree zero, or if the parameter "vertex" does not represent a vertex of
   * the graph, null is returned (instead of a Neighbors object).
   *
   * The returned Neighbors object, and the two arrays, are both newly created.
   * No previously existing Neighbors object or array is changed.
   *
   * (NOTE:  In the neighborList array, do not return any internal data
   * structure you use to represent vertices!  Return only the same objects
   * that were provided by the calling application in calls to addVertex().)
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public Neighbors getNeighbors(Object vertex);

  /**
   * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
   * u and v does not represent a vertex of the graph, the graph is unchanged.
   * The edge is assigned a weight of "weight".  If the edge is already
   * contained in the graph, the weight is updated to reflect the new value.
   * Self-edges (where u == v) are allowed.
   *
   * Running time:  O(1).
   */
  public void addEdge(Object u, Object v, int weight) {
    if (!(isVertex(u) && isVertex(v))) {
      return;
    }
    VertexPair pair = new VertexPair(u, v);
    if (isEdge(u, v)) {
      Edge e = edgeTable.find(pair);
      e1.weight = weight;
      e1.partner.weight = weight;
      return;
    }
    Edge e1 = new Edge(u, v, weight);
    Vertex v1 = vertexTable.find(u).value();
    if (u == v) {
      v1.addEdge(e1);
      e1.position = v1.edges.back();
    } else {
      Edge e2 = new Edge(v, u, weight);
      Vertex v2 = vertexTable.find(v).value();
      e1.partner = e2;
      e2.partner = e1;
      v1.addEdge(e1);
      v2.addEdge(e2);
      e1.position = v1.edges.back();
      e2.position = v2.edges.back();
    }
    edgeTable.insert(pair, e1);
    edgeCount++;
  }

  /**
   * removeEdge() removes an edge (u, v) from the graph.  If either of the
   * parameters u and v does not represent a vertex of the graph, the graph
   * is unchanged.  If (u, v) is not an edge of the graph, the graph is
   * unchanged.
   *
   * Running time:  O(1).
   */
  public void removeEdge(Object u, Object v) {
    if (!(isVertex(u) && isVertex(v))) {
      return;
    }
    if (!isEdge(u, v)) {
      return;
    }
    VertexPair pair = new VertexPair(u, v)
    Edge e1 = edgeTable.find(pair).value();
    if (e1.partner != null) {
      vertexTable.find(v).value().degree--;
      e1.partner.position.remove();
    }
    vertexTable.find(u).value().degree--;
    e1.position.remove();
    edgeTable.remove(pair);
  }

  /**
   * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
   * if (u, v) is not an edge (including the case where either of the
   * parameters u and v does not represent a vertex of the graph).
   *
   * Running time:  O(1).
   */
  public boolean isEdge(Object u, Object v) {
    if (edgeTable.find(new VertexPair(u, v)) == null) {
      return false;
    }
    return true;
  }

  /**
   * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
   * an edge (including the case where either of the parameters u and v does
   * not represent a vertex of the graph).
   *
   * (NOTE:  A well-behaved application should try to avoid calling this
   * method for an edge that is not in the graph, and should certainly not
   * treat the result as if it actually represents an edge with weight zero.
   * However, some sort of default response is necessary for missing edges,
   * so we return zero.  An exception would be more appropriate, but
   * also more annoying.)
   *
   * Running time:  O(1).
   */
  public int weight(Object u, Object v) {
    if (!isEdge(u, v)) {
      return 0;
    }
    Edge e = edgeTable.find(new VertexPair(u, v)).value();
    return e.weight;
  }
}
