package cs3500.animator.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.shapes.IShape;

/**
 * Represents an animation as a set of Instructions acting upon a set of Shapes.
 */
public class Animator implements IAnimator {
  // The set of instructions indexed by the shape they act upon.
  // List of instructions is always chronological.
  Map<IShape, List<Instruction>> instructions;
  private int top;
  private int right;
  private int width;
  private int height;

  /**
   * Creates a new Animator class from preset lists of shapes and instructions.
   * 
   * @param instructions the instructions that act upon the shapes.
   */
  public Animator(Map<IShape, List<Instruction>> instructions,
                  int top, int right, int width, int height) {
    this.instructions = instructions;
    this.top = top;
    this.right = right;
    this.width = width;
    this.height = height;
  }

  public int getTop() {
    return this.top;
  }

  public int getRight() {
    return this.right;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
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
    this.instructions = new LinkedHashMap<IShape, List<Instruction>>();
  }

  @Override
  public String description() {
    String description = "canvas " + this.top + " " + this.right
            + " " + this.width + " " + this.height;
    for (IShape s : this.instructions.keySet()) {
      if (description.length() != 0) {
        description += "\n";
      }
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
      s += String.format("%-3d", described[i]);
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
      int insertionTime = newInst.getDescription()[0];
      List<Instruction> l = instructions.get(mod);
      for (int i = 0; i < l.size(); i++) {
        int curTick = l.get(i).getDescription()[0];
        if (insertionTime == curTick) {
          throw new IllegalArgumentException("Instruction already exists at that tick");
        } else if (insertionTime < curTick) {
          l.add(i, newInst);
          return;
        }
      }
      l.add(newInst);
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
    // Find the instructions before and after the tick
    // Calculate the correct value
    int[] before = null;
    int[] after = null;
    List<Instruction> l = instructions.get(shape);

    if (tick < l.get(0).getDescription()[0]) {
      throw new IllegalArgumentException(
          "Shape does not exist yet at " + l.get(0).getDescription()[0]);
    }

    for (int i = 0; i < l.size(); i++) {
      int[] check = l.get(i).getDescription();
      if (check[0] > tick) {
        before = l.get(i - 1).getDescription();
        after = check;
        break;
      } else if (check[0] == tick) {
        return check;
      }
    }

    // If the tick is greater than the last instruction
    if (after == null) {
      throw new IllegalArgumentException("Time " + tick + " greater than animation length");
    }

    int tickDif = after[0] - before[0];
    int tickElapse = tick - before[0];
    int[] toReturn = new int[8];
    for (int i = 0; i < 8; i++) {
      toReturn[i] = before[i] + (after[i] - before[i]) / tickDif * tickElapse;
    }

    return toReturn;
  }

  @Override
  public void removeInstruction(IShape mod, int index) {
    if (this.instructions.containsKey(mod)) {
      this.instructions.get(mod).remove(index);
    } else {
      throw new IllegalArgumentException("Shape not found");
    }

  }

  @Override
  public List<IShape> getShapes() {
    ArrayList<IShape> toReturn = new ArrayList<IShape>();
    for (IShape shape : instructions.keySet()) {
      toReturn.add(shape);
    }
    return toReturn;
  }

  @Override
  public List<Instruction> getInstructions(IShape shape) {
    ArrayList<Instruction> toReturn = new ArrayList<Instruction>();
    for (Instruction i : instructions.get(shape)) {
      toReturn.add(i);
    }
    return toReturn;
  }
}
