package cs3500.animator.view;

import cs3500.animator.model.Animator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.shapes.Ellipse;
import cs3500.animator.shapes.IShape;
import cs3500.animator.shapes.Rectangle;

public class AnimatorBuilderImp implements AnimationBuilder<IAnimator> {

  IAnimator a = new Animator();

  @Override
  public IAnimator build() {
    return a;
  }

  @Override
  public AnimationBuilder<IAnimator> setBounds(int x, int y, int width, int height) {
    this.a.setWidth(width);
    this.a.setHeight(height);
    this.a.setTop(y);
    this.a.setRight(x);
    return this;
  }

  @Override
  public AnimationBuilder<IAnimator> declareShape(String name, String type) {
    switch (type) {
      case "rectangle":
        a.addShape(new Rectangle(name));
        break;
      case "ellipse":
        a.addShape(new Ellipse(name));
        break;
      default:
        throw new IllegalArgumentException("Shape type cannot be: " + type);
    }
    return this;
  }

  @Override
  public AnimationBuilder<IAnimator> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
      int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    for (IShape shape : a.getShapes()) {
      if (shape.getName().equals(name)) {
        Instruction beforeInstruction = new Instruction(new int[] {t1, x1, y1, w1, h1, r1, g1, b1});
        Instruction afterInstruction = new Instruction(new int[] {t2, x2, y2, w2, h2, r2, g2, b2});
        if (this.hasInstruction(shape, t1)) {
          a.addInstruction(shape, beforeInstruction);
        }
        if (this.hasInstruction(shape, t2)) {
          a.addInstruction(shape, afterInstruction);
        }
      }
    }
    return this;
  }

  private boolean hasInstruction(IShape shape, int tick) {
    for (Instruction i : a.getInstructions(shape)) {
      if (i.getDescription()[0] == tick) {
        return false;
      }
    }
    return true;
  }

  @Override
  public AnimationBuilder<IAnimator> addKeyframe(String name, int t, int x, int y, int w, int h,
      int r, int g, int b) {
    for (IShape shape : a.getShapes()) {
      if (shape.getName().equals(name)) {
        Instruction keyframe = new Instruction(new int[] {t, x, y, w, h, r, g, b});
        if (hasInstruction(shape, t)) {
          a.addInstruction(shape, keyframe);
        }
      }
    }
    return this;
  }
}
