package cs3500.animator.shapes;

/**
 * Represents a shape type in an animation.
 */
public interface IShape {
  /**
   * Gets the name of the shape.
   * 
   * @return the name of the shape.
   */
  public String getName();

  /**
   * Gets the type of shape this is.
   * 
   * @return the type of the shape.
   */
  public String getType();
}
