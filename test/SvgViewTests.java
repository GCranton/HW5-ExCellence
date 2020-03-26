import static org.junit.Assert.assertEquals;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import org.junit.Test;
import cs3500.animator.model.Animator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.shapes.Ellipse;
import cs3500.animator.shapes.IShape;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.SVGAnimatorView;

public class SvgViewTests {
  @Test
  public void testRenderRect() {
    IAnimator model = new Animator();
    StringWriter fw = new StringWriter();
    BufferedWriter writer = new BufferedWriter(fw);
    IAnimatorView view = new SVGAnimatorView();
    model.setHeight(500);
    model.setWidth(500);
    view.setTime(100);
    view.setOutput(writer);
    IShape testRect = new Rectangle("R");
    Instruction first = new Instruction(new int[] {0, 10, 10, 20, 10, 0, 0, 100});
    Instruction last = new Instruction(new int[] {100, 50, 50, 20, 10, 100, 0, 50});
    model.addShape(testRect);
    model.addInstruction(testRect, first);
    model.addInstruction(testRect, last);
    view.setAnimation(model);
    view.render();
    String expected =
        "<svg width=\"500\" height=\"500\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" overflow=\"scroll\">"
            + "\n<rect id=\"R\" x=\"10\" y=\"10\" width=\"20\" height=\"10\" fill=\"rgb(0,0,100)\" visibility=\"visible\" >"
            + "\n\t<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"10000.0ms\" attributeName=\"x\" from=\"10\" to=\"50\" fill=\"freeze\" />"
            + "\n\t<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"10000.0ms\" attributeName=\"y\" from=\"10\" to=\"50\" fill=\"freeze\" />"
            + "\n\t<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"10000.0ms\" attributeName=\"fill\" from=\"rgb(0,0,100)\" to=\"rgb(100,0,50)\" fill=\"freeze\" />"
            + "\n</rect>" + "\n</svg>";
    assertEquals(expected, fw.toString());
  }

  @Test
  public void testRenderEllipse() {
    IAnimator model = new Animator();
    StringWriter fw = new StringWriter();
    BufferedWriter writer = new BufferedWriter(fw);
    IAnimatorView view = new SVGAnimatorView();
    model.setHeight(500);
    model.setWidth(500);
    view.setTime(100);
    view.setOutput(writer);
    IShape testEll = new Ellipse("E");
    Instruction first = new Instruction(new int[] {0, 10, 10, 20, 10, 0, 0, 100});
    Instruction last = new Instruction(new int[] {100, 50, 50, 20, 10, 100, 0, 50});
    model.addShape(testEll);
    model.addInstruction(testEll, first);
    model.addInstruction(testEll, last);
    view.setAnimation(model);
    view.render();
    String expected =
        "<svg width=\"500\" height=\"500\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" overflow=\"scroll\">"
            + "\n<ellipse id=\"E\" cx=\"10\" cy=\"10\" rx=\"20\" ry=\"10\" fill=\"rgb(0,0,100)\" visibility=\"visible\" >"
            + "\n\t<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"10000.0ms\" attributeName=\"cx\" from=\"10\" to=\"50\" fill=\"freeze\" />"
            + "\n\t<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"10000.0ms\" attributeName=\"cy\" from=\"10\" to=\"50\" fill=\"freeze\" />"
            + "\n\t<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"10000.0ms\" attributeName=\"fill\" from=\"rgb(0,0,100)\" to=\"rgb(100,0,50)\" fill=\"freeze\" />"
            + "\n</ellipse>" + "\n</svg>";
    assertEquals(expected, fw.toString());
  }
}
