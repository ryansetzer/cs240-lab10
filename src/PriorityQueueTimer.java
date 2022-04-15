import java.util.ArrayList;
import java.util.Collections;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Timing comparisons for two implementations of the CS240 MinQueue ADT.
 * 
 * @author Nathan Sprague
 * @version 11/13/2020
 *
 */
public class PriorityQueueTimer {

  /**
   * Run the timings.
   * 
   * @param args Not used.
   */
  public static void main(String[] args) {
    ArrayList<Double> priorities = new ArrayList<>();
    for (double i = 0.0; i < 5000000.0; i++) {
      priorities.add(i);
    }
    Collections.shuffle(priorities);

    MinQueue<String> heapQueue = new HeapMinQueue<>();
    MinQueue<String> treeQueue = new TreeMinQueue<>();

    System.out.println("Timing build operations:");
    timeHeapBuilds(priorities);
    timeTreeBuilds(priorities);

    System.out.println("\nTiming add operations:");
    timeInserts(heapQueue, priorities);
    timeInserts(treeQueue, priorities);


    System.out.println("\nTiming peek operations:");
    Consumer<MinQueue<String>> peeks = (q) -> {
      for (int i = 0; i < q.size(); i++) {
        q.min();
      }
    };
    timeOperations(peeks, heapQueue);
    timeOperations(peeks, treeQueue);


    System.out.println("\nTiming remove operations:");
    Consumer<MinQueue<String>> removes = (q) -> {
      while (q.size() > 0) {
        q.removeMin();
      }
    };
    timeOperations(removes, heapQueue);
    timeOperations(removes, treeQueue);

  }


  /**
   * Print timing information for a series of build operations.
   * 
   * @param priorities An ArrayList of priority values.
   */
  public static void timeTreeBuilds(ArrayList<Double> priorities) {
    System.out.println("\n    TreeMinQueue");
    double[] copy1 = priorities.stream().mapToDouble(Double::doubleValue).toArray();
    Double[] values = new Double[priorities.size()];

    long start = java.lang.System.nanoTime();

    MinQueue<Double> queue = new TreeMinQueue<Double>(copy1, values);

    long total = java.lang.System.nanoTime() - start;
    System.out.format("        Total:    %.5f s\n", total / 1000000000.0);
    System.out.format("        Per item: %.5f \u00B5s\n", total / 1000.0 / queue.size());
  }

  /**
   * Print timing information for a series of build operations.
   * 
   * @param priorities An ArrayList of priority values.
   */
  public static void timeHeapBuilds(ArrayList<Double> priorities) {
    System.out.println("\n    HeapMinQueue");
    double[] copy1 = priorities.stream().mapToDouble(Double::doubleValue).toArray();
    Double[] values = new Double[priorities.size()];

    long start = java.lang.System.nanoTime();

    MinQueue<Double> queue = new HeapMinQueue<Double>(copy1, values);

    long total = java.lang.System.nanoTime() - start;
    System.out.format("        Total:    %.5f s\n", total / 1000000000.0);
    System.out.format("        Per item: %.5f \u00B5s\n", total / 1000.0 / queue.size());
  }

  /**
   * Print timing information for a series of insert operations.
   * 
   * @param queue The MinQueue
   * @param priorities An ArrayList of priority values.
   */
  public static void timeInserts(MinQueue<String> queue, ArrayList<Double> priorities) {
    System.out.println("\n    " + queue.getClass().getName());
    long start = java.lang.System.nanoTime();

    for (Double num : priorities) {
      queue.insert(num, "" + num);
    }

    long total = java.lang.System.nanoTime() - start;
    System.out.format("        Total:    %.5f s\n", total / 1000000000.0);
    System.out.format("        Per item: %.5f \u00B5s\n", total / 1000.0 / queue.size());
  }

  /**
   * Print timing information for arbitrary operations on a MinQueue.
   * 
   * @param operation A Consumer method that takes a MinQueue and performs some operations
   * @param queue A MinQueue
   */
  public static void timeOperations(Consumer<MinQueue<String>> operation, MinQueue<String> queue) {

    System.out.println("\n    " + queue.getClass().getName());

    long start = java.lang.System.nanoTime();
    int size = queue.size();

    operation.accept(queue);

    long total = java.lang.System.nanoTime() - start;
    System.out.format("        Total:    %.5f s\n", total / 1000000000.0);
    System.out.format("        Per item: %.5f \u00B5s\n", total / 1000.0 / size);
  }



}
