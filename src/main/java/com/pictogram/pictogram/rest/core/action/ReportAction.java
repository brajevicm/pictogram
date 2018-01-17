package com.pictogram.pictogram.rest.core.action;

import com.pictogram.pictogram.rest.core.Action;
import com.pictogram.pictogram.rest.model.Comment;
import com.pictogram.pictogram.rest.model.Post;
import com.pictogram.pictogram.rest.model.Report;
import com.pictogram.pictogram.rest.model.User;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
public class ReportAction implements Action {
  private Report report;
  private User user;

  public ReportAction(Report report, User user) {
    this.report = report;
    this.user = user;
  }

  public void setReport(Report report) {
    this.report = report;
  }

  @Override
  public void execute() {
    report.setUser(user);
    Object obj = report.getContent();

//    if (obj instanceof Post) {
//      user.reportPost((Post) obj);
//    } else if (obj instanceof Comment) {
//      user.reportComment((Comment) obj);
//    }
  }
}
