package cs3500.animator.model;

import java.util.List;
import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.shapes.IShape;

/**
 * Represents an animation created from a description.
 */
public interface IAnimator {
  /*
   * Structure:
   * - Needs to save a list of instructions over time.
   * - Stores all shapes involved
   * - Can get a description of what happens at a given tick
   * - Stores each independent Instruction and accesses them
   * - Implies need for an Instruction class
   */

  /**
   * Describes the animation as it will appear over time.
   * Description uses the following format:
   * 
   * <p>
   * shape X [type of shape]
   * motion X [Start: [tick] [position x y] [dimensions w h] [color r g b]]
   * [End: [tick] [position x y] [dimensions w h] [color r g b]]
   * </p>
   *
   * @return the string describing the motion.
   */
  public String description();

  /**
   * Adds the given shape to the animation, without any instructions yet.
   * 
   * @param newShape the shape to be added.
   */
  public void addShape(IShape newShape);

  /**
   * Removes a shape from the animation, deleting all instructions associated with the shape.
   * 
   * @param toRemove the shape to be removed
   */
  public void removeShape(IShape toRemove);

  /**
   * Get the full int array describing a shape at the given tick.
   * 
   * @param shape the shape to get the description of
   * @param tick the time to describe.
   * @return an int array describing the shape at that specific tick.
   */
  public int[] getDescriptionAt(IShape shape, int tick);

  /**
   * Adds the given instruction to the animation, acting upon the given shape.
   * If the instruction conflicts with an earlier instruction, attempts to reconcile them.
   * 
   * @param mod the shape the new instruction modifies.
   * @param newInst the instruction that modifies the given shape.
   */
  public void addInstruction(IShape mod, Instruction newInst);

  /**
   * Removes the instruction at the given shape's index from the animation.
   * 
   * @param mod the shape to modify.
   * @param index the index of the Instruction to remove.
   */
  public void removeInstruction(IShape mod, int index);

  /**
   * Gets the list of shapes in the animation.
   * 
   * @return the list of shape objects.
   */
  public List<IShape> getShapes();

  /**
   * Gets the list of instructions for the given shape.
   * 
   * @param shape the shape to get the instructions for.
   * @return the list of instructions.
   */
  public List<Instruction> getInstructions(IShape shape);
}
