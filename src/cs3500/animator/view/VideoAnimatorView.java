package cs3500.animator.view;

import cs3500.animator.model.Animator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.shapes.IShape;
import cs3500.animator.shapes.Rectangle;
import javax.swing.*;
import java.awt.*;
import java.io.Writer;

/**
 * VideoAnimatorView animates the Animator in a JFrame.
 */
public class VideoAnimatorView implements IAnimatorView {

  Animator model;
  private int waitTime;

  @Override
  public void setAnimation(IAnimator model) {
    this.model = (Animator) model;
  }

  @Override
  public void render() {
    JFrame frame = new JFrame("Animation");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(model.getWidth(), model.getHeight());
    MyPanel p = new MyPanel(this.model);
    int finalTime = 0;
    for (IShape s : model.getShapes()) {
      int lastTime =
          model.getInstructions(s).get(model.getInstructions(s).size() - 1).getDescription()[0];
      if (lastTime > finalTime) {
        finalTime = lastTime;
      }
    }
    JScrollPane scrollBar = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    frame.add(scrollBar, BorderLayout.CENTER);
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setSize(model.getWidth(), model.getHeight());
    for (int i = 0; i <= finalTime; i++) {
      p.currTick = i;
      try {
        Thread.sleep(waitTime);
      } catch (Exception e) {

      }
      // idk why this works but otherwise the code repaints again
      if (i == finalTime) {
        break;
      } else {
        p.repaint();
      }


    }

  }

  public static void main(String[] args) {
    VideoAnimatorView v = new VideoAnimatorView();
    Animator a = new Animator();
    Rectangle r = new Rectangle("a");
    a.addShape(r);
    a.addInstruction(r, new Instruction(new int[] {0, 100, 100, 75, 80, 0, 0, 0}));
    a.addInstruction(r, new Instruction(new int[] {5, 100, 100, 75, 100, 0, 0, 200}));
    a.addInstruction(r, new Instruction(new int[] {10, 100, 100, 75, 150, 0, 0, 255}));
    v.setAnimation(a);
    v.render();
  }

  @Override
  public void setTime(int msPerTick) {
    waitTime = msPerTick;
  }

  @Override
  public void setOutput(Writer output) {
    // Stub for visual view: no need for text out
  }
}
