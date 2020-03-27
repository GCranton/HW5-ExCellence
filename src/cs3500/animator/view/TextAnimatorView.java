package cs3500.animator.view;

import java.io.IOException;
import java.io.Writer;
import cs3500.animator.model.IAnimator;

/**
 * TextAnimatorView prints out the description of the Animator.
 */
public class TextAnimatorView implements IAnimatorView {

  private IAnimator model;
  private Writer output;

  @Override
  public void setAnimation(IAnimator model) {
    this.model = model;
  }

  @Override
  public void render() {
    if (model == null) {
      throw new IllegalStateException("No model set");
    } else {
      if (output == null) {
        throw new IllegalStateException("No output set");
      } else {
        try {
          output.append(model.description());
          output.flush();
        } catch (IOException e) {
          throw new IllegalStateException("I/O failure");
        }
      }
    }
  }

  @Override
  public void setTime(int msPerTick) {
    // Stub: no need for time in text view
  }

  @Override
  public void setOutput(Writer output) {
    this.output = output;
  }
}
