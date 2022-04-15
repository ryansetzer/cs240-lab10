
/**
 * Minimal Priority Queue ADT with double priorities.
 * 
 * @author Nathan Sprague
 * 
 * @version 4/3/2019
 *
 */
public interface MinQueue<V> {

  /**
   * Re-initialize.
   */
  void clear();

  /**
   * Insert a record.
   * 
   * @param priority the priority for the record being inserted
   * @param value the record being inserted
   */
  void insert(double priority, V value);


  /**
   * Remove and return the record with the minimum priority.
   */
  V removeMin();

  /**
   * Return the record with the minimum priority.
   */
  V min();
  
  /**
   * Return the number of records.
   */
  int size();
}
