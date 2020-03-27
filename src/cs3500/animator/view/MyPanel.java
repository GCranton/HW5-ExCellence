package cs3500.animator.view;

import cs3500.animator.model.Animator;
import cs3500.animator.shapes.IShape;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;


/**
 * Specialized JPanel class used to animate Animators in VideoAnimatorView.
 * currTick keeps track of the tick that is being animated.
 */
@SuppressWarnings("serial")
public class MyPanel extends JPanel {
  Animator model;
  int currTick = 0;

  /**
   * Constructor for MyPanel, takes in a model so it can run paintComponent.
   * @param model is the Animator this panel animates
   */
  public MyPanel(Animator model) {
    this.model = model;
  }

  /**
   * This code checks to see if a shape exists at the current tick.
   * @param g is the Graphics that needs to be there
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (IShape shape : model.getShapes()) {
      int firstTickOfShape = model.getInstructions(shape).get(0).getDescription()[0];
      if (currTick >= firstTickOfShape) {
        int[] currDescription = model.getDescriptionAt(shape, currTick);
        g.setColor(new Color(currDescription[5], currDescription[6], currDescription[7]));
        if (shape.getType().equals("Rectangle")) {
          g.fillRect(currDescription[1], currDescription[2],
                  currDescription[3], currDescription[4]);
        }
        else if (shape.getType().equals("Ellipse")) {
          g.fillOval(currDescription[1] - model.getRight(),
                  currDescription[2] - model.getTop(),
                  currDescription[3], currDescription[4]);
        }
      }

    }
  }




}
