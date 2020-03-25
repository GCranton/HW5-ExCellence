package cs3500.animator.shapes;

/**
 * Class that represents shapes.
 */
public abstract class AShape implements IShape {
  private final String name;

  /**
   * Creates a new shape.
   * 
   * @param name the name of the shape.
   */
  public AShape(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
