package cs3500.animator.view;

import cs3500.animator.model.Animator;
import cs3500.animator.shapes.Ellipse;
import cs3500.animator.shapes.IShape;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
  Animator model;
  int currTick = 0;

  public MyPanel(Animator model) {
    this.model = model;
  }

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
          g.fillOval(currDescription[1], currDescription[2],
                  currDescription[3], currDescription[4]);
        }
      }

    }
  }




}
