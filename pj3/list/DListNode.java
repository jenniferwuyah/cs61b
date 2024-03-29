/* DListNode.java */

package list;

/**
 *  A DListNode is a mutable node in a DList (doubly-linked list).
 **/

public class DListNode extends ListNode {

  /**
   *  (inherited)  item references the item stored in the current node.
   *  (inherited)  myList references the List that contains this node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   **/

  protected DListNode prev;
  protected DListNode next;

  /**
   *  DListNode() constructor.
   *  @param i the item to store in the node.
   *  @param l the list this node is in.
   *  @param p the node previous to this node.
   *  @param n the node following this node.
   */
  DListNode(Object i, DList l, DListNode p, DListNode n) {
    item = i;
    myList = l;
    prev = p;
    next = n;
  }

  /**
   *  isValidNode returns true if this node is valid; false otherwise.
   *  An invalid node is represented by a `myList' field with the value null.
   *  Sentinel nodes are invalid, and nodes that don't belong to a list are
   *  also invalid.
   *
   *  @return true if this node is valid; false otherwise.
   *
   *  Running time: O(1).
   */
  public boolean isValidNode() {
    return myList != null;
  }

  /**
   *  next() returns the node following this node.  If this node is invalid,
   *  throws an exception.
   *
   *  @return the node following this node.
   *  @exception InvalidNodeException if this node is not valid.
   *
   *  Running time: O(1).
   */
  public DListNode next() {
    return next;
  }

  /**
   *  prev() returns the node preceding this node.  If this node is invalid,
   *  throws an exception.
   *
   *  @param node the node whose predecessor is sought.
   *  @return the node preceding this node.
   *  @exception InvalidNodeException if this node is not valid.
   *
   *  Running time: O(1).
   */
  public DListNode prev() {
    return prev;
  }

  /**
   *  insertAfter() inserts an item immediately following this node.  If this
   *  node is invalid, throws an exception.
   *
   *  @param item the item to be inserted.
   *  @exception InvalidNodeException if this node is not valid.
   *
   *  Running time: O(1).
   */
  public void insertAfter(Object item) {
    DListNode node = ((DList) myList).newNode(item, (DList) myList, prev.next, next);
    next.prev = node;
    next = node;
    myList.size++;
  }

  /**
   *  insertBefore() inserts an item immediately preceding this node.  If this
   *  node is invalid, throws an exception.
   *
   *  @param item the item to be inserted.
   *  @exception InvalidNodeException if this node is not valid.
   *
   *  Running time: O(1).
   */
  public void insertBefore(Object item) {
    DListNode node = ((DList) myList).newNode(item, (DList) myList, prev, next.prev);
    prev.next = node;
    prev = node;
    myList.size++;
  }

  /**
   *  remove() removes this node from its DList.  If this node is invalid,
   *  throws an exception.
   *
   *  @exception InvalidNodeException if this node is not valid.
   *
   *  Running time: O(1).
   */
  public void remove() {
    prev.next = next;
    next.prev = prev;
    myList.size--;
    // Make this node an invalid node, so it cannot be used to corrupt myList.
    myList = null;
    // Set other references to null to improve garbage collection.
    next = null;
    prev = null;
  }

}
