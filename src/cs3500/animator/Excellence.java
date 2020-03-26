package cs3500.animator;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import cs3500.animator.model.IAnimator;
import cs3500.animator.view.AnimationReader;
import cs3500.animator.view.AnimatorBuilderImp;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.SVGAnimatorView;
import cs3500.animator.view.TextAnimatorView;
import cs3500.animator.view.VideoAnimatorView;

public class Excellence {

  public static void main(String[] args) {
    IAnimator model = null;
    IAnimatorView view = null;
    BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
    int speed = 100;

    for (int i = 0; i < args.length; i += 2) {
      String command = args[i];
      String second = args[i + 1];

      switch (command) {
        case "-in":
          FileReader readable;
          try {
            readable = new FileReader(second);
          } catch (FileNotFoundException e1) {
            throw new IllegalArgumentException("Invalid infile: " + second);
          }
          AnimatorBuilderImp builder = new AnimatorBuilderImp();
          model = AnimationReader.parseFile(readable, builder);
          break;
        case "-view":
          switch (second) {
            case "svg":
              view = new SVGAnimatorView();
              break;
            case "text":
              view = new TextAnimatorView();
              break;
            case "visual":
              view = new VideoAnimatorView();
              break;
            default:
              throw new IllegalArgumentException("Invalid view type");
          }
          break;
        case "-out":
          FileWriter fw;
          try {
            fw = new FileWriter(second);
          } catch (IOException e) {
            throw new IllegalArgumentException("Invalid outfile: " + second);
          }
          output = new BufferedWriter(fw);
          break;
        case "-speed":
          speed = Integer.parseInt(second);
          break;
        default:
          throw new IllegalArgumentException("Invalid command: " + command);
      }
    }
    if (model == null) {
      throw new IllegalStateException("Must provide an input file");
    }
    if (view == null) {
      throw new IllegalStateException("Must specify a view");
    }
    view.setAnimation(model);
    view.setTime(speed);
    view.setOutput(output);
    view.render();
  }

}
