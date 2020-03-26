package cs3500.animator.view;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.shapes.IShape;

/**
 * View that displays an Animation via SVG
 */
public class SVGAnimatorView implements IAnimatorView {
  private IAnimator model;
  private Writer writer;
  private double msPerTick;

  @Override
  public void setAnimation(IAnimator model) {
    this.model = model;
  }

  @Override
  public void render() {
    String toAppend = "<svg width=\"" + (model.getWidth() + model.getRight()) + "\" height=\""
        + (model.getHeight() + model.getTop())
        + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">";

    List<IShape> shapes = model.getShapes();

    for (IShape shape : shapes) {
      List<Instruction> instructions = model.getInstructions(shape);
      int[] startTick = instructions.get(0).getDescription();

      toAppend += "\n<";
      switch (shape.getType()) {
        case "Rectangle":
          toAppend += "rect id=\"" + shape.getName() + "\" x=\"" + startTick[1] + "\" y=\""
              + startTick[2] + "\" width=\"" + startTick[3] + "\" height=\"" + startTick[4]
              + "\" fill=\"rgb(" + startTick[5] + "," + startTick[6] + "," + startTick[7]
              + ")\" visibility=\"visible\" >\n";
          break;
        case "Ellipse":
          toAppend += "ellipse id=\"" + shape.getName() + "\" cx=\"" + startTick[1] + "\" cy=\""
              + startTick[2] + "\" rx=\"" + startTick[3] + "\" rx=\"" + startTick[4]
              + "\" fill=\"rgb(" + startTick[5] + "," + startTick[6] + "," + startTick[7]
              + ")\" visibility=\"visible\" >";
          break;
        default:
          throw new IllegalArgumentException("Invalid shape type: " + shape.getType());
      }

      // Find values that change and write down that change (Color is handled separately as it's
      // more complex)
      for (int i = 0; i < instructions.size() - 1; i++) {
        int[] start = instructions.get(i).getDescription();
        int[] end = instructions.get(i + 1).getDescription();
        for (int val = 1; val <= 4; val++) {
          if (start[val] != end[val]) {
            toAppend += "\n\t<animate attributeType=\"xml\" begin=\"" + start[0] * msPerTick
                + "ms\" dur=\"" + (end[0] - start[0]) * msPerTick + "ms\" attributeName=\"";
            toAppend += valName(shape, val) + "\" from=\"" + start[val] + "\" to=\"" + end[val]
                + "\" fill=\"freeze\" />";
          }

          // Color handling
          if (start[5] != end[5] || start[6] != end[6] || start[7] != end[7]) {
            toAppend +=
                "\n\t<animate attributeType=\"xml\" begin=\"" + start[0] * msPerTick + "ms\" dur=\""
                    + (end[0] - start[0]) * msPerTick + "ms\" attributeName=\"fill\" from=\"rgb("
                    + start[5] + "," + start[6] + "," + start[7] + ")\" to=\"rgb(" + end[5] + ","
                    + end[6] + "," + end[7] + ")\" fill=\"freeze\" />";
          }
        }
      }
      switch (shape.getType()) {
        case "Rectangle":
          toAppend += "\n</rect>";
          break;
        case "Ellipse":
          toAppend += "\n</ellipse>";
      }
    }
    toAppend += "\n</svg>";
    try {
      writer.append(toAppend);
      writer.flush();
    } catch (IOException e) {
      throw new IllegalArgumentException("Writer fail");
    }
  }

  private String valName(IShape shape, int val) {
    switch (val) {
      case 1:
        switch (shape.getType()) {
          case "Rectangle":
            return "x";
          case "Ellipse":
            return "cx";
        }
        break;
      case 2:
        switch (shape.getType()) {
          case "Rectangle":
            return "y";
          case "Ellipse":
            return "cy";
        }
        break;
      case 3:
        switch (shape.getType()) {
          case "Rectangle":
            return "width";
          case "Ellipse":
            return "rx";
        }
        break;
      case 4:
        switch (shape.getType()) {
          case "Rectangle":
            return "height";
          case "Ellipse":
            return "ry";
        }
        break;
    }
    throw new IllegalArgumentException("Bad shape or val");
  }

  @Override
  public void setTime(int msPerTick) {
    this.msPerTick = msPerTick;
  }

  @Override
  public void setOutput(Writer output) {
    this.writer = output;
  }
}
