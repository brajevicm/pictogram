package com.pictogram.pictogram.rest.core;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public class ActionInvoker {
  private Action action;

  public ActionInvoker(Action action) {
    this.action = action;
  }

  public void setAction(Action action) {
    this.action = action;
  }

  public void invoke() {
    action.execute();
  }
}
