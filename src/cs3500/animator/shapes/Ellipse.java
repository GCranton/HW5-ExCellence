package cs3500.animator.shapes;

/**
 * Represents an ellipse to be animated.
 */
public class Ellipse extends AShape {

  /**
   * Constructs a new Ellipse
   * 
   * @param name the name of the Ellipse.
   */
  public Ellipse(String name) {
    super(name);
  }

  @Override
  public String getType() {
    return "Ellipse";
  }

}
