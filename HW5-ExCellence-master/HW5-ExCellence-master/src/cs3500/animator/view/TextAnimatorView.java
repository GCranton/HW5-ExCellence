package cs3500.animator.view;

import cs3500.animator.model.IAnimator;

public class TextAnimatorView implements IAnimatorView{

  private IAnimator model;

  @Override
  public void setAnimation(IAnimator model) {
    this.model = model;
  }

  @Override
  public void render() {
    System.out.println(model.description());
  }
}
