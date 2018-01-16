package com.pictogram.pictogram.rest.projection;

import com.pictogram.pictogram.rest.model.Comment;
import com.pictogram.pictogram.rest.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

/**
 * Project: pictogram
 * Date: 16-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Projection(name = "userCommentsProjection", types = User.class)
public interface UserCommentsProjection {
  String getUsername();

  @Value("#{target.comments}")
  List<Comment> getComments();
}
