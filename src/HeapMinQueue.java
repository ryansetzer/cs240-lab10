import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Implementation of the CS240 MinQueue interface using the Java PriorityQueue collection (which
 * uses a heap).
 * 
 * @author Nathan Sprague
 *
 * @version 11/33/2020
 */
public class HeapMinQueue<V> implements MinQueue<V> {

  private PriorityQueue<Entry> queue;

  public HeapMinQueue() {
    queue = new PriorityQueue<>();
  }

  /**
   * Build a HeapMinQueue from an existing collection of priorities and values.
   * 
   * @param priorities Array of double priorities
   * @param values Array of values, where the corresponding priority is stored in the corresponding
   *        index in the priorities array
   */
  public HeapMinQueue(double[] priorities, V[] values) {
    ArrayList<Entry> entries = new ArrayList<>(priorities.length);
    for (int i = 0; i < priorities.length; i++) {
      entries.add(new Entry(priorities[i], values[i]));
    }
    queue = new PriorityQueue<>(entries);
  }

  @Override
  public void clear() {
    queue.clear();
  }

  @Override
  public void insert(double priority, V value) {
    queue.add(new Entry(priority, value));
  }

  @Override
  public V removeMin() {
    Entry entry = queue.poll();
    return entry.getValue();
  }

  @Override
  public V min() {
    return queue.peek().getValue();
  }



  @Override
  public int size() {
    return queue.size();
  }

  /**
   * The Entry class stores a priority/value pair.
   */
  private class Entry implements Comparable<Entry> {

    private final double priority;
    private final V value;

    public Entry(double priority, V value) {
      this.priority = priority;
      this.value = value;
    }

    public V getValue() {
      return value;
    }

    /**
     * Compare according to priority.
     */
    @Override
    public int compareTo(Entry o) {
      if (priority < o.priority) {
        return -1;
      } else if (priority > o.priority) {
        return 1;
      } else {
        return 0;
      }
    }
  }


}
