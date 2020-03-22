package cs3500.animator.view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.shapes.IShape;

/**
 * View that displays an Animation via SVG
 */
public class SVGAnimatorView implements IAnimatorView {
  IAnimator model;
  String fileName;

  /**
   * Construct a new SVGAnimatorView
   * 
   * @param fileName the name of the generated SVG file
   */
  public SVGAnimatorView(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public void setAnimation(IAnimator model) {
    this.model = model;
  }

  @Override
  public void render() {
    FileWriter fw;
    try {
      fw = new FileWriter(this.fileName + ".svg");
    } catch (IOException e) {
      throw new IllegalArgumentException("Failure to open given file");
    }
    BufferedWriter writer = new BufferedWriter(fw);
    String toAppend =
        "<svg width=\"700\" height=\"500\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n";

    List<IShape> shapes = model.getShapes();
    for (IShape shape : shapes) {
      toAppend += "<";
      switch (shape.getType()) {
        case "Rectangle":
          toAppend += "rect";
          break;
        case "Ellipse":
          toAppend += "ellipse";
          break;
        default:
          throw new IllegalArgumentException("Invalid shape type: " + shape.getType());
      }

      toAppend += " id=" + shape.getName() + " ";
      List<Instruction> instructions = model.getInstructions(shape);
    }
  }

}
