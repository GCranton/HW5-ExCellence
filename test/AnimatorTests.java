import static org.junit.Assert.assertEquals;
import org.junit.Test;
import cs3500.animator.model.Animator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.shapes.Ellipse;
import cs3500.animator.shapes.IShape;
import cs3500.animator.shapes.Rectangle;

/**
 * Test class for Animator.
 */
public class AnimatorTests {
  // TODO: Convert from Instruction tests to animator tests
  @Test
  public void testGetDescriptionAtOneInstruction() {
    IAnimator anim = new Animator();
    IShape shape = new Rectangle("R");
    Instruction test = new Instruction(new int[] {0, 200, 200, 100, 100, 255, 0, 0});
    anim.addShape(shape);
    anim.addInstruction(shape, test);

    int[] expected = new int[] {0, 200, 200, 100, 100, 255, 0, 0};
    for (int i = 0; i < 8; i++) {
      assertEquals(expected[i], anim.getDescriptionAt(shape, 0)[i]);
    }
  }

  @Test
  public void testGetDescriptionAtLateInstruction() {
    IAnimator anim = new Animator();
    IShape shape = new Rectangle("R");
    Instruction first = new Instruction(new int[] {0, 200, 200, 100, 100, 255, 0, 0});
    Instruction second = new Instruction(new int[] {5, 250, 250, 200, 200, 200, 20, 20});

    anim.addShape(shape);
    anim.addInstruction(shape, first);
    anim.addInstruction(shape, second);

    int[] expected = new int[] {5, 250, 250, 200, 200, 200, 20, 20};
    for (int i = 0; i < 8; i++) {
      assertEquals(expected[i], anim.getDescriptionAt(shape, 0)[i]);
    }
  }

  @Test
  public void testGetDescriptionBetweenInstructions() {
    IAnimator anim = new Animator();
    IShape shape = new Rectangle("R");
    Instruction first = new Instruction(new int[] {0, 200, 200, 100, 100, 255, 0, 0});
    Instruction second = new Instruction(new int[] {5, 250, 250, 200, 200, 200, 20, 20});

    anim.addShape(shape);
    anim.addInstruction(shape, first);
    anim.addInstruction(shape, second);

    int[] expected = new int[] {3, 230, 230, 160, 160, 222, 12, 12};
    for (int i = 0; i < 8; i++) {
      assertEquals(expected[i], anim.getDescriptionAt(shape, 0)[i]);
    }
  }

  @Test
  public void testGetDescriptionMultipleShapes() {
    IAnimator anim = new Animator();
    IShape rect = new Rectangle("R");
    IShape ell = new Ellipse("E");
    Instruction rFirst = new Instruction(new int[] {0, 200, 200, 100, 100, 255, 0, 0});
    Instruction rSecond = new Instruction(new int[] {5, 250, 250, 200, 200, 200, 20, 20});
    Instruction eFirst = new Instruction(new int[] {0, 10, 20, 30, 40, 100, 100, 0});
    Instruction eSecond = new Instruction(new int[] {10, 100, 0, 60, 20, 0, 200, 50});

    anim.addShape(rect);
    anim.addInstruction(rect, rFirst);
    anim.addInstruction(rect, rSecond);
    anim.addShape(ell);
    anim.addInstruction(ell, eFirst);
    anim.addInstruction(ell, eSecond);

    int[] expected1 = new int[] {3, 230, 230, 160, 160, 222, 12, 12};
    int[] expected2 = new int[] {7, 73, 6, 51, 26, 30, 170, 35};
    for (int i = 0; i < 8; i++) {
      assertEquals(expected1[i], anim.getDescriptionAt(rect, 0)[i]);
      assertEquals(expected2[i], anim.getDescriptionAt(ell, 0)[i]);
    }
  }

  @Test
  public void testDescriptionTwoInstructions() {
    IAnimator anim = new Animator();
    IShape shape = new Rectangle("R");
    Instruction first = new Instruction(new int[] {0, 200, 200, 100, 100, 255, 0, 0});
    Instruction second = new Instruction(new int[] {5, 250, 250, 200, 200, 200, 20, 20});

    anim.addShape(shape);
    anim.addInstruction(shape, first);
    anim.addInstruction(shape, second);
    assertEquals(
        "shape R Rectangle\n"
            + "motion R 0  200 200 100 100 255 0  0     5  250 250 200 200 200 20 20",
        anim.description());
  }
}
