package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.shapes.IShape;

/**
 * Represents an animation as a set of Instructions acting upon a set of Shapes.
 */
public class Animator implements IAnimator {
  // The set of instructions indexed by the shape they act upon.
  Map<IShape, List<Instruction>> instructions;

  /**
   * Creates a new Animator class from preset lists of shapes and instructions.
   * 
   * @param instructions the instructions that act upon the shapes.
   */
  public Animator(Map<IShape, List<Instruction>> instructions) {
    this.instructions = instructions;
  }

  /**
   * Generates a new Animator from a textual description.
   * 
   * @param description the description of the animation.
   */
  public Animator(String description) {
    // As user description is not part of this phase of the project, we won't implement this yet
  }

  /**
   * Creates an empty Animator.
   */
  public Animator() {
    this.instructions = new HashMap<IShape, List<Instruction>>();
  }

  @Override
  public String description() {
    String description = "";
    for (IShape s : this.instructions.keySet()) {
      description += "shape " + s.getName() + " " + s.getType();
      description += instructionBlockString(s);
    }
    return description;
  }

  private String instructionBlockString(IShape s) {
    String description = "";
    for (int i = 1; i < this.instructions.get(s).size(); i++) {
      int[] described = this.instructions.get(s).get(i - 1).getDescription();
      description += "\n";
      description += "motion " + s.getName() + " ";
      description += descriptionStringAddition(described);
      description += "    ";
      described = this.instructions.get(s).get(i).getDescription();
      description += descriptionStringAddition(described);
    }
    return description;
  }

  private String descriptionStringAddition(int[] described) {
    String s = "";
    for (int i = 0; i < described.length; i++) {
      s += String.format("%-2d", described[i]);
      if (i != described.length - 1) {
        s += " ";
      }
    }
    return s;
  }

  @Override
  public void addShape(IShape newShape) {
    this.instructions.put(newShape, new ArrayList<Instruction>());
  }

  @Override
  public void addInstruction(IShape mod, Instruction newInst) {
    if (this.instructions.containsKey(mod)) {
      this.instructions.get(mod).add(newInst);
      // TODO: Check for collision
    } else {
      throw new IllegalArgumentException("Shape not found");
    }
  }

  @Override
  public void removeShape(IShape toRemove) {
    this.instructions.remove(toRemove);
  }

  @Override
  public int[] getDescriptionAt(IShape shape, int tick) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void removeInstruction(IShape mod, int index) {
    if (this.instructions.containsKey(mod)) {
      this.instructions.get(mod).remove(index);
    } else {
      throw new IllegalArgumentException("Shape not found");
    }

  }

  // /**
  // * Get the given shape parameter at the requested time.
  // *
  // * @param tick the time within the time window of the Instruction.
  // * @param paramIndex which parameter to access.
  // * @return the value of the parameter at the given time.
  // */
  // public int getParameterAt(int tick, int paramIndex) {
  // // TODO: Finish this implementation, using either the switch or a general implementation.
  // switch (paramIndex) {
  // case 0:
  // // Access the tick @ tick
  // break;
  // case 1:
  // // Access the position x
  // break;
  // case 2:
  // // Access the position y
  // break;
  // case 3:
  // // Access the shape w
  // break;
  // case 4:
  // // Access the shape h
  // break;
  // case 5:
  // // Access the color r
  // break;
  // case 6:
  // // Access the color g
  // break;
  // case 7:
  // // Access the color b
  // break;
  // default:
  // throw new IllegalArgumentException("Access index is out of bounds");
  // }
  // return 0;
  // }
}
