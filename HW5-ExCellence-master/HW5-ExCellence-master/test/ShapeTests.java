import static org.junit.Assert.assertEquals;
import org.junit.Test;
import cs3500.animator.shapes.Ellipse;
import cs3500.animator.shapes.IShape;
import cs3500.animator.shapes.Rectangle;

/**
 * Test class for Shapes.
 * Currently only tests getters, but there may be more complex functionality in the future.
 */
public class ShapeTests {
  @Test
  public void testGetTypeRectangle() {
    IShape rectangle = new Rectangle("R");

    assertEquals("Rectangle", rectangle.getType());
  }

  @Test
  public void testGetTypeEllipse() {
    IShape ellipse = new Ellipse("E");

    assertEquals("Ellipse", ellipse.getType());
  }

  @Test
  public void testGetName() {
    IShape rect = new Rectangle("Rect");

    assertEquals("Rect", rect.getName());
  }
}
