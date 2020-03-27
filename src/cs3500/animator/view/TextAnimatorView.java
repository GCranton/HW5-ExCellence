package cs3500.animator.view;

import cs3500.animator.model.IAnimator;

/**
 * TextAnimatorView prints out the description of the Animator.
 */
public class TextAnimatorView implements IAnimatorView {

  private IAnimator model;

  @Override
  public void setAnimation(IAnimator model) {
    this.model = model;
  }

  @Override
  public void render() {
    if (model == null) {
      System.out.println("No animation set yet");
    } else {
      System.out.println(model.description());
    }
  }
}
