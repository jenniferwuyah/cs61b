/* EdgeWeight.java */

import graph.*;

/**
 * The EdgeWeight class represents an edge object for Kruskal's algorithm.
 * An EdgeWeight object holds the two connecting vertices and its weight.
 */

public class EdgeWeight {

	Object o1, o2;
	int weight;

  /**
   *  EdgeWeight() constructor.
   *  sets the two vertices and the weight of this edge.
   *
   *  Running time: O(1).
   */
	public EdgeWeight(Object u, Object v, int weight) {
		this.o1 = u;
		this.o2 = v;
		this.weight = weight;
	}

  /**
   * hashCode() returns a hashCode equal to the sum of the hashCodes of each
   * of the two objects of the edge, so that the order of the objects will
   * not affect the hashCode.  Self-edges are treated differently:  we don't
   * add an object's hashCode to itself, since the result would always be even.
   * We add one to the hashCode so that a self-edge will not collide with the
   * object itself if vertices and edges are stored in the same hash table.
   */
	public int hashCode() {
		if (o1.equals(o2)) {
			return o1.hashCode() + 1;
		} else {
			return o1.hashCode() + o2.hashCode();
		}
	}

  /**
   * equals() returns true if this EdgeWeight represents the same unordered
   * edge objects as the parameter "o".  The direction of the edge does not
   * affect the equality test, so (u, v) is found to be equal to (v, u).
   */
  public boolean equals(Object o) {
    if (o instanceof EdgeWeight) {
      return ((o1.equals(((EdgeWeight) o).o1)) &&
              (o2.equals(((EdgeWeight) o).o2))) ||
             ((o1.equals(((EdgeWeight) o).o2)) &&
              (o2.equals(((EdgeWeight) o).o1)));
    } else {
      return false;
    }
  }
}