package cs3500.animator.shapes;

/**
 * Represents a rectangle to be animated.
 */
public class Rectangle extends AShape {

  /**
   * Creates a new Rectangle.
   * 
   * @param name the name of the Rectangle.
   */
  public Rectangle(String name) {
    super(name);
  }

  @Override
  public String getType() {
    return "Rectangle";
  }

}
