/* Kruskal.java */

import graph.*;
import set.*;
import dict.*;
import list.*;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

  /**
   * minSpanTree() returns a WUGraph that represents the minimum spanning tree
   * of the WUGraph g.  The original WUGraph g is NOT changed.
   */
  public static WUGraph minSpanTree(WUGraph g) {
  	WUGraph t = new WUGraph();
  	Object[] gVertices = g.getVertices();
  	EdgeWeight[] allEdges = new EdgeWeight[g.edgeCount()];
  	System.out.println("edgeCount: "+g.edgeCount());
  	HashTableChained edgeHash = new HashTableChained();
  	int count = 0;
  	for (Object vertex : gVertices) {
  		t.addVertex(vertex);
  		Neighbors neighbors = g.getNeighbors(vertex);
  		Object[] nList = neighbors.neighborList;
  		int[] wList = neighbors.weightList;
  		for (int i = 0; i<nList.length; i++) {
  			System.out.println("i = "+i);
  			EdgeWeight edge = new EdgeWeight(vertex, nList[i], wList[i]);
  			if (edgeHash.find(edge) == null) {
  				System.out.println("adding edge: "+ edgeHash.find(edge)+"  count: "+count);
  				edgeHash.insert(edge, edge);
	  			allEdges[count] = edge;
	  			count++;
  			}
  		}
  	}
  	mergeSort(allEdges);
  	// do part [4]
  	HashTableChained vertexDict = new HashTableChained();
  	for (int v = 0; v < gVertices.length; v++) {
  		vertexDict.insert(gVertices[v], v);
  	}

  	DisjointSets vSets = new DisjointSets(gVertices.length);
  	for (EdgeWeight edge : allEdges) {
  		int u = (int)(vertexDict.find(edge.o1).value());
  		int v = (int)(vertexDict.find(edge.o2).value());
  		if (vSets.find(u) != vSets.find(v)) {
  			t.addEdge(edge.o1, edge.o2, edge.weight);
  			vSets.union(u, v);
  		}
  	}
  	return t;
  }



  /**
   *  Mergesort algorithm.
   *  @param a an array of int items.
   **/
  public static void mergeSort(EdgeWeight[] a) {
    EdgeWeight[] tmpArray = new EdgeWeight[a.length];

    mergeSort(a, tmpArray, 0, a.length - 1);
  }

  /**
   *  Internal method that makes recursive calls.
   *  @param a an array of int items.
   *  @param tmpArray an array to place the merged result.
   *  @param left the left-most index of the subarray.
   *  @param right the right-most index of the subarray.
   **/
  private static void mergeSort(EdgeWeight[] a, EdgeWeight[] tmpArray, int left, int right) {
    if (left < right) {
      int center = (left + right) / 2;
      mergeSort(a, tmpArray, left, center);
      mergeSort(a, tmpArray, center + 1, right);
      merge(a, tmpArray, left, center + 1, right);
    }
  }

  /**
   *  Internal method that merges two sorted halves of a subarray.
   *  @param a an array of int items.
   *  @param tmpArray an array to place the merged result.
   *  @param leftPos the left-most index of the subarray.
   *  @param rightPos the index of the start of the second half.
   *  @param rightEnd the right-most index of the subarray.
   **/
  private static void merge(EdgeWeight[] a, EdgeWeight[] tmpArray, int leftPos, int rightPos,
                            int rightEnd) {
    int leftEnd = rightPos - 1;
    int tmpPos = leftPos;
    int numElements = rightEnd - leftPos + 1;

    // Main loop
    while (leftPos <= leftEnd && rightPos <= rightEnd) {
      if (a[leftPos].weight < a[rightPos].weight) {
        tmpArray[tmpPos++] = a[leftPos++];
      } else {
        tmpArray[tmpPos++] = a[rightPos++];
      }
    }
    while (leftPos <= leftEnd) {
      tmpArray[tmpPos++] = a[leftPos++];
    }
    while(rightPos <= rightEnd) {
      tmpArray[tmpPos++] = a[rightPos++];
    }
    // Copy TmpArray back
    for (int i = 0; i < numElements; i++, rightEnd--) {
      a[rightEnd] = tmpArray[rightEnd];
    }
  }

}