/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  public DList[] buckets;
  public int bucketSize;
  public int size = 0;
  private int a;
  private int b;
  private int p;



  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
    bucketSize = closestPrime(sizeEstimate);
    buckets = new DList[bucketSize];
    for (int i=0; i < bucketSize; i++) {
      buckets[i] = new DList();
    }
    p = closestPrime(bucketSize*2);
    a = closestPrime(bucketSize+1);
    b = closestPrime(a+1);
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    this(101);
  }

  /**
   *  Helper functions: finding closest prime greater or equal to integer n
   *  isPrime() check if integer n is prime
   *  closestPrime() finds the closest prime to n
   **/

  private boolean isPrime(int n) {
    if (n%2 == 0) {
      return false;
    } else {
      for (int i = 3; i <= Math.sqrt(n); i+=2) {
        if (n%i == 0) {
          return false;
        }
      }
      return true;
    }
  }

  private int closestPrime(int n) {
    if (isPrime(n)) {
      return n;
    } else {
      return (closestPrime(n+1));
    }
  }

  /**
   *  Helper function: find (a mod m)
   **/
  private int mod(int a, int m) {
    if (a % m < 0) {
      return ((a % m) + m);
    } else {
      return (a % m);
    }
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.

    return mod(mod((a*code+b), p), (bucketSize-1));
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return (size == 0);
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    if (size/bucketSize > 0.75) {
      HashTableChained largerTable = new HashTableChained(1.25*bucketSize);
      for (int i = 0; i < bucketSize; i++){
        DListNode head = buckets[i].front();
        while (head.isValid) {
          Entry inserting = (Entry) head.item();
          largerTable.insert(inserting.key(), inserting.value());
        }
      }
    }
    Entry newEntry = new Entry();
    newEntry.key = key;
    newEntry.value = value;
    buckets[compFunction(key.hashCode())].insertBack(newEntry);
    size++;
    return newEntry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    DList bucket = buckets[compFunction(key.hashCode())];
    DListNode head = bucket.front();

    while (head !=null) { // go through all items inside the dlist 
      if (head.item() == null) {
        return null;
      }
      if (((Entry) head.item()).key().equals(key)) {
        return (Entry) head.item();
      }
      head = head.next();
    }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    DList bucket = buckets[compFunction(key.hashCode())];
    DListNode head = bucket.front();
    boolean found = false;
    Entry removed = new Entry();

    while (head !=null) { // go through all items inside the slist 
      if (head.item() == null) {
        break;
      }
      if (((Entry) head.item()).key().equals(key)) {
        removed = (Entry) head.item();
        head.remove(); 
        size--;
        found = true;
        break;
      }
      head = head.next();
    }
    if (size/bucketSize < 0.5) {
      HashTableChained largerTable = new HashTableChained(0.75*bucketSize);
      for (int i = 0; i < bucketSize; i++){
        DListNode head = buckets[i].front();
        while (head.isValid) {
          Entry inserting = (Entry) head.item();
          largerTable.insert(inserting.key(), inserting.value());
        }
      }
    }
    if (found) {
      return removed;
    }
    return null;
  }

  /**a
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    for (int i=0; i < bucketSize; i++) {
      buckets[i] = new DList();
      size = 0;
    }


  }

  /**
   *  checkCollision() prints out the number of entries in each bucket
   *  and prints out the total collisions
   */
  public void checkCollision() {
    int coll = 0;
    for (int i=0; i < bucketSize; i++) {
      if (buckets[i].length() > 1) {
        coll+= (buckets[i].length()-1);      
      }
      System.out.println("Bucket "+i+" has "+buckets[i].length()+" entries.");
    }
    System.out.println("Total collisions: "+ coll);
  }

}

