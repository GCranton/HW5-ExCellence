import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.Test;
import cs3500.animator.model.Animator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.shapes.IShape;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.SVGAnimatorView;

public class ViewTests {
  @Test
  public void testRender() {
    IAnimator model = new Animator();
    FileWriter fw;
    try {
      fw = new FileWriter("testAnim.svg");
    } catch (IOException e) {
      throw new IllegalArgumentException("Writer fail");
    }
    BufferedWriter writer = new BufferedWriter(fw);
    IAnimatorView view = new SVGAnimatorView(writer, 10);
    IShape testRect = new Rectangle("R");
    Instruction first = new Instruction(new int[] {0, 10, 10, 20, 10, 0, 0, 100});
    Instruction last = new Instruction(new int[] {1000, 50, 50, 20, 10, 100, 0, 50});
    model.addShape(testRect);
    model.addInstruction(testRect, first);
    model.addInstruction(testRect, last);
    view.setAnimation(model);
    view.render();
    // TODO: Figure out how to assert this properly
  }
}
