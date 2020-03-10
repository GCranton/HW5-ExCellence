package cs3500.animator.model.instruction;

/**
 * Represents an instruction within an Animation that affects a given Shape over time.
 */
public class Instruction {

  /*
   * Description numbers:
   * [0]: tick
   * [1]: position x
   * [2]: position y
   * [3]: dimension w
   * [4]: dimension h
   * [5]: color r
   * [6]: color g
   * [7]: color b
   */
  private int[] description;

  /**
   * Creates a new Instruction from a given shape and set of descriptions.
   * 
   * @param description the state of the shape at the tick.
   */
  public Instruction(int[] description) {
    this.checkDescriptionValid(description);
    this.description = description;
  }

  /**
   * Gets the array of variables.
   * 
   * @return description, the array of variables
   */
  public int[] getDescription() {
    return this.description;
  }

  private void checkDescriptionValid(int[] description) {
    if (description[0] < 0) {
      throw new IllegalArgumentException("Tick can't be negative.");
    }
    if (description[3] < 0 || description[4] < 0) {
      throw new IllegalArgumentException("Dimensions can't be negative.");
    }
    if (description[5] < 0 || description[5] > 255 || description[6] < 0 || description[6] > 255
        || description[7] < 0 || description[7] > 255) {
      throw new IllegalArgumentException("Color out of bounds.");
    }
  }

  /**
   * Change the description of the Instruction to something else.
   * 
   * @param description the new description
   */
  public void setDescription(int[] description) {
    this.checkDescriptionValid(description);
    this.description = description;
  }

}
