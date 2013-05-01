/* EdgeObj.java */

import list.*;

public class Edge {

	VertexPair pair;
	Edge partner;
	int weight;
	DListNode position;

	public EdgeObj(Object u, Object v, int weight) {
		pair = new VertexPair(u, v);
		this.weight = weight;
	}

	public Object destination(Object u) {
		if (pair.object1 == u) {
			return pair.object2;
		} else {
			return pair.object1;
		}
	}

	public void remove() {
		if (this.partner != null) {
	      this.partner.position.remove();
	    }
	    this.position.remove();
	}
}