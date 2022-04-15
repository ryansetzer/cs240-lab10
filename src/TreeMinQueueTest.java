import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Very minimal tests for the TreeMinQueue class.
 * 
 * @author Nathan Sprague
 * @version 11/13/2020
 *
 */
class TreeMinQueueTest {

  @Test
  void testSizeUpdatedCorrectly() {
    MinQueue<String> queue = new TreeMinQueue<>();
    assertEquals(0, queue.size());
    queue.insert(1.0, "A");
    assertEquals(1, queue.size());
    queue.insert(2.0, "B");
    assertEquals(2, queue.size());
    queue.removeMin();
    assertEquals(1, queue.size());
    queue.removeMin();
    assertEquals(0, queue.size());
  }


  @Test
  void testMinAndRemoveMin() {
    MinQueue<String> queue = new TreeMinQueue<>();
    queue.insert(1.0, "A");
    queue.insert(2.0, "B");
    queue.insert(3.0, "C");
    queue.insert(10.0, "D");
    queue.insert(9.0, "E");
    queue.insert(8.0, "F");
    queue.insert(7.0, "G");

    String[] correctOrder = new String[] {"A", "B", "C", "G", "F", "E", "D"};

    for (String letter : correctOrder) {
      assertEquals(letter, queue.min());
      assertEquals(letter, queue.removeMin());
    }

  }
  
  @Test
  void testBuild() {
    double[] priorities = {1.0, 2.0, 3.0, 10.0, 9.0, 8.0, 7.0};
    String[] values = {"A", "B", "C", "D", "E", "F", "G"};
    MinQueue<String> queue = new TreeMinQueue<>(priorities, values);

    String[] correctOrder = new String[] {"A", "B", "C", "G", "F", "E", "D"};
    int expectedSize = priorities.length;
    for (String letter : correctOrder) {
      assertEquals(expectedSize, queue.size());
      assertEquals(letter, queue.min());
      assertEquals(letter, queue.removeMin());
      expectedSize--;
    }

  }
  

  @Test
  void testClear() {
    MinQueue<String> queue = new TreeMinQueue<>();
    queue.insert(1.0, "A");
    queue.clear();
    assertEquals(0, queue.size());
  }



}
