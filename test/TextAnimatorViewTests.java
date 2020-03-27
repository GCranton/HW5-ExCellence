import cs3500.animator.model.Animator;
import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.shapes.Ellipse;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.view.TextAnimatorView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Tests for TextAnimatorView.
 */
public class TextAnimatorViewTests {

  //source: https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  public void testRender() {
    TextAnimatorView t = new TextAnimatorView();
    Animator a = new Animator();
    Rectangle rect = new Rectangle("rect");
    a.addShape(rect);
    a.addInstruction(rect, new Instruction(new int[]{0, 1, 2, 3, 4, 5, 6, 7}));
    a.addInstruction(rect, new Instruction(new int[]{5, 1, 2, 3, 4, 5, 6, 7}));
    Ellipse ell = new Ellipse("ell");
    a.addShape(ell);
    a.addInstruction(ell, new Instruction(new int[]{5, 1, 2, 3, 4, 5, 6, 7}));
    a.addInstruction(ell, new Instruction(new int[]{10, 1, 2, 3, 4, 5, 6, 7}));
    t.setAnimation(a);
    t.render();
    String s = outContent.toString();
    String[] printText = s.split("\n");
    assertEquals(printText[0], "canvas 0 0 0 0");
  }

  @Test(expected = NullPointerException.class)
  public void testRenderWithoutSet() {
    TextAnimatorView t = new TextAnimatorView();
    t.render();
    String s = outContent.toString();
    assertEquals(s, "No animation set yet.");
  }
}
