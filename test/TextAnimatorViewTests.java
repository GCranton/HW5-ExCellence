import cs3500.animator.model.Animator;
import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.shapes.Ellipse;
import cs3500.animator.shapes.IShape;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.view.TextAnimatorView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;

/**
 * Tests for TextAnimatorView.
 */
public class TextAnimatorViewTests {

  @Test
  public void testRender() {
    TextAnimatorView t = new TextAnimatorView();
    StringWriter writer = new StringWriter();
    t.setOutput(new BufferedWriter(writer));
    Animator a = new Animator();
    Rectangle rect = new Rectangle("rect");
    a.addShape(rect);
    a.addInstruction(rect, new Instruction(new int[] {0, 1, 2, 3, 4, 5, 6, 7}));
    a.addInstruction(rect, new Instruction(new int[] {5, 1, 2, 3, 4, 5, 6, 7}));
    Ellipse ell = new Ellipse("ell");
    a.addShape(ell);
    a.addInstruction(ell, new Instruction(new int[] {5, 1, 2, 3, 4, 5, 6, 7}));
    a.addInstruction(ell, new Instruction(new int[] {10, 1, 2, 3, 4, 5, 6, 7}));
    t.setAnimation(a);
    t.render();
    String s = writer.toString();
    String[] printText = s.split("\n");
    assertEquals("canvas 0 0 0 0", printText[0]);
  }

  @Test(expected = IllegalStateException.class)
  public void testRenderWithoutSet() {
    TextAnimatorView t = new TextAnimatorView();
    StringWriter writer = new StringWriter();
    t.setOutput(new BufferedWriter(writer));
    t.render();
    String s = writer.toString();
    assertEquals(s, "No animation set yet.");
  }
}
