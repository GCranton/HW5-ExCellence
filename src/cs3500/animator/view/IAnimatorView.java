package cs3500.animator.view;

import java.io.Writer;
import cs3500.animator.model.IAnimator;

/**
 * The view for an animation.
 */
public interface IAnimatorView {
  /**
   * Sets which animation the view will be displaying.
   * 
   * @param model the model to display.
   */
  public void setAnimation(IAnimator model);

  /**
   * Sets the amount of milliseconds each tick takes.
   * 
   * @param msPerTick the time
   */
  public void setTime(int msPerTick);

  /**
   * Sets the text output of the Animator.
   * 
   * @param output the Writer to output to
   */
  public void setOutput(Writer output);

  /**
   * Renders the whole animation into finished format.
   */
  public void render();
}
