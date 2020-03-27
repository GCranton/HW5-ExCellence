import cs3500.animator.model.Animator;
import cs3500.animator.shapes.IShape;
import cs3500.animator.view.AnimatorBuilderImp;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for AnimatorBuilderImp
 */
public class AnimatorBuilderImpTests {

  @Test
  public void testBuild() {
    AnimatorBuilderImp i = new AnimatorBuilderImp();
    assertEquals(i.build().getShapes().size(), 0);
    i.declareShape("Block", "rectangle");
    assertEquals(i.build().getShapes().size(), 1);
    IShape block = i.build().getShapes().get(0);
    i.addKeyframe("Block", 0, 100, 100, 50, 50, 150, 150, 150);
    assertEquals(i.build().getDescriptionAt(block, 0)[0], 0);
  }

  @Test
  public void testSetBounds() {
    AnimatorBuilderImp i = new AnimatorBuilderImp();
    i.setBounds(1, 2, 3, 4);
    assertEquals(i.build().getRight(), 1);
    assertEquals(i.build().getTop(), 2);
    assertEquals(i.build().getWidth(), 3);
    assertEquals(i.build().getHeight(), 4);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIllegalSetBounds() {
    AnimatorBuilderImp i = new AnimatorBuilderImp();
    i.setBounds(1, 2, -3, -4);
  }

  @Test
  public void testDeclareShape() {
    AnimatorBuilderImp i = new AnimatorBuilderImp();
    i.declareShape("shape", "rectangle");
    assertEquals(i.build().getShapes().size(), 1);
    i.declareShape("shape2", "ellipse");
    assertEquals(i.build().getShapes().size(), 2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testDeclareIllegalShape() {
    AnimatorBuilderImp i = new AnimatorBuilderImp();
    i.declareShape("triangles are yucky", "triangle");
  }

  @Test
  public void testAddMotion() {
    AnimatorBuilderImp i = new AnimatorBuilderImp();
    i.declareShape("rect", "rectangle");
    i.addMotion("rect", 1, 2, 3, 4, 5, 6, 7, 8, 9,
            10, 11, 12, 13, 14, 15, 16);
    IShape rect = i.build().getShapes().get(0);
    assertEquals(i.build().getInstructions(rect).size(), 2);
    assertEquals(i.build().getInstructions(rect).get(0).getDescription()[0], 1);
    assertEquals(i.build().getInstructions(rect).get(1).getDescription()[0], 9);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddIllegalMotion() {
    AnimatorBuilderImp i = new AnimatorBuilderImp();
    i.declareShape("rect", "rectangle");
    i.addMotion("rect", -1, 2, 3, 4, 5, 6, 67, 8, 9,
            10, 11, 12, 12, 13, 14, 15);
  }

  @Test
  public void testAddOverlappingMotion() {
    AnimatorBuilderImp i = new AnimatorBuilderImp();
    i.declareShape("rect", "rectangle");
    IShape rect = i.build().getShapes().get(0);
    i.addKeyframe("rect", 1, 2, 3, 4, 5, 6, 7, 8);
    i.addMotion("rect", 1, 2, 3, 4, 5, 6, 7, 8,
            1, 2, 3, 4, 5, 6, 7, 8);
    assertEquals(i.build().getInstructions(rect).size(), 1);
    i.addMotion("rect", 2, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 2, 2, 2, 2, 2);
    assertEquals(i.build().getInstructions(rect).size(), 2);
  }

  @Test
  public void testAddKeyframe() {
    AnimatorBuilderImp i = new AnimatorBuilderImp();
    i.declareShape("rect", "rectangle");
    IShape rect = i.build().getShapes().get(0);
    i.addKeyframe("rect", 1, 2, 3, 4, 5, 6, 7, 8);
    assertEquals(i.build().getInstructions(rect).size(), 1);
    i.addKeyframe("rect", 2, 3, 4, 5, 6, 7, 8, 9);
    assertEquals(i.build().getInstructions(rect).size(), 2);
  }

  @Test
  public void testAddIllegalKeyframe() {
    AnimatorBuilderImp i = new AnimatorBuilderImp();
    i.declareShape("rect", "rectangle");
    IShape rect = i.build().getShapes().get(0);
    i.addKeyframe("rect", 1, 2, 3, 4, 5, 6, 7, 8);
    i.addKeyframe("rect", 1, 2, 3, 4, 5, 6, 7, 8);
    assertEquals(i.build().getInstructions(rect).size(), 1);
  }
}
