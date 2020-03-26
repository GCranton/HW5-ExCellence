package cs3500.animator.view;

import java.io.IOException;
import java.io.Writer;
import cs3500.animator.model.IAnimator;

public class TextAnimatorView implements IAnimatorView {

  private IAnimator model;
  private Writer output;

  @Override
  public void setAnimation(IAnimator model) {
    this.model = model;
  }

  @Override
  public void render() {
    try {
      output.append(model.description());
      output.flush();
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public void setOutput(Writer output) {
    this.output = output;
  }

  @Override
  public void setTime(int msPerTick) {
    // Stub: no need for timing in the textual representation
  }
}
