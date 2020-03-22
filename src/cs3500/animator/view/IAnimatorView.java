package cs3500.animator.view;

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
   * Renders the whole animation into finished format.
   */
  public void render();
}
