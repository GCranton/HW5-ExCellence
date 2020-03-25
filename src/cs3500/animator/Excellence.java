package cs3500.animator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import cs3500.animator.model.IAnimator;
import cs3500.animator.view.IAnimatorView;

public class Excellence {

  public static void main(String[] args) {
    IAnimator model = null;
    IAnimatorView view = null;
    BufferedWriter file = null;

    for (int i = 0; i < args.length; i += 2) {
      String command = args[i];
      String second = args[i + 1];

      switch (command) {
        case "-in":
          // import file
          break;
        case "-view":
          switch (second) {
            case "svg":
              // create thing
              break;
            case "text":
              // create thing
              break;
            case "visual":
              // create thing
              break;
            default:
              throw new IllegalArgumentException("Invalid view type");
          }
          break;
        case "-out":
          FileWriter fw;
          try {
            fw = new FileWriter("testAnim.svg");
          } catch (IOException e) {
            throw new IllegalArgumentException("Writer fail");
          }
          file = new BufferedWriter(fw);
          break;
        case "-speed":
          // get int
          break;
        default:
          // throw error
      }
    }
  }

}
